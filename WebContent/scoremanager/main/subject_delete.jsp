<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目情報削除</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<p>「"${subject.name}"」を削除してもよろしいですか？</p>
<form action="SubjectDeleteAction" method="post">
    <input type="hidden" name="cd" value="${cd}">
    <button type="submit">削除</button>
    <a href="subject_list.jsp">キャンセル</a>
</form>
</body>
</html>

<%@include file="../../footer.jsp" %>