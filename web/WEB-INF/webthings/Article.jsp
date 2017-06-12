<%@ page import="java.io.File" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 25/05/2017
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Article</title>

    <%@include file="../../component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<!-- !!! NAVIGATION BAR START !!! -->

<%@ include file="../../component/NavBar-AfterLogin(Template).jsp" %>

<!-- !!! NAVIGATION BAR END !!! -->

<!-- !!! MAIN CONTENT START !!! -->

<div class="wrapper">
    <div class="header header-filter" id="custom-bg-user"></div><!-- background div -->

    <div class="container">
        <div class="row">

            <div class="main custom-container-main"><!-- main container start -->

                <div class="profile-content">
                    <div class="container">

                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <%--<img src="" alt="Circle Image" class="img-rounded img-responsive img-raised">--%>

                                    <c:choose>
                                        <c:when test="${articleList.equals('self')}">
                                            <c:choose>
                                                <c:when test="${profileInfo.profilepic != null}">
                                                    <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                         alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">

                                                </c:when>

                                                <c:otherwise>
                                                    <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="name" id="custom-profile-name">
                                                <h3 class="title">${profileInfo.name}'s Articles</h3>
                                            </div>
                                        </c:when>

                                        <c:otherwise>
                                            <h3>${articleContents.username}</h3>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">

                                <h1>${articleContents.articlename}</h1>

                                <c:if test="${not empty articleContents.category}">
                                    <h3>Category is: ${articleContents.category}</h3>
                                </c:if>

                                <div>${articleContents.content}</div>
                                <p>${articleContents.username}</p>
                                <p>${articleContents.datecreated}</p>


                                <c:if test="${articleContents.owner}">
                                    <p>${articleContents.owner}</p>
                                    <form action="/Articles" method="post">
                                        <input type="submit" name="add" value="EditArticle">
                                    </form>
                                    <form action="/Deleting" method="post">
                                        <input type="submit" name="log" value="DeleteArticle">
                                    </form>
                                </c:if>
                                <div id="containComments">
                                    <c:forEach items="${commentlist}" var="content">
                                        <div id="${content.commentId}" class="commentid">
                                            <p id="${content.commentId}username" class="username">${content.username}</p>
                                            <p id="${content.commentId}content" class="content">${content.content}</p>
                                            <p id="${content.commentId}commentedTime" class="commentedTime">${content.commentedTime}</p>
                                            <c:if test="${content.owner || articleContents.owner}">
                                                <button id="${content.commentId}delete" onclick="deleteComment($(this))">Delete</button>
                                                <%--<script>--%>
                                                    <%--<!-- ajax post request for the deleting of the comment -->--%>
                                                    <%--$("#${content.commentId}delete").click(function () {--%>
                                                        <%--$.post("/Deleting", {--%>
                                                            <%--"commentId": "${content.commentId}",--%>
                                                            <%--"log": "DeleteComment"--%>
                                                        <%--})--%>
                                                            <%--.done(function (data) {--%>
                                                                <%--$("#${content.commentId}").remove();--%>
                                                                <%--$(this).remove();--%>
                                                                <%--console.log("hello");--%>
                                                            <%--});--%>
                                                    <%--});--%>
                                                <%--</script>--%>
                                            </c:if>
                                            <c:if test="${content.owner}">
                                                <input type="text" id="${content.commentId}text" class="change">
                                                <button id="${content.commentId}edit" onclick="editComment($(this))">Edit</button>
                                                <%--<script>--%>
                                                    <%--<!-- ajax post request for the editing of the comment and then changing the text within the associated position. -->--%>
                                                    <%--$("#${content.commentId}edit").click(function () {--%>
                                                        <%--$.ajax({--%>
                                                            <%--url: '/Comments',--%>
                                                            <%--type: 'Post',--%>
                                                            <%--data: {--%>
                                                                <%--"commentId": "${content.commentId}",--%>
                                                                <%--"commentcontent": $("#${content.commentId}text").val(),--%>
                                                                <%--"comments": "EditComment"--%>
                                                            <%--},--%>
                                                            <%--success: function (msg) {--%>
                                                                <%--console.log(msg);--%>
                                                                <%--var Data = JSON.parse(msg);--%>
                                                                <%--var p1 = $("#${content.commentId}username");--%>
                                                                <%--p1.html(Data.Username);--%>
                                                                <%--var p2 =$("#${content.commentId}content");--%>
                                                                <%--p2.html(Data.Content);--%>
                                                                <%--var p3 = $("#${content.commentId}commentedTime");--%>
                                                                <%--p3.html(Data.CommentedTime);--%>
                                                            <%--}--%>
                                                        <%--});--%>
                                                    <%--});--%>
                                                <%--</script>--%>
                                            </c:if>
                                        </div>

                                        <%--needed to display the delete button when load, so needed to go through the commentsServlet first--%>

                                    </c:forEach>

                                </div>
                                <label for="comments">Comments: </label>
                                <br>
                                <textarea rows="4" cols="50" id="comments" name="commentcontent"></textarea>
                                <button id="addComment">Add Comment</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER START -->
