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
    <input name="ArticleName" type="text" id="ArticleName" value="${articleContents.articlename}">
    <textarea rows="4" cols="50" name="ArticleContent">${articleContents.content}</textarea>

    <% if (request.getAttribute("articleContents") != null){
        System.out.println("EDIT");
        out.println("<input type=\"submit\" name=\"add\" value=\"Editted\">");
    }else{
        out.println("<input type=\"submit\" name=\"add\" value=\"addingToDataBase\">");
    }
    %>
</form>
<form action="/Upload" method="post"
                 enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <input type="submit" name="Upload" value="ArticlesUpload"/>
    <br>
</form>
</body>
</html>
