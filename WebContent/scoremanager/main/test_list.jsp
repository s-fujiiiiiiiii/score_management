<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../../header.jsp"%>
<!DOCTYPE html>
<html lang="ja">
<div class="main-container">
<!-- 🔹 サイドメニュー -->
<div class="menu-container">
<ul class="menu-list">
<li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
<li><a href="<c:url value='/student_list'/>">学生管理</a></li>
<li>成績管理</li>
<li><a href="<c:url value='/main/TestRegistAction'/>">成績登録</a></li>
<li><a href="<c:url value='/main/TestListAction'/>">成績参照</a></li>
<li><a href="<c:url value='/SubjectListAction'/>">科目管理</a></li>
</ul>
</div>

<head>
<title>成績一覧</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

.no-score {
	color: #888;
}

.small-text {
	font-size: 12px;
	color: #888;
	margin-top: 10px;
}
</style>
</head>

<!-- 🔹 コンテンツエリア -->
<div class="content-container">
<body>


<c:choose>
<c:when test="${not empty subjectName and not empty testScores}">
<h2>成績一覧(科目)</h2>
</c:when>
<c:when test="${not empty studentName and not empty testScores}">
<h2>成績一覧(学生)</h2>
</c:when>
</c:choose>

<!-- 🔹 科目別検索フォーム -->
<h3>科目情報</h3>
<form action="/score_management/main/TestListAction" method="get">
	入学年度: <select name="entYear">
<option value="">---</option>
<c:forEach var="year" items="${entYearList}">
<option value="${year}" ${year == param.entYear ? 'selected' : ''}>${year}</option>
</c:forEach>
</select> クラス: <select name="classNum">
<option value="">---</option>
<c:forEach var="classNum" items="${classList}">
<option value="${classNum}"
							${classNum == param.classNum ? 'selected' : ''}>${classNum}</option>
</c:forEach>
</select> 科目： <select name="subjectCd">
<option value="">---</option>
<c:forEach var="subject" items="${subjectList}">
<option value="${subject.cd}">${subject.name}</option>
</c:forEach>
</select>

	<button type="submit">検索</button>
</form>

<!-- 🔹 入力チェック：検索後のみ表示 -->
<c:if
	test="${not empty param.entYear or not empty param.classNum or not empty param.subjectCd}">
<c:if
	test="${empty testScores and (empty param.entYear or empty param.classNum or empty param.subjectCd)}">
<p class="error-message">入学年度・クラス・科目を選択してください。</p>
</c:if>
</c:if>

<!-- 🔹 学生別検索フォーム -->
<h3>学生情報</h3>
<form action="/score_management/main/TestListAction" method="get">
	学生番号: <input type="text" name="studentNo" value="${param.studentNo}">
	<input type="submit" name="submit" value="検索">

	<!-- 🔹 未入力のチェック：検索が実行された場合のみエラーメッセージを表示 -->
<c:if test="${not empty param.submit and empty param.studentNo}">
<p class="error-message">このフィールドを入力してください。</p>
</c:if>
</form>

<p class="small-text">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください。</p>

<!-- 🔹 検索結果の表示 -->
<c:choose>
<c:when test="${not empty subjectName and not empty testScores}">
<h3>科目: ${subjectName}</h3>
<table>
<thead>
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<c:forEach var="i" begin="1" end="${maxNo}">
<th>${i}回</th>
</c:forEach>
</tr>
</thead>
<tbody>
<c:forEach var="record" items="${testScores}">
<tr>
<td>${record.entYear}</td>
<td>${record.classNum}</td>
<td>${record.studentNo}</td>
<td>${record.name}</td>

	<c:forEach var="i" begin="1" end="${maxNo}">
<td class="${record.points[i] == null ? 'no-score' : ''}">
	${record.points[i] != null ? record.points[i] : '-'}</td>
	</c:forEach>
</tr>
</c:forEach>
</tbody>
</table>
</c:when>

<c:when test="${not empty studentName and not empty testScores}">
    <h3>氏名: ${studentName} (学籍番号: ${param.studentNo})</h3>
    <table>
        <thead>
            <tr>
                <th>科目名</th>
                <th>科目コード</th>
                <th>回数</th>
                <th>点数</th>
                <th>操作</th> <!-- 🔸操作列追加 -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${testScores}">
                <tr>
                    <td>${record.subjectName}</td>
                    <td>${record.subjectCd}</td>
                    <td>${record.no}</td>
                    <td>${record.point}</td>
                    <td>
                        <!-- 🔹変更ボタン -->
                  <form action="${pageContext.request.contextPath}/main/TestUpdateAction" method="get" style="display:inline;">

                            <input type="hidden" name="entYear" value="${record.entYear}">
                            <input type="hidden" name="classNum" value="${record.classNum}">
                            <input type="hidden" name="subjectCd" value="${record.subjectCd}">
                            <input type="hidden" name="studentNo" value="${record.studentNo}">
                            <input type="hidden" name="No" value="${record.no}">
                            <input type="submit" value="変更">
                        </form>

                        <!-- 🔹削除ボタン -->

                       <form action="${pageContext.request.contextPath}/main/TestDeleteAction" method="post" style="display:inline;" onsubmit="return confirm('本当に削除しますか？');">

                            <input type="hidden" name="entYear" value="${record.entYear}">
                            <input type="hidden" name="classNum" value="${record.classNum}">
                            <input type="hidden" name="subjectCd" value="${record.subjectCd}">
                            <input type="hidden" name="studentNo" value="${record.studentNo}">
                            <input type="hidden" name="No" value="${record.no}">
                            <input type="submit" value="削除">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:when>


<c:otherwise>
<c:if
	test="${not empty param.entYear and not empty param.classNum and not empty param.subjectCd and empty testScores}">
<p class="error-message">学生情報が存在しませんでした。</p>
</c:if>

	<c:if test="${not empty param.studentNo and empty testScores}">
<p class="error-message">成績情報が存在しませんでした。</p>
</c:if>
</c:otherwise>
</c:choose>
</body>
</div>
</div>
</html>
<%@ include file="../../footer.jsp"%>
