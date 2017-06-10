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
        input {
            color: black !important;
        }
    </style>
</head>
<body>
<script>
    var ctx = "<%= request.getRequestURI() %>";
    var date = new Date();
    var currentTime = date.getTime();
    console.log(currentTime);
    ExpirationTime = currentTime + 1000*1800;
    console.log(ExpirationTime);
    date.setTime(ExpirationTime);
    document.cookie = "pagemark="+ctx+";expires="+date.toGMTString()+";path=/";
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
                        <li><a href="Articles?add=addNewArticle" class="clickOnce"><i class="material-icons">note_add</i>New
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
                        <li><a href="logout?submit=Logout" class="clickOnce">Sign Out</a></li>
                    </ul>
                </li>
                <%--Friend search bar here--%>
                <li>
                    <form id="NameBar" action="/ProfilePage" method="get">
                        <input list="usernames" name="accessFriend">
                        <datalist id="usernames">
                            <c:forEach items="${userlist}" var="names">
                            <option value="${names}">
                                </c:forEach>
                        </datalist>
                    </form>
                    <input id="imageselect" type="number" min="1" max="5">
                </li>
                <%--Friend search bar here--%>
            </ul>

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->
<script>
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

//    $('.clickOnce').click(function (e) {
//        e.preventDefault();
//    });

    //    $(document).ready(
    //        function clickOnce() {
    //            $(".clickOnce").on("click", function () {
    //                $(this).off('click');
    //            })
    //        }
    //    )
    $(document).ready(function () {
        $("#imageselect").change(function () {
            $('#custom-bg-user').css("background-image","url('../../assets/img/background/bg-0"+$("#imageselect").val()+".jpg')");
            console.log(document.getElementById('custom-bg-user'));
        });
    });

</script>
</body>
</html>