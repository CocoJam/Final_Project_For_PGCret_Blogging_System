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
    <title>Slash N - Article - ${articleContents.articlename} - by ${articleContents.username}</title>

    <%@include file="/component/Header(styling Template).html" %>

</head>
<body class="profile-page">

<!-- !!! NAVIGATION BAR START !!! -->

<%@ include file="/component/NavBar-AfterLogin(Template).jsp" %>

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
                                    <p hidden class="currentuser">${profileInfo.username}</p>
                                    <c:choose>
                                        <c:when test="${profileInfo.username == articleContents.username}">
                                            <c:choose>
                                                <c:when test="${profileInfo.profilepic != null}">

                                                    <c:choose>
                                                        <%--If this is a default profile image get the image from default photo directory--%>
                                                        <c:when test='${profileInfo.profilepic.startsWith("default")}'>
                                                            <img src="assets/img/defaultImg/${profileInfo.profilepic}"
                                                                 alt="Avatar"
                                                                 class="img-rounded img-responsive img-raised">
                                                        </c:when>

                                                        <%--Otherwise get the photo from the users photo page--%>
                                                        <c:otherwise>
                                                            <img src="Upload-photos/${profileInfo.username}/photo/${profileInfo.profilepic}"
                                                                 alt="Avatar"
                                                                 class="img-rounded img-responsive img-raised">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>

                                                <c:otherwise>
                                                    <img src="assets/img/defaultImg/placeholder.gif" alt="Avatar"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="name" id="custom-profile-name">
                                                <h3 class="title">${profileInfo.name}'s Article</h3>
                                            </div>
                                        </c:when>

                                        <c:otherwise>
                                            <h3 class="title"><strong>Written by: </strong><a
                                                    href="ProfilePage?accessFriend=${articleContents.username}">${articleContents.username}</a>
                                            </h3>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                            </div>
                        </div>

                        <div class="row">

                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col xs-offset-1">

                                <!-- Article Headings start -->
                                <div class="row text-center">
                                    <h1>${articleContents.articlename}</h1>
                                </div>

                                <div class="row text-center">
                                    <h4><strong>Written on:</strong> ${articleContents.datecreated}</h4>
                                    <c:if test="${not empty articleContents.category}">
                                        <h5><strong>Category:</strong> ${articleContents.category}</h5>
                                    </c:if>
                                </div>
                                <!-- Article Headings start -->

                                <!-- Like function -->
                                <div class="row text-center">
                                    <h1 id="likenumber">${articleContents.likeNumber}</h1>
                                    <%--<!-- DEBUG --><h1>LIKED ARTICLE? ${articleContents.liked}</h1>--%>

                                    <c:choose>
                                        <c:when test="${articleContents.liked}">
                                            <button class="btn btn-round btn-danger" onclick="likeadd($(this))"
                                                    id="${articleContents.articleid}"><i
                                                    class="material-icons">cancel</i> Unlike
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-round btn-success" onclick="likeadd($(this))"
                                                    id="${articleContents.articleid}"><i
                                                    class="material-icons">favorite</i> Like
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <!-- Like function -->

                                <!-- Edit / Delete article if user is owner -->
                                <div class="row text-center">
                                    <c:if test="${articleContents.owner}">
                                        <%--<!-- DEBUG --><h1>IS CURRENT USER THE OWNER? ${articleContents.owner}</h1>--%>
                                        <div class="btn-group btn-group-sm">
                                            <form action="Articles" method="post">
                                                <input type="hidden" name="articleidnumber"
                                                       value="${articleContents.articleid}">
                                                    <%--<input class="btn btn-round btn-info" type="submit" name="add" value="EditArticle">--%>
                                                <button class="btn btn-round btn-primary" type="submit" name="add"
                                                        value="EditArticle"><i class="material-icons">create</i> Edit
                                                    Article
                                                </button>
                                            </form>
                                            <form action="Deleting" method="post">
                                                    <%--<input class="btn btn-round btn-info" type="submit" name="log" value="DeleteArticle">--%>
                                                <button class="btn btn-round btn-danger" type="submit" name="log"
                                                        value="DeleteArticle"><i
                                                        class="material-icons">delete_forever</i> Delete Article
                                                </button>
                                            </form>
                                        </div>
                                    </c:if>
                                </div>
                                <!-- Edit / Delete section end -->

                                <!-- Article Content start -->
                                <div class="row">
                                    <div id="articleContents">${articleContents.content}</div>
                                </div>
                                <!-- Article Content end -->


                                <!-- COMMENTS SECTION start -->

                                <h2>Comments</h2>

                                <div class="row col-lg-12 col-lg-offset-1 col-md-12 col-md-offset-1 col-sm-12 col-sm-offset-1 col-xs-12 col xs-offset-1"
                                     id="comments-section" style="margin-left: 1em;">

                                    <h4 style="margin-left: -1em; margin-bottom: -1em;">Add a comment:</h4>
                                    <!-- Comments Box -->
                                    <div id="comment-box" class="row">
                                        <textarea class="form-control"
                                                  placeholder="Enter your comments here" rows="5"
                                                  id="comments" name="commentcontent"></textarea>
                                        <button class="btn btn-round btn-info" id="addComment"><i
                                                class="material-icons">speaker_notes</i> Add Comment
                                        </button>
                                    </div>
                                    <!-- Comments Box End -->

                                    <!-- Empty div for adding some space between divs -->
                                    <div class="row" style="height:2em;"></div>

                                    <!-- Display article comments section -->

                                    <c:if test="${not empty commentlist}">
                                    <h4 style="margin-left: -1em;">View comments:</h4>
                                    </c:if>

                                    <!-- Display comments -->
                                    <div id="containComments">
                                        <c:forEach items="${commentlist}" var="content">

                                            <!-- Each comment -->
                                            <div id="${content.commentId}" class="commentid">

                                                <!-- Display username and time posted -->
                                                <p><strong>
                                                    <small>Posted by: </small>
                                                </strong>
                                                    <small id="${content.commentId}username"
                                                           class="username">
                                                        <a href="ProfilePage?accessFriend=${content.username}">
                                                                ${content.username}
                                                        </a>
                                                    </small>
                                                    on
                                                    <small id="${content.commentId}commentedTime"
                                                           class="commentedTime">
                                                            ${content.commentedTime}
                                                    </small>
                                                </p>

                                                <!-- Display comment content -->
                                                <blockquote>
                                                    <p id="${content.commentId}content"
                                                       class="content">${content.content}</p>
                                                </blockquote>
                                                <!-- Display comment content end -->

                                                <!-- Button to show/hide the edit/delete buttons & textbox -->
                                                <c:if test="${content.owner || articleContents.owner}">
                                                <button data-toggle="collapse"
                                                        data-target="#comment-collapsible-${content.commentId}"
                                                        class="btn btn-round btn-white btn-sm" id="comment-collapsible-toggle" style="margin-top: -1em;">
                                                    Edit/Delete
                                                </button>

                                                <!-- Container for the show/hide -->
                                                <div id="comment-collapsible-${content.commentId}" class="collapse">
                                                    </c:if>

                                                    <!-- Edit and Delete Commment buttons & textbox -->
                                                    <c:if test="${content.owner}">

                                                        <!-- Edit comment - Text input area -->
                                                        <div class="form-group label-floating is-empty">
                                                            <label class="control-label">Edit comment</label>
                                                            <input type="text" id="${content.commentId}text"
                                                                   class="change form-control">
                                                        </div>
                                                        <!-- Edit comment - Text input area -->

                                                        <!-- Edit and delete comment buttons -->
                                                        <button class="edit btn btn-round btn-primary btn-sm"
                                                                id="${content.commentId}edit"
                                                                onclick="editComment($(this))"><i
                                                                class="material-icons">create</i><span class="hidden-xs"> Edit</span>
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${content.owner || articleContents.owner}">
                                                        <button class="delete btn btn-round btn-danger btn-sm"
                                                                id="${content.commentId}delete"
                                                                onclick="deleteComment($(this))"><i
                                                                class="material-icons">delete_forever</i><span class="hidden-xs"> Delete</span>
                                                        </button>
                                                    </c:if>
                                                    <!-- Edit and Delete Commment buttons end -->

                                                    <c:if test="${content.owner || articleContents.owner}"></div>
                                                </c:if>
                                            </div>

                                            <!-- Empty div for adding some space at the bottom of each comment -->
                                            <div class="row" style="height:1em;"></div>
                                        </c:forEach>
                                    </div>
                                    <!-- Display article comments section -->

                                </div>

                                <!-- COMMENTS SECTION end -->

                                <!-- Empty div for adding some space at the bottom of the container -->
                                <div class="row" style="height:2em;"></div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER START -->
