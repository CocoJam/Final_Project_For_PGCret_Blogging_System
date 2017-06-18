<%--
  Created by IntelliJ IDEA.
  User: ylin183
  Date: 18/06/2017
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<% if (username != null) { %>

<%--UPLOAD new profile photos--%>
<%--Upload modal trigger button--%>

<div class="col-xs-12 col-sm-12 col-md-12">
    <button type="button" class="btn btn-block btn-info btn-lg" data-toggle="modal"
            data-target="#uploadModal">Upload Avatar image
    </button>
</div>

<%--<div class="container">--%>
<!-- Trigger the modal with a button -->
<%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Profile Delete</button>--%>

<!-- Modal -->
<div class="modal fade" id="uploadModal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">

                <%--<h4 class="modal-title">Modal Header</h4>--%>
            </div>
            <div class="modal-body">
                <div class="card card-signup" id="uploadCard" style="margin: 0px">

                    <!-- FORM ELEMENT START -->
                    <form id="Upload" action="/Upload" method="post"
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
                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<%--</div>--%>

<!-- Delete profile end -->


<%--Upload modal ends--%>


<!-- Delete profile start -->

<div class="col-xs-12 col-sm-12 col-md-12">
    <button type="button" class="btn btn-block btn-danger btn-lg" data-toggle="modal"
            data-target="#myModal">Delete Profile
    </button>
</div>

<%--<div class="container">--%>
<!-- Trigger the modal with a button -->
<%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Profile Delete</button>--%>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">

                <%--<h4 class="modal-title">Modal Header</h4>--%>
            </div>



            <div class="modal-body">
                <div class="card card-signup" id="loginCard" style="margin: 0px">

                    <!-- FORM ELEMENT START -->
                    <form class="form" method="post" action="/Deleting" id="loginForm">

                        <!-- Form heading -->
                        <div class="header header-danger text-center">
                            <h4>Leaving Slash N</h4>
                        </div>

                        <!-- Form subtext -->
                        <span class="text-divider">
                                                        <p>We're sorry to see you go!</p>
                                                        <p>If you are sure about deleting your account, please reconfirm your username and password to continue.</p>
                                                        <p>You will not be able to recover your account once it's deleted.</p>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<%--</div>--%>

<!-- Delete profile end -->

<% } %>



</body>
</html>
