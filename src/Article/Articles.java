package Article;

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
    private Date datecreated =null;
    private boolean owner= false;
    private String category = null;

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
                "                                    \n" +
                "                                        \n" +
                "                                        \n" +
                "                                            <!-- creates new section boxes -->\n" +
                "                                            <ul>\n" +
                "                                                <li class=\"ui-state draggable ui-draggable ui-draggable-handle\" style=\"position: relative;\"></li>\n" +
                "                                            </ul>\n" +
                "\n" +
                "                                            <!-- place where you drag stuff into for sorting their order -->\n" +
                "                                            <ul id=\"sortable\" class=\"ui-sortable\">\n" +
                "                                            <li class=\"ui-state-default \">pokepokepoke</li></ul>\n" +
                "                                        \n" +
                "                                    \n" +
                "                                ");
        String lines = articles.cuttingLines(thing);
        System.out.println(lines);
    }


}
