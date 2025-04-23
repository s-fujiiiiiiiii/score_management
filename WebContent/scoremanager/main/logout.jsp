<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="/header.jsp" %>
<%
    session.invalidate();
    response.sendRedirect("/score_management/scoremanager/login.jsp");
%>
<p>ログアウト</p>
ログアウトしました。
<br><br>
<a href="../login.jsp">ログイン</a>

<%@include file="/footer.jsp" %>
