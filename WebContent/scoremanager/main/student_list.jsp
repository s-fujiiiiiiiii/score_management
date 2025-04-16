<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/header.jsp" %>  <%-- ヘッダー --%>

<div class="main-container">
    <!-- サイドメニュー -->
    <div class="menu-container">
        <ul class="menu-list">
            <li><a href="<c:url value='/scoremanager/main/menu.jsp'/>">メニュー</a></li>
            <li><a href="<c:url value='/student_list'/>">学生管理</a></li>
            <li>成績管理</li>
            <li><a href="<c:url value=''/>">成績登録</a></li>
            <li><a href="<c:url value=''/>">成績参照</a></li>
            <li><a href="<c:url value=''/>">科目管理</a></li>
        </ul>
    </div>

    <!-- コンテンツエリア -->
    <div class="content-container">
        <h1>学生管理</h1>

        <!-- 学生リストが空の場合のメッセージ -->
        <c:if test="${empty studentList}">
            <p>学生データがありません。</p>
        </c:if>

        <!-- 学生リストがある場合のテーブル表示 -->
        <c:if test="${!empty studentList}">
            <table>
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>在学中</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- ここでstudentListを繰り返し表示 -->
                    <c:forEach var="STUDENT" items="${studentList}">
                        <tr>
                            <td>${STUDENT.entYear}</td>
                            <td>${STUDENT.studentNumber}</td>
                            <td>${STUDENT.name}</td>
                            <td>${STUDENT.classNum}</td>
                            <td>${STUDENT.attend ? '○' : '×'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>

<%@ include file="/footer.jsp" %> <%-- フッター --%>
