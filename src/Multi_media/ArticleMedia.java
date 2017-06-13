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
import static Connection.ConnectionToTheDataBase.cookieTracker;

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
        String youtubeurl = req.getParameter("youtubeurl");
        System.out.println(youtubeurl);
        HttpSession session = req.getSession();
        if  (youtubeurl != null && youtubeurl.startsWith("https://www.youtube.com/")){
        if (youtubeurl.contains("/watch?v=")){
            youtubeurl= youtubeurl.replace("/watch?v=", "/embed/").replace("http(s)?:","");
        }
        System.out.println("Adding youtube");
        String youtubevideo= "<div class=\"embed-responsive embed-responsive-4by3\"><iframe class=\"embed-responsive-item\" src=\""+youtubeurl+"\"></iframe></div>";
        resp.getWriter().print(youtubevideo);
        }
        return;
    }
}
