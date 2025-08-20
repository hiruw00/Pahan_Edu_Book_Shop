<%@ page language="java" session="true" %>
<%
    // Invalidate session
    session.invalidate();

    // Redirect to login with message
    response.sendRedirect("login.jsp?error=You+have+been+logged+out+successfully");
%>
