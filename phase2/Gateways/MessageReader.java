package Gateways;

import Entities.Message;
import Entities.Users.User;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageReader extends MySQLReader{

    /**
     * MessageReader constructor
     *
     * @param fileName - name of the saved file
     */

    String fileName;

    public MessageReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the ser file to get the events from the previous session
     *
     * @return an instance of EventManager containing all the events
     */

    public UserFriendManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(this.fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            UserFriendManager userFriendManager = (UserFriendManager) oi.readObject();

            oi.close();
            fi.close();

            return userFriendManager;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new AttendeeFriendManager(new HashMap<>(), null);
    }

    /**
     * Stores the current userFriendManager and all the messages into a ser file
     *
     * @param userFriendManager the userFriendManager being stored into a ser file
     */
    public void saveFile(UserFriendManager userFriendManager) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(userFriendManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public void createTable(User user1, User user2){
        String myTableName = "CREATE TABLE IF NOT EXISTS "+"chat"+getKey(user1.getId(), user2.getId())+"(" +
                "sender INT(64) NOT NULL, receiver INT (64) NOT NULL, message VARCHAR(64), timeSent TIMESTAMP)";
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


    public HashMap<ArrayList<Integer>, ArrayList<Message>> readData(User user, UserManager userManager){
        HashMap<ArrayList<Integer>, ArrayList<Message>> map = new HashMap<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,userName,password);
            DatabaseMetaData metaData = conn.getMetaData();
            for (int friend: user.getFriendList()){
                ResultSet rs = metaData.getTables(null, null,"chat" + getKey(user.getId(), friend),null);
                if (rs.next()){
                    System.out.println(rs.getString(3));
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + rs.getString(3));
                    ArrayList<Message> messages = new ArrayList<>();
                    while(resultSet.next()){
                        messages.add(new Message(userManager.findUser(resultSet.getInt("sender")),
                                userManager.findUser(resultSet.getInt("receiver")), resultSet.getString("message"), LocalDateTime.parse(resultSet.getString("timeSent"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    }
                    map.put(getMapKey(user.getId(), friend), messages);
                } else {
                    map.put(getMapKey(user.getId(), friend), new ArrayList<>());
                }
            }
            System.out.println();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


    public void saveMessages(UserFriendManager userFriendManager, UserManager userManager) {
        for(ArrayList<Integer> users: userFriendManager.getUserToMessages().keySet()){
            createTable(userManager.findUser(users.get(0)), userManager.findUser(users.get(1)));
            saveMessage(users, userFriendManager.getUserToMessages().get(users));
        }
    }
    private void saveMessage(ArrayList<Integer> users, ArrayList<Message> messages){
        for (Message message:messages)
            try {
                Class.forName(jdbcDriver);
                Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(getCommand(users.get(0), users.get(1), message));
                conn.close();
            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
    }

    private String getCommand(int i, int j, Message message){
        return "INSERT IGNORE INTO " + "chat" + getKey(i, j) + "(sender, receiver, message, timeSent) VALUES('" + message.getSenderId() + "', '"+
                message.getRecipientId() + "', '"+ message.getContent() + "', '" +
                message.getTimeSent().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "')";
    }

    private String getKey(int i, int j){
        if(i<j){
            return Integer.toString(i) + j;
        } else {
            return Integer.toString(j) + i;
        }
    }

    private ArrayList<Integer> getMapKey(int i, int j){
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(i<j){
            arrayList.add(i);
            arrayList.add(j);
        }else {
            arrayList.add(j);
            arrayList.add(i);
        }
        return arrayList;
    }
}