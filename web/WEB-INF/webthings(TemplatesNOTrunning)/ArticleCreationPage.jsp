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
    <title>Article</title>

    <!-- jQUERY & jQUERY UI -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <!-- Load WYSIWYG STYLE -->
    <link rel="stylesheet" href="../../Trumbowyg/dist/ui/trumbowyg.min.css">
    <link rel="stylesheet" href="../../Trumbowyg/dist/plugins/colors/ui/trumbowyg.colors.min.css">

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

<!-- Load WYSIWYG JS -->
<script src="../../Trumbowyg/dist/trumbowyg.min.js"></script>
<script src="../../Trumbowyg/dist/plugins/preformatted/trumbowyg.preformatted.min.js"></script>
<script src="../../Trumbowyg/dist/plugins/colors/trumbowyg.colors.min.js"></script>
<script src="../../Trumbowyg/dist/plugins/upload/trumbowyg.upload.js"></script>
<script src="Trumbowyg/dist/plugins/emoji/trumbowyg.emoji.min.js"></script>
<script src="Trumbowyg/dist/plugins/insertaudio/trumbowyg.insertaudio.min.js"></script>

<!-- WYSIWYG -->
<!-- WYSIWYG Editor Implementation START -->
<script>
    $('.wysiwys').trumbowyg({
        // Settings
        semantic: true,
        autogrow: true,
        resetCss: true,

        // Buttons
        btnsDef: {
            // Customizables dropdowns
            image: {
                dropdown: ['insertImage', 'upload', 'base64', 'noembed'],
                ico: 'insertImage'
            }
        },
        btns: [
            ['viewHTML'],
            ['undo', 'redo'],
            ['formatting'],
            'btnGrp-design',
            ['link'],
            ['image'],
            ['upload'],
            'btnGrp-justify',
            'btnGrp-lists',
            ['foreColor', 'backColor'],
            ['preformatted'],
            ['horizontalRule'],
            ['removeformat'],
            ['fullscreen']
        ]
    });
</script>
<!-- WYSIWYG Editor Implementation END -->

<!-- Make a new section, put current WYSIWYG content in -->
<button onclick="addNewSection()">Add Section</button>

<!-- Delete currently selected section -->
<button onclick="deleteSection()">Delete Section</button>

<!-- Clear all content inside WYSIWYG editor -->
<button onclick="resetText()">Reset</button>

<p></p>

<!-- Space for holding content to be uploaded to DB -->
<!-- DRAGGABLE SECTIONS -->
<h2>Contents holding area</h2>
<div id="contents">
    <c:choose>
        <c:when test="${not empty articleContents}">
            ${articleContents.content}
        </c:when>
        <c:otherwise>
            <!-- creates new section boxes -->
            <ul>
                <li class="ui-state draggable"></li>
            </ul>

            <!-- place where you drag stuff into for sorting their order -->
            <ul id="sortable">
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


</body>

<!-- More WYSIWYG JS -->
<script>
    function resetText() {
        $('.wysiwys').trumbowyg('empty');
    }

    function addNewSection() {
        var content = $('.wysiwys').trumbowyg('html');
        if (mouse == -1) {
            console.log("making");
            console.log(content);
            number++;
            var paragraph1 = document.createElement("li");
            paragraph1.className = "ui-state-default ";
            paragraph1.onclick = getID;
//        var paragraph = document.createElement("p");
//        paragraph1.class = "typing";
            paragraph1.id = number;
            $("#sortable").append(paragraph1);
//        $(paragraph1).append(paragraph);
            paragraph1.innerHTML = content;
            resetText();
        }
        <!-- Make the selection number -1, so it won't put the content of what's currently in the WYSIWYG directly into the selected section if made -->
        else {
            console.log("change");
            $("#" + mouse).html(content);
            $("#" + mouse).removeClass("section-selected");
            clicked = false;
            mouse = -1;
            resetText();
        }
    }

    // Delete currently selected section
    function deleteSection() {
        if (mouse != -1) {
            $("#"+mouse).remove();
            mouse =-1;
        }
        else {
            console.log(mouse);
            console.log($(".ui-state-default").length);
            $(".ui-state-default").eq($(".ui-state-default").length - 1).remove();
        }
    }

    function clearSection() {
        mouse = -1;
        clicked = false;
        $(".section-selected").each(function () {
            console.log("hello");
            $(this).removeClass("section-selected");
        })
    }

</script>

<!-- Scripts for dealing with draggable sections -->
<script>
    var mouse = -1;
    var number = -1;
    var clicked = false;
    // Get ID of section we clicked on, grab the content, put back inside WYSIWYG
    function getID() {
        if (!clicked) {
            mouse = this.id;
            console.log("section id: " + mouse);
            $('.wysiwys').trumbowyg('html', $(".ui-state-default").eq(mouse).text());
            console.log($('.wysiwys').trumbowyg('html'));
            $(this).addClass("section-selected");
            console.log($(".ui-state-default section-selected").eq(mouse).text());
            clicked = true;
        }
        else {
            clearSection();
        }
    }

    // Typing and populating the section with content from the WYSIWYG
    // If mouse is null, won't do anything
    // Binds all input from keystroke inside textarea, runs function - display what's inside textarea in console.log, assign the id to a section inside the contents div

    //    $('.wysiwys').bind('input', function () {
    //        var content = $('.wysiwys').trumbowyg('html');
    //        console.log(content);
    //        $("#textarea").text(content);
    //        $("#" + mouse).text(content);
    //    });


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

</script>

<!-- AJAX Upload -->
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
                    var li = document.createElement("li");
                    li.className = "ui-state draggable";
                    if (msg.endsWith(".flv") || msg.endsWith(".m4v") || msg.endsWith(".mp4") || msg.endsWith(".mpg") || msg.endsWith(".mpeg") || msg.endsWith(".wmv")){
                        li.innerHTML = "<video width=\"400\" controls> <source src=\""+msg+"\"></video>";
                    }
                    else if(msg.endsWith(".mp3")){
                        li.innerHTML = " <audio controls><source src=\""+msg+"\" type=\"audio/ogg\"> </audio>";
                    }
                    else if (msg.endsWith(".jpg") || msg.endsWith(".png")||msg.endsWith(".gif")||msg.endsWith(".jpeg")||msg.endsWith(".svg")) {
                        li.innerHTML = "<img src=\"" + msg + "\">";
                    }
                    $("#sortable").append(li);
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

</html>
