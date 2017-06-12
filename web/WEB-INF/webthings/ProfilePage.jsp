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
    <title>Profile Page</title>

    <%@include file="../../component/Header(styling Template).html" %>

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
                                                <c:when test='${profileInfo.profilepic.startsWith("dEfAuLt")}'>
                                                    <img src="defaultImg/${profileInfo.profilepic}"
                                                         alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:when>

                                                <%--Otherwise get the photo from the users photo page--%>

                                                <c:otherwise>
                                                    <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                         alt="Circle Image" class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>

                                        </c:when>

                                        <c:otherwise>
                                            <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                 class="img-rounded img-responsive img-raised">
                                        </c:otherwise>
                                    </c:choose>
                                    <!-- loading the friendlist from the session, which allow the user to veiw is that person a friend of the user and display the button of friend and unfriend depending-->
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
                                            //Check are u the user or the other people's profile.
                                            if (friendsprofile) {
                                                out.println("<button id=\"addfriend\">Add</button>");
                                                out.println("<button id=\"unfriend\">Unfriend</button>");
                                            }
                                        }
                                    %>
                                </div>
                                <div class="name" id="custom-profile-name">
                                    <h3 class="title">Hello ${profileInfo.name}</h3>
                                </div>
                                <div class="name" id="custom-profile-subtitle">
                                    <h6>${profileInfo.education}</h6>
                                </div>
                            </div>
                        </div>

                        <!-- Profile bio text -->
                        <div class="description text-center col-lg-offset-5 col-md-offset-5 col-sm-offset-5">

                            <%--Introduction "blurb'--%>
                            <div>
                                ${profileInfo.introduction}
                            </div>

                            <table class="table borderless" align="center">
                                <tr>
                                    <th>Specs</th>
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

                            <% if (!friendsprofile) { %>

                            <c:if test="${firendlist != null}">
                                <table class="table table-striped table-hover table-responsive">
                                    <tr>
                                        <th>Friend List</th>
                                    </tr>

                                    <c:forEach var="friend" items="${firendlist}">
                                        <c:if test="${friend.friendusername != null}">
                                            <tr>
                                                <td><a href=ProfilePage?accessFriend=${friend.friendusername}
                                                       class="username">${friend.friendusername}</a></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </c:if>

                            <% }
                            %>

                            <button id="showArticleList">Show article list</button>

                            <div id="ArticleTable"></div>

                            <table class="table table-striped table-hover table-responsive">
                            </table>
                            <%--end testing--%>

                            <script>
                                // An ajax call of post to get the article list which is then append and display within the given table
                                var clickStatus = true;

                                $("#showArticleList").click(function () {


                                    if (clickStatus) {
                                        $.ajax({
                                            url: '/ProfilePage',
                                            type: 'Post',
                                            data: {
                                                "clickedShowList": "clickedShowList",
                                                "username": "<%= session.getAttribute("username")%>"
                                            },
                                            success: function (msg) {
                                                console.log(msg);

                                                $("#ArticleTable").html(msg);

                                            }
                                        });
                                        //Toggle between the hide of the list and the showing of the list based on the button
                                        $("#showArticleList").html("Hide article list");
                                        clickStatus = !clickStatus;
                                    } else {
                                        $("#ArticleTable").html("");
                                        $("#showArticleList").html("Show article list");
                                        clickStatus = !clickStatus;
                                    }


                                });
                            </script>
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
