package Gateways;

import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Entities.Users.User;
import UseCases.Users.UserManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this reads files and returns information on users
 */
public class UserReader {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String url ="jdbc:mysql://localhost:3306/phase2";
    String userName ="root";
    String password = "csc207group0700";

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

    public UserManager readData(){
        UserManager userManager = new UserManager(null, new ArrayList<>());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,userName,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users ORder BY id ");
            while (rs.next()){
                userManager.addUser(rs.getString(3), rs.getString(2),rs.getString(4));
                System.out.println(rs.getInt(1)+" " + rs.getString(3) + " "+ rs.getString(2)+" " +rs.getString(4));
            }
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return userManager;
    }

    public void saveUserManager(UserManager userManager) {
        cleanTable();
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

    private String generateInformation(User user){
        if(user.getClass() == Attendee.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'attendee', '"
                    + turnArrayIntoString(user.getPersonalSchedule())+"')";
        } else if (user.getClass() == Organizer.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, eventsOrganizing) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'organizer', '"
                    +turnArrayIntoString(user.getPersonalSchedule())+
                    "', '"+turnArrayIntoString(((Organizer)user).get_eventsOrganizing())+"')";
        } else if (user.getClass() == Speaker.class){
            return "INSERT INTO users(id, fullName, password, accountType, eventsAttending, eventsSpeaking) " +
                    "VALUES('" + user.getId() + "', '" + user.getName() + "', '" + user.getPassword() + "', 'speaker', '"
                    +turnArrayIntoString(user.getPersonalSchedule())+
                    "', '"+ turnArrayIntoString(((Speaker) user).getSpeakingSchedule())+"')";
        } else {
            return ""; //TODO
        }
    }

    private String turnArrayIntoString(HashMap<String, LocalDateTime> arrayList){
        StringBuilder string = new StringBuilder();
        for (String s: arrayList.keySet()){
            string.append(s).append(":").append(arrayList.get(s)).append("_");
        }
        return string.toString();
    }

    private void cleanTable(){
        try {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}
