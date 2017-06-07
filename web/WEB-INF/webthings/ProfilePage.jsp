<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Profile Page</title>

    <%@include file="../../component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<%--NAVBAR STARTS--%>

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

                        <!-- First profile row -->
                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <%--<img src="" alt="Circle Image" class="img-rounded img-responsive img-raised">--%>

                                    <c:choose>
                                        <c:when test="${profileInfo.profilepic != null}">
                                            <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                 alt="Circle Image" class="img-rounded img-responsive img-raised">
                                        </c:when>

                                        <c:otherwise>
                                            <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                 class="img-rounded img-responsive img-raised">
                                        </c:otherwise>
                                    </c:choose>


                                </div>
                                <div class="name" id="custom-profile-name">
                                    <h3 class="title">Hello ${profileInfo.name}</h3>
                                </div>
                                <div class="name" id="custom-profile-subtitle">
                                    <h6>${profileInfo.education}</h6>
                                </div>
                            </div>
                        </div>

                        <!-- Profile bio text -->
                        <div class="description text-center col-lg-offset-5 col-md-offset-5 col-sm-offset-5">

                            <table class="table borderless" align="center">
                                <tr>
                                    <th>Specs</th>
                                    <th>Your details</th>
                                </tr>
                                <tr>
                                    <td>Username:</td>
                                    <td>${profileInfo.username}</td>
                                </tr>
                                <tr>
                                    <td>Fullname:</td>
                                    <td>${profileInfo.name}</td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td>${profileInfo.email}</td>
                                </tr>
                                <tr>
                                    <td>Address:</td>
                                    <td>${profileInfo.address}</td>
                                </tr>
                                <tr>
                                    <td>Education:</td>
                                    <td>${profileInfo.education}</td>
                                </tr>
                                <tr>
                                    <td>Ethnicity:</td>
                                    <td>${profileInfo.ethnicity}</td>
                                </tr>
                                <tr>
                                    <td>Date of Birth:</td>
                                    <td>${profileInfo.date}</td>
                                </tr>


                            </table>

                            <%--Form to request for users articles to populate--%>

                            <%--<form action="/ArticlesIndex" method="post" >--%>
                            <%--<input type="hidden" name="articleList" value="self"/>--%>
                            <%--<input type="submit" name="profilePopulate" value="yes" id="helloWorld"/>--%>
                            <%--</form>--%>


                            <input type="button" id="loadProfile"
                                   onclick="location.href='/ArticlesIndex?articleList=self&profilePopulate=yes'"
                                   value="Click to populate"/>

                            <%--<script>--%>

                                <%--//                                $("#loadProfile").one("click", function(){--%>
                                <%--//--%>
                                <%--//                                    console.log("hello!")--%>
                                <%--//                                });--%>

<%--//                                document.getElementById("loadProfile").click();--%>
                                <%--$(document).ready(function(e){--%>
                                    <%--$("#loadProfile").trigger('click');--%>
                                    <%--e.preventDefault();--%>

                                <%--})--%>

                            <%--</script>--%>
                            <%--testing article list--%>

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

                            <%--end testing--%>


                        </div>

                        <%--<!-- Second profile row -->--%>
                        <%--<div class="row">--%>
                        <%--<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">--%>
                        <%--<div class="profile-tabs">--%>
                        <%--<!-- MORE CONTENT GOES HERE IF NEEDED -->--%>
                        <%--Welcome to your page, please navigate by using the features below.--%>

                        <%--&lt;%&ndash;The following are GET methods into either your article index or ALL article index&ndash;%&gt;--%>
                        <%--<h4>Articles</h4>--%>
                        <%--<a href="/ArticlesIndex?articleList=self">To my articles index</a>--%>
                        <%--<br>--%>
                        <%--<a href="/ArticlesIndex?articleList=all">To all articles index</a>--%>

                        <%--&lt;%&ndash;These links goes either to users own media, or all media (both accessed by GET method) goes to /Upload servlet&ndash;%&gt;--%>
                        <%--<a href="Upload?media=self">To self media</a>--%>
                        <%--<br>--%>
                        <%--<a href="Upload?media=all">To all media</a>--%>

                        <%--&lt;%&ndash;This goes to LogOut servlet, session invalidated&ndash;%&gt;--%>
                        <%--<form action="/logout" method="get">--%>
                        <%--<input type="submit" value="Logout">--%>
                        <%--</form>--%>

                        <%--&lt;%&ndash;This goes to changeUserInformation in GET method, see Registration serlvet&ndash;%&gt;--%>
                        <%--<form action="/Registration" method="get">--%>
                        <%--<input type="submit" name="log" value="ChangeUserInformation">--%>
                        <%--</form>--%>

                        <%--&lt;%&ndash;This deletes the whole profile and corresponding database entries (incl youtube links, comments, ) by POST method&ndash;%&gt;--%>
                        <%--&lt;%&ndash;TODO not being able to drop folders&ndash;%&gt;--%>
                        <%--<form action="/Deleting" method="post">--%>
                        <%--<label for="username">Username:</label>--%>
                        <%--<input type="text" id="username" name="username">--%>
                        <%--<label for="password">Password:</label>--%>
                        <%--<input type="password" id="password" name="password">--%>
                        <%--<input type="submit" name="log" value="DeletingProfile">--%>
                        <%--</form>--%>

                        <%----%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<!--End Profile Tabs-->--%>
                        <%--</div>--%>
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
