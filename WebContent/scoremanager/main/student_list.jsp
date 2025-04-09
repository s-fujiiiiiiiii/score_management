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
                    <th>得点</th>
                    <th>在学</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.enrollmentYear}</td>
                        <td>${student.studentNumber}</td>
                        <td>${student.name}</td>
                        <td>${student.className}</td>
                        <td>${student.score}</td>
                        <td>${student.enrolled ? '○' : '×'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/footer.jsp" %> <%-- フッター --%>