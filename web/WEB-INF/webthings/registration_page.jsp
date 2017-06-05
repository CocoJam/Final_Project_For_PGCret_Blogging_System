<%@ page import="java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.jms.Session" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 23/05/2017
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login Page</title>

    <%@include file="../../component/Template (HTML components)/Header(styling Template).html" %>

</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<c:choose>
    <c:when test="${log}">
        <%@ include file="../../component/NavBar-AfterLogin(Template).jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="../../component/NavBar-Login&Registration(Template).jsp" %>
    </c:otherwise>
</c:choose>

<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->


<%! Set<String> listofphotos = new TreeSet<>(); %>

<%--This is to check if person has logged in and if so it will not goto the registration page, so this is another bouncing mechanism. This is a duplicate of the servlet bouncing mechanism.--%>
<%
    if (session.getAttribute("log") != null && session.getAttribute("Registration") != null) {
        if ((boolean) session.getAttribute("Registration")) {
            request.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(request, response);
        }
    }
%>

<%--1st part: Grabbing all the strings of all the photo names and adding into a set. (NOTE the actual upload of the image is in the servlet)--%>
<% String userPath = request.getRealPath("/Upload-photos");
    System.out.println(userPath + " Paths");
    String username = (String) session.getAttribute("username");
    File listofThings = new File(userPath + "/" + username + "/photo");
    System.out.println(listofThings.getPath());
    File[] files = listofThings.listFiles();
    if (files != null) {
        for (File file : files) {
            listofphotos.add(file.getName());
        }
    }