<%@ include file="../../component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
<script>
    //Adding comments by the ajax call with a post method, which allow the adding of the comment and then append
    $("#addComment").click(function () {
        $.ajax({
            url: '/Comments',
            type: 'Post',
            data: {
                "commentcontent": $("#comments").val(),
                "comments": "Add a Comment"
            },
            success: function (msg) {
                console.log("hello there comments");
                var Data = JSON.parse(msg);
                var contain = $("#containComments");
                var div = document.createElement("div");
                div.id = Data.CommentId;
                var p1 = document.createElement("p");
                p1.innerHTML = Data.Username;
                p1.id = Data.CommentId+"username";
                p1.className  = "username";
                var p2 = document.createElement("p");
                p2.innerHTML = Data.Content;
                p2.id = Data.CommentId+"content";
                p2.className  = "content";
                var p3 = document.createElement("p");
                p3.innerHTML = Data.CommentedTime;
                p3.id = Data.CommentId+"commentedTime";
                p3.className  = "commentedTime";
                var deletebutton = document.createElement("button");
                deletebutton.id = Data.CommentId+"delete";
                deletebutton.className  = "delete";
                deletebutton.innerHTML = "Delete";
                deletebutton.setAttribute("onclick","deleteComment($(this))");
                var editinput = document.createElement("input");
                editinput.type = "text";
                editinput.id = Data.CommentId+"text";
                editinput.className = "change";
                var editbutton = document.createElement("button");
                editbutton.id = Data.CommentId+"edit";
                editbutton.className  = "edit";
                editbutton.innerHTML = "Edit";
                editbutton.setAttribute("onclick","editComment($(this))");
                div.append(p1);
                div.append(p2);
                div.append(p3);
                div.append(deletebutton);
                div.append(editinput);
                div.append(editbutton);
                contain.append(div);
            }
        });
    })

    <!-- ajax post request for the deleting of the comment -->

    function deleteComment(e){
        $.post("/Deleting", {
            "commentId": e.parent().attr("id"),
            "log": "DeleteComment"
        })
            .done(function (data) {
                $(e.parent()).remove();
                console.log("hello");
            });

    }
    function editComment(e) {
        $.ajax({
            url: '/Comments',
            type: 'Post',
            data: {
                "commentId": e.parent().attr("id"),
                "commentcontent": e.parent().find(".change").val(),
                "comments": "EditComment"
            },
            success: function (msg) {
                console.log(msg);
                var Data = JSON.parse(msg);
                var p1 = e.parent().find(".username");
                p1.html(Data.Username);
                var p2 =e.parent().find(".content");
                p2.html(Data.Content);
                var p3 = e.parent().find(".commentedTime");
                p3.html(Data.CommentedTime);
            }
        });
    }

    <%--$(".delete").click(function () {--%>
        <%--console.log($(this).parent().id);--%>
        <%--console.log($(this).parent().attr("id"));--%>
        <%--$.post("/Deleting", {--%>
        <%--"commentId": "${content.commentId}",--%>
        <%--"log": "DeleteComment"--%>
        <%--})--%>
        <%--.done(function (data) {--%>
        <%--$("#${content.commentId}").remove();--%>
        <%--$(this).remove();--%>
        <%--console.log("hello");--%>
        <%--});--%>
    <%--});--%>

    <!-- ajax post request for the editing of the comment and then changing the text within the associated position. -->
    <%--$(".edit").click(function () {--%>
        <%--console.log($(this).siblings(".content").html());--%>
        <%--$.ajax({--%>
        <%--url: '/Comments',--%>
        <%--type: 'Post',--%>
        <%--data: {--%>
        <%--"commentId": "${content.commentId}",--%>
        <%--"commentcontent": $("#${content.commentId}text").val(),--%>
        <%--"comments": "EditComment"--%>
        <%--},--%>
        <%--success: function (msg) {--%>
        <%--console.log(msg);--%>
        <%--var Data = JSON.parse(msg);--%>
        <%--var p1 = $("#${content.commentId}username");--%>
        <%--p1.html(Data.Username);--%>
        <%--var p2 =$("#${content.commentId}content");--%>
        <%--p2.html(Data.Content);--%>
        <%--var p3 = $("#${content.commentId}commentedTime");--%>
        <%--p3.html(Data.CommentedTime);--%>
        <%--}--%>
        <%--});--%>
    <%--});--%>
    <!-- Due to the database and the create and the editing page is constructed, that replace all or most of the html tags into the <p> tags -->
    $(".wrapper li").each(function () {
        $(this).replaceWith(function () {
            return $('<p>', {
                html: this.innerHTML
            });
        });
    });
</script>
</html>
