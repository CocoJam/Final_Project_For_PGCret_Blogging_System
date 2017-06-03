package Multi_media;

import java.util.Random;

/**
 * Created by ljam763 on 31/05/2017.
 */
public class Media {
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

    private int articleID;
    private String youtubeurl;

    public String getYouurl() {
        return youurl;
    }

    public void setYouurl(String youurl) {
        this.youurl = youurl;
    }

    private String youurl;

    public String alternatingUrl(String name){
        return name.substring(youtubeurl.indexOf("https"),youtubeurl.indexOf("\" frameborder"));
    }


    public static void main(String[] args) {
        Random rand = new Random();
        int random_integer = rand.nextInt(255-1) + 1;
        int random_integer2 = rand.nextInt(255-1) + 1;
        System.out.println(random_integer);
        System.out.println(random_integer2);
    }

}
