<%--Introduction: This is the Navigation template which is reused throughout the website when a user is logged in--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Navigation Bar Template</title>
    <%--The below header compoonent should ONLY be enabled when testing the component standalone and NOT when being used as a component as part of a page.
    NOTE. if you enable this the toggle menu function will not work--%>

    <%--<%@include file="Header(styling Template).html"%>--%>

    <style>
        input.searchBar::-webkit-calendar-picker-indicator {
            display: none;
        }
    </style>
</head>

<%--The following body content will be included into the other JSP pages including this template JSP--%>
<body>
<% System.out.println(response.getHeaderNames());%>

<%--No Caching could be implemented but left in for possible further development after the course has ended.--%>
<%--<% response.addHeader("Cache-Control", "max-age=0,no-cache,no-store,must-revalidate");--%>
<%--response.addHeader("Pragma", "no-cache");--%>
<%--response.addHeader("Expires", "Tue, 01 Jan 1970 00:00:00 GMT");--%>
<%--System.out.println(response.getHeaderNames());%>--%>

<script>
    var ctx = "<%= request.getRequestURI() %>";
    var date = new Date();
    var currentTime = date.getTime();

    ExpirationTime = currentTime + 1000 * 1800;
    date.setTime(ExpirationTime);
    document.cookie = "pagemark=" + ctx + ";expires=" + date.toGMTString() + ";path=/";

    //  Loader script: Loader gif displays between the time AJAX request made but not completed (This helps prevent user boredom while waiting).
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

    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <%--Loader div: This div is to populate the loader --%>
            <div id='loaderID'></div>
            <%--Loader div ends--%>

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
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <%--Search bar starts: This search bar allows you to search people by username--%>
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <form id="searchform" action="ProfilePage" method="get" class="navbar-form" role="search">
                        <div class="input-group" style="top:0.2em;">
                            <input list="usernames" name="accessFriend" class="form-control searchBar"
                                   placeholder="Search People"
                                   id="NameBarForm" style="width:100px;">
                            <button type="submit" class="btn btn-round btn-just-icon btn-simple" style="color:white"><i
                                    class="material-icons">search</i></button>

                            <datalist id="usernames">
                                <c:forEach items="${userlist}" var="names">
                                <option value="${names}">
                                    </c:forEach>
                            </datalist>
                        </div>
                    </form>
                </li>
            </ul>
            <%--Search bar ends--%>

            <!-- Navbar Right Section start -->
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="ProfilePage" class="clickOnce">

                        <%--Navbar logic gate starts: The following series of Logic gates determines the mini user's profile image in the navbar:
                            1. When there is a profile pic and it is a default image grab this from the default folder and display.
                            2. When there is a profile pic and it is a user's image grab this from the user's photo folder.
                            3. If there is no image at all display the default profile icon;
                            --%>
                        <c:choose>
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
                    <%--navbar mini image logic gates end--%>
                </li>

                <%--The cart list starts. This is placed in a IF statement to ensure that this is only popping up on the navbar when --%>
                <c:if test="${not empty cartlist}">
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
                </c:if>
                <%--The cart list ends--%>

                <%--The article dropdown menu links to:
                1. User's own article list;
                2. All users article list;
                3. Create new article.
                --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons"></i>Articles
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li><a href="ArticlesIndex?articleList=self" class="clickOnce"><i class="material-icons">library_books</i>
                            My
                            Articles</a>
                        </li>
                        <li><a href="ArticlesIndex?articleList=all" class="clickOnce"><i class="material-icons">dashboard</i>
                            All
                            Articles</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Articles?add=addNewArticle" class="clickOnce"><i
                                class="material-icons">note_add</i> New
                            Article</a>
                        </li>
                    </ul>
                </li>

                <%--The media drop down menu links to:
                1. User's own media;
                2. All media.
                --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons"></i>Media
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li><a href="Upload?media=self" class="clickOnce"><i class="material-icons">photo_library</i> My
                            Media</a></li>
                        <li><a href="Upload?media=all" class="clickOnce"><i class="material-icons">view_carousel</i> All
                            Media</a></li>
                    </ul>
                </li>

                <%--The settings drop down menu links to:
                1. Edit profile menu.
                2. Changing the background image.
                --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
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
                        <li><a href="logout?submit=Logout" class="clickOnce"><i class="material-icons">exit_to_app</i>
                            Sign Out</a></li>
                    </ul>
                </li>
            </ul>
            <%--/Navbar Right Section ends--%>

        </div>
    </div>
</nav>

<script>

    <%--Various specific scripts have been deployed and implemented specifically in this navbar to ensure that the functionality is transferred to the JSP pages including this JSP.--%>
    //Script Functions 1 & 2: This script enables the left and right button for switching the background photos plus updating the cookie values
    var imageValue = 1;

    $("#leftButton").click(function () {
        if (imageValue > 1) {
            imageValue = imageValue - 1;

            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
            document.cookie = "background=" + imageValue;
        }
    });
    $("#rightButton").click(function () {
        if (imageValue < 5) {
            imageValue = imageValue + 1;
            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
            document.cookie = "background=" + imageValue;
        }
    });


    // Script Function 3: Using key bind to allow enter press to search friend
    $("[name='accessFriend']").on('keyup', function (e) {
        if (e.keyCode == 13) {
            return $("#NameBar").submit;
        }
    });

    //    Script Function 4: limits clicking of the button to one click
    var clicked = false;
    $(".clickOnce").on("click", function (e) {
        if (clicked === false) {
            clicked = true;
        } else {
            e.preventDefault();
        }
    });

    //  Script function 5: Making a cookie and detecting a cookie called the background with value and using it to adjust the background photo
    $(document).ready(function () {
        var cookielist = document.cookie.split(";");

        for (var i = 0; i < cookielist.length; i++) {
            if (cookielist[i].trim().startsWith("background")) {
                imageValue = parseInt(cookielist[i].trim().replace("background=", ""));
                break;
            }
        }
        $("#bgImgStatus").text("Background " + imageValue + "/5");
        $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
        $("#imageselect").change(function () {
            $('#custom-bg-user').css("background-image", "url('assets/img/background/bg-0" + imageValue + ".jpg')");
        });
    });

</script>
</body>
</html>