<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目情報変更</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<form action="SubjectUpdateAction" method="post">
    <label for="schoolCd">学校コード:</label>
    <input type="text" id="schoolCd" name="schoolCd" value="${subject.schoolCd}" readonly>
    <br>
    <label for="cd">科目コード:</label>
    <input type="text" id="cd" name="cd" value="${subject.cd}" readonly>
    <br>
    <label for="name">科目名:</label>
    <input type="text" id="name" name="name" value="${subject.name}" required>
    <br>
    <button type="submit">保存</button>
</form>
</body>
</html>

<%@include file="../../footer.jsp" %>