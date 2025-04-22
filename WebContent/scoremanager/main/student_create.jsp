<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header.jsp"%>
<%-- タイトル + CSS --%>

<!-- メニューとコンテンツを横並びに配置するコンテナ -->
<div class="main-container">

	<!-- 左メニューエリア -->
	<div class="menu-container">
		<ul class="menu-list">
		<li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
		<li><a href="<c:url value='/student_list'/>">学生管理</a></li>
		<li>成績管理</li>
		<li><a href="<c:url value=''/>">成績登録</a></li>
		<li><a href="<c:url value=''/>">成績参照</a></li>
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
					<option value="2023">2023年度</option>
					<option value="2024">2024年度</option>
				</select>
			</label> <br>

		 <label>学生番号<br>
		 	<input type="text"id="studentNum" name="studentNum" required><br>
		 </label>

		 <label>氏名<br>
		 	<input type="text"id="studentName" name="studentName" required><br>
		 </label>

			<label>クラス<br>
				<select name="className" required>
					<option value="">選択してください</option>
					<option value="A">201</option>
					<option value="B">202</option>
				</select>
			</label><br>

           <input type="submit" value="登録して終了"><br>
	</form>

           <a href="menu.jsp">戻る</a>
	</div>
</div>

