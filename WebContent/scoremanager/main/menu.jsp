<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/header.jsp" %>  <%-- タイトル + CSS --%>

<!-- メニューとコンテンツを横並びに配置するコンテナ -->
<div class="main-container">

<!-- 左メニューエリア -->
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

    <!-- 右コンテンツエリア -->
<!-- 右コンテンツエリア -->
<div class="content-container">
    <h1>メニュー</h1>

    <div class="card-container">
        <!-- 学生管理 -->
        <div class="card card-red">
            <a href="<c:url value='/student_list'/>">学生管理</a>
        </div>

        <!-- 成績管理 -->
        <div class="card card-green">
            <p>成績管理</p>
            <a href="<c:url value='/ScoreInsertAction'/>">成績登録</a><br>
            <a href="<c:url value='/main/TestListAction'/>">成績参照</a>
        </div>

        <!-- 科目管理 -->
        <div class="card card-purple">
            <a href="<c:url value='/SubjectListAction'/>">科目管理</a>
        </div>
    </div>
</div>

</div>


<%@ include file="/footer.jsp" %>
