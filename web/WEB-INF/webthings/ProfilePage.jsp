
<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile Page -> ArticleIndex</title>
</head>
<body>
<h1>${profileInfo.username}</h1>
<p>${profileInfo.name}</p>
<p>${profileInfo.email}</p>
<p>${profileInfo.address}</p>
<p>${profileInfo.education}</p>
<p>${profileInfo.ethnicity}</p>
<p>${profileInfo.date}</p>
<a href="/ArticlesIndex">To articles index</a>
<form action="/logout" method="get">
    <input type="submit" value="Logout">
</form>
<form action="/Registration" method="get">
    <input type="submit" name="log" value="ChangeUserInformation">
</form>
<form action="/DeletingProfile" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" name="log" value="DeletingProfile">
</form>
</body>
</html>
