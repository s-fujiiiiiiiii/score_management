<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

<h2>成績一覧</h2>

<table border="1">
    <thead>
        <tr>
            <th>学生番号</th>
            <th>科目</th>
            <th>得点</th>
            <th>クラス</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="testScore" items="${testScores}">
            <tr>
                <td>${testScore.studentNo}</td>
                <td>${testScore.subjectCd}</td>
                <td>${testScore.point}</td>
                <td>${testScore.classNum}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/footer.jsp" %>