<%--
  Created by IntelliJ IDEA.
  User: J Won
  Date: 28/05/2017
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>

<fieldset>
    <legend>Comments</legend>

    <form action="/Comments" method="post">
        <label for="commentID">Hello ${Commenter}, comment now: </label>
        <input type="text" id="commentID" name="commentText">

        <input type="submit" name="commentSubmit" value="submitComment">
    </form>
    <c:forEach var="Comment" items="${commentList}">
        <section class="Comment">
            <p>
                ${Comment.comment}
            </p>

        </section>
    </c:forEach>


</fieldset>

</body>
</html>
