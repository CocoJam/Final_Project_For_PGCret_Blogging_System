<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 26/05/2017
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WYSIWYG</title>

    <!-- jQUERY & jQUERY UI -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <!-- Load WYSIWYG STYLE -->
    <link rel="stylesheet" href="Trumbowyg/dist/ui/trumbowyg.min.css">
    <link rel="stylesheet" href="Trumbowyg/dist/plugins/colors/ui/trumbowyg.colors.min.css">

    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            margin-bottom: 10px;
        }

        li {
            margin: 5px;
            padding: 5px;
            width: 100%;
        }

        .section-selected {
            border: 1px solid #ededed;
            background: #c5c5c5;
            color: #2b2b2b;
        }

        #contents {
            border: 1px solid #ededed;
            border-radius: 6px;
            box-shadow: 0 16px 24px 2px rgba(0, 0, 0, 0.14), 0 6px 30px 5px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2);
        }
    </style>

</head>

<body>
<form id="form" action="/Articles" method="post">
    <label for="ArticleName">Article Name</label>
    <input name="ArticleName" type="text" id="ArticleName" value="${articleContents.articlename}">
    <input type="hidden" name="ArticleContent">

    <% if (session.getAttribute("articleContents") != null) {
        System.out.println("EDIT");
        out.println("<input type=\"submit\" name=\"add\" value=\"Editted\">");
    } else {
        out.println("<input type=\"submit\" name=\"add\" value=\"addingToDataBase\">");
    }
    %>
</form>

<!-- WYSIWYG -->
<!-- Editor Box -->
<h1>WYSIWYG</h1>
<div class="wysiwys" placeholder="Enter your content here"></div>

<!-- Make a new section, put current WYSIWYG content in -->
<button onclick="addNewSection()">Add Section</button>

<!-- Delete currently selected section -->
<button onclick="deleteSection()">Delete Section</button>

<!-- Clear all content inside WYSIWYG editor -->
<button onclick="resetText()">Reset</button>

<!-- Space for holding content to be uploaded to DB -->
<!-- DRAGGABLE SECTIONS -->
<div id="contents">
    <c:choose>
        <c:when test="${not empty articleContents}">
            ${articleContents.content}
        </c:when>
        <c:otherwise>
            <ul id="sortable">
                <li class="ui-state-default"><p></p></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>

<form action="/Upload" method="post" id="Upload"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <input type="submit" name="Upload" value="ArticlesUpload"/>
</form>

<form id="Youtube"  action="/ArticleUpload" method="post">
    <input id="youtubeurl" type="text" name="youtube">
    <input type="submit" name="youtubeVideoSubmition" value="youtubesubmit">
</form>

<script>
    // AJAX UPLOAD
    $('#Upload')
        .submit(function (e) {
            $.ajax({
                url: '/Upload',
                type: 'POST',
                data: new FormData(this),
                processData: false,
                contentType: false,
                success: function (msg) {
                    console.log(msg);
                    var image = document.createElement("ul");
                    console.log(image);
                    var li = document.createElement("li");
                    li.className = "ui-state draggable";
                    if (msg.endsWith(".flv") || msg.endsWith(".m4v") || msg.endsWith(".mp4") || msg.endsWith(".mpg") || msg.endsWith(".mpeg") || msg.endsWith(".wmv")){
                        li.innerHTML = "<video width=\"400\" controls> <source src=\""+msg+"\"></video>";
                    }
                   else if(msg.endsWith(".mp3")){
                        li.innerHTML = " <audio controls><source src=\""+msg+"\" type=\"audio/ogg\"> </audio>";
                    }
                    else if (msg.endsWith(".jpg") || msg.endsWith(".png")) {
                        li.innerHTML = "<img src=\"" + msg + "\">";
                    }
                    image.append(li);
                    $("#sortable").append(image);
                }
            });
            e.preventDefault();
        });

    // YOUTUBE AJAX
    $('#Youtube')
        .submit(function (e) {
            $.ajax({
                url: '/ArticleUpload',
                type: 'POST',
                data: {"youtube":$("#youtubeurl").val()},
                success: function (msg) {
                    console.log(msg);
                    var image = document.createElement("ul");
                    console.log(image);
                    var li = document.createElement("li");
                    li.className = "ui-state draggable";
                    li.innerHTML = msg;
                    image.append(li);
                    $("#sortable").append(image);
                }
            });
            e.preventDefault();
        });

    // SUBMITTING
    $("#form").submit(function () {
        $("input:hidden").val($("#contents").html());
    });
</script>

</body>
</html>
