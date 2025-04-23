<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

<h2>成績登録完了</h2>

<p>${message}</p>

<a href="<c:url value='/scoremanager/main/test_list.jsp'/>">成績一覧へ戻る</a>

<%@ include file="/footer.jsp" %>