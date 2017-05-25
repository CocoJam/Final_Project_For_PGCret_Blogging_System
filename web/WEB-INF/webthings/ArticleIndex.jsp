<%--
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
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>
            Article Numbers
        </th>
        <th>
            Article Names
        </th>
        <th>
            Date Created
        </th>
    </tr>
<c:forEach items="${ArticleIndex}" var="art">
    <tr>
        <%--<p>${index}</p>--%>
        <td>${index.ArticlesID}</td>
        <td><a href="" >${index.ArticlesName}</a></td>
        <td>${index.SpecificDateCreated}</td>
    </tr>
</c:forEach>
</table>

</body>
</html>


<%--//Java Bean cant access needed to be checked--%>
<%--private int ArticlesID;--%>
<%--private String ArticlesName;--%>
<%--private Date SpecificDateCreated;--%>
