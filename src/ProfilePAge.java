import java.lang.reflect.Field;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ProfilePAge {

    private String username;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public static void main(String[] args) {
        ProfilePAge profilePAge = new ProfilePAge ();
        Field[] fields =  profilePAge.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }

    }
}
