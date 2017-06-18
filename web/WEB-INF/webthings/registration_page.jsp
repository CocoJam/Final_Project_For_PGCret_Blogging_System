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
    <title>
        <c:choose>
            <c:when test="${log}">
                Slash N - Edit Profile
            </c:when>
            <c:otherwise>
                Slash N - Register
            </c:otherwise>
        </c:choose>
    </title>

    <%@include file="/component/Header(styling Template).html" %>

    <style>
        /* MODAL Positioning z-index fixes */
        .header-filter .container > .modal, .modal-content .modal-header, .modal-content .modal-body, .modal-content .modal-footer, .modal-content .modal-footer button, .modal-content .modal-footer button.pull-left, .modal-content .modal-footer button + button, .modal-content .modal-body + .modal-footer, .modal .modal-dialog, .modal .modal-header .close, .modal .modal-header .close:hover, .modal .modal-header .close:focus {
            z-index: 9999;
        }

        /* MODAL BACKDROP */
        .modal-backdrop {
            z-index: 1;
        }

        /* Toggle footer z-index based on whether modal is open or not */
        /*#footer {*/
            /*-webkit-transition: width 2s ease;*/
            /*-moz-transition: width 2s ease;*/
            /*-o-transition: width 2s ease;*/
            /*transition: width 2s ease;*/
        /*}*/

        #footer.inactive {
            z-index: 0;
        }

        .material-scrolltop.inactive {
            z-index: -5;
        }

    </style>

</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<c:choose>
    <c:when test="${log}">
        <%@ include file="/component/NavBar-AfterLogin(Template).jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/component/NavBar-Login&Registration(Template).jsp" %>
    </c:otherwise>
</c:choose>

<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->


<%--This is to check if person has logged in and if so it will not goto the registration page, so this is another bouncing mechanism. This is a duplicate of the servlet bouncing mechanism.--%>
<%
    if (session.getAttribute("log") != null && session.getAttribute("Registration") != null) {
        if ((boolean) session.getAttribute("Registration")) {
            request.getRequestDispatcher("WEB-INF/webthings/ProfilePage.jsp").forward(request, response);
        }
    }
%>

