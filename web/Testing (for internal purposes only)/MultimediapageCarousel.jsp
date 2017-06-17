<%--
  Created by IntelliJ IDEA.
  User: jwon117
  Date: 14/06/2017
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">


        <c:forEach var="mediagroups" items="${mediaOutPut}">

            <c:if test="${mediagroups.key.equals(\"photo\")}">

                <%--Carousel for Photos--%>
                <%--Carousel view images--%>
                <%--<div class="card card-raised card-carousel">--%>

                <div id="carouselMediaPage" class="carousel slide card card-raised card-carousel"
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

                    <div class="card card-raised card-carousel">
                        <audio controls class="center-block">
                            <source src="${media}" type="audio/ogg">
                        </audio>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${mediagroups.key.equals(\"video\")}">
                <c:forEach var="media" items="${mediagroups.value}">
                    <div class="card card-raised card-carousel">
                        <video controls class="center-block">
                            <source src="${media}">
                        </video>
                    </div>
                </c:forEach>
            </c:if>

        </c:forEach>


    </div>
</div>
<script>
    var regex= /Upload-photos\\.+\\/i;
    var sample1 ="Upload-photos\jongwoowon\audio\Beethoven-Moonlight Sonata (Mvt. 1).mp3";
    console.log(sample1.replace(regex,""));
</script>

</body>
</html>




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

                        <video controls>
                            <source src="/defaultImg/pokemon.mp4">
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
                            src="../../assets/img/white-navigate-left.png">
                    </a>
                    <a class="carouselSlashControl-right"
                       href="#videoGallery"
                       data-slide="next">
                        <img src="../../assets/img/white-navigate-right.png">
                    </a>
                </div>

            </div>
        </div>

    </div>
</div>
<%--END: TAB 3--%>
