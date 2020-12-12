package Gateways;

import Entities.Users.*;
import UseCases.Users.UserManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this reads files and returns information on users
 */
public class UserReader extends MySQLReader{
    String fileName;

    /**
     * UserReader constructor
     *
     * @param fileName the directory of where the ser file of the userManager is stored
     */
    public UserReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the ser file to get the users from the previous session. If the file is not found, creates a new instance
     * of UserManager with no users.
     *
     * @return an instance of UserManager containing all the events
     */
    public UserManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            UserManager userManager = (UserManager) oi.readObject();

            oi.close();
            fi.close();

            return userManager;

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new UserManager(null, new ArrayList<>());
    }

    /**
     * Stores the current userManager with all the users into a ser file
     *
     * @param userManager the userManager being stored into a ser file
     */
    public void saveFile(UserManager userManager) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(userManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    /**
     * Creates a table in a MySQL database
     */
    public void createTable(){
        String myTableName = "CREATE TABLE IF NOT EXISTS Users(" +
                "id INT(64) NOT NULL," +
                "password VARCHAR(20)," +
                "fullName VARCHAR (64)," +
                "accountType VARCHAR (64)," +
                "eventsAttending VARCHAR(64)," +
                "friends VARCHAR(64),"+
                "eventsOrganizing LONGTEXT," +
                "eventsSpeaking VARCHAR(64))";
        try{
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(myTableName);
            conn.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }

    /**
     * Reads the data in a MySQL database to generate user information
     * @return a userManager that stores all the users in the system
     */
    public UserManager readData(){
        UserManager userManager = new UserManager(null, new ArrayList<>());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,userName,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users ORDER BY id ");
            while (rs.next()){
                userManager.addUser(rs.getInt("id"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("accountType"),
                        turnStringIntoHashMap(rs.getString("eventsAttending")), turnStringToArrayList(rs.getString("friends")),
                        turnStringIntoHashMap(rs.getString("eventsOrganizing")), turnStringIntoHashMap(rs.getString("eventsSpeaking")));
            }
            conn.close();
        } catch (Exception e){
            System.out.println(e + "at reading users");
        }
        return userManager;
    }

    /**
     * Saves the userManager into a database in MySQL
     * @param userManager contains all the users
     */
    public void saveUserManager(UserManager userManager) {
        cleanTable("users");
        for (User user : userManager.getUsers()) {
            try {
                Class.forName(jdbcDriver);
                Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(generateInformation(user));
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Turns a user into a string to be saved into a database
     * @param user the user
     * @return a string stating how to save the data
     */
    private String generateInformation(User user){
        if(user.getClass() == Attendee.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, friends) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'attendee', '"
                    + turnHashMapIntoString(user.getPersonalSchedule())+"', '" + turnArrayListIntoString(user.getFriendList())+"')";
        } else if (user.getClass() == Organizer.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, friends, eventsOrganizing) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'organizer', '"
                    + turnHashMapIntoString(user.getPersonalSchedule())+ "', '" + turnArrayListIntoString(user.getFriendList())+
                    "', '"+ turnHashMapIntoString(((Organizer)user).get_eventsOrganizing())+"')";
        } else if (user.getClass() == Speaker.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, friends, eventsSpeaking) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'speaker', '"
                    + turnHashMapIntoString(user.getPersonalSchedule())+ "', '" + turnArrayListIntoString(user.getFriendList())+
                    "', '"+ turnHashMapIntoString(((Speaker) user).getSpeakingSchedule())+"')";
        } else {
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, friends) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'admin', '"
                    + turnHashMapIntoString(user.getPersonalSchedule())+"', '" + turnArrayListIntoString(user.getFriendList())+"')";
        }
    }
}
