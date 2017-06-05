<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login Page</title>

    <%@ include file="component/Template (HTML components)/Header(styling Template).html" %>
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="-1" />
</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<%@ include file="/component/NavBar-Login&Registration(Template).jsp" %>
<!-- !!! NAVIGATION BAR END !!! -->


<!-- !!! MAIN CONTENT START !!! -->

<%--<%    String username = (String) session.getAttribute("username");--%>
<%--if ( username != null){--%>
<%--out.println("<p>"+ username + " has been Logged Out</p>");--%>
<%--session.setAttribute("username",null);--%>
<%--}--%>
<%--%>--%>


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
        <%@ include file="/component/Template (HTML components)/Footer(Template).html" %>
        <!-- FOOTER END -->

    </div><!-- background div end -->

</div><!-- Wrapper end -->

</body>

</html>
