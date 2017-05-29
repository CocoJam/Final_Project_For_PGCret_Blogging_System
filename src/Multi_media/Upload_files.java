package Multi_media;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServlet;
import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.util.Iterator;
import java.util.List;


/**
 * Created by ljam763 on 28/05/2017.
 */


/**
 * Created by James lam on 14/05/2017.
 */
public class Upload_files extends HttpServlet {
    public Upload_files() {
        super();
    }

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 5000 * 1024;
    private File file;
    private String fileName;
    private String caption;
    private String value;
    private String dir_name;


    public void init() {
        System.out.println();
    }

    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        HttpSession session = req.getSession();
        caption = (String) session.getAttribute("Upload");
        System.out.println(caption);
        System.out.println("What");
        ServletContext servletContext = getServletContext();
        filePath = servletContext.getRealPath("/Upload-photos");
        File uploads = new File(filePath);
        if (!uploads.exists()) {
            System.out.println("upload-photos");
            boolean made = uploads.mkdir();
            System.out.println(made);
        }
        filePath = servletContext.getRealPath("/Upload-photos") + "/";
        dir_name = (String) session.getAttribute("username");
        File dir = new File(filePath + dir_name);
        if (!dir.exists()) {
            boolean yes = dir.mkdir();
            System.out.println(dir.getPath());
            System.out.println(yes);
        }

        //needed to make a music, photos and audio files for each user.

        filePath = dir.getPath() + "/";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            List fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    if (caption.equals("ArticlesUpload")) {
                        String filing = ((int) session.getAttribute("articleID")) + "/";
                        filePath += filing;
                        File Article = new File(filePath);
                        if (!Article.exists()) {
                            boolean made = Article.mkdir();
                            System.out.println("ArticleFile: " + made);
                        }
//                        filePath += "/";
                    }
                    if (fileName.endsWith(".flv") || fileName.endsWith(".m4v") || fileName.endsWith(".mp4") || fileName.endsWith(".mpg") || fileName.endsWith(".mpeg") || fieldName.endsWith(".wmv")) {
                        FormingVideoFileAndVideo();
                    } else if (fileName.endsWith(".mp3")) {
                        FormingAudioFileAndAudio();
                    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                        FormingPhotoFileAndPhoto();
                    }
                    fileNameEditting();
                    fi.write(file);
                    if (caption.equals("ArticlesUpload")) {
                        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                    } else {
                        req.getRequestDispatcher("/WEB-INF/webthings/ProfilePage.jsp").forward(req, resp);
                    }
                    return;
                } else {
                    System.out.println("somthing else is throwing here");
                }
            }
        } catch (FileUploadException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }


    }

    private void FormingPhotoFileAndPhoto() {
        filePath += "photo";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("photoFile: " + made);
        }
        filePath += "/";
    }

    private void FormingAudioFileAndAudio() {
        filePath += "audio";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("audioFile: " + made);
        }
        filePath += "/";
    }

    private void FormingVideoFileAndVideo() {
        filePath += "video";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("videoFile: " + made);
        }
        filePath += "/";
    }

    private void fileNameEditting() {
        if (fileName.lastIndexOf("\\") >= 0) {
            file = new File(filePath +
                    fileName.substring(fileName.lastIndexOf("\\")));
        } else {
            file = new File(filePath +
                    fileName.substring(fileName.lastIndexOf("\\") + 1));
        }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, java.io.IOException {

    }
}

