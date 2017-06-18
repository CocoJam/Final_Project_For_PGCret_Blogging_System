<%@ page import="sun.java2d.cmm.Profile" %>
<%@ page import="ProfilePage.ProfilePAge" %>
<%@ page import="Friend.Friend" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <title>Slash N - Profile Page</title>

    <%@include file="../../component/Header(styling Template).html" %>

    <style>
        /* borderless table */
        .table.table-borderless td, .table.table-borderless th {
            border: 0 !important;
        }
        .table.table-borderless {
            margin-bottom: 0;
        }
    </style>

</head>
<body class="profile-page">

<%--NAVBAR STARTS--%>
<!-- !!! NAVIGATION BAR START !!! -->

<%@ include file="../../component/NavBar-AfterLogin(Template).jsp" %>

<!-- !!! NAVIGATION BAR END !!! -->


<%-- Switching the profilepage between the user and the friends one that they are veiwing using the session and swtich the session attribute withe the showFriend--%>
<% ProfilePAge currentuser = null;
    boolean friendsprofile = false;
    if (session.getAttribute("showFriend") != null) {
        currentuser = (ProfilePAge) session.getAttribute("profileInfo");
        session.setAttribute("profileInfo", session.getAttribute("showFriend"));
        friendsprofile = true;
    }%>

