package ProfilePage;

import Connection.*;
import Login.*;

import java.sql.*;

/**
 * Created by ljam763 on 25/05/2017.
 */

//TODO: SQL Exceptions.
//TODO: SQL Injection preventions
//This DAO handles all the connections to the UserNames Table concerning profile details. This hides all the logic for database.
public class ProfilePageDAO extends LoginPassing {

    private String usernames; // Stores username as globally accessible variable
    private String name;
    private String email;
    private String address;
    private String education;
    private String ethnicity;
    private String profilePicture;
    private Date date;

    public ProfilePageDAO() {
        this.pass = new Passwords_Checker();
    }

    public ProfilePAge getUsersProfile(String username) { // Pass in username from GET
        ProfilePAge profilePAge = new ProfilePAge();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, Name, Email, Address, Education, Ethnicity, DateOfBirth, profilePicture FROM UsersNames WHERE Username = ?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        profilePAge = makeProfilePAge(resultSet);
                    }
                }
                return profilePAge;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No ProfilePage under this username");
        }
        return null;
    }


    public ProfilePAge createUsersProfile(ProfilePAge profilePAge, String password) throws SQLException {
        ProfilePageGetters(profilePAge);
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO UsersNames (Username, Name, Email, Address, Education, Ethnicity, DateOfBirth, Password) VALUES(?,?,?,?,?,?,?,?);")) {
                System.out.println(statement);
                statement.setString(1, usernames);
                sqlSetStatment(password, statement);
                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            return getUsersProfile(profilePAge.getUsername());
        } catch (SQLException e) {
            System.out.println("Error username already exist. Cannot create profile page.");
            System.out.println("SQL error");
        }
        return null;
    }

    public ProfilePAge updateUsersProfile(String username, String password, ProfilePAge profilePAge, String newPassword) {
        ProfilePageGetters(profilePAge);
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE UsersNames SET Username=?, Name=?, Email=?, Address=?, Education=?, Ethnicity=?, DateOfBirth =?, Password=?, profilePicture=?  WHERE  Password= ? AND Username = ?;")) {
                statement.setString(1, username);
                sqlSetStatment(password, statement);
                statement.setString(8, newPassword);
                statement.setString(9, profilePicture);
                statement.setString(10, password);
                statement.setString(11, usernames);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            return getUsersProfile(username);
        } catch (SQLException e) {
            System.out.println("Can not update user information based on the username and password");
        }
        return null;
    }

    //This is the shortcut for setting strings for all normal user details apart from password and username.
    private void sqlSetStatment(String password, PreparedStatement statement) throws SQLException {
        statement.setString(2, name);
        statement.setString(3, email);
        statement.setString(4, address);
        statement.setString(5, education);
        statement.setString(6, ethnicity);
        statement.setDate(7, date);
        statement.setString(8, password);
    }

    //Setting the javabean instance variables for ProfilePage
    private ProfilePAge makeProfilePAge(ResultSet resultSet) throws SQLException {
        ProfilePAge profilePAge = new ProfilePAge();
        sqlGetStatements(resultSet);
        profilePAge.setUsername(usernames);
        profilePAge.setName(name);
        profilePAge.setEmail(email);
        profilePAge.setAddress(address);
        profilePAge.setEducation(education);
        profilePAge.setEthnicity(ethnicity);
        profilePAge.setDate(date);
        profilePAge.setProfilepic(profilePicture);
        System.out.println("setting " + profilePicture);
        return profilePAge;
    }

    //This is grabbing information from result sets into the variables that you later set to the profile page.
    private void sqlGetStatements(ResultSet resultSet) throws SQLException {
        usernames = resultSet.getString(1);
        name = resultSet.getString(2);
        email = resultSet.getString(3);
        address = resultSet.getString(4);
        education = resultSet.getString(5);
        ethnicity = resultSet.getString(6);
        date = resultSet.getDate(7);
        profilePicture = resultSet.getString(8);
    }

    //Getting things from the profile page and putting to the Database page.
    private void ProfilePageGetters(ProfilePAge profilePAge) {
        usernames = profilePAge.getUsername();
        name = profilePAge.getName();
        email = profilePAge.getEmail();
        address = profilePAge.getAddress();
        education = profilePAge.getEducation();
        ethnicity = profilePAge.getEthnicity();
        date = profilePAge.getDate();
        profilePicture = profilePAge.getProfilepic();
        System.out.println("getting :" + profilePicture);
    }
}
