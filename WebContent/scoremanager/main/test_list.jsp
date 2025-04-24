<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../header.jsp" %>

<html>
<head>
    <title>成績一覧</title>
</head>
<body>
    <h2>成績一覧(科目)</h2>

 <!-- 🔹 上段：入学年度・クラス・科目で検索 -->
<h2>科目情報</h2>
<form action="/scoremanager/main/TestListAction" method="get">
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
<form action="/scoremanager/main/TestListAction" method="get">
    学生番号:
    <input type="text" name="studentNo">
    <button type="submit">検索</button>
</form>
</body>
</html>

<%@include file="../../footer.jsp" %>