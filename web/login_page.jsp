<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>

    <!-- Favicons -->
    <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../assets/img/favicon.png">
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
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../assets/css/material-kit.css" rel="stylesheet"/>
    <link href="../assets/css/style.css" rel="stylesheet"/><!-- Additional Custom CSS -->

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

</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<!-- Navbar with class to set color and position behaviour -->
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
            </ul>
            <!-- Nav bar right side icons ends -->
        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container"><!-- background div -->

        <div class="container">
            <div class="row">

                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="card card-signup">

                        <!-- FORM ELEMENT START -->
                        <form class="form" method="post" action="/login">

                            <!-- Form heading -->
                            <div class="header header-info text-center">
                                <h4>Welcome to Slash N</h4>
                            </div>

                            <!-- Form subtext -->
                            <p class="text-divider">Please login or register to continue</p>
                            <div class="content">

                                <!-- TEXT BOXES -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">account_circle</i>
										</span>
                                    <input id="username" name="username" type="text" class="form-control"
                                           placeholder="Username">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                    <input id="password" name="password" type="password" placeholder="Password"
                                           class="form-control"/>
                                </div>
                            </div>

                            <!-- BUTTONS -->

                            <%--This button is to submit the above form to sign in--%>
                            <div class="footer text-center" class="form">
                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <input type="submit" value="login" name="login"
                                           class="btn btn-block btn-success btn-lg">
                                </div>

                                <%--<div class="col-xs-6 col-sm-6 col-md-6"><a href="/Registration"--%>
                                <%--class="btn btn-block btn-danger btn-lg">Register</a>--%>
                                <%--</div>--%>
                            </div>
                        </form>
                        <%--Notes for Nicole. This button is to goto the registration page, since this does not submit the details in the above form it is not possible to include as part of the above form as this will cause only one action. Thus a separate registration form is required. Please see corresponding POST and GET methods inside of the registration and login servlets--%>
                        <div class="footer text-center">
                            <form action="/login" method="get">
                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <input type="submit" value="Registration" name="Registration"
                                           class="btn btn-block btn-danger btn-lg">
                                </div>
                            </form>
                        </div>


                    </div>
                </div>
            </div>
        </div>
        <!-- !!! MAIN CONTENT END !!! -->

        <!-- FOOTER START -->
        <footer class="footer">
            <div class="container">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Profile
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                T & C
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="copyright pull-right">
                    <a href="#" target="_self">Team Slash N</a> &copy; 2016
                </div>
            </div>
        </footer>
        <!-- FOOTER END -->

    </div><!-- background div end -->

</div><!-- Wrapper end -->


<%--<%    String username = (String) session.getAttribute("username");--%>
<%--if ( username != null){--%>
<%--out.println("<p>"+ username + " has been Logged Out</p>");--%>
<%--session.setAttribute("username",null);--%>
<%--}--%>
<%--%>--%>


</body>

</html>
