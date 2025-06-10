<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/header.jsp"%>

<html>
<head>
    <title>点数編集</title>
</head>

<div class="main-container">
    <!-- サイドメニュー -->
    <div class="menu-container">
        <ul class="menu-list">
            <li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
            <li><a href="<c:url value='/student_list'/>">学生管理</a></li>
            <li>成績管理</li>
            <li><a href="<c:url value='/main/TestRegistAction'/>">成績登録</a></li>
            <li><a href="<c:url value='/main/TestListAction'/>">成績参照</a></li>
            <li><a href="<c:url value='/SubjectListAction'/>">科目管理</a></li>
        </ul>
    </div>

<body>

<!-- メインコンテンツ -->
    <div class="content-container">
<!-- ✅ エラーメッセージを追加 -->
<c:if test="${not empty errorMessage}">
    <p class="error-message" style="color: red; font-weight: bold;">${errorMessage}</p>
</c:if>

<c:if test="${empty test}">
    <p class="error-message">テスト情報が取得できませんでした。</p>
</c:if>

<h2>点数編集</h2>
<form action="TestUpdateExecuteAction" method="post">
    <input type="hidden" name="classNum" value="${test.classNum}" />
    <input type="hidden" name="subjectCd" value="${test.subjectCd}" />
    <input type="hidden" name="studentNo" value="${test.studentNo}" />
    <input type="hidden" name="No" value="${test.no}" />

    氏名：${test.studentName}<br/>
    点数：<input type="number" name="point" value="${test.point}" required /><br/>

    <input type="submit" value="更新" />
</form>

<form action="TestDeleteAction" method="post" onsubmit="return confirm('本当に削除しますか？');">
    <input type="hidden" name="classNum" value="${test.classNum}" />
    <input type="hidden" name="subjectCd" value="${test.subjectCd}" />
    <input type="hidden" name="studentNo" value="${test.studentNo}" />
    <input type="hidden" name="No" value="${test.no}" />
    <input type="submit" value="削除" />
</form>

<br/>
<a href="/score_management/main/TestListAction">戻る</a>
</div>
</div>
</body>
</html>

<%@ include file="/footer.jsp"%>