<%--1st part: Grabbing all the strings of all the photo names and adding into a set. (NOTE the actual upload of the image is in the servlet)--%>
<% String userPath = request.getRealPath("Upload-photos");
    System.out.println(userPath + " Paths");
    String username = (String) session.getAttribute("username");
    File listofThings = new File(userPath + "/" + username + "/photo");
    System.out.println(listofThings.getPath());
    File[] files = listofThings.listFiles();
    Set<String> listofphotos = new TreeSet<>();
    if (files != null) {
        for (File file : files) {
            System.out.println(file.getPath());
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
                        <form class="form" action="Registration" id="form" method="post">

                            <!-- Form subtext -->
                            <!-- TODO need to use JSTL to dynamically change the header depending on login status -->
                            <div class="header header-info text-center">

                                <c:choose>
                                    <c:when test="${log}">
                                        <h4>Edit Profile</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4>Sign up with Slash N</h4>
                                    </c:otherwise>
                                </c:choose>

                                <!-- TODO change this text when user is logged in to: "Edit profile" -->
                            </div>

                            <c:choose>
                                <c:when test="${log}">
                                    <p class="text-divider">Please update your details below:</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="text-divider">Please enter your details to register:</p>
                                </c:otherwise>
                            </c:choose>

                            <div class="content">

                                <!-- TEXT BOXES -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">account_circle</i>
										</span>
                                    <input type="text" id="username" name="username" placeholder="Username"
                                           class="form-control"
                                           value="${profileInfo.username}" required>
                                </div>
                                <!-- Text input box end -->

                                <%--The following paragraph is to click to check when the username is available TODO STYLING is required!! (consider taking this outside of <p> and into styled <div>--%>
                                <p style="text-align: center">
                                    Username Availability: <span id="reponseToUsername"></span>
                                </p>

                                <%--The following is the script for username availability feature above--%>
                                <script>
                                    //    Sending AJAX call to /Registration servlet by GET method which goes to Database and matches the typed username and comes back with boolean which confirms availability.
                                    //TODO the boolean appears to be returning true all the time, check if right message being sent back by doGET method in Registration.java


                                    $("#username").change(function () {
                                        $.ajax({
                                            url: 'Registration',
                                            type: 'GET',
                                            data: {"log": "RegistrationCheck", "usernameCheck": $("#username").val()},
                                            success: function (msg) { //specifically msg is coming back with the boolean.

//                                                $(reponseToUsername).text(msg);
                                                //msg is returning a boolean for check if false meaning no such username so ok

                                                //If the msg is true, return 'The username already exists'
                                                if (msg == "true") {
                                                    $(reponseToUsername).text("The username already exist");
                                                    $("#reponseToUsername").css({"color": "red", "font-weight": "bold"})
                                                } else {
                                                    $(reponseToUsername).text("You can use the username!");
                                                    $("#reponseToUsername").css({
                                                        "color": "green",
                                                        "font-weight": "bold"
                                                    });
                                                }

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
                                           value="${password}" required/>
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
                                           value="${profileInfo.name}" required/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">email</i>
										</span>
                                    <input type="email" id="email" name="email" placeholder="Email"
                                           class="form-control"
                                           value="${profileInfo.email}" required/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">date_range</i>
										</span>
                                    <input type="text" id="date" name="date" placeholder="Date"
                                           class="form-control datepicker"
                                           value="${profileInfo.date}"/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">home</i>
										</span>
                                    <input type="text" id="address" name="address" placeholder="Address"
                                           class="form-control"
                                           value="${profileInfo.address}"/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">import_contacts</i>
										</span>
                                    <input type="text" id="education" name="education" placeholder="Education"
                                           class="form-control"
                                           value="${profileInfo.education}" required/>
                                </div>
                                <!-- Text input box end -->

                                <!-- Text input box start -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">language</i>
										</span>
                                    <input type="text" id="ethnicity" name="ethnicity" placeholder="Ethnicity"
                                           class="form-control"
                                           value="${profileInfo.ethnicity}"/>
                                </div>

                                <!-- Introduction about the user -->
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">chat</i>
										</span>
                                    <textarea form="form" id="Introduction" name="Introduction"
                                              placeholder="Let your classmates know a little bit about yourself!"
                                              class="form-control">${profileInfo.introduction}</textarea>
                                </div>

                                <%--Viewing list of users photos--%>
                                <%--2nd part: uses the above set to display the images in the set, by going through the list of photoname strings, and then accessing by pathing in the photo by the username folder--%>


                                <c:set var="photoname" value="${profileInfo.profilepic}"/>
                                <%
                                    String checkedOrNot = "";
                                    // Printing default photos
                                    for (int i = 1; i <= 3; i++) {
                                        checkedOrNot = "";
                                        String defaultPhoto = "defaultslashn" + i + ".png";
                                        if (defaultPhoto.equals((String) pageContext.getAttribute("photoname"))) {
                                            checkedOrNot = "checked";
                                        }
                                        out.println("<div class=\"radio\"> <label> <input type=\"radio\" name=\"profilePicture\" value=\"" + defaultPhoto + "\"" + checkedOrNot + "> </label> <img src=\"assets/img/defaultImg/" + defaultPhoto + "\" class='img-registrationResponsive'> </div> <br>");
                                    }
                                    if (username != null) {

//                                        Printing user photos
                                        for (String listofphoto : listofphotos) {
                                            System.out.println(listofphoto);
                                            System.out.println((String) pageContext.getAttribute("photoname"));

                                            if (listofphoto.equals((String) pageContext.getAttribute("photoname"))) {
                                                checkedOrNot = "checked";
                                            }

                                            out.println("<div class=\"radio\"> <label> <input type='radio' name='profilePicture' value='" + listofphoto + "'" + checkedOrNot + "> </label> <img src=\"Upload-photos/" + username + "/photo/" + listofphoto + "\" class='img-registrationResponsive'> </div> <br>");


                                        }
                                    }
                                %>
                                <!-- Text input box end -->

                            </div>

                            <!-- BUTTONS -->

                            <!-- TODO use JSTL to dynamically update the buttons based on login status -->
                            <div class="footer text-center">

                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <%--The following two scenarios are to differentiate between whether user is logged in or not, and displays different button functionality--%>
                                    <c:choose>
                                        <%--Scenario 1: when already logged in see ChangeUserInformation--%>
                                        <c:when test="${log}">
                                            <input type="submit" name="log" value="Update Profile"
                                                   class="btn btn-block btn-lg btn-success">
                                        </c:when>
                                        <%--Scenario 2: when NOT logged in then just see Registration--%>
                                        <c:otherwise>
                                            <input type="submit" name="log" value="Register"
                                                   class="btn btn-block btn-lg btn-success btn-lg">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </form>

                        <!-- BEGIN MODAL CODE -->

                        <% if (username != null) { %>

                        <!-- MODAL BUTTONS -->

                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <button type="button" class="btn btn-block btn-lg btn-info" data-toggle="modal"
                                    data-target="#uploadModal">
                                Upload Avatar Image
                            </button>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <button class="btn btn-block btn-lg btn-danger" data-toggle="modal"
                                    data-target="#deleteModal">
                                Delete Profile
                            </button>
                        </div>

                        <!-- MODALS -->

                        <!-- UPLOAD MODAL start -->
                        <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
                             aria-labelledby="uploadModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="btn btn-danger btn-simple close"
                                                data-dismiss="modal" aria-hidden="true"><i
                                                class="material-icons">clear</i> Close
                                        </button>
                                        <%--<h4 class="modal-title"></h4>--%>
                                    </div>

                                    <div class="modal-body">

                                        <!-- CONTENT -->

                                        <div class="card card-signup" id="uploadCard"
                                             style="margin-top: 1em; margin-left:0em; margin-right: 0em; margin-bottom: 0em;">

                                            <!-- FORM ELEMENT START -->
                                            <form id="Upload" action="Upload" method="post"
                                                  enctype="multipart/form-data">

                                                <!-- Form heading -->
                                                <div class="header header-info text-center">
                                                    <h4>Upload Avatar</h4>
                                                </div>

                                                <!-- Form subtext -->
                                                <span class="text-divider">
                                                        <p>Upload your own custom image to use as your avatar around the site.
                                                    </span>

                                                <div class="content">

                                                    <!-- Text input box start -->

                                                    <div class="input-group" align="center">
                                                        <input class="text-center" type="file" id="file" name="file"
                                                               placeholder="your file here">
                                                    </div>
                                                    <!-- Text input box end -->

                                                </div>
                                                <%--submit button for loading images--%>
                                                <div class="footer text-center">
                                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                                        <input type="submit" name="Upload" value="Upload Image"
                                                               class="btn btn-block btn-info btn-lg"/>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <%--<button type="button" class="btn btn-default btn-simple">Nice Button</button>--%>
                                        <%--<button type="button" class="btn btn-danger btn-simple" data-dismiss="modal" >Close</button>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- UPLOAD MODAL end -->

                        <!-- DELETE MODAL start -->
                        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                             aria-labelledby="deleteModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="btn btn-danger btn-simple close"
                                                data-dismiss="modal" aria-hidden="true"><i
                                                class="material-icons">clear</i> Close
                                        </button>
                                        <%--<h4 class="modal-title"></h4>--%>
                                    </div>

                                    <div class="modal-body">

                                        <!-- CONTENT -->

                                        <div class="card card-signup" id="deleteCard"
                                             style="margin-top: 1em; margin-left:0em; margin-right: 0em; margin-bottom: 0em;">

                                            <!-- FORM ELEMENT START -->
                                            <form class="form" method="post" action="Deleting" id="loginForm">

                                                <!-- Form heading -->
                                                <div class="header header-danger text-center">
                                                    <h4>Leaving Slash N</h4>
                                                </div>

                                                <!-- Form subtext -->
                                                <span class="text-divider">
                                                        <p>We're sorry to see you go!</p>
                                                        <p>If you are sure about deleting your account, please reconfirm your username and password to continue. You will not be able to recover your account once it's deleted.</p>
                                                    </span>

                                                <div class="content">

                                                    <!-- TEXT BOXES -->

                                                    <!-- Text input box start -->
                                                    <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">account_circle</i>
										</span>
                                                        <input name="username" type="text" class="form-control"
                                                               placeholder="Username">
                                                    </div>
                                                    <!-- Text input box end -->

                                                    <!-- Text input box start -->
                                                    <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                                        <input name="password" type="password" placeholder="Password"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <%--This button is to submit the above form to sign in--%>
                                                <div class="footer text-center" class="form">
                                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                                        <input type="submit" value="Deleting Profile" name="log"
                                                               class="btn btn-block btn-danger btn-lg clickOnce">
                                                    </div>

                                                </div>
                                            </form>

                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <%--<button type="button" class="btn btn-default btn-simple">Nice Button</button>--%>
                                        <%--<button type="button" class="btn btn-danger btn-simple" data-dismiss="modal" >Close</button>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- DELETE MODAL end -->

                        <% } %>

                        <!-- BEGIN MODAL CODE -->

                        <%--Short script which ensures that no empty space is entered--%>
                        <script type="text/javascript">
                            <%--SCRIPT onclick on FORM above. Checks if form contains any space or empty, if so then submission will be --%>
                            $("#form").submit(function (event) {
                                if ($("#username").val().trim().length == 0 || $("#password").val().trim().length == 0 || $("#Name").val().trim().length == 0 || $("#education").val().trim().length == 0 || $("#email").val().trim().length == 0) {
                                    alert("validation failed false");
                                    event.preventDefault();
                                    return;
                                }
                                alert("validations passed");
                                return;
                            });
                            <!-- Password Strength test based on the regex of the combination of the password val, as shown the Strong consist of at least 8 char and in combination of one of each kind. While the middle is 2 of each and the weakMiddle is 4 of each kind to match the given password strength. -->
                            var passwordStrengthStrong = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/i;
                            var passwordStrengthMiddle = /(?=.*[a-z]{2,})(?=.*[A-Z]{2,})(?=.*[0-9]{2,})(?=.*[!@#\$%\^&\*]{2,})(?=.{8,})/i;
                            var passwordStrengthWeakMiddle = /(?=.*[a-z]{4,})(?=.*[A-Z]{4,})(?=.*[0-9]{4,})(?=.*[!@#\$%\^&\*]{4,})(?=.{8,})/i;
                            <!-- Using the bind of the input that to check the password val and changes the text within the response text to display to user the strength-->
                            $("#password").bind('input', function () {
                                if ($("#password").val().match(passwordStrengthStrong)) {
                                    $("#reponseToPassword").text("Strong");
                                    return
                                }
                                if ($("#password").val().match(passwordStrengthMiddle)) {
                                    $("#reponseToPassword").text("Middle");
                                    return
                                }
                                if ($("#password").val().match(passwordStrengthWeakMiddle)) {
                                    $("#reponseToPassword").text("WeakMiddle");
                                    return
                                }
                                <!-- Simplely using the length to test strength -->
                                if ($("#password").val().length < 8 && $("#password").val().length > 0) {
                                    $("#reponseToPassword").text("Weak");
                                    return
                                }
                                if ($("#password").val().length > 8) {
                                    $("#reponseToPassword").text("Ok");
                                    return
                                }
                                <!-- When nothing is there tell user there isnt anything -->
                                if ($("#password").val().length == 0) {
                                    $("#reponseToPassword").text("Please enter a password");
                                    return
                                }
                            });

                            <!-- Upload post ajax for  media, but this is confined into only the images only, which then append the image with the assosicated ratio button and br for selection.-->
                            $('#Upload')
                                .submit(function (e) {
                                    $.ajax({
                                        url: 'Upload',
                                        type: 'POST',
                                        data: new FormData(this),
                                        processData: false,
                                        contentType: false,
                                        success: function (msg) {
                                            console.log(msg)
                                            if (msg.endsWith(".jpg") || msg.endsWith(".png") || msg.endsWith(".gif") || msg.endsWith(".jpeg") || msg.endsWith(".svg")) {
                                                var ratioButton = "<div class=\"radio\"> <label>" + "<input type=\"radio\" name= \"profilePicture\" value=\"" + msg.replace("Upload-photos/${username}/photo/", "") + "\">" + "<span class=\"circle\"></span>" + "<span class=\"check\"></span>" + "</label>" + "<img src=\"" + msg + "\"height=\"20%\">" + "</div>" + "</br>";
                                                $(".content").eq(0).append(ratioButton);
                                            }
                                            <!-- if the media is successfully uploaded but it is not a picture or photo in the right formate, that the alert will pop and show -->
                                            else {

                                                alert(msg.replace("Upload-photos\\${username}\\photo\\", "") + " failed to upload due to format not supplied");
                                            }
                                        },
                                        <!-- Error when the error such as the file is not in any right formate or such that the size of the file is too big, then the this will alert the user. -->
                                        error: function (request, status, error) {

                                            alert("Upload File Is Too Big.");
                                        }
                                    });
                                    e.preventDefault();
                                });

                        </script>

                        <%--Registration Form ends--%>

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
    /* Toggle footer z-index when modal is launched */
    $("[id$=Modal]").on('show.bs.modal', function (e) {
        $("#footer").toggleClass('inactive');
        $(".material-scrolltop").toggleClass('inactive');
    });
    $("[id$=Modal]").on('hidden.bs.modal', function (e) {
        $("#footer").toggleClass('inactive');
        $(".material-scrolltop").toggleClass('inactive');
    });
</script>

</html>
