<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
//    String userPath = request.getRealPath("/Upload-photos/"+username) ;
    String userPath = request.getRealPath("/Upload-photos");
    File file = new File(userPath);
    String fileNames = findingDirectory(file, out);
    Set<String> list = findingDirectory(file, out);
    findingarticleId(file);
    for (String s : list) {
        if (s.endsWith(".flv") || s.endsWith(".m4v") || s.endsWith(".mp4") || s.endsWith(".mpg") || s.endsWith(".mpeg") || s.endsWith(".wmv")) {
            out.println("<video width=\"400\" controls> <source src="+ s +"></video>");
        } else if (s.endsWith(".mp3")) {
            out.println("<audio controls><source src=\""+ s +"\" type=\"audio/ogg\"> </audio>");
        } else if (s.endsWith(".jpg") || s.endsWith(".png")) {
            out.println("<img src=\""+ s +"\">");
        }
    }
%>


<%!
    Set<String> filepaths = new TreeSet<>();

    private Set<String> findingDirectory(File file, JspWriter out) {
        if (!file.isDirectory()) {
            File[] parent = file.getParentFile().listFiles();
            for (File file1 : parent) {
                filepaths.add(filePath(file1));
            }
        } else {
            File[] directory = file.listFiles();
            for (File file1 : directory) {
                findingDirectory(new File(file1.getPath()), out);
            }
        }
        return filepaths;
    }

    private String filePath(File file1) {
        return file1.getPath().substring(file1.getPath().indexOf("Upload-photos\\"));
    }

    private void findingarticleId(File file) {
        if (file.getPath().endsWith("asd")) {
            File[] parent = file.listFiles();
            for (File file1 : parent) {
                System.out.println(file1.getParentFile().getPath());
            }
            System.out.println("break");
            return;
        } else {
            if (file.isDirectory()) {
                File[] directory = file.listFiles();
                System.out.println(directory.length);
                for (File file1 : directory) {
                    findingarticleId(new File(file1.getPath()));
                }
            }
        }
    }
%>
</body>
</html>
