<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<div class="main-container">
    <!-- サイドメニュー -->
    <div class="menu-container">
        <ul class="menu-list">
            <li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
            <li><a href="<c:url value='/student_list'/>">学生管理</a></li>
            <li>成績管理</li>
            <li><a href="<c:url value=''/>">成績登録</a></li>
            <li><a href="<c:url value='/main/TestListAction'/>">成績参照</a></li>
            <li><a href="<c:url value='/main/SubjectListAction'/>">科目管理</a></li>
        </ul>
    </div>


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
<!-- コンテンツエリア -->
    <div class="content-container">

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
</div>
</div>
</html>

<%@include file="../../footer.jsp" %>

