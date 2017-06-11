<%@ page import="java.util.List" %><%--
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
    <title>ArticleIndex</title>

    <%@include file="../../component/Header(styling Template).html" %>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <style>
        #gallery {
            float: left;
            width: 70%;
            min-height: 12em;
        }

        .gallery.custom-state-active {
            background: #eee;
        }

        .gallery li {
            float: left;
            width: 120px !important;
            height: 200px !important;
            padding: 0.4em;
            margin: 0 0.4em 0.4em 0;
            text-align: center;
        }

        .gallery li h5 {
            margin: 0 0 0.4em;
            cursor: move;
        }

        .gallery li a {
            float: right;
        }

        .gallery li a.ui-icon-zoomin {
            float: left;
        }

        .gallery li img {
            width: 100%;
            cursor: move;
        }

        #save {
            float: right;
            width: 30%;
            min-height: 18em;
            padding: 1%;
        }

        #save h4 {
            line-height: 16px;
            margin: 0 0 0.4em;
        }

        #save h4 .ui-icon {
            float: left;
        }

        #save .gallery h5 {
            display: none;
        }
    </style>

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
                                                    <div class="name" id="custom-profile-name">
                                                        <h3 class="title">${profileInfo.name}'s Articles list</h3>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="Upload-photos/placeholder.gif" alt="Circle Image"
                                                         class="img-rounded img-responsive img-raised">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <h1>ALL ARTICLES</h1>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                            </div>
                        </div>


                        <div class="row">
                            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
                                <label for="searchBar">Search: </label>
                                <input type="text" id="searchBar">
                                <label for="sort">Sort</label>
                                <button id="sort">Assemble</button>
                                <button id="sorttitle">By title</button>
                                <button id="sortcategory">By category</button>
                                <button id="sortdate">By date</button>
                                <div class="ui-widget ui-helper-clearfix">
                                    <ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix">
                                        <c:forEach items="${ArticleIndex}" var="index">

                                            <li class="ui-widget-content ui-corner-tr">
                                                <h5 class="ui-widget-header">${index.articlename}<br><c:if
                                                        test="${articleList.equals('all')}">
                                                    <a href=ProfilePage?accessFriend=${index.username}
                                                       class="username">${index.username}</a>
                                                </c:if></h5>
                                                <p class="articlename" hidden>${index.articlename}</p>
                                                <c:choose>
                                                    <c:when test="${not empty index.firstimage}">
                                                        <img src="${index.firstimage}"
                                                             alt="${index.articlename}"
                                                             width="96" height="72">
                                                    </c:when>
                                                    <c:otherwise>

                                                    </c:otherwise>
                                                </c:choose>
                                                <div hidden>${index.content}</div>
                                                <a href="/Articles?acticleId=${index.articleid}"
                                                   title="View larger image"
                                                   class="ui-icon ui-icon-zoomin articleid">View larger</a>
                                                <a href="link/to/trash/script/when/we/have/js/off"
                                                   title="Delete this image" class="ui-icon ui-icon-plusthick">Delete
                                                    image</a>

                                                <p class="category">${index.category}</p>
                                                <p hidden class="date">${index.datecreated}</p>
                                                <p hidden class="id">${index.articleid}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div id="save" class="ui-widget-content ui-state-default">
                                        <h4 class="ui-widget-header"><span
                                                class="ui-icon ui-icon-plusthick">Save</span> Cart</h4>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>
            </div>

        </div><!-- main container end -->

    </div><!-- outer div 2 -->
</div><!-- outer div 1 -->
</div><!-- wrapper div -->

