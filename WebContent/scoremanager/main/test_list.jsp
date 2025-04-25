<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../../header.jsp"%>

<html>
<head>
    <title>成績一覧</title>
</head>
<body>
    <h2>成績一覧(科目)</h2>

    <!-- 🔹 上段：入学年度・クラス・科目で検索 -->
    <h2>科目情報</h2>
    <form action="/score_management/main/TestListAction" method="get">
        入学年度:
        <select name="entYear">
            <c:forEach var="year" items="${entYearList}">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>

        クラス:
        <select name="classNum">
            <c:forEach var="classNum" items="${classList}">
                <option value="${classNum}">${classNum}</option>
            </c:forEach>
        </select>

        科目:
        <select name="subjectCd">
            <c:forEach var="subject" items="${subjectList}">
                <option value="${subject.cd}">${subject.name}</option>
            </c:forEach>
        </select>

        <button type="submit">検索</button>
    </form>

    <!-- 🔹 下段：学生番号で直接検索 -->
    <h2>学生成績</h2>
    <form action="/score_management/main/TestListAction" method="get">
        学生番号:
        <input type="text" name="studentNo">
        <button type="submit">検索</button>
    </form>

    <!-- 🔹 検索結果の表示 -->
    <h2>検索結果</h2>

    <c:if test="${empty testScores}">
        <p>該当するデータがありません。</p>
    </c:if>

    <!-- 🔹 学生別表示 -->
    <c:if test="${testType eq 'student'}">
        <table border="1">
            <thead>
                <tr>
                    <th>科目名</th>
                    <th>科目コード</th>
                    <th>試験回数</th>
                    <th>得点</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${testScores}">
                    <tr>
                        <td>${record.subjectName}</td>
                        <td>${record.subjectCd}</td>
                        <td>${record.no}</td>
                        <td>${record.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- 🔹 科目別表示 -->
    <c:if test="${testType eq 'subject'}">
        <table border="1">
            <thead>
                <tr>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>クラス</th>
                    <th>入学年度</th>
                    <th>試験回数</th>
                    <th>得点</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${testScores}">
                    <tr>
                        <td>${record.studentNo}</td>
                        <td>${record.name}</td>
                        <td>${record.classNum}</td>
                        <td>${record.entYear}</td>
                        <td>${record.no}</td>
                        <td>${record.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>

<%@include file="../../footer.jsp"%>
