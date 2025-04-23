<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../header.jsp" %>

<html>
<head>
    <title>成績一覧</title>
</head>
<body>
    <h2>成績一覧</h2>

    <!-- データが存在しない場合の処理 -->
    <c:if test="${empty testScores}">
        <p>成績データがありません。</p>
    </c:if>

    <!-- データが存在する場合の一覧表示 -->
    <c:if test="${not empty testScores}">
        <table border="1">
            <thead>
                <tr>
                    <th>学生番号</th>
                    <th>科目コード</th>
                    <th>得点</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="testScore" items="${testScores}">
                <p>${testScore.studentNo} - ${testScore.subjectCd} - ${testScore.point}</p>
                    <tr>
                        <td>${testScore.studentNo}</td>
                        <td>${testScore.subjectCd}</td>
                        <td>${testScore.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>

<%@include file="../../footer.jsp" %>