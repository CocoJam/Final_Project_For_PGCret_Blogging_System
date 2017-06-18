<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Friend.FriendDAO" %>
<html lang="en">
<head>

    <title>Navigation Bar Template</title>

    <%--HEADER TEMPLATE RETRIEVED FROM THE HTML FILE--%>

    <%--The below header compoonent should ONLY be enabled when testing the component standalone and NOT when being used as a component as part of a page.
    NOTE. if you enable this the toggle menu function WILL NOT WORK--%>
    <%--<%@include file="Header(styling Template).html"%>--%>
    <style>
        /*.searchBar {*/
        /*color: black !important;*/
        /*}*/

        /*input.searchBar::-webkit-input-placeholder { !* Chrome/Opera/Safari *!*/
        /*color: pink;*/
        /*}*/
        input.searchBar::-webkit-calendar-picker-indicator {
            display: none;
        }
    </style>
</head>
<body>
<% System.out.println(response.getHeaderNames());%>
<%--<% response.addHeader("Cache-Control", "max-age=0,no-cache,no-store,must-revalidate");--%>
    <%--response.addHeader("Pragma", "no-cache");--%>
    <%--response.addHeader("Expires", "Tue, 01 Jan 1970 00:00:00 GMT");--%>
    <%--System.out.println(response.getHeaderNames());%>--%>
<script>
    var ctx = "<%= request.getRequestURI() %>";
    var date = new Date();
    var currentTime = date.getTime();
    console.log(currentTime);
    ExpirationTime = currentTime + 1000 * 1800;
    console.log(ExpirationTime);
    date.setTime(ExpirationTime);
    document.cookie = "pagemark=" + ctx + ";expires=" + date.toGMTString() + ";path=/";
    console.log(date.toGMTString());
    console.log(document.cookie);

//    Loader gif displays while Ajax request started but not completed.
    $(document).ready(function () {

        $(document).ajaxStart(function () {
            $("#loaderID").css("visibility", "visible");
        });
        $(document).ajaxComplete(function () {
            $("#loaderID").css("visibility", "hidden");
        });
    });

</script>

<nav class="navbar navbar-fixed-top navbar-inverse navbar-transparent navbar-color-on-scroll">

    <!-- responsive container for div --><!-- do not remove -->
    <div class="container">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <%--Div to load up loader--%>
            <div id='loaderID'></div>
            <%--Div loader ending--%>

            <!-- Navbar toggle which display when on mobile device -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#expand-navbar-icons">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- Navbar name of our website, displays on left -->
            <a href="ProfilePage" class="navbar-brand clickOnce"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <%--Search bar here--%>
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <%--<button type="button" class="btn btn-round btn-just-icon btn-simple" style="color:white" data-toggle="collapse" data-target="#searchform"><i class="material-icons">code</i></button>--%>
                    <form id="searchform" action="ProfilePage" method="get" class="navbar-form" role="search">
                        <div class="input-group" style="top:0.2em;">
                            <input list="usernames" name="accessFriend" class="form-control searchBar" placeholder="Search People"
                                   id="NameBarForm" list="usernames" style="width:100px;">
                            <button type="submit" class="btn btn-round btn-just-icon btn-simple" style="color:white"><i class="material-icons">search</i></button>
                            <datalist id="usernames">
                                <c:forEach items="${userlist}" var="names">
                                <option value="${names}">
                                    </c:forEach>
                            </datalist>
                        </div>
                    </form>
                </li>
            </ul>
            <%--Search bar here--%>

            <!-- Nav bar right side links start -->
            <!-- Use on all other pages except login/register -->
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="ProfilePage" class="clickOnce"><c:choose>
                        <c:when test="${profileInfo.profilepic != null}">
                            <c:choose>
                                <%--If this is a default profile image get the image from default photo directory--%>
                                <c:when test='${profileInfo.profilepic.startsWith("defaultslashn")}'>
                                    <img src="assets/img/defaultImg/${profileInfo.profilepic}"
                                         height="20">
                                </c:when>

                                <%--Otherwise get the photo from the users photo page--%>
                                <c:otherwise>
                                    <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                         height="20">
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <i class="material-icons">portrait</i>
                        </c:otherwise>
                    </c:choose>${profileInfo.username}</a>
                </li>

                <c:choose>
                    <c:when test="${not empty cartlist}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="material-icons"></i>Stash
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Your Saved Articles</li>
                                <c:forEach var="carted" items="${cartlist}">
                                    <li>
                                            ${carted}
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons"></i>Articles
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <%--<li class="dropdown-header">Article dropdown</li>--%>
                        <li><a href="ArticlesIndex?articleList=self" class="clickOnce"><i class="material-icons">library_books</i> My
                            Articles</a>
                        </li>
                        <%--<li class="divider"></li>--%>
                        <li><a href="ArticlesIndex?articleList=all" class="clickOnce"><i class="material-icons">dashboard</i> All
                            Articles</a>
                        </li>
                        <%--<li class="divider"></li>--%>
                        <li><a href="Articles?add=addNewArticle" class="clickOnce"><i
                                class="material-icons">note_add</i> New
                            Article</a>
                        </li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons"></i>Media
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <%--<li class="dropdown-header">Media dropdown</li>--%>
                        <li><a href="Upload?media=self" class="clickOnce"><i class="material-icons">photo_library</i> My
                            Media</a></li>
                        <%--<li class="divider"></li>--%>
                        <li><a href="Upload?media=all" class="clickOnce"><i class="material-icons">view_carousel</i> All
                            Media</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <%--<li class="dropdown-header">Options</li>--%>
                        <li><a href="Registration?log=Update Profile" class="clickOnce"><i
                                class="material-icons">create</i> Edit
                            Profile</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header" id="bgImgStatus">Background</li>
                        <li><a href="#" id="leftButton"><i
                                class="material-icons">keyboard_arrow_left</i>Previous</a>
                            <a href="#" id="rightButton"><i
                                    class="material-icons">keyboard_arrow_right</i>Next</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout?submit=Logout" class="clickOnce"><i class="material-icons">exit_to_app</i> Sign Out</a></li>
                    </ul>
                </li>

            </ul>
            <%--Navbar right links end--%>

        </div>
    </div>
