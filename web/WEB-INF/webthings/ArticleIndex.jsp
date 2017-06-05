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
    <title>ArticleIndex</title>

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
                                <h1></h1>

                                <table class="table table-striped table-hover table-responsive">
                                    <tr>
                                        <th>
                                            Article Numbers
                                        </th>
                                        <th>
                                            Article Names
                                        </th>
                                        <th>
                                            Date Created
                                        </th>
                                        <%--Scenario 1: ALL articles are requested--%>
                                        <c:if test="${articleList.equals('all')}">
                                            <th>
                                                Article Author
                                            </th>
                                        </c:if>

                                    </tr>
                                    <%--Looping through the Article Index (list of articles in the ArticleIndex Servlet) and populates a row per article--%>
                                    <c:forEach items="${ArticleIndex}" var="index">
                                        <tr>
                                            <td>${index.articleid}</td>
                                            <td>
                                                <a href="/Articles?acticleId=${index.articleid}">${index.articlename}</a>
                                            </td>
                                            <td>${index.datecreated}</td>

                                            <c:if test="${articleList.equals('all')}">
                                                <td>
                                                        ${index.username}
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>

                                </table>

                                <%--This is to add a new article--%>
                                <form action="/Articles" method="post">
                                    <input type="submit" name="add" value="addNewArticle" id="addNewArticle">
                                </form>
                            </div>

                        </div>


                    </div>
                </div>
            </div>

        </div><!-- main container end -->

    </div><!-- outer div 2 -->
</div><!-- outer div 1 -->
</div><!-- wrapper div -->

<!-- FOOTER START -->
<%@ include file="../../component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
</html>
