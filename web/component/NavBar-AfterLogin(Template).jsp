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
        .searchBar {
            color: black !important;
        }

        input::-webkit-calendar-picker-indicator {
            display: none;
        }
    </style>
</head>
<body>
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
</script>
<nav class="navbar navbar-info navbar-transparent navbar-fixed-top navbar-color-on-scroll">

    <!-- responsive container for div --><!-- do not remove -->
    <div class="container">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

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

            <%--Search bar here--%>
            <%--<li>--%>
            <form action="/ProfilePage" method="get">
                <input list="usernames" name="accessFriend" class="form-control" placeholder="Search People"
                       style="color: white" id="NameBarForm" list="usernames">
                <datalist id="usernames">
                    <c:forEach items="${userlist}" var="names">
                    <option value="${names}">
                        </c:forEach>
                </datalist>
            </form>
            <%--</li>--%>
            <%--Search bar here--%>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <!-- Nav bar right side icons -->
            <!-- Use on all other pages except login/register -->
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="ProfilePage" class="clickOnce"><c:choose>
                        <c:when test="${profileInfo.profilepic != null}">
                            <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                 height='20'>
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
                                <i class="material-icons">library_books</i>Carted Articles
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Carted</li>
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
                        <i class="material-icons">library_books</i>Articles
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Article dropdown</li>
                        <li><a href="ArticlesIndex?articleList=self" class="clickOnce"><i class="material-icons">insert_drive_file</i>My
                            Articles</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="ArticlesIndex?articleList=all" class="clickOnce"><i class="material-icons">library_books</i>All
                            Articles</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Articles?add=addNewArticle" class="clickOnce"><i
                                class="material-icons">note_add</i>New
                            Article</a>
                        </li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">photo_library</i>Media
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Media dropdown</li>
                        <li><a href="Upload?media=self" class="clickOnce"><i class="material-icons">pages</i>My
                            Media</a></li>
                        <li class="divider"></li>
                        <li><a href="Upload?media=all" class="clickOnce"><i class="material-icons">collections</i>All
                            Media</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="Registration?log=ChangeUserInformation" class="clickOnce"><i
                                class="material-icons">create</i>Edit
                            Profile</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header" id="bgImgStatus">Background</li>
                        <li><a href="#" id="leftButton"><i
                                class="material-icons">keyboard_arrow_left</i>Previous</a>
                            <a href="#" id="rightButton"><i
                                    class="material-icons">keyboard_arrow_right</i>Next</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout?submit=Logout" class="clickOnce">Sign Out</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->

<script>
    var imageValue = 1;

    $(document).ready(function () {
        $("#bgImgStatus").text("Background " + imageValue + "/5");
    })

    $("#leftButton").click(function () {
        if (imageValue > 1) {
            imageValue = imageValue - 1;
//            $("#imageselect").val(imageValue);
            $('#custom-bg-user').css("background-image", "url('../../assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
        }
    });

    $("#rightButton").click(function () {

        if (imageValue < 5) {
            imageValue = imageValue + 1;
//            $("#imageselect").val(imageValue);
            $('#custom-bg-user').css("background-image", "url('../../assets/img/background/bg-0" + imageValue + ".jpg')");
            $("#bgImgStatus").text("Background " + imageValue + "/5");
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

    <%--Function to prevent user spamming the button.--%>
    //    var clicked = false;
    //    $(".clickOnce").on("click", function (e) {
    //        if (clicked === false) {
    //            clicked = true;
    //        } else {
    //            e.preventDefault();
    //        }
    //    });

    $(document).ready(function () {
        $("#imageselect").change(function () {
            $('#custom-bg-user').css("background-image", "url('../../assets/img/background/bg-0" + imageValue + ".jpg')");
            console.log(document.getElementById('custom-bg-user'));
        });
    });

</script>
</body>
</html>