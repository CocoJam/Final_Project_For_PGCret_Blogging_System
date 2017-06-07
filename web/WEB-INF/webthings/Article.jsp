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
    <title>Article</title>

    <%@include file="../../component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<!-- !!! NAVIGATION BAR START !!! -->

<%@ include file="../../component/NavBar-AfterLogin(Template).jsp" %>

<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->

<div class="wrapper">
    <div class="header header-filter" id="custom-bg-user"></div><!-- background div -->

    <div class="container">
        <div class="row">

            <div class="main custom-container-main"><!-- main container start -->

                <div class="profile-content">
                    <div class="container">

                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <%--<img src="" alt="Circle Image" class="img-rounded img-responsive img-raised">--%>

                                    <c:choose>
                                        <c:when test="${articleList.equals('self')}">
                                            <c:choose>
                                                <c:when test="${profileInfo.profilepic != null}">
                                                    <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                         alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                    <div class="name" id="custom-profile-name">
                                                        <h3 class="title">${profileInfo.name}'s Articles list</h3>
                                                    </div>
                                                </c:when>

                                                <c:otherwise>
                                                    <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <h1>ALL ARTICLES</h1>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">

                                <h1>${articleContents.articlename}</h1>
                                <div>${articleContents.content}</div>
                                <p>${articleContents.username}</p>
                                <p>${articleContents.datecreated}</p>


                                <%--<c:forEach var="mediagroups" items="${mediaOutPut}">--%>
                                <%--<c:if test="${mediagroups.key.equals(\"photo\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>
                                <%--<img src="${media}">--%>
                                <%--<c:if test="${articleContents.owner}">--%>
                                <%--<form action="/Deleting" method="post">--%>
                                <%--<input type="hidden" name="media" value="${media}">--%>
                                <%--<input type="submit" name="log" value="DeleteMedia">--%>
                                <%--</form>--%>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${mediagroups.key.equals(\"audio\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>
                                <%--<audio controls><source src="${media}" type="audio/ogg"> </audio>--%>
                                <%--<c:if test="${articleContents.owner}">--%>
                                <%--<form action="/Deleting" method="post">--%>
                                <%--<input type="hidden" name="media" value="${media}">--%>
                                <%--<input type="submit" name="log" value="DeleteMedia">--%>
                                <%--</form>--%>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${mediagroups.key.equals(\"video\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>
                                <%--<video width="400" controls> <source src="${media}"></video>--%>
                                <%--<c:if test="${articleContents.owner}">--%>
                                <%--<form action="/Deleting" method="post">--%>
                                <%--<input type="hidden" name="media" value="${media}">--%>
                                <%--<input type="submit" name="log" value="DeleteMedia">--%>
                                <%--</form>--%>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${mediagroups.key.equals(\"youtube\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>
                                <%--<c:if test="${articleContents.owner}">--%>
                                <%--<form action="/Deleting" method="post">--%>
                                <%--<input type="hidden" name="media" value="${media}">--%>
                                <%--<input type="submit" name="log" value="DeleteYoutube">--%>
                                <%--</form>--%>
                                <%--</c:if>--%>
                                <%--${media}--%>
                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>


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
                                    </c:if>
                                </c:forEach>


                                <form action="/Comments" method="post">
                                    <label for="comments">Comments: </label>
                                    <br>
                                    <textarea rows="4" cols="50" id="comments" name="commentcontent"></textarea>
                                    <input type="submit" name="comments" value="Add a Comment">
                                </form>

                                <script>
                                $(".wrapper li").each(function () {
                                $(this).replaceWith(function () {
                                return $('<p>', {
                                html: this.innerHTML
                                });
                                });
                                });
                                </script>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER START -->
<%@ include file="../../component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
</html>
