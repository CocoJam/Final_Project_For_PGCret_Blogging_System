<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %><%--
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
    String userPath = request.getRealPath("/Upload-photos/hello") ;
    File file = new File(userPath);
//    String fileNames = findingDirectory(file, out);
    findingDirectory(file, out);
%>


<%!
    private String findingDirectory(File file, JspWriter out) {
        if (!file.isDirectory()){
            File[] parent = file.getParentFile().listFiles();
            String everything ="";
            for (File file1 : parent) {
                everything+= " "+file1.getPath();
                try {
                    out.println("<p>"+file1.getName()+"</p>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return everything;
        }
        File[] directory = file.listFiles();
        for (File file1 : directory) {
            return findingDirectory(new File(file1.getPath()),out);
        }
        return null;
    }
%>
</body>
</html>
