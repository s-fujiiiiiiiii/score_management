<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../header.jsp"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>科目情報削除</title>
<style>
label {
	display: block;
	margin-top: 10px;
}

.delete-button {
    background-color: red;
    color: white;
    padding: 10px 20px;
    font-weight: bold;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
    cursor: pointer;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

.delete-button:hover {
    background-color: darkred;
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.3);
}

</style>
</head>
<body>

	<h2>科目情報削除</h2>
	<%-- データが渡されているか確認 --%>
	<c:if test="${subject != null}">
		<p>「${subject.name}(${subject.cd})」を削除してもよろしいですか？</p>

		<%-- デバッグ情報（削除後は削除） --%>
		<!-- <p>学校コード: ${subject.schoolCd}</p>
    <p>科目コード: ${subject.cd}</p>-->

		<form action="/score_management/main/SubjectDeleteAction"
			method="post">
			<input type="hidden" name="schoolCd" value="${subject.schoolCd}">
			<input type="hidden" name="cd" value="${subject.cd}">
			<button type="submit" class="delete-button">削除</button>
			<br><br>
			<a href="/score_management/main/SubjectListAction">戻る</a>
		</form>
	</c:if>

	<%-- データが渡っていない場合のエラーメッセージ --%>
	<c:if test="${subject == null}">
		<p style="color: red;">エラー: 削除対象のデータが存在しません。</p>
		<a href="/score_management/main/SubjectListAction">戻る</a>
	</c:if>

</body>
</html>

<%@include file="../../footer.jsp"%>