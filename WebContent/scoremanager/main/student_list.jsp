<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/header.jsp" %>  <%-- ヘッダー --%>

<div class="main-container">
    <!-- サイドメニュー -->
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

    <!-- コンテンツエリア -->
    <div class="content-container">
        <h1>学生管理</h1>
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>クラス</th>
                    <th>学校コード</th>
                    <th>出席</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="STUDENT" items="${studentList}">
                    <tr>
                        <td>${STUDENT.entYear}</td>
                        <td>${STUDENT.studentNumber}</td>
                        <td>${STUDENT.name}</td>
                        <td>${STUDENT.classNum}</td>
                        <td>${STUDENT.schoolCd}</td>
                        <td>${STUDENT.attend ? '○' : '×'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/footer.jsp" %> <%-- フッター --%>