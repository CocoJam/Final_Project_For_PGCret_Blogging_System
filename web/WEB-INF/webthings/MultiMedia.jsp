<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 30/05/2017
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>

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
                                        <c:when test="${AllOrSelf.equals('self')}">
                                            <c:choose>
                                                <c:when test="${profileInfo.profilepic != null}">
                                                    <c:choose>
                                                        <%--If this is a default profile image get the image from default photo directory--%>
                                                        <c:when test='${profileInfo.profilepic.startsWith("defaultslashn")}'>
                                                            <img src="defaultImg/${profileInfo.profilepic}"
                                                                 alt="Circle Image"
                                                                 class="img-rounded img-responsive img-raised">
                                                        </c:when>

                                                        <%--Otherwise get the photo from the users photo page--%>
                                                        <c:otherwise>
                                                            <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                                 alt="Circle Image"
                                                                 class="img-rounded img-responsive img-raised">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div class="name" id="custom-profile-name">
                                                        <h3 class="title">${profileInfo.name}'s Media Gallery</h3>
                                                    </div>
                                                </c:when>

                                                <c:otherwise>
                                                    <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <h3>EVERYONE'S GALLERY</h3>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="container">

                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">


                                <%--Triology Part 3.5 TODO display this in tables--%>

                                <%--<table class="table table-striped table-hover table-responsive">--%>

                                <%--<tr>--%>
                                <%--<th>Media Item</th>--%>
                                <%--</tr>--%>

                                <%--<c:forEach var="mediagroups" items="${mediaOutPut}">--%>
                                <%--<c:if test="${mediagroups.key.equals(\"photo\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>

                                <%--<tr>--%>
                                <%--<td>--%>
                                <%--<img src="${media}" width='300px'>--%>
                                <%--</td>--%>
                                <%--</tr>--%>

                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${mediagroups.key.equals(\"audio\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>

                                <%--<tr>--%>
                                <%--<td>--%>
                                <%--<audio controls>--%>
                                <%--<source src="${media}" type="audio/ogg">--%>
                                <%--</audio>--%>
                                <%--</td>--%>
                                <%--</tr>--%>

                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${mediagroups.key.equals(\"video\")}">--%>
                                <%--<c:forEach var="media" items="${mediagroups.value}">--%>
                                <%--<tr>--%>
                                <%--<td>--%>
                                <%--<video width="400" controls>--%>
                                <%--<source src="${media}" height='30%'>--%>
                                <%--</video>--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                                <%--</c:forEach>--%>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>

                                <%--</table>--%>


                                <c:forEach var="mediagroups" items="${mediaOutPut}">

                                    <c:if test="${mediagroups.key.equals(\"photo\")}">

                                        <%--Carousel for Photos--%>
                                        <%--Carousel view images--%>
                                        <%--<div class="card card-raised card-carousel">--%>

                                        <div id="carouselMediaPage" class="carousel slide card card-raised card-carouse"
                                             data-ride="carousel">

                                            <div class="carousel slide" data-ride="carousel">

                                                <!-- Indicators -->
                                                <ol class="carousel-indicators">
                                                    <li data-target="#carouselMediaPage" data-slide-to="0"
                                                        class="active"></li>
                                                    <li data-target="#carouselMediaPage" data-slide-to="1"></li>
                                                    <li data-target="#carouselMediaPage" data-slide-to="2"></li>
                                                </ol>

                                                <!-- Wrapper for slides -->
                                                <div class="carousel-inner">
                                                        <%--TODO need to replace this with proper welcome to media gallery--%>
                                                    <div class="item active">
                                                        <img src="/defaultImg/defaultslashn1.png" class="center-block">
                                                    </div>

                                                        <%--Duplicate of the media content for us to populate in the --%>

                                                    <c:forEach var="media" items="${mediagroups.value}">
                                                        <div class="item">
                                                            <img src="${media}" class="center-block">
                                                        </div>
                                                    </c:forEach>

                                                </div>
                                                    <%--</div>--%>

                                                <!-- Controls -->
                                                <a class="left carousel-control" href="#carouselMediaPage"
                                                   data-slide="prev">
                                                    <i class="material-icons">arrow_back</i>
                                                </a>
                                                <a class="right carousel-control" href="#carouselMediaPage"
                                                   data-slide="next">
                                                    <i class="material-icons">arrow_forward</i>
                                                </a>
                                            </div>

                                        </div>
                                        <%--Carousel ends--%>

                                    </c:if>

                                    <%--Carousel for photos end--%>
                                    <c:if test="${mediagroups.key.equals(\"audio\")}">
                                        <c:forEach var="media" items="${mediagroups.value}">

                                            <div class="card card-raised card-carouse">
                                                <audio controls class="center-block">
                                                    <source src="${media}" type="audio/ogg">
                                                </audio>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${mediagroups.key.equals(\"video\")}">
                                        <c:forEach var="media" items="${mediagroups.value}">
                                            <div class="card card-raised card-carouse">
                                                <video controls class="center-block">
                                                    <source src="${media}">
                                                </video>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                </c:forEach>


                            </div>
                        </div>


                    </div>
                </div>
            </div>


        </div><!-- outer div 2 -->
    </div><!-- outer div 1 -->
</div><!-- wrapper div -->

<!-- FOOTER START -->
<%@ include file="../../component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
</html>
