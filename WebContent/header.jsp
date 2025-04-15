<!-- 本ファイルはヘッダエリアに表示する内容のJSPです -->

<!-- TODO: 現在は仮の実装です。必要に応じて修正または書き換えてください。 -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
</head>


<!-- 上部タイトルエリア -->
<div class="header-wrapper">
    <h1>得点管理システム</h1>
     <div class="user-info">
     <!-- ログイン中のIDを表示 -->
     <%
     bean.Teacher teacher = (bean.Teacher)session.getAttribute("teacher");
     if (teacher != null){
     %>
     	<p> <%= teacher.getId() %>様</p>
     <%
     }
     %>
        <a href="../main/logout.jsp" >ログアウト</a>
    </div>
</div>
