<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../../header.jsp"%>

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
<title>科目情報登録</title>
<style>
label {
	display: block;
	margin-top: 10px;
}

.create-button {
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

        .create-button:hover {
            background-color: #0056b3;
        }
</style>
	</head>
	<!-- コンテンツエリア -->
	<div class="content-container">
		<h2>科目情報登録</h2>
		<body>
			<form action="/score_management/main/SubjectCreateAction"
				method="post">
				<!-- <label for="schoolCd">学校コード:</label>
    <input type="text" id="schoolCd" name="schoolCd" maxlength="3" required>
    <br>-->
				<label for="cd">科目コード:</label> <input type="text" id="cd" name="cd"
					maxlength="3" placeholder="科目コードを入力してください" required> <br>
				<label for="name">科目名:</label> <input type="text" id="name"
					name="name" maxlength="20" placeholder="科目名を入力してください" required>
				<br>
				<button type="submit" class="create-button">登録</button>
			</form>
			<a href="/score_management/main/SubjectListAction">戻る</a>
		</body>
	</div>
</div>
</html>

<%@include file="../../footer.jsp"%>