<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目情報登録</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<form action="/score_management/main/SubjectCreateAction" method="post">
    <!-- <label for="schoolCd">学校コード:</label>
    <input type="text" id="schoolCd" name="schoolCd" maxlength="3" required>
    <br>-->
    <label for="cd">科目コード:</label>
    <input type="text" id="cd" name="cd" maxlength="3" placeholder="科目コードを入力してください" required>
    <br>
    <label for="name">科目名:</label>
    <input type="text" id="name" name="name" maxlength="20" placeholder="科目名を入力してください" required>
    <br>
    <button type="submit">登録</button>
</form>
	<a href="/score_management/main/SubjectListAction">戻る</a>
</body>
</html>

<%@include file="../../footer.jsp" %>