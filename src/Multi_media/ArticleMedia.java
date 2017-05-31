package Multi_media;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class ArticleMedia extends Upload_files {
    private String targetLocation;
    private Set<String> filepaths;
    public ArticleMedia() {
        this.filepaths = new TreeSet<>();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String articleID = session.getAttribute("articleID")+"";
        ServletContext servletContext = getServletContext();
        String userPath = servletContext.getRealPath("/Upload-photos");
        File startingFile = new File(userPath);
        findingTheRightFile(startingFile,articleID);
        Set<String> list = findingDirectory(new File(targetLocation));
        Map<String, List<String>> mediaMapping = mapSetUp();
        assigningMultipleMediaIntoMap(list, mediaMapping);
        request.setAttribute("mediaOutPut", mediaMapping);
        request.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(request, response);
    }

    protected void findingTheRightFile(File file, String target) {
        if (file.getPath().endsWith(target)) {
            File[] parent = file.listFiles();
            for (File file1 : parent) {
                System.out.println(file1 + " found");
                System.out.println(file1.getParent());
                targetLocation = file1.getParent();
                return;
            }
            System.out.println("break");
        }
        if (file.isDirectory()) {
            System.out.println(file);
            File[] directory = file.listFiles();
            System.out.println(directory.length);
            for (File file1 : directory) {
                findingTheRightFile(new File(file1.getPath()), target);
            }
        }
    }
}
