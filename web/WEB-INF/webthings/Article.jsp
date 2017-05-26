<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article -> ArticleCreationPage</title>
</head>
<body>
<h1>${articleContents.articlename}</h1>
<textarea rows="50" cols="50">${articleContents.content}</textarea>
<p>${articleContents.username}</p>
<p>${articleContents.datecreated}</p>

<form action="/Articles" method="post">
    <input type="submit" name="add" value="EditArticle">
</form>
</body>
</html>


<%--articleContents--%>

<%--private int articleid = 0;--%>
<%--private String articlename = null;--%>
<%--private String username = null;--%>
<%--private String content = null;--%>
<%--private Date datecreated =null;--%>