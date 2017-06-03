
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page -> ProfilePage.Registration , Profile Page</title>
</head>
<body>

<%--<%    String username = (String) session.getAttribute("username");--%>
    <%--if ( username != null){--%>
    <%--out.println("<p>"+ username + " has been Logged Out</p>");--%>
        <%--session.setAttribute("username",null);--%>
<%--}--%>
<%--%>--%>

<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="login" name="login">
</form>

<form action="/login" method="get">
    <input type="submit" value="Registration" name="Registration">
</form>
</body>
</html>
