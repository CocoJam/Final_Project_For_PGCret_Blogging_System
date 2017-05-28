<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Article -> ArticleCreationPage</title>
</head>
<body>
<h1>${articleContents.articlename}</h1>
<textarea rows="50" cols="50">${articleContents.content}</textarea>
<p>${articleContents.username}</p>
<p>${articleContents.datecreated}</p>

<c:if test="${articleContents.owner}">
    <p>${articleContents.owner}</p>
    <form action="/Articles" method="post">
        <input type="submit" name="add" value="EditArticle">
    </form>
    <form action="/Deleting" method="post">
        <input type="submit" name="log" value="DeleteArticle">
    </form>
</c:if>


<form action="/Comments" method="post">
    <label for="comments">Comments: </label>
    <br>
    <textarea rows="4" cols="50" id="comments" name="commentcontent"></textarea>
    <input type="submit" name="comments" value="Add a Comment">
</form>

<c:forEach items="${commentlist}" var="content">
    <p>${content.username}</p>
    <p>${content.content}</p>
    <p>${content.commentedTime}</p>
    <c:if test="${content.owner}">
        <%--needed to display the delete button when load, so needed to go through the commentsServlet first--%>
    <form action="/Deleting" method="post">
        <input type="hidden" name="commentId" value="${content.commentId}">
        <input type="submit" name="log" value="DeleteComment">
    </form>
    </c:if>
</c:forEach>


</body>
</html>


<%--articleContents--%>

<%--private int articleid = 0;--%>
<%--private String articlename = null;--%>
<%--private String username = null;--%>
<%--private String content = null;--%>
<%--private Date datecreated =null;--%>

<%--private int commentId;--%>
<%--private String username;--%>
<%--private String content;--%>
<%--private Date commentedTime;--%>
<%--private int acticleId;--%>