<!-- !!! MAIN CONTENT START !!! -->
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-user"></div><!-- background div -->

    <div class="container">
        <div class="row">

            <div class="main custom-container-main"><!-- main container start -->

                <div class="profile-content">
                    <div class="container">

                        <!-- First profile row -->
                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <%--<img src="" alt="Circle Image" class="img-rounded img-responsive img-raised">--%>

                                    <c:choose>
                                        <c:when test="${profileInfo.profilepic != null}">

                                            <c:choose>
                                                <%--If this is a default profile image get the image from default photo directory--%>
                                                <c:when test='${profileInfo.profilepic.startsWith("defaultslashn")}'>
                                                    <img src="../../assets/img/defaultImg/${profileInfo.profilepic}"
                                                         alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:when>

                                                <%--Otherwise get the photo from the users photo page--%>

                                                <c:otherwise>
                                                    <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                         alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <img src="../../assets/img/defaultImg/placeholder.gif" alt="Circle Image"
                                                 class="img-rounded img-responsive img-raised">
                                        </c:otherwise>
                                    </c:choose>
                                    <!-- loading the friendlist from the session, which allow the user to veiw is that person a friend of the user and display the button of friend and unfriend depending-->
                                    <% boolean friended = false;
                                        List<Friend> friendList = null;
                                        if (session.getAttribute("firendlist") != null) {
                                            friendList = (ArrayList<Friend>) session.getAttribute("firendlist");

                                            for (Friend friend : friendList) {
                                                if (friend.getFriendusername().equals(((ProfilePAge) session.getAttribute("profileInfo")).getUsername())) {
                                                    friended = true;
                                                }
                                            }
                                        }
                                        if (session.getAttribute("showFriend") != null) {
                                            //Check whether you are the user or you are visiting other people's profile.
                                            if (friendsprofile) {
                                                out.println("<button class=\"btn btn-success btn-round\" id=\"addfriend\"><i class=\"material-icons\">add_circle</i>Add</button>");
                                                out.println("<button class=\"btn btn-danger btn-round\" id=\"unfriend\"><i class=\"material-icons\">remove_circle</i>Unfriend</button>");
                                            }
                                        }
                                    %>
                                </div>
                                <div class="name" id="custom-profile-name">
                                    <h3 class="title">
                                        <% if (!friendsprofile) { %>
                                        Welcome back
                                        <% } %>
                                        ${profileInfo.name}</h3>
                                    </h3>
                                </div>
                                <div class="name" id="custom-profile-subtitle">
                                    <h6>${profileInfo.education}</h6>
                                </div>
                            </div>
                        </div>

                        <%--Introduction "blurb'--%>
                        <div class="description text-center">

                            <c:choose>
                                <c:when test="${not empty profileInfo.introduction}">
                                    <h5>${profileInfo.introduction}</h5>
                                </c:when>
                                <c:otherwise>
                                    <p>Welcome to ${profileInfo.name}'s page! Feel free to look around and view the
                                        articles!</p>
                                </c:otherwise>
                            </c:choose>


                        </div>

                        <div class="container">
                            <div class="row">
                                <!-- Profile bio text -->
                                <div class="description text-center col-lg-offset-5 col-md-offset-5 col-sm-offset-5">

                                    <table class="table table-borderless">
                                        <tr>
                                            <th>Profile</th>
                                            <th>Your Details</th>
                                        </tr>
                                        <tr>
                                            <td>Username:</td>
                                            <td>${profileInfo.username}</td>
                                        </tr>
                                        <tr>
                                            <td>Fullname:</td>
                                            <td>${profileInfo.name}</td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td>${profileInfo.email}</td>
                                        </tr>
                                        <tr>
                                            <td>Address:</td>
                                            <td>${profileInfo.address}</td>
                                        </tr>
                                        <tr>
                                            <td>Education:</td>
                                            <td>${profileInfo.education}</td>
                                        </tr>
                                        <tr>
                                            <td>Ethnicity:</td>
                                            <td>${profileInfo.ethnicity}</td>
                                        </tr>
                                        <tr>
                                            <td>Date of Birth:</td>
                                            <td>
                                                <fmt:formatDate value="${profileInfo.date}" pattern="dd MMMM YYYY"/>
                                            </td>
                                        </tr>
                                    </table>
                                    <%--Profile details end--%>

                                    <%--Friends list section begins--%>
                                    <% if (!friendsprofile) { %>

                                    <hr>

                                    <h3>Friend List:</h3>

                                    <c:if test="${empty firendlist}">
                                        Aww... ${profileInfo.name} has no friends, time to make some friends ${profileInfo.name}!
                                    </c:if>

                                    <c:if test="${not empty firendlist}">


                                        <div class="card-group">

                                            <c:forEach var="friend" items="${firendlist}">
                                                <c:if test="${friend.friendusername != null}">

                                                    <div class="card" style="max-width: 15rem">
                                                            <%--<div class="imageContainer" style="width: 10rem">--%>
                                                        <c:choose>

                                                            <%--If this is a default profile image get the image from default photo directory--%>
                                                            <c:when test='${friend.friendProfilePicture.startsWith("defaultslashn")}'>
                                                                <a href="ProfilePage?accessFriend=${friend.friendusername}"
                                                                   class="friendButton"><img
                                                                        src="../../assets/img/defaultImg/${friend.friendProfilePicture}"
                                                                        alt="Card image cap"
                                                                        class="img-slashResponsive card-img-top center-block"></a>
                                                            </c:when>

                                                            <%--Otherwise get the photo from the users photo page--%>

                                                            <c:when test="${empty friend.friendProfilePicture}">
                                                                <img src="../../assets/img/defaultImg/placeholder.gif" alt="Circle Image"
                                                                     class="img-slashResponsive card-img-top center-block">
                                                            </c:when>

                                                            <c:otherwise>
                                                                <img src="Upload-photos/${friend.friendusername}/photo/${friend.friendProfilePicture}"
                                                                     alt="Card image cap"
                                                                     class="img-slashResponsive card-img-top center-block">
                                                            </c:otherwise>

                                                        </c:choose>
                                                            <%--</div>--%>
                                                        <div class="card-block">
                                                            <div style="height: 1em; line-height: 1em">
                                                                <h7
                                                                        class="card-title"
                                                                        style="text-overflow: ellipsis"><a
                                                                        href="ProfilePage?accessFriend=${friend.friendusername}"
                                                                        class="friendButton">${friend.friendusername}</a>
                                                                </h7>

                                                                <p class="card-text"></p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <%--</table>--%>


                                    </c:if>

                                    <% }

