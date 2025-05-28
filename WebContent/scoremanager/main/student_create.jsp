<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header.jsp"%>

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
	<div class="content-container">
		<h1>学生情報登録</h1>


		<form action="/score_management/main/StudentCreate.action" method="post">
			<label>入学年度<br>
				<select name="year" required>
					<option value="">--------</option>
					<option value="2015">2015年度</option>
					<option value="2016">2016年度</option>
					<option value="2017">2017年度</option>
					<option value="2018">2018年度</option>
					<option value="2019">2019年度</option>
					<option value="2030">2020年度</option>
					<option value="2021">2021年度</option>
					<option value="2022">2022年度</option>
					<option value="2023">2023年度</option>
					<option value="2024">2024年度</option>
					<option value="2025">2025年度</option>
				</select>
			</label><br>

			<label>学生番号<br>
				<input type="text" id="studentNum" name="studentNum" required><br>
			</label>

		<% String error = (String) request.getAttribute("error"); %>
		<% if (error != null) { %>
			<p style="color: red;"><%= error %></p> <!-- エラーメッセージ表示 -->
		<% } %>

			<label>氏名<br>
				<input type="text" id="studentName" name="studentName" required><br>
			</label>

			<label>クラス<br>
				<select name="className" required>
					<option value="">選択してください</option>
					<option value="201">201</option>
					<option value="202">202</option>
				</select>
			</label><br>

			<input type="submit" value="登録して終了"><br>
		</form>

		<a href="menu.jsp">戻る</a>
	</div>
</div>

<%@ include file="/footer.jsp" %>