<!-- FOOTER START -->
<%@ include file="../../component/Footer(Template).html" %>
<!-- FOOTER END -->
<script>
    $(function () {
        $("#searchBar").bind('keyup', function () {
            var value = $(this).val();
            $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").each(function () {
                var title = $(this).children().siblings("h5").text();
                var username = $(this).children().siblings(".username").text();
                var category = $(this).children().siblings(".category").text().trim();
                var matching = new RegExp(value, "gi");
                if (title.match(matching) || username.match(matching) || (category.match(matching) && category.length > 0)) {
                    $(this).fadeIn("fast", function () {
                        $(this).css("z-index", 0)
                    })
                }
                else {
                    $(this).fadeOut("fast", function () {
                        $(this).css("z-index", -1);
                    })
                }
            })
        });


        // There's the gallery and the trash
        var $gallery = $("#gallery"),
            $save = $("#save");

        // Let the gallery items be draggable
        $("li", $gallery).draggable({
            cancel: "a.ui-icon", // clicking an icon won't initiate dragging
            revert: "invalid", // when not dropped, the item will revert back to its initial position
            containment: "document",
            helper: "clone",
            cursor: "move"
        });

        // Let the trash be droppable, accepting the gallery items
        $save.droppable({
            accept: "#gallery > li",
            classes: {
                "ui-droppable-active": "ui-state-highlight"
            },
            drop: function (event, ui) {
                deleteImage(ui.draggable);
                console.log($(this))
                console.log(ui.draggable.eq(0))
                var hyper = ui.draggable.eq(0).children().siblings("a").attr('href');
                var title = ui.draggable.eq(0).children().siblings(".articlename").text();
                $.post("/ArticleCart", {cartadd: "<a href=\"" + hyper + "\">" + title + "</a>"})
            }
        });

        // Let the gallery be droppable as well, accepting items from the trash
        $gallery.droppable({
            accept: "#save li",
            classes: {
                "ui-droppable-active": "custom-state-active"
            },
            drop: function (event, ui) {
                recycleImage(ui.draggable);
                var hyper = ui.draggable.eq(0).children().siblings("a").attr('href');
                var title = ui.draggable.eq(0).children().siblings(".articlename").text();
                $.post("/ArticleCart", {cartunadd: "<a href=\"" + hyper + "\">" + title + "</a>"})
            }
        });


        // Image deletion function
        var recycle_icon = "<a href='link/to/recycle/script/when/we/have/js/off' title='Recycle this image' class='ui-icon ui-icon-refresh'>Recycle image</a>";

        function deleteImage($item) {
            $item.fadeOut(function () {
                var $list = $("ul", $save).length ?
                    $("ul", $save) :
                    $("<ul class='gallery ui-helper-reset'/>").appendTo($save);

                $item.find("a.ui-icon-plusthick").remove();
                $item.append(recycle_icon).appendTo($list).fadeIn(function () {
                    $item
                        .animate({width: "48px"})
                        .find("img")
                        .animate({height: "36px"});
                });
            });
        }

        // Image recycle function
        var trash_icon = "<a href='link/to/trash/script/when/we/have/js/off' title='Delete this image' class='ui-icon ui-icon-plusthick'>Delete image</a>";

        function recycleImage($item) {
            $item.fadeOut(function () {
                $item
                    .find("a.ui-icon-refresh")
                    .remove()
                    .end()
                    .css("width", "96px")
                    .append(trash_icon)
                    .find("img")
                    .css("height", "72px")
                    .end()
                    .appendTo($gallery)
                    .fadeIn();
            });
        }

        // Image preview function, demonstrating the ui.dialog used as a modal window
        function viewLargerImage($link) {

            var hyper = $link.attr('href');
            var image = $link.siblings("img").attr('src');
            var title = $link.siblings("h5").html();
            var content = $link.siblings("div").html().substr(0, 20);
            var username = null;
            username = $link.siblings(".username").html();

            var img = $("<p>" + content + "</p>");
            if (image != undefined) {
                img.html("<a href=\"" + hyper + "\">" + title + "</a>" + "<br/>" + "<img src=\'" + image + "\'width=\"96\" height=\"72\">" + "<p>" + content + "</p>");
            }
            else {
                img.html("<a href=\"" + hyper + "\">" + title + "</a>" + "<p>" + content + "</p>");
            }
            var linking = $("<a href=\"" + hyper + "\">");
            setTimeout(function () {
                img.dialog({
                    width: 400,
                    height: 200,
                    modal: true
                });
            }, 1);
        }


        $("ul.gallery > li").on("click", function (event) {
            console.log($(this))
            var $item = $(this),
                $target = $(event.target);
            var hyper = $target.siblings("a").attr('href');
            var title = $target.siblings(".articlename").text();
            console.log(title);
            console.log(hyper);
            if ($target.is("a.ui-icon-plusthick")) {
                deleteImage($item);
                $.post("/ArticleCart", {cartadd: "<a href=\"" + hyper + "\">" + title + "</a>"})
            } else if ($target.is("a.ui-icon-zoomin")) {
                viewLargerImage($target);
            } else if ($target.is("a.ui-icon-refresh")) {
                recycleImage($item);
                $.post("/ArticleCart", {cartunadd: "<a href=\"" + hyper + "\">" + title + "</a>"})
            } else if ($target.is(".username")) {
                return $target.attr('href');
            }
            return false;
        });

//needed time delay to sort needed to wait for all animation to finish.


        var things = [];
        var assemibled = false;
        var type = null;

        function defaultSort(elementX, elementY) {
            if (elementX.children().siblings(type).text().toLowerCase() < elementY.children().siblings(type).text().toLowerCase())
                return -1;
            if (elementX.children().siblings(type).text().toLowerCase() > elementY.children().siblings(type).text().toLowerCase())
                return 1;
            return 0;
        }

        function attachment() {
            $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").each(function () {
                things.push($(this));
            });
            for (var i = 0; i < things.length; i++) {
                deleteImage($(things[i]));
            }
        }

        function display() {
            while (things.length > 0) {
                recycleImage($(things[0]));
                things.shift();
            }
        }

        $("#sort").on('click', function () {
            if (assemibled === false) {
                attachment();
                assemibled = true;
            }
        });
        $("#sorttitle").on('click', function () {
            type = "h5";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });
        $("#sortcategory").on('click', function () {
            type = ".category";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });
        $("#sortdate").on('click', function () {
            type = ".date";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });
        var cartlist = '<%= session.getAttribute("cartlist") %>';
        var cartArray = cartlist.substring(1, cartlist.length - 1).split(",")
        console.log(cartlist);
        console.log(cartArray);
        $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").each(function () {
            var blah = ($(this).children().siblings(".articleid").attr('href').replace("?", "\\?"));
            var match = new RegExp(blah);
            console.log($(this).children().siblings(".articleid").attr('href'));
            console.log(match)
            console.log("yes?")
            for (var i = 0; i < cartArray.length; i++) {
                console.log(cartArray[i]);
                if (cartArray[i].match(match)) {
                    console.log("matched?")
                    deleteImage($(this));
                    break;
                }
                else {
                    console.log("nope")
                }
            }
        });

    });
</script>

</body>
</html>
