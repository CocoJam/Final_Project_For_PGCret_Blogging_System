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
        try {
            ArticleID = Integer.parseInt(session.getAttribute("articleID")+"");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        String articleID = session.getAttribute("articleID")+"";
        ServletContext servletContext = getServletContext();
        String userPath = servletContext.getRealPath("/Upload-photos");
        File startingFile = new File(userPath);
        findingTheRightFile(startingFile,articleID);
        Set<String> filepaths = new TreeSet<>();
        Set<String> list = findingDirectory(new File(targetLocation),filepaths);
        System.out.println("here is the list " + list );
        Map<String, List<String>> mediaMapping = mapSetUp();
        List<String> youtubeList = new ArrayList<>();
        youtubeList = mediaDAO.gettingAllYoutube(ArticleID);
        mediaMapping.put("youtube",youtubeList);
        assigningMultipleMediaIntoMap(list, mediaMapping);
        request.setAttribute("mediaOutPut", mediaMapping);
        closingConnection();
        request.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(request, response);
    }

    protected void findingTheRightFile(File file, String target) {
        if (file.getPath().endsWith(target)) {
            File[] parent = file.listFiles();
            for (File file1 : parent) {
                targetLocation = file1.getParent();
                System.out.println(targetLocation);
                return;
            }

        }
        if (file.isDirectory()) {
            File[] directory = file.listFiles();
            for (File file1 : directory) {
                findingTheRightFile(new File(file1.getPath()), target);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String youtubeVideo = req.getParameter("youtube");
       HttpSession session = req.getSession();
        try {
            ArticleID = Integer.parseInt(session.getAttribute("articleID")+"");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        MediaDAO mediaDAO = new MediaDAO();
        mediaDAO.addingYoutubeToDatabase(ArticleID, youtubeVideo);
        closingConnection();
        doGet(req,resp);
    }
}
