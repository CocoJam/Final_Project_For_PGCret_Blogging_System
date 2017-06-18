<%--This is the navbar for the website when the user has not yet logged in. This is specifically different from the
logged in navbar as it hides the unaccessible parts away from users who have not yet signed up/logged in--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>

    <title>Navigation Bar(prior to login)</title>

    <%--The below header compoonent should ONLY be enabled when testing the component standalone and NOT when being used as a component as part of a page.
    NOTE. if you enable this the toggle menu function WILL NOT WORK--%>

    <%--<%@include file="Header(styling Template).html"%>--%>

</head>
<body>

<nav class="navbar navbar-fixed-top navbar-inverse navbar-transparent navbar-color-on-scroll">

    <div class="container">

        <div class="navbar-header">

            <%--Loader div: This div is to populate the loader --%>
            <div id='loaderID'></div>
            <%--Loader div ends--%>

            <%-- Navbar toggle which display when on mobile device --%>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#expand-navbar-icons">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%-- Navbar name of our website, displays on left --%>
            <a class="navbar-brand" href="login_page.jsp"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <%-- Navbar elements which display on right --%>
        <%-- Container for elements to hide on mobile --%>
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <%-- Nav bar right side icons --%>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="login?Registration=Register" target="_self">
                        <i class="material-icons">create</i>
                        Register
                    </a>
                </li>
                <li>
                    <a href="login_page.jsp"><i class="material-icons">fingerprint</i>Sign In</a>
                </li>
            </ul>
            <%-- Nav bar right side icons ends --%>
        </div>
    </div>
</nav>

</body>
</html>