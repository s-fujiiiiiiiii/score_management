<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header.jsp"%>
<%-- タイトル + CSS --%>

<!-- メニューとコンテンツを横並びに配置するコンテナ -->
<div class="main-container">

	<!-- 左メニューエリア -->
	<div class="menu-container">
		<ul class="menu-list">
			<li><a href="<c:url value=''/>">メニュー</a></li>
			<li><a href="<c:url value=''/>">学生管理</a></li>
			<li><a href="<c:url value=''/>">成績管理</a></li>
			<li><a href="<c:url value=''/>">成績登録</a></li>
			<li><a href="<c:url value=''/>">成績参照</a></li>
			<li><a href="<c:url value=''/>">科目管理</a></li>
		</ul>
	</div>

	<!-- 右コンテンツエリア -->
	<div class="content-container">
		<h1>学生情報登録</h1>

		<label>入学年度<br>
			<select name="courseId" required>

			</select>
		</label> <br>

		 <label>学生番号<br>
		 	<input type="text"id="studentName" name="studentName" required><br>
		 </label>

		 <label>氏名<br>
		 	<input type="text"id="studentName" name="studentName" required><br>
		 </label>

		 <label>クラス<br>
		 	<select name="class" reqired>

		 	</select>
		 </label><br>

           <input type="submit" value="登録して終了"><br>

           <a href="menu.jsp">戻る</a>
	</div>
</div>

<%@ include file="/footer.jsp"%>