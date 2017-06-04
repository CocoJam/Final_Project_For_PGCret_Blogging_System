<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Navigation Bar Template</title>

    <%--HEADER TEMPLATE RETRIEVED FROM THE HTML FILE--%>

    <%@ include file="Template (HTML components)/Header(styling Template).html" %>

</head>
<body>

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
            <a class="navbar-brand" href="/ProfilePage"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">



            <!-- Nav bar right side icons -->
            <!-- Use on all other pages except login/register -->
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="/ProfilePage"><i class="material-icons">face</i>Profile</a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">fullscreen</i>Articles
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="/ArticlesIndex?articleList=self"><i class="material-icons">fullscreen</i>My Articles</a></li>
                        <li class="divider"></li>
                        <li><a href="/ArticlesIndex?articleList=all"><i class="material-icons">fullscreen</i>All Articles</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">photo_library</i>Media
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="Upload?media=self"><i class="material-icons">fullscreen</i>My Media</a></li>
                        <li class="divider"></li>
                        <li><a href="Upload?media=all"><i class="material-icons">fullscreen</i>All Media</a></li>
                    </ul>
                </li>

                <li>
                    <a href="/Registration?log=ChangeUserInformation">
                        <i class="material-icons">create</i>
                        Change User Info</a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="#">Edit Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout?submit=Logout">Sign Out</a></li>
                    </ul>
                </li>
            </ul>
            <!-- Nav bar right side icons ends -->

            <!-- Register/Login page right side display. Use this one only on login/register pages. Use the other right menu icons display for when logged in -->
            <!--<ul class="nav navbar-nav navbar-right">-->
                <!--&lt;!&ndash; Should dynamically display the title of the current page &ndash;&gt;-->
                <!--&lt;!&ndash; If on login screen, should show and link to Register &ndash;&gt;-->
                <!--<li>-->
                    <!--<a href="#" target="_self">-->
                        <!--&lt;!&ndash;<i class="material-icons">apps</i>&ndash;&gt;-->
                        <!--Register-->
                    <!--</a>-->
                <!--</li>-->
            <!--</ul>-->

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->

</body>

</html>