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
    <title>ArticleIndex -> Article</title>
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
<c:forEach items="${ArticleIndex}" var="index">
    <tr>
        <td>${index.articleid}</td>
        <td><a href="/Articles?articleID=${index.articleid}" >${index.articlename}</a></td>
        <td>${index.datecreated}</td>
    </tr>
</c:forEach>
</table>


<form action="/Articles" method="post">
    <input type="submit" name="add" value="addNewArticle" id="addNewArticle">
</form>
</body>
</html>


<%--//Java Bean cant access needed to be checked--%>
<%--private int ArticleID;--%>
<%--private String ArticleName;--%>
<%--private Date DateCreated;--%>
