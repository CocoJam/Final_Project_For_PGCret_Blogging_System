<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 23/05/2017
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<% String username = (String) session.getAttribute("username");
    System.out.println(username);
    if ( username != null){
    out.println("<p>"+ username + " has been Logged Out</p>");
        session.setAttribute("username",null);
}
%>

<form action="/login_in" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="login" name="log">
</form>
</body>
</html>
