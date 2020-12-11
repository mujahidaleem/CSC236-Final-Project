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
//    private String turnHashMapIntoString(HashMap<String, LocalDateTime> arrayList){
//        if(arrayList.size()>0){
//            StringBuilder string = new StringBuilder();
//            for (String s: arrayList.keySet()){
//                string.append(s).append("=").append(arrayList.get(s).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("_");
//            }
//            string.delete(string.length()-1,string.length());
//            return string.toString();
//        }else {
//            return "";
//        }
//    }
//
//    private HashMap<String, LocalDateTime> turnStringIntoHashMap(String string){
//        HashMap<String, LocalDateTime> map = new HashMap<>();
//        if(string != null && string.length()>0){
//            String[] strings = string.split("_");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            for(String s:strings){
//                String[] s1 = s.split("=");
//                String event = s1[0];
//                LocalDateTime date = LocalDateTime.parse(s1[1], formatter);
//                map.put(event, date);
//            }
//            return map;
//        } else{
//            return map;
//        }
//    }
//
//    private String turnArrayListIntoString(ArrayList<Integer> arrayList){
//        StringBuilder stringBuilder = new StringBuilder();
//        if(arrayList.size()>0){
//            for(int i:arrayList){
//                stringBuilder.append(i).append("_");
//            }
//            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
//        }
//        return stringBuilder.toString();
//    }
//
//    private ArrayList<Integer> turnStringToArrayList(String string){
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        if(string != null && string.length() > 0){
//            String[] strings = string.split("_");
//            for(String s:strings){
//                arrayList.add(Integer.parseInt(s));
//            }
//        } else {
//            return new ArrayList<>();
//        }
//        return arrayList;
//    }

}
