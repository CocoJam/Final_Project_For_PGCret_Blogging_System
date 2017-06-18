<%@ page import="sun.java2d.cmm.Profile" %>
<%@ page import="ProfilePage.ProfilePAge" %>
<%@ page import="Friend.Friend" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 2:07 PM
--%>
<%--Introduction: This is the JSP page which displays profile pages of users. This page has been purposefully reused
with the assistance of JSTL logic gates to allow different case scenarios:
1. User's own profile page
2. Another user's profile page--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <title>Slash N - Profile Page</title>
    <%@include file="/component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<%--Navbar starts--%>
<%@ include file="/component/NavBar-AfterLogin(Template).jsp" %>
<%--Navbar ends--%>

<%-- Retrieving Attribute from Servlet: Receiving attribute as to whether the current page is the user's own or friend's profile page by using session and switching where required.
 Switching the profilepage between the user and the friends one that they are veiwing using the session and swtich the session attribute withe the showFriend--%>
<% ProfilePAge currentuser = null;
    boolean friendsprofile = false;
    if (session.getAttribute("showFriend") != null) {
        currentuser = (ProfilePAge) session.getAttribute("profileInfo");
        session.setAttribute("profileInfo", session.getAttribute("showFriend"));
        friendsprofile = true;
    }%>

<!-- !!! MAIN CONTENT START !!! -->

<%--The main content of the profilepage--%>
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
                                                    <img src="assets/img/defaultImg/${profileInfo.profilepic}"
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
                                            <img src="assets/img/defaultImg/placeholder.gif" alt="Circle Image"
                                                 class="img-rounded img-responsive img-raised">
                                        </c:otherwise>
                                    </c:choose>

                                    <%--Loading the friendlist from the session, which allows the user to view whether the person is a friend of the user. If so displays the button of friend and unfriend depending--%>
                                    <% boolean friended = false;
                                        List<Friend> friendList = null;
                                        if (session.getAttribute("firendlist") != null) {
                                            System.out.println("yes this is friend");
                                            friendList = (ArrayList<Friend>) session.getAttribute("firendlist");

                                            for (Friend friend : friendList) {
                                                if (friend.getFriendusername().equals(((ProfilePAge) session.getAttribute("profileInfo")).getUsername())) {
                                                    System.out.println("yes it is one of your friend");
                                                    friended = true;
                                                }
                                            }
                                        }
                                        if (session.getAttribute("showFriend") != null) {
                                            //Button responsive depending on whether the profile viewed is a friend or not.
                                            if (friendsprofile) {
                                                out.println("<button class=\"btn btn-success btn-round\" id=\"addfriend\"><i class=\"material-icons\">add_circle</i>Add</button>");
                                                out.println("<button class=\"btn btn-danger btn-round\" id=\"unfriend\"><i class=\"material-icons\">remove_circle</i>Unfriend</button>");
                                            }
                                        }
                                    %>
                                </div>

                                <%--Displays a welcome message to the user if this is the users profile page--%>
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

                        <%--Introduction content where the user has written a little about themselves--%>
                        <div class="description text-center">
                            <c:choose>
                                <c:when test="${not empty profileInfo.introduction}">
                                    <p>${profileInfo.introduction}</p>
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

                                    <%--Table specifying out the profile details--%>
                                    <table class="table borderless" align="center">
                                        <tr>
                                            <th>Profile</th>
                                            <th>Your details</th>
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

                                    <%--If this is the user's own profile Friends list section begins:
                                    Simple logic gates have been implemented to make this section more interactive
                                    1. If the user has no friends, displays a simple message encouraging them to make
                                    more friends.
                                    2. If the friendlist contains at least one friend to display the list of friends
                                    in cards with the profile photo of the friend.

                                    --%>
                                    <% if (!friendsprofile) { %>
                                    <hr>
                                    <h3>Friend List:</h3>

                                    <c:if test="${empty friendlist}">
                                        Aww... ${profileInfo.name} has no friends, time to make some friends ${profileInfo.name} feel free to find new friends on the searchbar!
                                    </c:if>

                                    <c:if test="${not empty firendlist}">
                                        <div class="card-group">
                                            <c:forEach var="friend" items="${firendlist}">
                                                <c:if test="${friend.friendusername != null}">

                                                    <div class="card" style="max-width: 15rem">
                                                        <c:choose>
                                                            <%--A. If this is a default profile image get the image from default photo directory--%>
                                                            <c:when test='${friend.friendProfilePicture.startsWith("defaultslashn")}'>
                                                                <a href="ProfilePage?accessFriend=${friend.friendusername}"
                                                                   class="friendButton"><img
                                                                        src="assets/img/defaultImg/${friend.friendProfilePicture}"
                                                                        alt="Card image cap"
                                                                        class="img-slashResponsive card-img-top center-block"></a>
                                                            </c:when>
                                                            <%--B. Otherwise get the photo from the users photo page--%>
                                                            <c:when test="${empty friend.friendProfilePicture}">
                                                                <img src="assets/img/defaultImg/placeholder.gif"
                                                                     alt="Circle Image"
                                                                     class="img-slashResponsive card-img-top center-block">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="Upload-photos/${friend.friendusername}/photo/${friend.friendProfilePicture}"
                                                                     alt="Card image cap"
                                                                     class="img-slashResponsive card-img-top center-block">
                                                            </c:otherwise>
                                                        </c:choose>
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
                                    </c:if>

                                    <% } %>

                                    <hr>
                                    <%--Article list dropdown option below--%>
                                    <div>
                                        <button id="showArticleList" class="btn btn-info btn-round">Show Articles List
                                            <i class='material-icons'>arrow_drop_down</i></button>

                                        <div id="ArticleTable"></div>

                                        <table class="table table-striped table-hover table-responsive">
                                        </table>
                                    </div>

                                    <script>
                                        // Script: An ajax call of post to get the article list which is then append
                                        // and display within the given table.
                                        var clickStatus = true;

                                        $("#showArticleList").click(function () {
                                            if (clickStatus) {
                                                $.ajax({
                                                    url: 'ProfilePage',
                                                    type: 'Post',
                                                    data: {
                                                        "clickedShowList": "clickedShowList",
                                                        "username": "${profileInfo.username}"
                                                    },
                                                    success: function (msg) {


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

                </div><!-- main container end -->

            </div>
        </div>
    </div>

    <%--Footer starts--%>
    <%@ include file="/component/Footer(Template).html" %>
    <%--Footer ends--%>

        <% //if The user is within their own profile this script will not appear, so the following function will  not be apply to the jsp.
    if (session.getAttribute("showFriend") != null) { %>
    <script>
        //The switching of the button of adding friend and unfriending the person depending the orginal state of is this person friended.

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

            $.post("Friend", {
                username: "<%= currentuser.getUsername()%>",
                friendname: "${profileInfo.username}",
                friendprocess: "add"
            })
                .done(function () {
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