<%@ include file="/component/Footer(Template).html" %>
<!-- FOOTER END -->

</body>
<script>
    //Adding comments by the ajax call with a post method, which allow the adding of the comment and then append
    $("#addComment").click(function () {
        $.ajax({
            url: 'Comments',
            type: 'Post',
            data: {
                "commentcontent": $("#comments").val(),
                "comments": "Add a Comment"
            },
            success: function (msg) {
                
                var Data = JSON.parse(msg);
                var contain = $("#containComments");
                var div = document.createElement("div");
                div.id = Data.CommentId;

                /* Content */ /* p1 = username, p2 = content, p3 = time */
                var p1 = document.createElement("span");
                p1.innerHTML = "<small><a href='ProfilePage?accessFriend=" + Data.Username + "'>" + Data.Username + "</a></small>";
                p1.id = Data.CommentId + "username";
                p1.className = "username";

                var p3 = document.createElement("span");
                p3.innerHTML = "<small> on " + Data.CommentedTime + " </small>";
                p3.id = Data.CommentId + "commentedTime";
                p3.className = "commentedTime";

                var commentinfocontainer = document.createElement("p");
                var commentinfo = document.createElement("span");
                commentinfo.innerHTML = "<strong><small>Posted by: </small></strong>";
                commentinfocontainer.append(commentinfo);
                commentinfocontainer.append(p1);
                commentinfocontainer.append(p3);

                var p2 = document.createElement("p");
                p2.innerHTML = Data.Content;
                p2.id = Data.CommentId + "content";
                p2.className = "content";
                var commentcontent = document.createElement("blockquote");

                /* Collapsible */
                var collapsebutton = document.createElement("button");
                collapsebutton.innerHTML = "Edit/Delete";
                collapsebutton.setAttribute("data-toggle","collapse");
                collapsebutton.setAttribute("data-target","#comment-collapsible-" + Data.CommentId);
                collapsebutton.className = "btn btn-round btn-white btn-sm";
                collapsebutton.id = "comment-collapsible-toggle";
                collapsebutton.style.marginTop = "-1em";
                collapsebutton.style.marginBottom = "1em";

                /* Collapsible Div */
                var collapsediv = document.createElement("div");
                collapsediv.id = "comment-collapsible-" + Data.CommentId;
                collapsediv.className = "collapse";
                collapsediv.style.marginTop = "-1em";
                collapsediv.style.marginBottom = "1em";

                /* Edit Input */
                var editinputdiv = document.createElement("div");
                editinputdiv.className = "form-group label-floating is-empty";

                var editinputdivlabel = document.createElement("label");
                editinputdivlabel.className = "control-label";
                editinputdivlabel.innerHTML = "Edit comment";

                var editinput = document.createElement("input");
                editinput.type = "text";
                editinput.id = Data.CommentId + "text";
                editinput.className = "change form-control";

                /* Edit & Delete Buttons */
                var editbutton = document.createElement("button");
                editbutton.id = Data.CommentId + "edit";
                editbutton.className = "edit btn btn-round btn-primary btn-sm";
                editbutton.innerHTML = "<i class='material-icons'>create</i><span class='hidden-xs'> Edit</span>";
                editbutton.setAttribute("onclick", "editComment($(this))");
                var deletebutton = document.createElement("button");
                deletebutton.id = Data.CommentId + "delete";
                deletebutton.className = "delete btn btn-round btn-danger btn-sm";
                deletebutton.innerHTML = "<i class='material-icons'>delete_forever</i><span class='hidden-xs'> Delete</span>";
                deletebutton.setAttribute("onclick", "deleteComment($(this))");

                /* Append elements to div */
                div.append(commentinfocontainer);
                commentcontent.append(p2);
                div.append(commentcontent);
                div.append(collapsebutton);
                div.append(collapsediv);
                editinputdiv.append(editinputdivlabel);
                editinputdiv.append(editinput);
                collapsediv.append(editinputdiv);
                collapsediv.append(editbutton);
                collapsediv.append(deletebutton);
                contain.append(div);
            }
        });
    });

    <!-- ajax post request for the deleting of the comment -->
    function deleteComment(e) {
        $.post("Deleting", {
            "commentId": e.parent().parent().attr("id"),
            "log": "DeleteComment"
        })
            .done(function (data) {
                $(e.parent().parent()).remove();
                
            });

    }
    <!-- ajax post request for the editing of the comment and then changing the text within the associated position. -->
    function editComment(e) {
        $.ajax({
            url: 'Comments',
            type: 'Post',
            data: {
                "commentId": e.parent().parent().attr("id"),
                "commentcontent": e.parent().parent().find(".change").val(),
                "comments": "EditComment"
            },
            success: function (msg) {
                
                var Data = JSON.parse(msg);
                var p1 = e.parent().parent().find(".username");
                p1.html(Data.Username);
                var p2 = e.parent().parent().find(".content");
                p2.html(Data.Content);
                var p3 = e.parent().parent().find(".commentedTime");
                p3.html(Data.CommentedTime);
            }
        });
    }
    var orginalcontent = null;
    <!-- This function is to like or unlike someone. -->
    function likeadd(e) {
        $.ajax({
            url: 'Articles',
            type: 'Post',
            data: {
                "like": "like",
                "likepeople": $(".currentuser").html(),
                "articleIdnumber": e.attr("id")
            },
            success: function (msg) {
                if (msg > $("#likenumber").html()) {
                    if (orginalcontent != null) {
                        $("#articleContents").html(orginalcontent);
                    }
                    e.html("<i class='material-icons'>cancel</i> Unlike");
                    e.addClass("btn-danger");
                    e.removeClass("btn-success");
                }
                else {
                    e.html("<i class='material-icons'>favorite</i> Like");
                    e.addClass("btn-success");
                    e.removeClass("btn-danger");

                    orginalcontent = $("#articleContents").html();
                    $("#articleContents").each(function () {
                        var aplha = $(this).html().split("");
                        for (var i = 0; i < aplha.length; i++) {
                            random = Math.floor(Math.random() * i);
                            var x = aplha[i - 1];
                            aplha[i - 1] = aplha[random];
                            aplha[random] = x;
                        }
                        
                        $(this).html(aplha.join(""));
                    });
                }
                $("#likenumber").html(msg);
            },
            error: function (request, status, error) {
                
                alert("Upload File Fail.");
            }
        });
    }
    <!-- Due to the database and the create and the editing page is constructed, that replace all or most of the html tags into the <p> tags -->


    <!-- Making all images img-responsive -->
    $(document).ready(function () {
        $("#articleContents img").each(function () {
            $(this).addClass('img-responsive');
        });

        $('#articleContents > *').replaceWith(function () {
            return $('<div/>', {
                html: $(this).html()
            });
        });
        $(".ui-state").each(function () {
            
            $(this).replaceWith(function () {
                return $('<p>', {
                    html: this.innerHTML
                });
            });
        });
        $(".ui-state-default,ui-state").each(function () {
            
            $(this).replaceWith(function () {
                return $('<p>', {
                    html: this.innerHTML
                });
            });
        });
        /* Alternative version below: this will grab all the img on page */
//        var allImages = $('img');
//        allImages.addClass('img-responsive');
    });

</script>

</html>
