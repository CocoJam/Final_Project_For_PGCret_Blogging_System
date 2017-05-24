<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<%
    if ( session.getAttribute("log") != null){
        if ((boolean)session.getAttribute("log")){
    request.getRequestDispatcher("/WEB-INF/webthings/content_page.jsp").forward(request,response);}
}
    String username = (String) session.getAttribute("username");
    System.out.println(username + " username");
    if ( username != null){
    out.println("<p>"+ username + " has been Logged Out</p>");
        session.setAttribute("username",null);
}
%>

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
