<%--
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
    <title>ProfilePage.Registration -> Profile Page</title>
</head>
<body>

<%
    if ( session.getAttribute("log") != null && session.getAttribute("Registration") != null){
        if ((boolean)session.getAttribute("Registration")){
            request.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(request,response);}
    }
%>


<form action="/Registration" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username"  name="username" value="${profileInfo.username}">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${password}">
    <label for="Name">Name:</label>
    <input type="text" id="Name" name="name" value="${profileInfo.name}">
    <label for="email">email:</label>
    <p>${profileInfo.email}</p>
    <input type="email" id="email" name="email">
    <label for="address">address:</label>
    <input type="text" id="address" name="address" value="${profileInfo.address}">
    <label for="education">education:</label>
    <input type="text" id="education" name="education" value="${profileInfo.education}">
    <label for="ethnicity">ethnicity:</label>
    <input type="text" id="ethnicity" name="ethnicity" value="${profileInfo.ethnicity}">
    <label for="date">date:</label>
    <input type="date" id="date" name="date" value="${profileInfo.date}">
    <c:choose>
    <c:when  test="${log.equals('Update')}">
            <input type="submit" name="log" value="ChangeUserInformation">
    </c:when>
        <c:otherwise>
            <input type="submit" name="log" value="Registration" >
        </c:otherwise>
    </c:choose>
</form>

<script>
    document.getElementById("email").value = ${profileInfo.email};
    $("form").submit(function (event) {
        if (!$("#username").val() =="" || !$("#password").val()=="" ) {
            document.getElementById("email").value = ${profileInfo.email};
            return;
        }
        event.preventDefault();
    })
</script>

</body>
</html>
<%--Update user information section done--%>
<%--user JS to block username and password empty fields--%>
