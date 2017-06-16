<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 30/05/2017
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>

    <%@include file="/component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<!-- !!! NAVIGATION BAR START !!! -->

<%@ include file="/component/NavBar-AfterLogin(Template).jsp" %>

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
                                                            <img src="assets/img/defaultImg/${profileInfo.profilepic}"
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
                        <%--Row ends--%>

                        <%--<div class="description text-center">--%>
                        <%--<p>Welcome to the Media Gallery page.</p>--%>
                        <%--</div>--%>

                        <%--Content Row--%>

                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <div class="profile-tabs">
                                    <div class="nav-align-center">
                                        <ul class="nav nav-pills nav-pills-slashn" role="tablist">
                                            <li class="active">
                                                <a href="#photoStudio" role="tab" data-toggle="tab">
                                                    <i class="material-icons">photo_album</i>
                                                    Images
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#audioStudio" role="tab" data-toggle="tab">
                                                    <i class="material-icons">audiotrack</i>
                                                    Audio
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#videoStudio" role="tab" data-toggle="tab">
                                                    <i class="material-icons">video_library</i>
                                                    Video
                                                </a>
                                            </li>
                                        </ul>
                                        <br><br>

                                        <%--Three tab option--%>

                                        <div class="tab-content gallery">
                                            <c:forEach var="mediagroups" items="${mediaOutPut}">


                                                <c:if test="${mediagroups.key.equals(\"photo\")}">

                                                    <%--TAB 1: Image gallery--%>
                                                    <div class="tab-pane active" id="photoStudio">
                                                        <div class="row">
                                                                <%--Carousel for Photos--%>
                                                                <%--Carousel view images--%>
                                                                <%--<div class="card card-raised card-carousel">--%>

                                                            <div id="carouselMediaPage"
                                                                 class="carousel slide card card-raised card-carousel"
                                                                 data-ride="carousel">

                                                                <div class="carousel slide" data-ride="carousel">

                                                                    <!-- Indicators -->
                                                                    <ol class="carousel-indicators">
                                                                        <li data-target="#carouselMediaPage"
                                                                            data-slide-to="0"
                                                                            class="active"></li>
                                                                        <li data-target="#carouselMediaPage"
                                                                            data-slide-to="1"></li>
                                                                        <li data-target="#carouselMediaPage"
                                                                            data-slide-to="2"></li>
                                                                    </ol>

                                                                    <!-- Wrapper for slides -->
                                                                    <div class="carousel-inner">
                                                                            <%--TODO need to replace this with proper welcome to media gallery--%>
                                                                        <div class="item active">
                                                                            <img src="assets/img/defaultImg/defaultslashn1.png"
                                                                                 class="center-block">
                                                                        </div>

                                                                            <%--Duplicate of the media content for us to populate in the --%>

                                                                        <c:forEach var="media"
                                                                                   items="${mediagroups.value}">
                                                                            <div class="item">
                                                                                <img src="${media}"
                                                                                     class="center-block">
                                                                            </div>
                                                                        </c:forEach>

                                                                    </div>
                                                                        <%--</div>--%>

                                                                    <!-- Controls -->
                                                                    <a class="left carousel-control"
                                                                       href="#carouselMediaPage"
                                                                       data-slide="prev">
                                                                        <i class="material-icons">arrow_back</i>
                                                                    </a>
                                                                    <a class="right carousel-control"
                                                                       href="#carouselMediaPage"
                                                                       data-slide="next">
                                                                        <i class="material-icons">arrow_forward</i>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                                <%--Carousel ends--%>
                                                        </div>
                                                    </div>
                                                    <%--END: TAB1--%>

                                                </c:if>


                                                <c:if test="${mediagroups.key.equals(\"audio\")}">

                                                    <%--TAB 2: AUDIO Gallery--%>
                                                    <div class="tab-pane text-center" id="audioStudio">
                                                        <div class="row">

                                                            <div id="audioGallery"
                                                                 class="carousel slide card card-raised card-carousel"
                                                                 data-ride="carousel">

                                                                <div class="carousel slide" data-ride="carousel">

                                                                        <%--<!-- Indicators -->--%>
                                                                        <%--<ol class="carousel-indicators">--%>
                                                                        <%--<li data-target="#audioGallery"--%>
                                                                        <%--data-slide-to="0"--%>
                                                                        <%--class="active"></li>--%>
                                                                        <%--<li data-target="#audioGallery"--%>
                                                                        <%--data-slide-to="1"></li>--%>
                                                                        <%--<li data-target="#audioGallery"--%>
                                                                        <%--data-slide-to="2"></li>--%>
                                                                        <%--</ol>--%>

                                                                    <!-- Wrapper for slides -->
                                                                    <div class="carousel-inner">
                                                                        <div class="item active">
                                                                            <img src="assets/img/audio_wave.png" id="defaultAudioWave" class="center-block">
                                                                            <audio controls>
                                                                                    <%--<p>Audio filename: opening mp3</p>--%>
                                                                                <source src="assets/img/defaultImg/opening.mp3"
                                                                                        type="audio/ogg">
                                                                            </audio>
                                                                        </div>

                                                                        <c:forEach var="media"
                                                                                   items="${mediagroups.value}">

                                                                            <div class="item">
                                                                                <img src="assets/img/audio_wave.png" id="audioWave" class="center-block">
                                                                                <audio controls class="center-block">
                                                                                    <source src="${media}"
                                                                                            type="audio/ogg">
                                                                                </audio>
                                                                                <p>Audio filename: ${media}</p>
                                                                            </div>
                                                                        </c:forEach>
                                                                    </div>



                                                                </div>

                                                                <!-- Audio Controls -->
                                                                <a class="carouselSlashControl-left"
                                                                   href="#audioGallery"
                                                                   data-slide="prev"><img
                                                                        src="assets/img/black-navigate-left.png">
                                                                </a>
                                                                <a class="carouselSlashControl-right"
                                                                   href="#audioGallery"
                                                                   data-slide="next">
                                                                    <img src="assets/img/black-navigate-right.png">
                                                                </a>

                                                            </div>


                                                        </div>
                                                    </div>
                                                    <%--END: TAB 2--%>
                                                </c:if>

                                                <c:if test="${mediagroups.key.equals(\"video\")}">

                                                    <%--TAB 3: VIDEO Gallery--%>
                                                    <div class="tab-pane text-center" id="videoStudio">
                                                        <div class="row">

                                                            <div id="videoGallery"
                                                                 class="carousel slide card card-raised card-carousel"
                                                                 data-ride="carousel">

                                                                <div class="carousel slide" data-ride="carousel">

                                                                        <%--<!-- Indicators -->--%>
                                                                        <%--<ol class="carousel-indicators">--%>
                                                                        <%--<li data-target="#videoGallery"--%>
                                                                        <%--data-slide-to="0"--%>
                                                                        <%--class="active"></li>--%>
                                                                        <%--<li data-target="#videoGallery"--%>
                                                                        <%--data-slide-to="1"></li>--%>
                                                                        <%--<li data-target="#videoGallery"--%>
                                                                        <%--data-slide-to="2"></li>--%>
                                                                        <%--</ol>--%>

                                                                    <!-- Wrapper for slides -->
                                                                    <div class="carousel-inner">
                                                                            <%--TODO need to replace this with proper welcome to media gallery--%>
                                                                        <div class="item active">
                                                                            <video controls class="center-block">
                                                                                <source src="assets/img/defaultImg/pokemon.mp4">
                                                                            </video>
                                                                        </div>

                                                                        <c:forEach var="media"
                                                                                   items="${mediagroups.value}">
                                                                            <div class="item">
                                                                                <video controls
                                                                                       class="center-block">
                                                                                    <source src="${media}">
                                                                                </video>
                                                                            </div>
                                                                        </c:forEach>

                                                                    </div>

                                                                    <!-- Controls -->

                                                                    <div class="carouselSlash">
                                                                        <a class="carouselSlashControl-left"
                                                                           href="#videoGallery"
                                                                           data-slide="prev"><img
                                                                                src="assets/img/white-navigate-left.png">
                                                                        </a>
                                                                        <a class="carouselSlashControl-right"
                                                                           href="#videoGallery"
                                                                           data-slide="next">
                                                                            <img src="assets/img/white-navigate-right.png">
                                                                        </a>
                                                                    </div>

                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                    <%--END: TAB 3--%>
                                                </c:if>

                                            </c:forEach>
                                        </div>
                                        <%--End of three tab option--%>

                                    </div>
                                </div>
                                <!-- End Profile Tabs -->
                            </div>
                        </div>

                        <%--Content Row ends--%>

                    </div>
                </div>
            </div>


        </div><!-- outer div 2 -->
    </div><!-- outer div 1 -->
</div><!-- wrapper div -->

<!-- FOOTER START -->
<%@ include file="/component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
</html>
