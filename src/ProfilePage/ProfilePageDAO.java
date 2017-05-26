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
    private Date date;

    public ProfilePageDAO() {
        ConnectionToTheDataBase.ConnectionToBase(ConnectionToTheDataBase.ConnectionTypes.Get);
        this.conn = ConnectionToTheDataBase.conn;
        this.pass = new Passwords_Checker();
    }

    public ProfilePAge getUsersProfile(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Name ,Email, Address, Education, Ethnicity, DateOfBirth FROM UsersNames WHERE Username = ?;"
            );
            {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ProfilePAge profilePAge = makeProfilePAge(resultSet);
                    return profilePAge;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void createUsersProfile(String username, String password, String name, String email, String address, String education, String ethnicity,Date DateOfBirth) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO UsersNames (Username, Name, Email, Address, Education, Ethnicity , DateOfBirth, Password) VALUES( ?, ? ,?,?,?,?,?,?);"
            );
            {
                sqlSetStatment(username, password, name, email, address, education, ethnicity, DateOfBirth, statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updataUsersNames(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE UsersNames SET Username=?, Name=?, Email=?, Address=?, Education=?, Ethnicity=?, DateOfBirth =? , , Password= ? WHERE Username = ?, Password= ?;"
            );
            {
//                sqlSetStatment();

                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void sqlSetStatment(String username, String password, String name, String email, String address, String education, String ethnicity, Date DateOfBirth, PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, name);
        statement.setString(3, email);
        statement.setString(4, address);
        statement.setString(5, education);
        statement.setString(6, ethnicity);
        statement.setDate(7,DateOfBirth);
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
    }
}
