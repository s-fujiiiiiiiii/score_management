<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="/header.jsp" %>
<%
    // セッションの破棄
    session.invalidate(); // セッションを無効にする
%>
<p>ログアウト</p>
ログアウトしました。
<br><br>
<a href="../login.jsp">ログイン</a>

<%@include file="/footer.jsp" %>
