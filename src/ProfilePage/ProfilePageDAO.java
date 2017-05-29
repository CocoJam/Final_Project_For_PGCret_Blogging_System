package ProfilePage;
import Connection.*;
import Login.*;
import java.sql.*;
/**
 * Created by ljam763 on 25/05/2017.
 */
public class ProfilePageDAO extends LoginPassing {

    private String usernames;
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private String profilepciture;
    private Date date;

    public ProfilePageDAO() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        this.pass = new Passwords_Checker();
    }

    public ProfilePAge getUsersProfile(String username)  {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Name ,Email, Address, Education, Ethnicity, DateOfBirth, profilePicture FROM UsersNames WHERE Username = ?;"
            );
            {
                System.out.println(statement);
                statement.setString(1, username);
                System.out.println(statement);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ProfilePAge profilePAge = makeProfilePAge(resultSet);
                    return profilePAge;
                }
            }
        } catch (SQLException e) {
            System.out.println("No ProfilePage under this username");
        }
        return null;
    }


    public ProfilePAge createUsersProfile(ProfilePAge profilePAge, String password) throws SQLException{
        ProfilePageGetters(profilePAge);
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO UsersNames (Username, Name, Email, Address, Education, Ethnicity , DateOfBirth, Password) VALUES( ?, ? ,?,?,?,?,?,?);"
            );
            {   statement.setString(1, usernames);
                sqlSetStatment( password, statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error there is such username already");
            System.out.println("SQL error");
            throw new SQLException();
        }
        return getUsersProfile(profilePAge.getUsername());
    }

    public ProfilePAge updataUsersProfile(String username, String password , ProfilePAge profilePAge, String newPassword) {
        ProfilePageGetters(profilePAge);
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE UsersNames SET Username=?, Name=?, Email=?, Address=?, Education=?, Ethnicity=?, DateOfBirth =?, Password=?, profilePicture=?  WHERE  Password= ? AND Username = ?;"
            );
            {   statement.setString(1, username);
                sqlSetStatment(password,statement);
                statement.setString(8,newPassword);
                statement.setString(9,profilepciture);
                statement.setString(10,password);
                statement.setString(11, usernames);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Can not update user infor based on the username and password");
        }
        return getUsersProfile(username);
    }



    private void sqlSetStatment(String password, PreparedStatement statement) throws SQLException {

        statement.setString(2, name);
        statement.setString(3, email);
        statement.setString(4, address);
        statement.setString(5, education);
        statement.setString(6, ethnicity);
        statement.setDate(7,date);
        statement.setString(8,password);
    }


    private ProfilePAge makeProfilePAge(ResultSet resultSet) throws SQLException {
        ProfilePAge profilePAge = new ProfilePAge();
        sqlGetStatments(resultSet);
        profilePAge.setUsername(usernames);
        profilePAge.setName(name);
        profilePAge.setEmail(email);
        profilePAge.setAddress(address);
        profilePAge.setEducation(education);
        profilePAge.setEthnicity(ethnicity);
        profilePAge.setDate(date);
        profilePAge.setProfilepic(profilepciture);
        System.out.println("setting "+ profilepciture);
        return profilePAge;
    }

    private void sqlGetStatments(ResultSet resultSet) throws SQLException {
        usernames = resultSet.getString(1);
        name = resultSet.getString(2);
        email = resultSet.getString(3);
        address = resultSet.getString(4);
        education = resultSet.getString(5);
        ethnicity = resultSet.getString(6);
        date = resultSet.getDate(7);
        profilepciture = resultSet.getString(8);
    }

    private void ProfilePageGetters (ProfilePAge profilePAge){
        usernames = profilePAge.getUsername();
        name= profilePAge.getName();
        email = profilePAge.getEmail();
        address =profilePAge.getAddress();
        education = profilePAge.getEducation();
        ethnicity = profilePAge.getEthnicity();
        date = profilePAge.getDate();
        profilepciture = profilePAge.getProfilepic();
        System.out.println("getting :"+ profilepciture);
    }


}
