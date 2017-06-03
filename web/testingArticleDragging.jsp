<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 3/06/2017
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <%--<style>--%>
    <%--#draggable { width: 100px; height: 100px; padding: 0.5em; float: left; margin: 10px 10px 10px 0; }--%>
    <%--#droppable { width: 150px; height: 150px; padding: 0.5em; float: left; margin: 10px; }--%>
    <%--</style>--%>
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
    <title>Title</title>

</head>
<body>
<p id="testing">asd</p>
<%--<p class="testing">asd</p>--%>
<textarea id="textarea"></textarea>


<%--<div id="draggable" class="ui-widget-content">--%>
<%--<p>Drag me to my target</p>--%>
<%--</div>--%>

<%--<div id="droppable" class="ui-widget-header">--%>
<%--<p>Drop here</p>--%>
<%--</div>--%>
<div id="contents">
<ul>
    <li id="draggable" class="ui-state"><img src="/Jellyfish.jpg" width="50" length="50"></li>
</ul>

<ul id="sortable">
    <li class="ui-state-default"><p></p></li>
    <%--<li class="ui-state-default">Item 2</li>--%>
    <%--<li class="ui-state-default">Item 3</li>--%>
    <%--<li class="ui-state-default">Item 4</li>--%>
    <%--<li class="ui-state-default">Item 5</li>--%>
</ul>
</div>
</body>
<script>
    //    console.log($(".ui-state-default").eq(0).text());
    //    var number = 0;
    //    $("#textarea").bind('input',function () {
    //        console.log(number);
    //        console.log($("#textarea").val());
    //        console.log("changing");
    //        $("#testing").text($("#textarea").val());
    //    });
    //    $("#textarea").on('keyup', function (e) {
    //        if (e.keyCode == 13) {
    //            console.log(number);
    //
    //            var paragraph = document.createElement("p");
    //            paragraph.text($("#textarea").val());
    //            paragraph.className = "ui-state-default";
    //            $("#sortable").append(paragraph);
    //            $("#textarea").val("");
    //            number++;
    //        }
    //    });


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
        if (e.keyCode == 16){
            console.log($("#contents").html())
        }
    });
    $(function () {
        $("#sortable").sortable({
            revert: true
        });
        $("#draggable").draggable({
            connectToSortable: "#sortable",
            helper: "clone",
            revert: "invalid"
        });
        $("ul, li").disableSelection();
    });


    //    $( function() {
    //        $( "#draggable" ).draggable();
    //        $( "#droppable" ).droppable({
    //            drop: function( event, ui ) {
    //                $( this )
    //                    .addClass( "ui-state-highlight" )
    //                    .find( "p" )
    //                    .html( "Dropped!" );
    //            }
    //        });
    //    } );


    //var number = 0;
    //$("#textarea").bind('input',function () {
    //    console.log(number);
    //    console.log($("#textarea").val());
    //    console.log("changing");
    //    $(".testing").eq(number).text($("#textarea").val());
    //});
    //    $("#textarea").on('keyup', function (e) {
    //        if (e.keyCode == 13) {
    //            console.log(number);
    //            $("#textarea").val("");
    //            var paragraph = document.createElement("p");
    //            paragraph.className = "testing";
    //            $(".testing").eq(number).append(paragraph);
    //            number++;
    //        }
    //    });

</script>
</html>
