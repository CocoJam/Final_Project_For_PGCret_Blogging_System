<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="Template (HTML components)/Header(styling Template).html" %>

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
            <a class="navbar-brand" href=""><i class="material-icons">apps</i>
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

                <%--This script allows sending a POST method for GET--%>
                <script>
                    function DoPost(){
                        $.post("/login", {name: "Registration", value: "Registration"} );
                    }
                </script>

                <li>
                    <a href="login_page.jsp"><i class="material-icons">fingerprint</i>Sign In</a>
                </li>




            </ul>
            <!-- Nav bar right side icons ends -->

        </div>
    </div>
</nav>
<!-- !!! NAVIGATION BAR END !!! -->
