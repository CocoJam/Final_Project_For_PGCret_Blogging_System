<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login Page</title>

    <%@ include file="component/Header(styling Template).html" %>
<style>
    .modal-backdrop {
        z-index: -1;
    }
</style>
</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<%@ include file="/component/NavBar-Login&Registration(Template).jsp" %>
<!-- !!! NAVIGATION BAR END !!! -->


<!-- !!! MAIN CONTENT START !!! -->


<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container"><!-- background div -->

        <div class="container">
            <div class="row">

                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="card card-signup" id="loginCard">

                        <!-- FORM ELEMENT START -->
                        <form class="form" method="post" action="login" id="loginForm">

                            <!-- Form heading -->
                            <div class="header header-info text-center">
                                <h4>Welcome to Slash N</h4>
                            </div>

                            <!-- Form subtext -->
                            <p class="text-divider">Please login to continue</p>
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
                                           class="btn btn-block btn-success btn-lg clickOnce">
                                </div>

                            </div>
                        </form>
                        <div class="footer text-center">

                            Click <a href="login?Registration=Register" style="color: green" class="clickOnce">Register</a>
                            to to join our community!
                            <br><br>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- !!! MAIN CONTENT END !!! -->

        <!-- FOOTER START -->
        <%@ include file="/component/Footer(Template).html" %>
        <!-- FOOTER END -->

    </div><!-- background div end -->

</div><!-- Wrapper end -->

</body>

<script>

    $(document).ready(function () {

        if (${loginFail == true}){
                $("#loginCard").effect("shake");

        }
        document.cookie = "pagemark=login_page.jsp";
    })
</script>

</html>
