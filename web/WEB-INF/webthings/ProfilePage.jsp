
<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:if test="${profileInfo.profilepic != null}">
<img  src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}">
</c:if>

<a href="/ArticlesIndex?articleList=self">To articles index</a>
<a href="/ArticlesIndex?articleList=all">To all articles index</a>
<form action="/logout" method="get">
    <input type="submit" value="Logout">
</form>

<form action="/Registration" method="get">
    <input type="submit" name="log" value="ChangeUserInformation">
</form>

<form action="/Deleting" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" name="log" value="DeletingProfile">
</form>

<%--<form action="/Upload" method="post"--%>
      <%--enctype="multipart/form-data">--%>
    <%--<input type="file" name="file" size="50"/>--%>
    <%--<input type="submit"  name="Upload" value="ProfileUpload"/>--%>
    <%--<br>--%>
<%--</form>--%>

<a href="/Upload?media=self">To self media</a>
<a href="/Upload?media=all">To all media</a>
</body>
</html>
