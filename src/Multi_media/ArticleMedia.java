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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MediaDAO mediaDAO = new MediaDAO();
        HttpSession session = request.getSession();
        //Grabs session to get articleID
        try {
            ArticleID = Integer.parseInt(session.getAttribute("articleID") + "");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        String articleID = session.getAttribute("articleID") + "";
        ServletContext servletContext = getServletContext();
        String userPath = servletContext.getRealPath("/Upload-photos");
        File startingFile = new File(userPath);

//        Trilogy PART 4: This is the new hope see findingTheRightFile
        findingTheRightFile(startingFile, articleID);
        System.out.println(targetLocation + " Finding the target location");
        Set<String> list = new TreeSet<>();
        if (targetLocation != null) {
            Set<String> filepaths = new TreeSet<>();
            list = findingDirectory(new File(targetLocation), filepaths);
            System.out.println("here is the list " + list);
        }

        Map<String, List<String>> mediaMapping = mapSetUp();
        List<String> youtubeList = new ArrayList<>();

        youtubeList = mediaDAO.gettingAllYoutube(ArticleID);
        System.out.println(youtubeList);
        mediaMapping.put("youtube", youtubeList);

        assigningMultipleMediaIntoMap(list, mediaMapping);
        request.setAttribute("mediaOutPut", mediaMapping);

        closingConnection();
        request.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(request, response);
    }

    //This function finds the absolutely right file based on the name and not the path. TODO Need to consider security risks: Can find all folders. (see the vulnerability with endsWith())
    protected void findingTheRightFile(File file, String target) {
        if (file.getPath().endsWith(target)) {
            File[] parent = file.listFiles();
            for (File file1 : parent) {
                System.out.println(file1.getParentFile().getName());
                targetLocation = file1.getParent();
                System.out.println(targetLocation);
                return;
            }

        }
        if (file.isDirectory()) {
            File[] directory = file.listFiles();
            for (File file1 : directory) {
                System.out.println(file1.getPath());
                findingTheRightFile(new File(file1.getPath()), target);
            }
        }
    }


    //Post method to post a Youtube link. TODO this is stuffing everything up.
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String youtubeVideo = req.getParameter("youtube");
        HttpSession session = req.getSession();
        try {
            ArticleID = Integer.parseInt(session.getAttribute("articleID") + "");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        MediaDAO mediaDAO = new MediaDAO();
        mediaDAO.addingYoutubeToDatabase(ArticleID, youtubeVideo);
        closingConnection();

        //Sends doGet to go back
        doGet(req, resp);
    }
}
