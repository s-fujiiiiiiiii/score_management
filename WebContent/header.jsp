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

    <%
    //  現在のページのURLを取得
    String currentPage = request.getRequestURI();

    //  logout.jsp ではIDとログアウトボタンを表示しない
    if (!currentPage.contains("logout.jsp")) {
        bean.Teacher teacher = (bean.Teacher) session.getAttribute("teacher");

        if (teacher != null) {
    %>
            <div class="user-info">
                <p> <%= teacher.getId() %>様</p>
                <a href="/score_management/scoremanager/main/logout.jsp" class="logout-btn">ログアウト</a>
            </div>
    <%
        }
    }
    %>
</div>