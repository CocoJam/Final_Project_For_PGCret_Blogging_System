import java.sql.*;

/**
 * Created by ljam763 on 25/05/2017.
 */
public class ProfilePageDAO {
    protected Connection conn;
    protected Passwords_Checker pass;
    private String username;
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

    public ProfilePAge generateUsersProfile (String username) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Username, Name ,Email, Address, Education, Ethnicity, DateOfBirth FROM UsersNames WHERE Username = ?;"
            );
            {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ProfilePAge profilePAge = new ProfilePAge();

                    username = resultSet.getString(1);
                    name = resultSet.getString(2);
                    email = resultSet.getString(3);
                    address = resultSet.getString(4);
                    education = resultSet.getString(5);
                    ethnicity = resultSet.getString(6);
                    date = resultSet.getDate(7);

                    profilePAge.setUsername(username);
                    profilePAge.setName(name);
                    profilePAge.setEmail(email);
                    profilePAge.setAddress(address);
                    profilePAge.setEducation(education);
                    profilePAge.setEthnicity(ethnicity);
                    profilePAge.setDate(date);

                    return profilePAge;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
