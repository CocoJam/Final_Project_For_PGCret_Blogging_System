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

<%--if (s.endsWith(".flv") || s.endsWith(".m4v") || s.endsWith(".mp4") || s.endsWith(".mpg") || s.endsWith(".mpeg") || s.endsWith(".wmv")) {--%>
<%--map.get("video").add(s);--%>
<%--} else if (s.endsWith(".mp3")) {--%>
<%--map.get("audio").add(s);--%>
<%--} else if (s.endsWith(".jpg") || s.endsWith(".png")) {--%>
<%--map.get("photo").add(s);--%>
<%--}--%>

<%--Triology Part 3.5 TODO display this in tables--%>

<c:forEach var="mediagroups" items="${mediaOutPut}">
    <c:if test="${mediagroups.key.equals(\"photo\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <img src="${media}">
        </c:forEach>
    </c:if>
    <c:if test="${mediagroups.key.equals(\"audio\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <audio controls><source src="${media}" type="audio/ogg"> </audio>
        </c:forEach>
    </c:if>
    <c:if test="${mediagroups.key.equals(\"video\")}">
        <c:forEach var="media" items="${mediagroups.value}">
            <video width="400" controls> <source src="${media}"></video>
        </c:forEach>
    </c:if>
</c:forEach>


<audio controls><source src="" type="audio/ogg"> </audio>

</body>
</html>
