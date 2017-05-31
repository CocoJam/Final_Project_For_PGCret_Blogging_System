<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile Page -> ArticleIndex</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Favicons -->
    <link rel="apple-touch-icon" sizes="76x76" href="../../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../../assets/img/favicon.png">
    <!-- Do not edit -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- Responsive settings -->
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <!-- Fonts and icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
    <!-- Google material design icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"/>
    <!-- Google Roboto font -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>
    <!-- Font awesome icon library -->
    <!-- CSS Files -->
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../assets/css/material-kit.css" rel="stylesheet"/>
    <link href="../../assets/css/style.css" rel="stylesheet"/><!-- Additional Custom CSS -->

</head>
<body class="profile-page">

<%--NAVBAR STARTS--%>

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
            <a class="navbar-brand" href="#"><i class="material-icons">apps</i>
                Slash N</a>
        </div>

        <!-- Navbar elements which display on right -->
        <!-- Container for elements to hide on mobile -->
        <div class="collapse navbar-collapse" id="expand-navbar-icons">

            <!-- TODO need to use JSTL to dynamically change the icons on the right side on the nav bar depending on login status -->

            <!-- Nav bar right side icons -->
            <!-- Use on all other pages except login/register -->
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#"><i class="material-icons">fullscreen</i>Articles</a>
                </li>
                <li>
                    <a href="#"><i class="material-icons">face</i>Profile</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="material-icons">settings</i>Options
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-header">Options</li>
                        <li><a href="#">My Articles</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Edit Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Sign Out</a></li>
                    </ul>
                </li>
            </ul>
            <!-- Nav bar right side icons ends -->

            <!-- Register/Login page right side display. Use this one only on login/register pages. Use the other right menu icons display for when logged in -->
            <ul class="nav navbar-nav navbar-right">
                <!-- Should dynamically display the title of the current page -->
                <!-- If on login screen, should show and link to Register -->
                <li>
                    <a href="#" target="_self">
                        <!--<i class="material-icons">apps</i>-->
                        Register
                    </a>
                </li>
            </ul>

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-user"></div><!-- background div -->

    <div class="container">
        <div class="row">

            <div class="main main-raised"><!-- main container start -->

                <div class="profile-content">
                    <div class="container">

                        <!-- First profile row -->
                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <img src="" alt="Circle Image" class="img-rounded img-responsive img-raised">
                                </div>
                                <div class="name" id="custom-profile-name">
                                    <h3 class="title">${profileInfo.name}</h3>
                                </div>
                                <div class="name" id="custom-profile-subtitle">
                                    <h6>${profileInfo.education}</h6>
                                </div>
                            </div>
                        </div>

                        <!-- Profile bio text -->
                        <div class="description text-center">

                            <p>asdfsfsdfsdfsdfsdfsdfsd</p>

                        </div>

                        <!-- Second profile row -->
                        <div class="row">
                            <!--<div class="col-md-6 col-md-offset-3">-->
                            <div class="profile-tabs">
                                <!-- MORE CONTENT GOES HERE IF NEEDED -->
                            </div>
                            <!--</div>-->
                            <!--End Profile Tabs-->
                        </div>
                    </div>

                </div>
            </div><!-- main container end -->

        </div><!-- outer div 2 -->
    </div><!-- outer div 1 -->
</div><!-- wrapper div -->

<footer class="footer">
    <div class="container">
        <nav class="pull-left">
            <ul>
                <li>
                    <a href="#" onclick="topFunction()" id="myBtn" title="Back to top">
                        Back to top
                    </a>
                </li>
            </ul>
        </nav>
        <div class="copyright pull-right">
            <a href="#" target="_self">Team Slash N</a> &copy; 2016
        </div>
    </div>
</footer>

</body>

<!--   Core JS Files   -->
<script src="../../assets/js/jquery.min.js" type="text/javascript"></script>
<script src="../../assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../assets/js/material.min.js"></script>

<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="../../assets/js/nouislider.min.js" type="text/javascript"></script>

<!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
<script src="../../assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
<script src="../../assets/js/material-kit.js" type="text/javascript"></script>

</html>
