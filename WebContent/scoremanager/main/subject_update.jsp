<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<div class="main-container">
    <!-- サイドメニュー -->
    <div class="menu-container">
        <ul class="menu-list">
            <li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
            <li><a href="<c:url value='/student_list'/>">学生管理</a></li>
            <li>成績管理</li>
            <li><a href="<c:url value=''/>">成績登録</a></li>
            <li><a href="<c:url value='/main/TestListAction'/>">成績参照</a></li>
            <li><a href="<c:url value='/main/SubjectListAction'/>">科目管理</a></li>
        </ul>
    </div>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目情報変更</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }

        .update-button {
            background-color: #007BFF;
            color: white;
            padding: 12px 24px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
            display: inline-block;
        }

        .update-button:hover {
            background-color: #0056b3;
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
<!-- コンテンツエリア -->
    <div class="content-container">
<body>
    <h2>科目情報変更</h2>

    <form action="/score_management/main/SubjectUpdateAction" method="post">
        <label for="cd">科目コード</label>
        <p>${subject.cd}</p>

        <label for="name">科目名</label>
        <input type="text" id="name" name="name" value="${subject.name}" required>
        <br>


        <button type="submit" class="update-button">変更</button>
    </form>

    <a href="/score_management/main/SubjectListAction">戻る</a>
    </div>
    </div>
</body>
</html>

<%@include file="../../footer.jsp" %>