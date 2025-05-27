<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/header.jsp"%>

<div class="main-container">

  <!-- サイドメニュー -->
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

  <!-- メインコンテンツ -->
  <div class="content-container">
	<h2>成績管理</h2>

	<!-- 検索フォーム -->
	<form action="<c:url value='/main/TestRegistAction'/>" method="get">
      入学年度：
      <select name="entYear">
			<option value="">-----</option>
			<c:forEach var="year" items="${entYearList}">
				<option value="${year}" ${param.entYear == year ? 'selected' : ''}>${year}</option>
			</c:forEach>
      </select>
      クラス：
      <select name="classNum">
			<option value="">-----</option>
			<c:forEach var="cls" items="${classList}">
				<option value="${cls}" ${param.classNum == cls ? 'selected' : ''}>${cls}</option>
			</c:forEach>
      </select>
      <label for="subject">科目：</label>
<select name="subject" id="subject" required>
    <option value="">-----</option>
    <c:forEach var="subj" items="${subjectList}">
        <option value="${subj.cd}" ${param.subject == subj.cd ? 'selected' : ''}>${subj.name}</option>
    </c:forEach>
</select>
      <select name="times">
			<option value="">-----</option>
			<c:forEach var="i" begin="1" end="2">
				<option value="${i}" ${param.times == i ? 'selected' : ''}>${i}</option>
			</c:forEach>
      </select>
      <input type="submit" value="検索">
	</form>

	<!-- 学生成績入力フォーム -->
	<c:if test="${not empty testScores}">
		<form action="<c:url value='/main/TestRegistExecAction.action'/>"
			method="post">
			<input type="hidden" name="entYear" value="${param.entYear}">
			<input type="hidden" name="classNum" value="${param.classNum}">
			<input type="hidden" name="subject" value="${param.subject}">
			<input type="hidden" name="times" value="${param.times}">

			<table border="1">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数</th>
				</tr>
          </thead>
          <tbody>
            <c:forEach var="student" items="${testScores}">
					<tr>
						<td>${student.entYear}</td>
						<td>${student.classNum}</td>
                <td>
                  ${student.studentNumber}
                  <input type="hidden" name="studentNumber" value="${student.studentNumber}" />
                </td>
						<td>${student.name}</td>
                <td><input type="text" name="score" /></td>
					</tr>
				</c:forEach>
          </tbody>
			</table>

        <input type="submit" value="登録" style="margin-top: 15px;">
		</form>
	</c:if>
</div>
</div>

<%@ include file="/footer.jsp"%>