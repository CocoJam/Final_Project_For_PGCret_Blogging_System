
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page -> ProfilePage.Registration , Profile Page</title>

    <!-- Favicons -->
    <link rel="apple-touch-icon" sizes="76x76" href="../../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../../assets/img/favicon.png">
    <!-- Do not edit -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- Responsive settings -->
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <!-- Fonts and icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/><!-- Google material design icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"/><!-- Google Roboto font -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/><!-- Font awesome icon library -->
    <!-- CSS Files -->
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../assets/css/material-kit.css" rel="stylesheet"/>
    <link href="../../assets/css/style.css" rel="stylesheet"/><!-- Additional Custom CSS -->

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
            <a class="navbar-brand" href="#"><i class="material-icons">apps</i>
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
                    <a href="#" target="_self">
                        <!--<i class="material-icons">apps</i>-->
                        Register
                    </a>
                </li>
                <li>
                    <a href="#"><i class="material-icons">face</i>Sign In</a>
                </li>
                <!--<li class="dropdown">-->
                <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                <!--<i class="material-icons">settings</i>Options-->
                <!--<b class="caret"></b>-->
                <!--</a>-->
                <!--<ul class="dropdown-menu dropdown-menu-right">-->
                <!--<li class="dropdown-header">Options</li>-->
                <!--<li><a href="#">My Articles</a></li>-->
                <!--<li class="divider"></li>-->
                <!--<li><a href="#">Edit Profile</a></li>-->
                <!--<li class="divider"></li>-->
                <!--<li><a href="#">Sign In</a></li>-->
                <!--</ul>-->
                <!--</li>-->
            </ul>
            <!-- Nav bar right side icons ends -->

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->


<%--<%    String username = (String) session.getAttribute("username");--%>
    <%--if ( username != null){--%>
    <%--out.println("<p>"+ username + " has been Logged Out</p>");--%>
        <%--session.setAttribute("username",null);--%>
<%--}--%>
<%--%>--%>




<form action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="login" name="login">
</form>

<form action="/login" method="get">
    <input type="submit" value="Registration" name="Registration">
</form>





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
