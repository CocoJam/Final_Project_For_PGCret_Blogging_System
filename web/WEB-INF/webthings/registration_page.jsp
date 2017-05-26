<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 23/05/2017
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProfilePage.Registration -> Profile Page</title>
</head>
<body>

<%
    if ( session.getAttribute("log") != null || session.getAttribute("Registration") != null){
        if ((boolean)session.getAttribute("Registration")){
            request.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(request,response);}
    }
%>


<form action="/Registration" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username"  name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" >
    <label for="Name">Name:</label>
    <input type="text" id="Name" name="Name">
    <label for="email">email:</label>
    <input type="email" id="email" name="email">
    <label for="address">address:</label>
    <input type="text" id="address" name="address">
    <label for="education">education:</label>
    <input type="text" id="education" name="education">
    <label for="ethnicity">ethnicity:</label>
    <input type="text" id="ethnicity" name="ethnicity">
    <label for="date">date:</label>
    <input type="date" id="date" name="date">
    <input type="submit" name="log" value="Registration">
</form>
</body>
</html>


<%--private String username;--%>
<%--private String name;--%>
<%--private String email;--%>
<%--private String address;--%>
<%--private String education;--%>
<%--private String ethnicity;--%>
<%--private Date date;--%>