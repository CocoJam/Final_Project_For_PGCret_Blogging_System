package Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class Articles {

    private int articleid = 0;
    private String articlename = null;
    private String username = null;
    private String content = null;
    private Date datecreated = null;
    private boolean owner = false;
    private String category = null;
    private String firstimage = null;
    private int likeNumber = 0;
    private boolean liked = false;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }


    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }


    //Normal getters and setters
    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Articles() {
    }

    //This method helps cleaning the lines for the content of the article, which is greatly inference by the text editor.
    public String cuttingLines(String thing) {
        return thing.replace("\\n", "").replace("\"", "").replace("+", "").replaceAll("<[^>]*>", "").trim();
    }

    //This method is using regex to find the first image within the article, which ignores the other.
    public String FirstImg(String thing) {
        Document doc = Jsoup.parse(thing);
        Element img = doc.select("img").first();
        String src = null;
        if (img != null) {
            src = img.attr("src");
        }
        return src;
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+");
        Matcher matcher = pattern.matcher("https://www.youtube.com/watch?v=pHs4riipoS8&list=PLXqOIuKcLjKf5arUaE530VYdt8aKiH6Ig");
        matcher.matches();
        String whatYouNeed = matcher.group(0);
        System.out.println(whatYouNeed.substring(matcher.end(matcher.groupCount())).replace("/watch?v=",  "/embed/"));
        System.out.println("https://www.youtube.com"+ whatYouNeed.substring(matcher.end(matcher.groupCount())).replace("/watch?v=",  "/embed/"));
    }

}

