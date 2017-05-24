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
    <title>Title</title>
</head>
<body>
<form action="/Registration" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username"  name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" >
    <label for="Lucky Number">Lucky Number:</label>
    <input type="text" id="Lucky Number" name="Lucky number">
    <input type="submit" name="log" value="Registration">
</form>
</body>
</html>
