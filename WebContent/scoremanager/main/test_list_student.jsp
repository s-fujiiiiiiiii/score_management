<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>生徒別成績一覧</h2>
<table border="1">
    <thead>
        <tr>
            <th>科目名</th>
            <th>科目コード</th>
            <th>回数</th>
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