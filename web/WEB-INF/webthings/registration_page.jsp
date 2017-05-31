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
                <div class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
                    <div class="card card-signup">

                        <!-- FORM ELEMENT START -->
                        <form class="form" action="/Registration" id="form" method="post">

                            <!-- Form subtext -->
                            <!-- TODO need to use JSTL to dynamically change the header depending on login status -->
                            <div class="header header-info text-center">
                                <h4>Sign up with Slash N</h4><!-- TODO change this text when user is logged in to: "Edit profile" -->

                            </div>
                            <!-- TODO use JSTL to change this text dynamically based on login status -->
                            <p class="text-divider">Please enter your details to register</p><!-- TODO change this text when user is logged in to: "Please update your details" -->
                            <div class="content">

                                <!-- TEXT BOXES -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">account_circle</i>
										</span>
                                    <input type="text" id="username" name="username" placeholder="Username"
                                           class="form-control"
                                           value="${profileInfo.username}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                    <input type="password" id="password" name="password" placeholder="Password"
                                           class="form-control"
                                           value="${password}"/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">face</i>
										</span>
                                    <input type="text" id="name" name="name" placeholder="Name"
                                           class="form-control"
                                           value="${profileInfo.name}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">email</i>
										</span>
                                    <input type="email" id="email" name="email" placeholder="Email"
                                           class="form-control"
                                           value="${profileInfo.email}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">date_range</i>
										</span>
                                    <input type="text" id="date" name="date" placeholder="Date"
                                           class="form-control datepicker"
                                           value="${profileInfo.date}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">home</i>
										</span>
                                    <input type="text" id="address" name="address" placeholder="Address"
                                           class="form-control"
                                           value="${profileInfo.address}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">import_contacts</i>
										</span>
                                    <input type="text" id="education" name="education" placeholder="Education"
                                           class="form-control"
                                           value="${profileInfo.education}">
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">language</i>
										</span>
                                    <input type="text" id="ethnicity" name="ethnicity" placeholder="Ethnicity"
                                           class="form-control"
                                           value="${profileInfo.ethnicity}">
                                </div>
                                <!-- Text input box end -->

                                <!--If you want to add a checkbox to this form, uncomment this code:-->
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="optionsCheckboxes" checked>
                                        I agree with the terms & conditions
                                    </label>
                                </div>

                            </div>

                            <!-- BUTTONS -->

                            <!-- TODO use JSTL to dynamically update the buttons based on login status -->
                            <div class="footer text-center">
                                <!-- TODO rename this button to something else. e.g. "Edit Profile" -->
                                <div class="col-xs-6 col-sm-6 col-md-6"><input type="submit" name="log" value="ChangeUserInformation"
                                                                               class="btn btn-block btn-successdanger btn-lg">
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6"><input type="submit" name="log" value="Registration"
                                                                               class="btn btn-block btn-danger btn-lg">
                                </div>
                            </div>
                            <!-- BUTTONS -->

                        </form>
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
