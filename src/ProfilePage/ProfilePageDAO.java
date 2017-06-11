package ProfilePage;

import Connection.*;
import Login.*;

import java.sql.*;
import java.util.Random;

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
    private String introduction;
    private String profilePicture;
    private Date date;
    private int salt;
    private int iterations;
    private int oldSalt;
    private int oldIterations;


    public ProfilePageDAO() {
        this.pass = new Passwords_Checker();
    }

//    Allows retrieving of user details from UsersNames database with username parameter.
    public ProfilePAge getUsersProfile(String username) { // Pass in username from GET
        ProfilePAge profilePAge = null;
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT Username, Name, Email, Address, Education, Ethnicity, DateOfBirth, Introduction, profilePicture FROM UsersNames WHERE Username = ?;")) {
                System.out.println(statement);
                statement.setString(1, username);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        profilePAge = makeProfilePAge(resultSet);
                    }
                }
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
        } catch (SQLException e) {
            System.out.println("Error. No profile page under this username.");
            e.printStackTrace();
        }
        return profilePAge;
    }

    //    Creating user Profile in UsersNames database.
    public ProfilePAge createUsersProfile(ProfilePAge profilePAge, String password) throws SQLException {
        ProfilePageGetters(profilePAge);
        saltAndIteration();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO UsersNames (Username, Name, Email, Address, Education, Ethnicity , DateOfBirth, Introduction, Password, salt, iteration) VALUES( ?, ? ,?,?,?,?,?,?,?,?,?);")) {
                System.out.println(statement);
                statement.setString(1, usernames);
                sqlSetStatment(pass.hashing(password, salt, iterations), statement);
                statement.setInt(10, salt);
                statement.setInt(11, iterations);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Username already exist. Cannot create profile page.");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            return null;
        }
        return getUsersProfile(profilePAge.getUsername());
    }

//    Update user details in the UsersNames table in database.
    public ProfilePAge updateUsersProfile(String username, String password, ProfilePAge profilePAge, String newPassword) {
        ProfilePageGetters(profilePAge);
        getSaltAndIteration(username);
        saltAndIteration();
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE UsersNames SET Username=?, Name=?, Email=?, Address=?, Education=?, Ethnicity=?, DateOfBirth =?, Introduction=?, Password=?, profilePicture=?, salt=?, iteration=?  WHERE  Username = ?;")) {
                statement.setString(1, username);
                sqlSetStatment(password, statement);
                System.out.println(newPassword);
                System.out.println(password);
                System.out.println(salt);
                System.out.println(iterations);
                System.out.println(oldSalt);
                System.out.println(oldIterations);
                System.out.println(pass.hashing(newPassword, salt, iterations));
//                System.out.println(pass.hashing(password, oldSalt, oldIterations));
                statement.setString(9, pass.hashing(newPassword, salt, iterations));
                statement.setString(10, profilePicture);
                statement.setInt(11, salt);
                statement.setInt(12, iterations);
//                statement.setString(12, pass.hashing(password, oldSalt, oldIterations));
                statement.setString(13, usernames);

                System.out.println(statement);
                statement.executeUpdate();
                System.out.println("CONNECTION CLOSED: " + connection.isClosed());
            }
            System.out.println("CONNECTION CLOSED: " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Error. Cannot update user information based on the username and password.");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            return null;
        }
        return getUsersProfile(username);
    }

    public void getSaltAndIteration(String username) {
        try (Connection connection = new ConnectionToTheDataBase().getConn()) {
            //user pass.hashing() with the password needed to be hash to match and salt number with iteration numbers
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT salt,iteration FROM UsersNames WHERE Username = ?;"
            )) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        oldSalt = resultSet.getInt(1);
                        System.out.println(oldSalt);
                        oldIterations = resultSet.getInt(2);
                        System.out.println(oldIterations);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //This is the shortcut for setting strings for all normal user details apart from password and username.
    private void sqlSetStatment(String password, PreparedStatement statement) throws SQLException {
        statement.setString(2, name);
        statement.setString(3, email);
        statement.setString(4, address);
        statement.setString(5, education);
        statement.setString(6, ethnicity);
        statement.setDate(7, date);
        statement.setString(8, introduction);
        statement.setString(9, password);
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
        profilePAge.setIntroduction(introduction);
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
        introduction = resultSet.getString(8);
        profilePicture = resultSet.getString(9);
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
        introduction = profilePAge.getIntroduction();
        profilePicture = profilePAge.getProfilepic();
        System.out.println("getting :" + profilePicture);
    }
    private void saltAndIteration() {
        Random rand = new Random();
        salt = rand.nextInt(255-1) + 1;
        iterations = rand.nextInt(1000-1) + 1;
        System.out.println(salt);
        System.out.println(iterations);
    }
}
