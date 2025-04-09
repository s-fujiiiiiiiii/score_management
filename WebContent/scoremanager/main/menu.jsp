<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="header.jsp" %>  <%-- タイトル + CSS --%>

<!-- メニューとコンテンツを横並びに配置するコンテナ -->
<div class="main-container">

<!-- 左メニューエリア -->
<div class="menu-container">
	<ul class="menu-list">
		<li><a href="<c:url value=''/>">学生管理</a></li>
		<li><a href="<c:url value=''/>">成績管理</a></li>
		<li><a href="<c:url value=''/>">成績登録</a></li>
		<li><a href="<c:url value=''/>">成績参照</a></li>
		<li><a href="<c:url value=''/>">科目管理</a></li>
	</ul>
</div>

    <!-- 右コンテンツエリア -->
    <div class="content-container">
        <h1>メニュー</h1>


    </div>
</div>


<%@ include file="footer.jsp" %>
