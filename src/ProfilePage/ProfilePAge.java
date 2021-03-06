package ProfilePage;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by ljam763 on 25/05/2017.
 */

//Javabean for ProfilePAge
public class ProfilePAge {

    private String username;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private java.sql.Date date;//formatting for date has been done TODO check.
    private boolean update;
    private String photoname;
    private String profilepic;
    private String introduction;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    public boolean isUpdate() {
        return update;
    }
    public void setUpdate(boolean update) {
        this.update = update;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public ProfilePAge() {
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
