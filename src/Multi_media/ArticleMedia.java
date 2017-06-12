package Multi_media;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static Connection.ConnectionToTheDataBase.closingConnection;
import static Connection.ConnectionToTheDataBase.cookieLogOut;

/**
 * Created by ljam763 on 31/05/2017.
 */

//This extends Upload_files because to reuse the doPost of the upload folders and finding director TODO consider making the relevant methods in the Upload_files as STATIC methods.

public class ArticleMedia extends Upload_files {
    private String targetLocation;
    private Set<String> filepaths;
    private int ArticleID;

    public ArticleMedia() {
        this.filepaths = new TreeSet<>();
    }

//    //This function finds the absolutely right file based on the name and not the path. TODO Need to consider security risks: Can find all folders. (see the vulnerability with endsWith())
//    protected void findingTheRightFile(File file, String target) {
//        if (file.getPath().endsWith(target)) {
//            File[] parent = file.listFiles();
//            for (File file1 : parent) {
//                targetLocation = file1.getParent();
//                return;
//            }
//
//        }
//        if (file.isDirectory()) {
//            File[] directory = file.listFiles();
//            for (File file1 : directory) {
//                findingTheRightFile(new File(file1.getPath()), target);
//            }
//        }
//    }


    //Post method to post a Youtube link. TODO this is stuffing everything up.
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req,resp);
        String youtubeVideo = req.getParameter("youtube").replaceAll("<(/?script[^>]*)>", "");
        HttpSession session = req.getSession();
        if (youtubeVideo.contains("/watch?v=")){
            youtubeVideo= youtubeVideo.replace("/watch?v=", "/embed/");
        }
        System.out.println("Adding youtube");
        String youtube = "<iframe width=\"854\" height=\"480\" src=\"" + youtubeVideo + "\" frameborder=\"0\" allowfullscreen></iframe>";
        resp.getWriter().print(youtube);
    }
}
