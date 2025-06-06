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
			<li><a href="<c:url value='/main/TestRegistAction'/>">成績登録</a></li>
			<li><a href="<c:url value='/main/TestListAction'/>">成績参照</a></li>
			<li><a href="<c:url value='/SubjectListAction'/>">科目管理</a></li>
		</ul>
    </div>

    <!-- コンテンツエリア -->
    <div class="content-container">
        <h1>学生管理</h1>
        <form method="get" action="<c:url value='/student_list' />">
            入学年度:
            <select name="entYear">
                <option value="">-- 全て --</option>
                <c:forEach var="year" items="${yearList}">
                    <option value="${year}" ${param.entYear == year || selectedEntYear == year ? "selected" : ""}>${year}</option>
                </c:forEach>
            </select>
            クラス:
            <select name="classNum">
                <option value="">-- 全て --</option>
                <c:forEach var="c" items="${classList}">
                    <option value="${c}" ${param.classNum == c || selectedClassNum == c ? "selected" : ""}>${c}</option>
                </c:forEach>
            </select>
			在学状態:
			<select name="isAttend">
			    <option value="">-- 全員 --</option>
			    <option value="true" ${param.isAttend == 'true' ? 'selected' : ''}>在学中</option>
			    <option value="false" ${param.isAttend == 'false' ? 'selected' : ''}>在学していない</option>
			</select>


		    <input type="submit" value="絞り込む" />
        </form>

        <a href="<c:url value='/scoremanager/main/student_create.jsp'/>">
            <button type="button">新規登録</button>
        </a>

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
                            <td>    <form action="<c:url value='/scoremanager/main/student_update.jsp' />" method="post" style="display:inline;">
                                    <input type="hidden" name="studentNumber" value="${STUDENT.studentNumber}" />
                                    <input type="hidden" name="entYear" value="${STUDENT.entYear}" />
                                    <input type="hidden" name="name" value="${STUDENT.name}" />
                                    <input type="hidden" name="classNum" value="${STUDENT.classNum}" />
                                    <input type="hidden" name="attend" value="${STUDENT.attend}" />
                                    <input type="submit" value="変更" />
                                </form></td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>

<%@ include file="/footer.jsp" %> <%-- フッター --%>
