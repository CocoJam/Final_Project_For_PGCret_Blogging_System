package Article;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class Articles {

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

    private int articleid = 0;
    private String articlename = null;
    private String username = null;
    private String content = null;
    private Date datecreated =null;
    private boolean owner= false;

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    private String firstimage = null;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }



    public Articles() {
    }

    public String cuttingLines (String thing){
        return thing.replace("\\n","").replace("\"","").replace("+","").replaceAll("<[^>]*>","").trim();
    }

    public String FirstImg (String thing){
        String ptr= "img alt=\"\" src\\s*=\\s*([\"'])?([^\"']*)";
        Pattern p = Pattern.compile(ptr);
        Matcher m = p.matcher(thing);
        if (m.find()) {

            return m.group(2);
        }
        return null;
    }

    public static void main(String[] args) {
        Articles articles= new Articles();
        String thing =("\n" +
                "    \n" +
                "        \n" +
                "            \n" +
                "    \n" +
                "        \n" +
                "            \n" +
                "    \n" +
                "        \n" +
                "            \n" +
                "    \n" +
                "        \n" +
                "        \n" +
                "            <!-- creates new section boxes -->\n" +
                "            <ul>\n" +
                "                <li style=\"position: relative;\" class=\"ui-state draggable ui-draggable ui-draggable-handle\"></li>\n" +
                "            </ul>\n" +
                "\n" +
                "            <!-- place where you drag stuff into for sorting their order -->\n" +
                "            <ul class=\"ui-sortable\" id=\"sortable\">\n" +
                "            <li style=\"\" id=\"1\" class=\"ui-state-default ui-sortable-handle\"><iframe src=\"https://www.youtube.com/embed/fvWXstffJBQ\" allowfullscreen=\"\" height=\"315\" width=\"560\" frameborder=\"0\"></iframe></li><li style=\"\" id=\"2\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/l0IykI5OLMhjtnB60/giphy.gif\"></li><li id=\"3\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/FxEwsOF1D79za/giphy.gif\"></li><li id=\"5\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/y8Mz1yj13s3kI/giphy.gif\"></li><li id=\"6\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/f6pOe5e8ShRhS/giphy.gif\"></li><li id=\"7\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/3oKIPkZx3Y9RS1wG9q/giphy.gif\"></li><li id=\"8\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/26ueZiMhTdWLIVGGk/giphy.gif\"></li><li style=\"\" id=\"9\" class=\"ui-state-default ui-sortable-handle\"><img alt=\"\" src=\"https://media.giphy.com/media/JSueytO5O29yM/giphy.gif\"></li></ul>\n" +
                "        \n" +
                "    \n" +
                "\n" +
                "        \n" +
                "        \n" +
                "    \n" +
                "\n" +
                "        \n" +
                "        \n" +
                "    \n" +
                "\n" +
                "        \n" +
                "        \n" +
                "    \n");


    }


}
