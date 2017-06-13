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



    //Post method to post a Youtube link. TODO this is stuffing everything up.
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieLogOut(req,resp);
        String youtubeVideo = req.getParameter("youtube");
        HttpSession session = req.getSession();
        if (youtubeVideo.contains("/watch?v=")){
            youtubeVideo= youtubeVideo.replace("/watch?v=", "/embed/").replace("http(s)?:","");
        }
        System.out.println("Adding youtube");
        String youtubevideo= "<div class=\"embed-responsive embed-responsive-4by3\"><iframe class=\"embed-responsive-item\" src=\""+youtubeVideo+"\"></iframe></div>";

//        String youtube = "<iframe width=\"854\" height=\"480\" src=\"" + youtubeVideo + "\" frameborder=\"0\" allowfullscreen></iframe>";
        resp.getWriter().print(youtubevideo);
    }
}
