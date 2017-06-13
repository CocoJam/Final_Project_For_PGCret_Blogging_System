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
            width: 120px;
            height: 200px;
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
                                                    <c:choose>
                                                        <%--If this is a default profile image get the image from default photo directory--%>
                                                        <c:when test='${profileInfo.profilepic.startsWith("defaultslashn")}'>
                                                            <img src="defaultImg/${profileInfo.profilepic}"
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
                                                    <div class="name" id="custom-profile-name">
                                                        <h3 class="title">${profileInfo.name}'s Articles list</h3>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="assets/img/placeholder.gif" alt="Circle Image"
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
                        <button id="listorcard">To list</button>
                        <label>Search: </label>
                        <input type="text" id="searchBar">
                        <!-- This is the card based article index-->
                        <div class="row">
                            <div id="cardarticle"
                                 class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
                                <label for="sort">Sort</label>
                                <button id="sort">Assemble</button>
                                <button id="sorttitle">By title</button>
                                <button id="sortcategory">By category</button>
                                <button id="sortdate">By date</button>
                                <div class="ui-widget ui-helper-clearfix">
                                    <ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix"
                                        style="margin-top: 0px;">
                                        <c:forEach items="${ArticleIndex}" var="index">

                                            <%--<li class="ui-widget-content ui-corner-tr">--%>
                                            <%--<div>--%>
                                            <%--<c:choose>--%>
                                            <%--<c:when test="${not empty index.firstimage}">--%>
                                            <%--<img class="card-img-top" src="${index.firstimage}"--%>
                                            <%--alt="${index.articlename}"--%>
                                            <%--style="width: 80%; height: 50%">--%>
                                            <%--</c:when>--%>
                                            <%--<c:otherwise>--%>
                                            <%--<img class="card-img-top" src="assets/img/img6.jpg" alt="Circle Image"--%>
                                            <%--style="width: 80%; height: 50%">--%>
                                            <%--</c:otherwise>--%>
                                            <%--</c:choose>--%>
                                            <%--<p class="card-block">--%>
                                            <%--<h4 class="ui-widget-header cart-title" style="margin-bottom: 0">${index.articlename}</h4>--%>
                                            <%--<p class="card-text">${index.content}</p>--%>

                                            <%--<a href="/Articles?acticleId=${index.articleid}"--%>
                                            <%--title="View larger image"--%>
                                            <%--class="ui-icon ui-icon-zoomin articleid">View larger</a>--%>
                                            <%--<a href="link/to/trash/script/when/we/have/js/off"--%>
                                            <%--title="Delete this image" class="ui-icon ui-icon-plusthick">Delete--%>
                                            <%--image</a>--%>
                                            <%--<p class="category">${index.category}</p>--%>
                                            <%--<p hidden class="date">${index.datecreated}</p>--%>
                                            <%--<p hidden class="id">${index.articleid}</p>--%>
                                            <%--</div>--%>
                                            <%--</div>--%>
                                            <%--</li>--%>


                                            <li class="ui-widget-content ui-corner-tr">

                                                    <%--<c:if test="${articleList.equals('all')}">--%>

                                                    <%--</c:if>--%>
                                                <p class="articlename" hidden>${index.articlename}</p>
                                                <c:choose>
                                                    <c:when test="${not empty index.firstimage}">
                                                        <img class="card-img-top" src="${index.firstimage}"
                                                             alt="${index.articlename}"
                                                             width="96" height="72" style="margin-bottom: 5">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img class="card-img-top" src="../../pokemonloader4.gif"
                                                             alt="Circle Image"
                                                             width="96" height="72" style="margin-bottom: 5">
                                                    </c:otherwise>
                                                </c:choose>
                                                <h4 class="card-title articlename"
                                                    style="overflow: hidden; width: 100%; height: 10%">${index.articlename}</h4>

                                                <div class="card-text"
                                                     style="overflow: hidden; width: 100%; height: 30%">${index.content}
                                                    <div class="Articlecategory"
                                                         style="overflow: hidden; width: 80%; height: 30%; align-content: center;">${index.category}</div>
                                                </div>
                                                    <%--<p class="category" style="overflow: hidden; width: 80%; height: 10%">${index.category}</p>--%>

                                                <a href="/Articles?acticleId=${index.articleid}"
                                                   title="View larger image"
                                                   class="ui-icon ui-icon-zoomin articleid">View larger</a>

                                                <a href="link/to/trash/script/when/we/have/js/off"
                                                   title="Delete this image" class="ui-icon ui-icon-plusthick">Delete
                                                    image</a>

                                                <p hidden class="date">${index.datecreated}</p>
                                                <p hidden class="id">${index.articleid}</p>
                                                <a href=ProfilePage?accessFriend=${index.username} class="username" hidden>${index.username}</a>
                                            </li>


                                            <%--<div>--%>
                                            <%--&lt;%&ndash;<img class="card-img-top" src="../pokemonloader4.gif" alt="Card image cap" style="width: 80%">&ndash;%&gt;--%>
                                            <%--<c:choose>--%>
                                            <%--<c:when test="${not empty index.firstimage}">--%>
                                            <%--<img src="${index.firstimage}"--%>
                                            <%--alt="${index.articlename}"--%>
                                            <%--style="width: 80%">--%>
                                            <%--</c:when>--%>
                                            <%--<c:otherwise>--%>
                                            <%--<img src="assets/img/img6.jpg" alt="Circle Image"--%>
                                            <%--style="width: 80%">--%>
                                            <%--</c:otherwise>--%>
                                            <%--</c:choose>--%>
                                            <%--<div class="card-block">--%>
                                            <%--<h5 class="ui-widget-header card-title" style="margin-bottom: 0">${index.articlename}</h5>--%>

                                            <%--<p class="card-text" style="overflow: hidden">${index.content}</p>--%>
                                            <%--<a href="images/high_tatras3.jpg" title="View larger image" class="ui-icon ui-icon-zoomin">View--%>
                                            <%--larger</a>--%>
                                            <%--<!--<a href="#" class="btn btn-primary">Go somewhere</a>-->--%>
                                            <%--<a href="/Articles?acticleId=${index.articleid}"--%>
                                            <%--title="View larger image"--%>
                                            <%--class="ui-icon ui-icon-zoomin articleid">View larger</a>--%>
                                            <%--<a href="link/to/trash/script/when/we/have/js/off"--%>
                                            <%--title="Delete this image" class="ui-icon ui-icon-plusthick">Delete--%>
                                            <%--image</a>--%>
                                            <%--<p class=" articlename" hidden>${index.articlename}</p>--%>
                                            <%--<p hidden class="date">${index.datecreated}</p>--%>
                                            <%--<p hidden class="id">${index.articleid}</p>--%>
                                            <%--</div>--%>
                                            <%--</div>--%>


                                        </c:forEach>
                                    </ul>
                                    <div id="save" class="ui-widget-content ui-state-default">
                                        <h4 class="ui-widget-header"><span
                                                class="ui-icon ui-icon-plusthick">Save</span> Cart</h4>
                                    </div>
                                </div>
                            </div>

                            <!-- The card based article index end here-->

                            <!-- The list based article index start here-->
                            <div id="listarticle"
                                 class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
                                <table class="table table-striped table-hover table-responsive">
                                    <tr>
                                        <th>
                                            Article Names
                                        </th>
                                        <th>
                                            Date Created
                                        </th>
                                        <%--Scenario 1: ALL articles are requested--%>
                                        <c:if test="${articleList.equals('all')}">
                                            <th>
                                                Article Author
                                            </th>
                                        </c:if>

                                        <th>
                                            Article Category
                                        </th>

                                    </tr>
                                    <%--Looping through the Article Index (list of articles in the ArticleIndex Servlet) and populates a row per article--%>
                                    <c:forEach items="${ArticleIndex}" var="index">
                                        <tr class="tablecontentR">
                                            <td class="tablearticleid" hidden>${index.articleid}</td>
                                            <td class="tablearticlename">
                                                <a href="/Articles?acticleId=${index.articleid}">${index.articlename}</a>
                                            </td>
                                            <td class="tablearticledate">${index.datecreated}</td>

                                            <c:if test="${articleList.equals('all')}">
                                                <td class="tablearticleusername">
                                                        ${index.username}
                                                </td>
                                            </c:if>
                                            <td class="tablearticlecategory">
                                                    ${index.category}
                                            </td>

                                        </tr>
                                    </c:forEach>

                                </table>
                            </div>
                            <!-- The list based article index end here-->
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

        <!-- Switch between the card mode or the list mode -->
        var cardOrList = false;

        $("#listarticle").fadeOut("fast", function () {
            $(this).css("z-index", -1);
        });

        <!-- This is the toggle button for the change in between the list and card mode to switch the table out/in and the card out/in based on the state of the cardOrList var -->
        $("#listorcard").on("click", function () {
            if (cardOrList == false) {
                $("#cardarticle").fadeOut("fast", function () {
                    $(this).css("z-index", -1);
                });
                $("#listarticle").fadeIn("fast", function () {
                    $(this).css("z-index", 0);
                });
                cardOrList = true;
                $("#listorcard").html("To card");
            }
            else {
                $("#cardarticle").fadeIn("fast", function () {
                    $(this).css("z-index", 0);
                });
                $("#listarticle").fadeOut("fast", function () {
                    $(this).css("z-index", -1);
                });
                cardOrList = false;
                $("#listorcard").html("To list");
            }
        });

        <!-- Search bar function for searching article based on the title, creater's name and the category for both list and card form -->
        function searching(element, firstitem, seconditem, thriditem) {
            var value = $("#searchBar").val();
            $(element).each(function () {
                var title = $(this).children().siblings(firstitem).text().trim();
                var username = $(this).children().siblings(seconditem).text().trim();
                var category = $(this).children().siblings(thriditem).text().trim();
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
        }

        <!-- Searching function from the list mode and card mode applying to one search bar, plus the searching will not affect the cards that is saved within the cart.-->
        $("#searchBar").bind('keyup', function () {
            searching(".tablecontentR", ".tablearticlename", ".tablearticleusername", ".tablearticlecategory");
            searching(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)", "h4", ".username", ".category");
        });


        <!-- draggible jquery ui with majar alteration such as img to words and strings, the saving into the cart and other things. -->
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
        <!-- This applied a post request to ArticleCart servlet which is used to save the current article that has been click to cart into the servlet. When the cad is dragged to the cart -->
        $save.droppable({
            accept: "#gallery > li",
            classes: {
                "ui-droppable-active": "ui-state-highlight"
            },
            drop: function (event, ui) {
                deleteImage(ui.draggable);
                var hyper = ui.draggable.eq(0).children().siblings("a").attr('href');
                var title = ui.draggable.eq(0).children().siblings(".articlename").text();
                $.post("/ArticleCart", {cartadd: "<a href=\"" + hyper + "\">" + title + "</a>"})
            }
        });
        <!-- Same theory applies but to unadd or to disattatch the card from the cart session if dragged out of the cart -->
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

                        .animate({height: "100px"})
                        .animate({width: "80px"});
                    $item.find($("img")).css("height", "50%");
                    $item.find(".Articlecategory").css("height", "0");
                    storageText = $item.find($(".card-text")).html();
                    $item.find($(".card-text")).css("height", "0px");
                    $item.find($(".articlename")).css("height", "20%");
                });
            });
        }

        // Image recycle function
        var trash_icon = "<a href='link/to/trash/script/when/we/have/js/off' title='Delete this image' class='ui-icon ui-icon-plusthick'>Delete image</a>";

        function recycleImage($item) {
            $item.fadeOut(function () {
//                $item
//                    .find("a.ui-icon-refresh")
//                    .remove()
//                    .end()
//                    .css("width", "96px")
//                    .append(trash_icon)
//                    .find("img")
//                    .css("height", "72px")
//                    .end()
//                    .appendTo($gallery)
//                    .fadeIn();
                $item.find($(".card-text")).css("height", "30%");
                $item.find($("img")).css("height", "72");
                $item.find($("img")).css("weight", "96");
                $item.find(".Articlecategory").css("height", "10%");
                $item.find($(".articlename")).css("height", "10%");
                $item
                    .find("a.ui-icon-refresh")
                    .remove()
                    .end()
                    .css("height", "200px")
                    .css("width", "120px")
                    .append(trash_icon)
                    .find("img")
                    .end()
                    .appendTo($gallery)
                    .fadeIn();
            });
        }

        // Image preview function, demonstrating the ui.dialog used as a modal window
        <!-- This is the mini summary that pop up if the glass is clicked, as you would see some specific alteration is done to allow other things such as more than one html tag to be included. Due to how the ui is structured that a hack around is needed and done, or not several pop up will appear based on how many html tags is within. -->
        function viewLargerImage($link) {

            var hyper = $link.attr('href');
            var image = $link.siblings("img").attr('src');
            var title = $link.siblings("h4").html();
            var content = $link.siblings(".card-text").html();
            var username =$link.siblings(".username").html();
            var usernameAddress = $link.siblings(".username").attr("href");

            var img = $("<p style='text-align:center'></p>");
            "ProfilePage?accessFriend=111"
            if (image != undefined) {
                img.html("<a href=\"" + hyper + "\">" + title + "</a>"+"<br/>" +"<a href=\"" + usernameAddress + "\">" + username + "</a>"+ "<br/>" + "<img src=\'" + image + "\'width=\"96\" height=\"72\">" + "<p>" + content + "</p>");
            }
            else {
                img.html("<a href=\"" + hyper + "\">" + title + "</a>" + "<p>" + content + "</p>");
            }
            var linking = $("<a href=\"" + hyper + "\">");
            setTimeout(function () {
                img.dialog({
                    width: 400,
                    height: 400,
                    modal: true
                });
            }, 1);
        }

        <!-- Ths detection of each card of which icon or <a> has been clicked, which lead to different effects, plus the followed post calls to the ARticleCart servlet. -->
        $("ul.gallery > li").on("click", function (event) {

            var $item = $(this),
                $target = $(event.target);
            var hyper = $target.siblings("a").attr('href');
            var title = $target.siblings(".articlename").text();

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

        <!-- Card model sorting function  -->
        var things = [];
        var assemibled = false;
        var type = null;
        <!-- The defaultsorting algorithm comparator, which allow the cards' children to be selected based on what type is given -->
        function defaultSort(elementX, elementY) {

            if (elementX.children().siblings(type).text().toLowerCase() < elementY.children().siblings(type).text().toLowerCase())
                return -1;
            if (elementX.children().siblings(type).text().toLowerCase() > elementY.children().siblings(type).text().toLowerCase())
                return 1;
            return 0;
        }

        <!-- Function that is called when assemible the cards into the cart without actually adding into the session, this is due to the ui constructed as normal z-index alteration can't be used to shift the position due to not enough time delay. Hence allowing the cards to be put within the cart and reapply to the external space in sorted order instead. (The function of the ui only applies when element exsist during the load of the doc.) -->
        <!-- Adding all cards array which is used for defaultsorting.-->
        function attachment() {
            $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").each(function () {
                things.push($(this));
            });
            for (var i = 0; i < things.length; i++) {
                deleteImage($(things[i]));
            }
        }

        <!-- This is use to put back the cards for sorting within the external space, which also shift the first card out from the array making sure the array is empty. -->
        function display() {
            while (things.length > 0) {
                recycleImage($(things[0]));
                things.shift();
            }
        }

        <!-- The assemble function -->
        $("#sort").on('click', function () {
            if (assemibled === false) {
                attachment();
                assemibled = true;
            }
        });
        <!-- After assembled that allow the user to sort by title using the defaultsort and setting the type into the h5, which is the title -->
        $("#sorttitle").on('click', function () {
            type = ".articlename";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });
        <!-- After assembled that allow the user to sort by title using the defaultsort and setting the type into the catrgory class. -->
        $("#sortcategory").on('click', function () {
            type = ".Articlecategory";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });
        <!-- After assembled that allow the user to sort by title using the defaultsort and setting the type into the date class. -->
        $("#sortdate").on('click', function () {
            type = ".date";
            if (assemibled === true) {
                things.sort(defaultSort);
                display();
            }
            assemibled = false;
        });

        <!-- The card displaying function, as the cartlist is return from the session, which is the servlet that is from the ArticleCart. This will return array list from the attribute that is called cartlist. Due to the nature of this that is passed in as a string in the fashion of array, hence substring and regex spliting is needed. -->
        var cartlist = '<%= session.getAttribute("cartlist") %>';
        var cartArray = cartlist.substring(1, cartlist.length - 1).split(",");

        $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").each(function () {
            <!-- The reverse mathcing using the card's <a href> to match is there an exsisting string within the array that matches if so then run that card with the deleteImage function to move it to the cart at the doc ready for storage. The regex of "\\?" is used to escape the escape which in turns to escape the ? for accurate matching. -->
            var blah = ($(this).children().siblings(".articleid").attr('href').replace("?", "\\?"));
            var match = new RegExp(blah);
            for (var i = 0; i < cartArray.length; i++) {

                if (cartArray[i].match(match)) {

                    deleteImage($(this));
                    break;
                }

            }
        });
        $(".ui-widget-content.ui-corner-tr.ui-draggable.ui-draggable-handle:not(#save *)").click(function () {
            $(this).effect("shake");
        })
    });
</script>

</body>
</html>
