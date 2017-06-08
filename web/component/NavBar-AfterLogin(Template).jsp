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

<%
    List<String> nameList = new FriendDAO().GetAllPeopleUsername();
%>

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
            <a class="navbar-brand" href="ProfilePage"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <!-- Nav bar right side icons -->
            <!-- Use on all other pages except login/register -->
            <ul class="nav navbar-nav navbar-right">


                <li>
                    <a href="ProfilePage"><c:choose>
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
                        <li><a href="ArticlesIndex?articleList=self"><i class="material-icons">fullscreen</i>My Articles</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="ArticlesIndex?articleList=all"><i class="material-icons">fullscreen</i>All Articles</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Articles?add=addNewArticle"><i class="material-icons">create</i>New Article</a>
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
                        <li><a href="Upload?media=self"><i class="material-icons">fullscreen</i>My Media</a></li>
                        <li class="divider"></li>
                        <li><a href="Upload?media=all"><i class="material-icons">fullscreen</i>All Media</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="Registration?log=ChangeUserInformation"><i class="material-icons">create</i>Edit
                            Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="logout?submit=Logout">Sign Out</a></li>
                    </ul>
                </li>
                <%--Friend search bar here--%>
                <li>
                    <form id="searchBar" action="/ProfilePage" method="get">
                        <input list="usernames" name="accessFriend">
                        <datalist id="usernames">
                            <% for (String s : nameList) {
                                out.println("<option value=\"" + s + "\">");
                            }
                            %>
                        </datalist>
                    </form>
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
            return $("#searchBar").submit;
        }
    });
</script>
</body>
</html>