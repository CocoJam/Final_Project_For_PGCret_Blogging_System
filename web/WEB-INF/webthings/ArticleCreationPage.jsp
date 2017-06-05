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
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
    </style>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
<textarea rows="4" cols="50" id="textarea"></textarea>
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

<script>
    var number = 0;
    $("#textarea").bind('input', function () {
        console.log(number);
        console.log($("#textarea").val());
        console.log("changing");
        $(".ui-state-default>p").eq(number).text($("#textarea").val());
    });
    $("#textarea").on('keyup', function (e) {
        if (e.keyCode == 13) {
            number++;
            console.log(number);
            $("#textarea").val("");
            var paragraph1 = document.createElement("li");
            paragraph1.className = "ui-state-default";
            var paragraph = document.createElement("p");
            paragraph.className = "testing";
            $("#sortable").append(paragraph1);
            $(paragraph1).append(paragraph);
        }
        if (e.keyCode == 46) {
            if (number > 0) {
                $(".ui-state-default").eq(number).remove();
                console.log("removing");
                number--;
            }
        }
    });
    $(function () {
        $("#sortable").sortable({
            revert: true
        });
        $(".draggable").draggable({
            connectToSortable: "#sortable",
            revert: "invalid"
        });
        $("ul, li").disableSelection();
    });




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
    $("#form").submit(function () {
        $("input:hidden").val($("#contents").html());
    });
</script>


<%--if (fileName.endsWith(".flv") || fileName.endsWith(".m4v") || fileName.endsWith(".mp4") || fileName.endsWith(".mpg") || fileName.endsWith(".mpeg") || fileName.endsWith(".wmv")) {--%>
<%--FormingVideoFileAndVideo();--%>
<%--} else if (fileName.endsWith(".mp3")) {--%>
<%--FormingAudioFileAndAudio();--%>
<%--} else if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {--%>
<%--FormingPhotoFileAndPhoto();--%>
<%--}--%>
<%--<c:forEach var="mediagroups" items="${mediaOutPut}">--%>
    <%--<c:if test="${mediagroups.key.equals(\"photo\")}">--%>
        <%--<c:forEach var="media" items="${mediagroups.value}">--%>
            <%--<img src="${media}">--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--<c:if test="${mediagroups.key.equals(\"audio\")}">--%>
        <%--<c:forEach var="media" items="${mediagroups.value}">--%>
            <%--<audio controls><source src="${media}" type="audio/ogg"> </audio>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--<c:if test="${mediagroups.key.equals(\"video\")}">--%>
        <%--<c:forEach var="media" items="${mediagroups.value}">--%>
            <%--<video width="400" controls> <source src="${media}"></video>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
<%--</c:forEach>--%>


</body>
</html>
