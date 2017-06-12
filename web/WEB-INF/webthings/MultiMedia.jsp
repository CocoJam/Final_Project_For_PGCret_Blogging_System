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
                                                        <c:when test='${profileInfo.profilepic.startsWith("dEfAuLt")}'>
                                                            <img src="defaultImg/${profileInfo.profilepic}"
                                                                 alt="Circle Image"
                                                                 class="img-rounded img-responsive img-raised">
                                                        </c:when>

                                                        <%--Otherwise get the photo from the users photo page--%>
                                                        <c:otherwise>
                                                            <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                                 alt="Circle Image" class="img-rounded img-responsive img-raised">
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

                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">


                                <%--Triology Part 3.5 TODO display this in tables--%>

                                <c:forEach var="mediagroups" items="${mediaOutPut}">
                                    <c:if test="${mediagroups.key.equals(\"photo\")}">
                                        <c:forEach var="media" items="${mediagroups.value}">
                                            <img src="${media}" height='30%'>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${mediagroups.key.equals(\"audio\")}">
                                        <c:forEach var="media" items="${mediagroups.value}">
                                            <audio controls>
                                                <source src="${media}" type="audio/ogg">
                                            </audio>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${mediagroups.key.equals(\"video\")}">
                                        <c:forEach var="media" items="${mediagroups.value}">
                                            <video width="400" controls>
                                                <source src="${media}" height='30%'>
                                            </video>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>


                                <audio controls>
                                    <source src="" type="audio/ogg">
                                </audio>

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
