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
        <h1>学生情報の変更</h1>

        <form method="post" action="<c:url value='/student_update' />">
            <!-- 入学年度と学生番号は表示のみ -->
            <p>入学年度: <strong>${param.entYear}</strong></p>
            <p>学生番号: <strong>${param.studentNumber}</strong></p>
            <input type="hidden" name="studentNumber" value="${param.studentNumber}" />
            <input type="hidden" name="entYear" value="${param.entYear}" />

            <!-- 氏名 -->
            <label>
                氏名:
                <input type="text" name="name" value="${param.name}" required />
            </label>
            <br><br>

            <!-- クラス -->
            <label>
                クラス:
                <select name="classNum">
                    <c:forEach var="c" items="${classList}">
                        <option value="${c}" ${param.classNum == c ? "selected" : ""}>${c}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br>

            <!-- 在学中 -->
            <label>
                在学中:
                <select name="attend">
                    <option value="true" ${param.attend == 'true' ? 'selected' : ''}>○</option>
                    <option value="false" ${param.attend == 'false' ? 'selected' : ''}>×</option>
                </select>
            </label>
            <br><br>

            <input type="submit" value="更新" />
            <a href="<c:url value='/student_list' />"><button type="button">キャンセル</button></a>
        </form>
    </div>
</div>

<%@ include file="/footer.jsp" %> <%-- フッター --%>
