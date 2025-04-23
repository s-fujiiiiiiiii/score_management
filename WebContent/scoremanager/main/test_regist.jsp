<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

<h2>成績登録</h2>

<form action="<c:url value='/scoremanager/main/TestRegisterAction'/>" method="post">
    <label>学生番号:</label>
    <input type="text" name="studentNo" required>

    <label>科目コード:</label>
    <input type="text" name="subjectCd" required>

    <label>得点:</label>
    <input type="number" name="point" min="0" max="100" required>

    <button type="submit">登録</button>
</form>

<%@ include file="/footer.jsp" %>