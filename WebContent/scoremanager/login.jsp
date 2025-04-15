<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
String enteredId = (String) request.getAttribute("enteredId");
if (errorMessage != null) {
%>
    <p style="color:red;"><%= errorMessage %></p>
<%
}
%>

    <h2>ログイン画面</h2>
    <form action="LoginAction" method="post">
        <!-- ログインID -->
        <label for="id">ログインID:</label>
        <input type="text" id="id" name="id" value ="<%= enteredId != null ? enteredId : "" %>"maxlength="10" placeholder="半角でご入力ください" required>

        <!-- パスワード -->
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" maxlength="30" placeholder="30文字以内の半角英数字でご入力ください" required>

        <!-- パスワード表示/非表示 -->
		<label> <input type="checkbox" id="chk_d_ps"
			onclick="togglePassword()"> パスワードを表示
		</label>

		<!-- ログインボタン -->
        <button type="submit" name="login" value="ログイン">ログイン</button>
    </form>

    <script>
        // パスワードの表示/非表示を切り替える
        function togglePassword() {
            const passwordField = document.getElementById('password');
            const checkbox = document.getElementById('chk_d_ps');
            passwordField.type = checkbox.checked ? 'text' : 'password';
        }
    </script>
</body>
</html>

<%@include file="../footer.jsp" %>