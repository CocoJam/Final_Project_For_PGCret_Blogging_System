<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: ljam763
  Date: 29/05/2017
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
//    String userPath = request.getRealPath("/Upload-photos/"+username) ;
    String userPath = request.getRealPath("/Upload-photos/"+username) ;
    File file = new File(userPath);
    String[] names = file.list();

    for(String name : names)
    {
        if (new File("C:\\Windows\\" + name).isDirectory())
        {
            System.out.println(name);
        }
    }
%>


</body>
</html>
