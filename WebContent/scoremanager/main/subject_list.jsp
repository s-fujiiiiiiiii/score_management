<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>科目管理</title>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h2>ログイン画面</h2>
<a href="/score_management/scoremanager/main/subject_create.jsp">新規登録</a>

<!--  <p>取得した科目数: ${fn:length(subjects)}</p>-->
<table>
    <tr>
        <th>科目コード</th>
        <th>科目名</th>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.cd}</td>
            <td>${subject.name}</td>
            <td><a href="/score_management/main/SubjectUpdateConfirmAction?schoolCd=${subject.schoolCd}&cd=${subject.cd}">変更</a></td>
            <td><a href="/score_management/main/SubjectDeleteConfirmAction?schoolCd=${subject.schoolCd}&cd=${subject.cd}">削除</a></td>

            <!--  <td><a href="/score_management/scoremanager/main/subject_delete.jsp?cd=${subject.cd}">削除</a></td>-->
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@include file="../../footer.jsp" %>

