package Multi_media;

import java.util.Random;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class Media {

    private int articleID;
    private String youtubeurl;
    private String youurl;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getYoutubeurl() {
        return youtubeurl;
    }

    public void setYoutubeurl(String youtubeurl) {
        this.youtubeurl = youtubeurl;
    }

    public String getYouurl() {
        return youurl;
    }

    public void setYouurl(String youurl) {
        this.youurl = youurl;
    }

    public String alternatingUrl(String name){
        return name.substring(youtubeurl.indexOf("https"),youtubeurl.indexOf("\" frameborder"));
    }

}
