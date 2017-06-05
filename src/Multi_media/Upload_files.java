package Multi_media;


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

import java.util.*;


/**
 * Created by ljam763 on 28/05/2017.
 */


/**
 * Created by James lam on 14/05/2017.
 */

//This is uploading and viewing multimedia TODO rename servlet to multimedia
public class Upload_files extends HttpServlet {
    public Upload_files() {
        super();
    }

    private String filePath;
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 5000 * 1024;
    private File file;
    private String fileName;
    private String caption;
    private String dir_name;
    //    private Set<String> filepaths;
    private String userPath;
    private String username;


    public void init() {
        System.out.println();
    }

    //doPost SHOULD come from 2 places (1) Upload while changing profile; (2)upload inside article itself (but currently it is stuffing up)
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
            System.out.println("username File is made: " + yes);
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

                    //This is the differentiator where it checks where it is from. ArticleMedia class stuffed things up after this was added in. CREATE Article ID folder. TODO currently not working need fixes.
                    if (caption.equals("ArticlesUpload")) {
                        String filing = ((int) session.getAttribute("articleID")) + "/";
                        filePath += filing;
                        File Article = new File(filePath);
                        if (!Article.exists()) {
                            boolean made = Article.mkdir();
                            System.out.println("ArticleFile: " + made);
                        }
                    }

                    //Filters writing into right folder
                    System.out.println(filePath);
                    if (fileName.endsWith(".flv") || fileName.endsWith(".m4v") || fileName.endsWith(".mp4") || fileName.endsWith(".mpg") || fileName.endsWith(".mpeg") || fileName.endsWith(".wmv")) {
                        FormingVideoFileAndVideo();
                    } else if (fileName.endsWith(".mp3")) {
                        FormingAudioFileAndAudio();
                    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                        FormingPhotoFileAndPhoto();
                    }
                    fileNameEditting(); //this slims down the filenname before it is written
                    fi.write(file);
                    if (caption.equals("ArticlesUpload")) {
//                        req.getRequestDispatcher("/WEB-INF/webthings/Article.jsp").forward(req, resp);
                        req.getRequestDispatcher("/ArticleUpload").forward(req, resp);
                    }
//                    } else if (caption.equals("addNewArticle")){
//                        req.getRequestDispatcher("/WEB-INF/webthings/ArticleCreationPage.jsp").forward(req, resp);
//                    }
                    else {
                        req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
                    }
                    return;
                } else {
                    System.out.println("somthing else is throwing here");
                }
            }
        } catch (FileUploadException ex) {
            System.out.println("Upload not successful due to the file size exceeding limit");
            req.getRequestDispatcher("/WEB-INF/webthings/registration_page.jsp").forward(req, resp);
            System.out.println(ex);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    //altering filepath to put into the photo folder.
    private void FormingPhotoFileAndPhoto() {
        filePath += "photo";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("photoFile: " + made);
        }
        filePath += "/";
    }

    //altering filepath to put into the audio folder.
    private void FormingAudioFileAndAudio() {
        filePath += "audio";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("audioFile: " + made);
        }
        filePath += "/";
    }

    //altering filepath to put into the video folder.
    private void FormingVideoFileAndVideo() {
        filePath += "video";
        File videoFile = new File(filePath);
        if (!videoFile.exists()) {
            boolean made = videoFile.mkdir();
            System.out.println("videoFile: " + made);
        }
        filePath += "/";
    }

    //This slims down the filename for ease of access purposes.
    private void fileNameEditting() {
        if (fileName.lastIndexOf("\\") >= 0) {
            file = new File(filePath +
                    fileName.substring(fileName.lastIndexOf("\\")));
        } else {
            file = new File(filePath +
                    fileName.substring(fileName.lastIndexOf("\\") + 1));
        }
    }


    //doGET is when you want to show the media, both for self and all.
    //Grab the parameter called media which is submitted by the submitmedia hyperlink button, make file and path

    //Only tested on jsp due to database issue (TestingMultiMedia.jsp as testing grounds), but this should work ing theory.
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, java.io.IOException {

        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        String media = request.getParameter("media");
        allOrSelf(media, request);

        File file = new File(userPath);

        Set<String> filepaths = new TreeSet<>();

        System.out.println(filepaths.size() + " paths");
        //This is the recursions, finding all the directories. (START OF TRILOGY 1) TODO unify the 3 trilogies together.
        Set<String> list = findingDirectory(file, filepaths);
        Map<String, List<String>> mediaMapping = mapSetUp();

        assigningMultipleMediaIntoMap(list, mediaMapping);
        request.setAttribute("mediaOutPut", mediaMapping);
        request.getRequestDispatcher("/WEB-INF/webthings/MultiMedia.jsp").forward(request, response);
    }

    //Trilogy 3: Using end of the file name to sort into the right folders.
    protected void assigningMultipleMediaIntoMap(Set<String> list, Map<String, List<String>> map) {
        for (String s : list) {
            if (s.endsWith(".flv") || s.endsWith(".m4v") || s.endsWith(".mp4") || s.endsWith(".mpg") || s.endsWith(".mpeg") || s.endsWith(".wmv")) {
                map.get("video").add(s);
            } else if (s.endsWith(".mp3")) {
                map.get("audio").add(s);
            } else if (s.endsWith(".jpg") || s.endsWith(".png")) {
                map.get("photo").add(s);
            }
        }
    }

    private void allOrSelf(String media, HttpServletRequest request) {
        if (media != null) {
            ServletContext servletContext = getServletContext();
            if (media.equals("all")) {
                request.setAttribute("AllOrSelf", "all");
                userPath = servletContext.getRealPath("/Upload-photos");
            } else if (media.equals("self")) {
                request.setAttribute("AllOrSelf", "self");
                userPath = servletContext.getRealPath("/Upload-photos/" + username);
            }
        }
    }

    //Trilogy 2: map of name of media
    protected Map<String, List<String>> mapSetUp() {
        Map<String, List<String>> map = new TreeMap<>();
        List<String> video = new ArrayList<>();
        List<String> audio = new ArrayList<>();
        List<String> photo = new ArrayList<>();
        map.put("video", video);
        map.put("audio", audio);
        map.put("photo", photo);
        return map;
    }

    //This is danger needed to check is there a article number as such before running this since it is recussion.
    //This function uses recussion to find the leaf files from the file input onwards.
    protected Set<String> findingDirectory(File file, Set<String> filepaths) {

        System.out.println(file + " finding the directory");
        if (!file.isDirectory()) {

            File[] parent = file.getParentFile().listFiles();
            for (File file1 : parent) {
                filepaths.add(filePath(file1));
            }
            return filepaths;
        } else {
            File[] directory = file.listFiles();
            for (File file1 : directory) {
                findingDirectory(new File(file1.getPath()), filepaths);
            }
        }
        return filepaths;
    }

    private String filePath(File file1) {
        return file1.getPath().substring(file1.getPath().indexOf("Upload-photos\\"));
    }

}