</nav>

<script>
    var imageValue = 1;
//    console.log(document.cookie);
//    var cookielist = document.cookie.split(";");
//    console.log(cookielist);
//    for (var i =0 ; i<cookielist.length; i++){
//        if (cookielist[i].trim().startsWith("background")){
//            console.log(parseInt(cookielist[i].trim().replace("background=","")));
//            imageValue = parseInt(cookielist[i].trim().replace("background=",""));
//            break;
//        }
//    }
//    $(document).ready(function () {
//        var cookielist = document.cookie.split(";");
//        console.log(cookielist);
//        for (var i =0 ; i<cookielist.length; i++){
//            if (cookielist[i].trim().startsWith("background")){
//                console.log(parseInt(cookielist[i].trim().replace("background=","")));
//                imageValue = parseInt(cookielist[i].trim().replace("background=",""));
//                break;
//            }
//        }
//        $("#bgImgStatus").text("Background " + imageValue + "/5");
//    });
    $("#leftButton").click(function () {
        if (imageValue > 1) {
            imageValue = imageValue - 1;
//            $("#imageselect").val(imageValue);
            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
            document.cookie = "background="+ imageValue;
        }
    });

    $("#rightButton").click(function () {

        if (imageValue < 5) {
            imageValue = imageValue + 1;
//            $("#imageselect").val(imageValue);
            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
            document.cookie = "background="+ imageValue;
        }
    });

    $("[name='accessFriend']").on('keyup', function (e) {
        if (e.keyCode == 13) {
            return $("#NameBar").submit;
        }
    });
</script>

<%--This script limits clicking of the button to one click--%>
<script>

    var clicked = false;
    $(".clickOnce").on("click", function (e) {
        if (clicked === false) {
            clicked = true;
        } else {
            e.preventDefault();
        }
    });

    $(document).ready(function () {

        var cookielist = document.cookie.split(";");
        console.log(cookielist);
        for (var i =0 ; i<cookielist.length; i++){
            if (cookielist[i].trim().startsWith("background")){
                console.log(parseInt(cookielist[i].trim().replace("background=","")));
                imageValue = parseInt(cookielist[i].trim().replace("background=",""));
                break;
            }
        }
        $("#bgImgStatus").text("Background " + imageValue + "/5");
        $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
        $("#imageselect").change(function () {
            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
            console.log(document.getElementById('custom-bg-user'));
        });
    });

</script>
</body>
</html>