<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>科目別成績一覧</h2>
<table border="1">
    <thead>
        <tr>
            <th>入学年度</th>
            <th>クラス番号</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>回数</th>
            <th>得点</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="record" items="${testScores}">
            <tr>
                <td>${record.entYear}</td>
                <td>${record.classNum}</td>
                <td>${record.studentNo}</td>
                <td>${record.name}</td>
                <td>${record.no}</td>
                <td>${record.point}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>