%>


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
                                <h4>Sign up with Slash N</h4>
                                <!-- TODO change this text when user is logged in to: "Edit profile" -->

                            </div>
                            <!-- TODO use JSTL to change this text dynamically based on login status -->
                            <p class="text-divider">Please enter your details to register</p>
                            <!-- TODO change this text when user is logged in to: "Please update your details" -->
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

                                <%--The following paragraph is to click to check when the username is available TODO STYLING is required!! (consider taking this outside of <p> and into styled <div>--%>
                                <p style="text-align: center">
                                    Username taken: <span id="reponseToUsername"></span>
                                </p>

                                <%--The following is the script for username availability feature above--%>
                                <script>
                                    //    Sending AJAX call to /Registration servlet by GET method which goes to Database and matches the typed username and comes back with boolean which confirms availability.
                                    //TODO the boolean appears to be returning true all the time, check if right message being sent back by doGET method in Registration.java

                                    console.log($("#username").val());
                                    $("#username").change(function () {
                                        $.ajax({
                                            url: '/Registration',
                                            type: 'GET',
                                            data: {"log": "RegistrationCheck", "usernameCheck": $("#username").val()},
                                            success: function (msg) { //specifically msg is coming back with the boolean.
                                                console.log(msg);
                                                $(reponseToUsername).text(msg);
                                                //msg is returning a boolean for check if false meaning no such username so ok
                                                if (msg) {
                                                    event.preventDefault();
                                                }
                                            }
                                        });
                                    })
                                </script>

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                    <input type="password" id="password" name="password" placeholder="Password"
                                           class="form-control"
                                           value="${password}"/>
                                </div>
                                <p style="text-align: center">
                                    Password Strength: <span id="reponseToPassword"></span>
                                </p>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">face</i>
										</span>
                                    <input type="text" id="Name" name="name" placeholder="Name"
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

                                <%--Viewing list of users photos--%>
                                <%--2nd part: uses the above set to display the images in the set, by going through the list of photoname strings, and then accessing by pathing in the photo by the username folder--%>
                                <% if (username != null) {
                                    for (String listofphoto : listofphotos) {
                                        System.out.println(listofphoto);
                                        out.println(" <input type=\"radio\" name=\"profilePicture\" value=\"" + listofphoto + "\"> <img  src=\"Upload-photos/" + username + "/photo/" + listofphoto + "\" height='20%'><br>");
                                    }
                                }
                                %>
                                <!-- Text input box end -->

                                <!--If you want to add a checkbox to this form, uncomment this code:-->
                                <%--Optional checkbox with terms and conditions--%>
                                <%--<div class="checkbox">--%>
                                <%--<label>--%>
                                <%--<input type="checkbox"  name="optionsCheckboxes" checked>--%>
                                <%--I agree with the terms & conditions--%>
                                <%--</label>--%>
                                <%--</div>--%>

                            </div>

                            <!-- BUTTONS -->

                            <!-- TODO use JSTL to dynamically update the buttons based on login status -->
                            <div class="footer text-center">

                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <%--The following two scenarios are to differentiate between whether user is logged in or not, and displays different button functionality--%>
                                    <c:choose>
                                        <%--Scenario 1: when already logged in see ChangeUserInformation--%>
                                        <c:when test="${log}">
                                            <input type="submit" name="log" value="ChangeUserInformation"
                                                   class="btn btn-block btn-danger btn-lg">
                                        </c:when>
                                        <%--Scenario 2: when NOT logged in then just see Registration--%>
                                        <c:otherwise>
                                            <input type="submit" name="log" value="Registration"
                                                   class="btn btn-block btn-success btn-lg">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </form>

                        <% if (username != null) { %>

                        <%--UPLOAD new profile photos--%>

                        <form action="/Upload" method="post"
                              enctype="multipart/form-data">
                            <!-- Text input box start -->
                            <div class="input-group">
                                <span class="input-group-addon">
                                <i class="material-icons">attachment</i>
                                </span>
                                <input type="file" id="file" name="file" placeholder="your file here" size="50">
                            </div>
                            <!-- Text input box end -->

                            <%--submit button for loading images--%>
                            <div class="footer text-center">
                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <input type="submit" name="Upload" value="ProfileUpload"
                                           class="btn btn-block btn-success btn-lg"/>
                                </div>
                            </div>
                        </form>
                        <% } %>

                        <%--Short script which ensures that no empty space is entered--%>
                        <script type="text/javascript">
                            <%--SCRIPT onclick on FORM above. Checks if form contains any space or empty, if so then submission will be --%>
                            $("#form").submit(function (event) {
                                if ($("#username").val().startsWith(" ") || $("#password").val().startsWith(" ") || $("#username").val() == "" || $("#password").val() == "") {
                                    alert("validation failed false");
                                    event.preventDefault();
                                    return;
                                }
                                alert("validations passed");
                                return;
                            });
                            var passwordStrengthStrong = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/i;
                            var passwordStrengthMiddle = /(?=.*[a-z]{2,})(?=.*[A-Z]{2,})(?=.*[0-9]{2,})(?=.*[!@#\$%\^&\*]{2,})(?=.{8,})/i;
                            var passwordStrengthWeakMiddle = /(?=.*[a-z]{4,})(?=.*[A-Z]{4,})(?=.*[0-9]{4,})(?=.*[!@#\$%\^&\*]{4,})(?=.{8,})/i;
                            $("#password").bind('input', function () {
                                if ($("#password").val().match(passwordStrengthStrong)) {
                                    $("#reponseToPassword").text("Strong");
                                    return console.log("strong")
                                }
                                if ($("#password").val().match(passwordStrengthMiddle)) {
                                    $("#reponseToPassword").text("Middle");
                                    return console.log("middle")
                                }
                                if ($("#password").val().match(passwordStrengthWeakMiddle)) {
                                    $("#reponseToPassword").text("WeakMiddle");
                                    return console.log("weakMiddle")
                                }
                                if ($("#password").val().length < 8 && $("#password").val().length >0) {
                                    $("#reponseToPassword").text("Weak");
                                    return console.log("weak")
                                }
                                if ($("#password").val().length > 8 ) {
                                    $("#reponseToPassword").text("Ok");
                                    return console.log("ok")
                                }
                                if ($("#password").val().length == 0) {
                                    $("#reponseToPassword").text("Please enter a password");
                                    return console.log("ok")
                                }
                            })

                        </script>

                        <%--Registration Form ends--%>

                    </div>
                </div>
            </div>
        </div>
        <!-- !!! MAIN CONTENT END !!! -->

        <!-- FOOTER START -->
        <%@ include file="../../component/Template (HTML components)/Footer(Template).html" %>
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
