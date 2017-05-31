<%@ page import="java.io.File" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.io.IOException" %><%--
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
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <title>Article -> ArticleCreationPage</title>
</head>
<body>
<h1>${articleContents.articlename}</h1>
<textarea rows="50" cols="50">${articleContents.content}</textarea>
<p>${articleContents.username}</p>
<p>${articleContents.datecreated}</p>

<c:forEach var="mediagroups" items="${mediaOutPut}">
    <c:if test="${mediagroups.key.equals(\"photo\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <img src="${media}">
        </c:forEach>
    </c:if>
    <c:if test="${mediagroups.key.equals(\"audio\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <audio controls><source src="${media}" type="audio/ogg"> </audio>
        </c:forEach>
    </c:if>
    <c:if test="${mediagroups.key.equals(\"video\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <video width="400" controls> <source src="${media}"></video>
        </c:forEach>
    </c:if>
</c:forEach>

<c:if test="${articleContents.owner}">
    <p>${articleContents.owner}</p>
    <form action="/Articles" method="post">
        <input type="submit" name="add" value="EditArticle">
    </form>
    <form action="/Deleting" method="post">
        <input type="submit" name="log" value="DeleteArticle">
    </form>
</c:if>

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
        <form action="/Comments" method="post">
            <input type="hidden" name="commentId" value="${content.commentId}">
            <input type="text" name="commentcontent" value="${content.content}">
            <input type="submit" name="comments" value="EditComment">
        </form>
        <form   action="/ArticleUpload" method="post">
            <input type="text" name="youtube">
            <input type="submit" name="youtubeVideoSubmition" value="">
        </form>
    </c:if>
</c:forEach>


<form action="/Comments" method="post">
    <label for="comments">Comments: </label>
    <br>
    <textarea rows="4" cols="50" id="comments" name="commentcontent"></textarea>
    <input type="submit" name="comments" value="Add a Comment">
</form>


</body>
</html>
