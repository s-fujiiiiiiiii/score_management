<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目情報削除</title>
    <style>
        .delete-message {

            font-weight: bold;
            font-size: 18px;
            background-color: #E8F5E9; /* 🔹 背景色を薄い緑で統一 */
            padding: 10px;
            border-radius: 5px;
            display: inline-block;
        }

        a {
            display: block;
            margin-top: 20px;
            font-size: 16px;
            color: #007BFF;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>科目情報削除</h2>

    <!-- 🔹 削除完了メッセージを緑色で強調 -->
    <p class="delete-message">削除が完了しました</p>

    <a href="../SubjectListAction">科目一覧</a>
</body>
</html>

<%@include file="../../footer.jsp" %>