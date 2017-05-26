<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="theme/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="theme/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Login</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>

    <!-- Fonts and icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
    <!-- Google material design icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"/>
    <!-- Google Roboto font -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>
    <!-- Font awesome icon library -->

    <!-- CSS Files -->
    <link href="theme/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="theme/assets/css/material-kit.css" rel="stylesheet"/>

    <!-- Additional Custom CSS -->
    <link href="theme/style.css" rel="stylesheet"/>

</head>

<!-- JAVA CODE -->
<% String username = (String) session.getAttribute("username");
    if (session.getAttribute("log") != null) {
        if ((boolean) session.getAttribute("log")) {
            request.getRequestDispatcher("/WEB-INF/webthings/content_page.jsp").forward(request, response);
        }
    }
    if (username != null) {
        out.println("<p>" + username + " has been Logged Out</p>");
        session.setAttribute("username", null);
    }
%>


<body class="signup-page">

<!-- Navigation Bar Start -->

<!-- Navbar with class to set color and position behaviour -->
<nav class="navbar navbar-info navbar-transparent navbar-fixed-top navbar-color-on-scroll">

    <!-- responsive container for div -->
    <div class="container">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <!-- Navbar toggle which display when on mobile device -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example">
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
        <div class="collapse navbar-collapse" id="navigation-example">
            <ul class="nav navbar-nav navbar-right">

                <!-- Should dynamically display the title of the current page -->
                <!-- If on login screen, should show and link to Register -->
                <li>
                    <a href="Registration" target="_self">
                        <!--<i class="material-icons">apps</i>-->
                        Register
                    </a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<!-- Navigation Bar End -->

<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="card card-signup">

                        <!-- FORM ELEMENT START -->
                        <form class="form" action="/login" method="post">
                            <div class="header header-info text-center">
                                <h4>Welcome to Slash N</h4>

                            </div>
                            <p class="text-divider">Please login or register to continue</p>
                            <div class="content">

                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">group</i>
										</span>
                                    <input id="username" name="username" type="text" class="form-control"
                                           placeholder="Username">
                                </div>

                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                    <input id="password" name="password" type="password" placeholder="Password"
                                           class="form-control"/>
                                </div>

                            </div>

                            <!-- Buttons start -->
                        <!-- LOGIN -->
                            <input type="submit" value="login" name="login"
                                                                           class="btn btn-success btn-lg btn-block col-xs-6 col-sm-6 col-md-6">
                        </form>

                        <!-- REGISTER -->
                            <form class="form" action="/login" method="get">

                                <input type="submit" value="Registration" name="Registration"
                                       class="btn btn-danger btn-lg btn-block col-xs-6 col-sm-6 col-md-6">
                            </form>
                        <!-- Buttons end -->

                    </div>
                </div>
            </div>
        </div>

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
                                Documentation
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                About
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
                    <a href="#" target="_self">Team Slash N</a> &copy; 2017
                </div>
            </div>
        </footer>

    </div>

</div>

</body>


<!--   Core JS Files   -->
<script src="theme/assets/js/jquery.min.js" type="text/javascript"></script>
<script src="theme/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="theme/assets/js/material.min.js"></script>

<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="theme/assets/js/nouislider.min.js" type="text/javascript"></script>

<!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
<script src="theme/assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
<script src="theme/assets/js/material-kit.js" type="text/javascript"></script>

</html>
