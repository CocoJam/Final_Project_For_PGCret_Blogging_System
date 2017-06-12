<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>

    <title>Navigation Bar Template</title>

    <%--HEADER TEMPLATE RETRIEVED FROM THE HTML FILE--%>

    <%--The below header compoonent should ONLY be enabled when testing the component standalone and NOT when being used as a component as part of a page.
    NOTE. if you enable this the toggle menu function WILL NOT WORK--%>
    <%--<%@include file="Header(styling Template).html"%>--%>

</head>
<body>

<!-- Navbar with class to set color and position behaviour -->
<nav class="navbar navbar-info navbar-transparent navbar-fixed-top navbar-color-on-scroll">

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
            <a class="navbar-brand" href="login_page.jsp"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <!-- TODO need to use JSTL to dynamically change the icons on the right side on the nav bar depending on login status -->

            <!-- Register/Login page right side display. Use this one only on login/register pages. Use the other right menu icons display for when logged in -->

            <!-- Nav bar right side icons -->
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="/login?Registration=Registration" target="_self">
                        <i class="material-icons">create</i>
                        Register
                    </a>
                </li>

                <li>
                    <a href="login_page.jsp"><i class="material-icons">fingerprint</i>Sign In</a>
                </li>

            </ul>
            <!-- Nav bar right side icons ends -->

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->


</body>
</html>