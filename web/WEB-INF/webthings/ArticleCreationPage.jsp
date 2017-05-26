<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 26/05/2017
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/Articles" method="post">
    <label for="ArticleName">Article Name</label>
    <input name="ArticleName" type="text" id="ArticleName">
    <textarea rows="4" cols="50" name="ArticleContent">
        Please enter Text here.
    </textarea>
    <input type="submit" name="add" value="addingToDataBase">
</form>
</body>
</html>