//                                    if (!friendsprofile) {
                                    %>

                                    <hr>

                                    <%--Articles begin--%>
                                    <div>

                                        <button id="showArticleList" class="btn btn-info btn-round">Show Articles List
                                            <i class='material-icons'>arrow_drop_down</i></button>

                                        <div id="ArticleTable"></div>

                                        <table class="table table-striped table-hover table-responsive">
                                        </table>
                                        <%--<% }%>--%>
                                        <%--end testing--%>

                                    </div>


                                    <script>
                                        // An ajax call of post to get the article list which is then append and display within the given table
                                        var clickStatus = true;

                                        <%--var articleUsername = "";--%>
                                        <%----%>
                                        <%--if (<%= friendsprofile%>){--%>
                                        <%--articleUsername = <%=fri%>--%>
                                        <%--}--%>


                                        $("#showArticleList").click(function () {

                                            if (clickStatus) {
                                                $.ajax({
                                                    url: '/ProfilePage',
                                                    type: 'Post',
                                                    data: {
                                                        "clickedShowList": "clickedShowList",
                                                        "username": "${profileInfo.username}"
                                                    },
                                                    success: function (msg) {
                                                        console.log(msg);

                                                        $("#ArticleTable").html(msg);

                                                    }
                                                });
                                                //Toggle between the hide of the list and the showing of the list based on the button
                                                $("#showArticleList").html("Hide article list <i class='material-icons'>arrow_drop_up</i>");
                                                clickStatus = !clickStatus;
                                            } else {
                                                $("#ArticleTable").html("");
                                                $("#showArticleList").html("Show article list <i class='material-icons'>arrow_drop_down</i>");
                                                clickStatus = !clickStatus;
                                            }


                                        });
                                    </script>
                                </div>

                            </div>

                        </div>

                    </div>
                    <%--table of friends for this person--%>
                </div><!-- main container end -->

            </div><!-- outer div 2 -->
        </div><!-- outer div 1 -->
    </div><!-- wrapper div -->

    <!-- FOOTER START -->
    <%@ include file="../../component/Footer(Template).html" %>
    <!-- FOOTER END -->


        <% //if The user is within their own profile this script will not appear, so the following function will  not be apply to the jsp.
    if (session.getAttribute("showFriend") != null) { %>
    <script>
        //The switching of the button of adding friend and unfriending the person depending the orginal state of is this person friended.

        <%--$(document).ready(function (){--%>
        <%--<% if (friended){--%>
        <%--%>--%>
        <%----%>
        <%----%>
        <%----%>
        <%--<% --%>
        <%--}--%>
        <%--%>--%>
        <%--})--%>

        <% if (!friended){ %>
        $("#addfriend").fadeIn("1", function () {
            $(this).css("z-index", 0)
        });
        $("#unfriend").fadeOut("1", function () {
            $(this).css("z-index", -1)
        });
        <% } else {%>
        $("#unfriend").fadeIn("1", function () {
            $(this).css("z-index", 0)
        });
        $("#addfriend").fadeOut("1", function () {
            $(this).css("z-index", -1)
        });
        <% } %>
        //Depending the button of the clicked, which speific ajax call of post will be sent, which allow the friend servlet to be adding the friend bi-directional to the database.
        $("#addfriend").click(function () {
            $("#unfriend").fadeIn("1", function () {
                $(this).css("z-index", 0)
            });
            $("#addfriend").fadeOut("1", function () {
                $(this).css("z-index", -1)
            });
            console.log("clicked")
            $.post("Friend", {
                username: "<%= currentuser.getUsername()%>",
                friendname: "${profileInfo.username}",
                friendprocess: "add"
            })
                .done(function () {
                    console.log("done adding");

                })
        });

        $("#unfriend").click(function () {
            $("#addfriend").fadeIn("1", function () {
                $(this).css("z-index", 0)
            });
            $("#unfriend").fadeOut("1", function () {
                $(this).css("z-index", -1)
            });
            $.post("Friend", {
                username: "<%= currentuser.getUsername()%>",
                friendname: "${profileInfo.username}",
                friendprocess: "unadd"
            })
                .done(function () {
                    console.log("done unadding");
                })
        });
    </script>
        <%
        session.setAttribute("profileInfo", currentuser);
        session.setAttribute("showFriend", null);
    }
%>
</body>

</html>
