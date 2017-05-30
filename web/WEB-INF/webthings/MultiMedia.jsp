<%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 30/05/2017
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<c:forEach var="mediagroups" items="${mediaOutPut}">
    <c:forEach var="media" items="${mediagroups.value}">
        <p>${media}</p>
    </c:forEach>
</c:forEach>




</body>
</html>
