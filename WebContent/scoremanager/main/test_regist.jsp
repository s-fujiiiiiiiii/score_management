<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

<h2>成績登録</h2>

<form action="/scoremanager/main/TestRegistAction" method="post">


    入学年度:
    <select name="entYear">
        <c:forEach var="year" items="${entYearList}">
            <option value="${year}">${year}</option>
        </c:forEach>
    </select><br>

    クラス番号:
    <select name="classNum">
        <c:forEach var="classNum" items="${classList}">
            <option value="${classNum}">${classNum}</option>
        </c:forEach>
    </select><br>

    科目コード:
    <select name="subjectCd">
        <c:forEach var="subject" items="${subjectList}">
            <option value="${subject.cd}">${subject.name}</option>
        </c:forEach>
    </select><br>

    回数 (登録回):
    <select name="examRound">
        <option value="1">1回目</option>
        <option value="2">2回目</option>
        <option value="3">3回目</option>
    </select><br>

    成績入力: <input type="number" name="point" min="0" max="100"><br>

    <button type="submit">登録</button>

    <c:if test="${not empty errorMessage}">
        <p style="color: red">${errorMessage}</p>
    </c:if>
</form>

<%@ include file="/footer.jsp" %>