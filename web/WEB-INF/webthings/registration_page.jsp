<%@ page import="java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.jms.Session" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 23/05/2017
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <title>ProfilePage.Registration -> Profile Page</title>
</head>
<body>
<%! Set<String> listofphotos = new TreeSet<>(); %>

<%--This is to check if person has logged in and if so it will not goto the registration page, so this is another bouncing mechanism. This is a duplicate of the servlet bouncing mechanism.--%>
<%
    if (session.getAttribute("log") != null && session.getAttribute("Registration") != null) {
        if ((boolean) session.getAttribute("Registration")) {
            request.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(request, response);
        }
    }
%>

<%--1st part: Grabbing all the strings of all the photo names and adding into a set. (NOTE the actual upload of the image is in the servlet)--%>
<% String userPath = request.getRealPath("/Upload-photos");
    System.out.println(userPath + " Paths");
    String username = (String) session.getAttribute("username");
    File listofThings = new File(userPath + "/" + username + "/photo");
    System.out.println(listofThings.getPath());
    File[] files = listofThings.listFiles();
    if (files != null) {
        for (File file : files) {
            listofphotos.add(file.getName());
        }
    }
%>

<%--This form is generic for both states of logged in/register new user. The reason is if a new registration is taking place the values with profileInfo will be empty and not display anything and vice versa--%>
<form action="/Registration" id="form" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="${profileInfo.username}">
    <p id="reponseToUsername"></p>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${password}">
    <label for="Name">Name:</label>
    <input type="text" id="Name" name="name" value="${profileInfo.name}">
    <label for="email">email:</label>
    <input type="email" id="email" name="email" value="${profileInfo.email}">
    <label for="address">address:</label>
    <input type="text" id="address" name="address" value="${profileInfo.address}">
    <label for="education">education:</label>
    <input type="text" id="education" name="education" value="${profileInfo.education}">
    <label for="ethnicity">ethnicity:</label>
    <input type="text" id="ethnicity" name="ethnicity" value="${profileInfo.ethnicity}">
    <label for="date">date:</label>
    <input type="date" id="date" name="date" value="${profileInfo.date}"> <%--THIS   DOES NOT WORK IN IE IE DONT USE IE--%>

    <%--2nd part: uses the above set to display the images in the set, by going through the list of photoname strings, and then accessing by pathing in the photo by the username folder--%>
    <% if (username != null) {
        for (String listofphoto : listofphotos) {
            System.out.println(listofphoto);
            out.println(" <input type=\"radio\" name=\"profilePicture\" value=\"" + listofphoto + "\"> <img  src=\"Upload-photos/" + username + "/photo/" + listofphoto + "\"><br>");
        }
    }
    %>
    <%--This is to differentiate between whether user is logged in or not, and displays different button functionality--%>

    <c:choose>
        <c:when test="${log}">
            <input type="submit" name="log" value="ChangeUserInformation">
        </c:when>
        <c:otherwise>
            <input type="submit" name="log" value="Registration">
        </c:otherwise>
    </c:choose>
</form>

<form action="/Upload" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <input type="submit" name="Upload" value="ProfileUpload"/>
    <br>
</form>

<%--Button is to check for availability of username--%>
<%--TODO Extra: add functionality to allow when click away that ajax function sent--%>
<button id="responseToCheck">Check For UserNames</button>

<script type="text/javascript">
    <%--SCRIPT onclick on FORM above. Checks if form contains any space or empty, if so then submission will be --%>
    $("#form").submit(function (event) {
        if ($("#username").val().startsWith(" ") || $("#password").val().startsWith(" ") || $("#username").val() == "" || $("#password").val() == "") {
            alert("validation failed false");
            event.preventDefault();
            return;
        }
        alert("validations passed");
        return;
    });

//    Sending AJAX call to /Registration servlet by GET method which goes to Database and matches the typed username and comes back with boolean which confirms availability.
    //TODO the boolean appears to be returning true all the time, check if right message being sent back by doGET method in Registration.java

    console.log($("#username").val());
    $("#responseToCheck").click(function () {
        $.ajax({
            url: '/Registration',
            type: 'GET',
            data: {"log": "RegistrationCheck", "usernameCheck": $("#username").val()},
            success: function (msg) { //specifically msg is coming back with the boolean.
                console.log(msg);
                $(reponseToUsername).text(msg);
                //msg is returning a boolean for check if false meaning no such username so ok
                if (msg) {
                    event.preventDefault();
                }
            }
        });
    })
</script>
</body>
</html>
<%--Update user information section done--%>
<%--user JS to block username and password empty fields--%>
