<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ログイン</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        flex-direction: column;
        background-color: #f9f9f9;
    }

    .login-container {
        max-width: 400px;
        width: 100%;
        padding: 20px;
        background: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        text-align: center;
    }

    .form-group {
    position: relative;
    margin-top: 30px;
}

.form-group input {
    width: 100%;
    height: 50px;
    padding: 20px 12px 10px 12px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

.form-group input::placeholder {
    font-size: 13px;
    color: #888;
}

.inline-label {
    position: absolute;
    top: 6px;
    left: 12px;
    font-size: 10px;
    color: #555;
    background-color: white;
    padding: 0 4px;
    pointer-events: none;
}




    .login-button {
        background-color: #007BFF;
        color: white;
        padding: 12px 24px;
        font-size: 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 20px;
        width: 100%;
    }

    .login-button:hover {
        background-color: #0056b3;
    }

    h2 {
    background-color: #e0e0e0;  /* 薄いグレー */
    padding: 12px;
    border-radius: 4px;
    font-size: 20px;
    margin-bottom: 20px;
}




</style>
</head>
<body>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String enteredId = (String) request.getAttribute("enteredId");
    if (errorMessage != null) {
%>
    <p style="color: red;"><%= errorMessage %></p>
<%
    }
%>

<div class="login-container">
    <h2>ログイン</h2>

    <form action="LoginAction" method="post">
        <div class="form-group">
    <input type="text" id="id" name="id"
           value="<%= enteredId != null ? enteredId : "" %>"
           maxlength="10"
           placeholder="半角でご入力ください"
           required>
    <span class="inline-label">ID</span>
</div>

<div class="form-group">
    <input type="password" id="password" name="password"
           maxlength="30"
           placeholder="30文字以内の半角英数字でご入力ください"
           required>
    <span class="inline-label">パスワード</span>
</div>




        <label><input type="checkbox" id="chk_d_ps" onclick="togglePassword()"> パスワードを表示</label>

        <button type="submit" class="login-button" name="login" value="ログイン">ログイン</button>
    </form>


</div>

<script>
    // 🔹 パスワードの表示/非表示を切り替える
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const checkbox = document.getElementById('chk_d_ps');
        passwordField.type = checkbox.checked ? 'text' : 'password';
    }
</script>

</body>
</html>

<%@ include file="../footer.jsp"%>