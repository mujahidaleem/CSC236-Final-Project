package Gateways;

import Entities.Room;
import Entities.Users.User;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Users.UserManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this reads files and returns information on events
 */
public class RoomReader extends MySQLReader{
    String fileName;

    /**
     * EventReader constructor
     *
     * @param fileName the directory of where the ser file of the eventManager is stored
     */
    public RoomReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the ser file to get the events from the previous session. If the file is not found, creates a new instance
     * of EventManager will no events.
     *
     * @return an instance of EventManager containing all the events
     */
    public RoomManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            RoomManager eventManager = (RoomManager) oi.readObject();

            oi.close();
            fi.close();

            return eventManager;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new RoomManager();
    }


    /**
     * Stores the current roomManager with all the events into a ser file
     *
     * @param roomManager the roomManager being stored into a ser file
     */
    public void saveFile(RoomManager roomManager) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(roomManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }

    public RoomManager readData(){
        RoomManager roomManager = new RoomManager();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,userName,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms");
            while (rs.next()){
                HashMap<ArrayList<LocalDateTime>, String> map = turnStringToHashMap(rs.getString("schedule"));
                roomManager.addRoom(new Room(rs.getInt("roomNumber"), rs.getInt("roomCapacity"), map));
            }
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return roomManager;
    }

    public void createTable(){
        String myTableName = "CREATE TABLE IF NOT EXISTS rooms(" +
                "roomNumber INT(64) NOT NULL," +
                "roomCapacity INT(64)," +
                "schedule LONGTEXT)";
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

    public void saveRoomManager(RoomManager roomManager) {
        cleanTable("rooms");
        for (Room room : roomManager.getRooms()) {
            try {
                Class.forName(jdbcDriver);
                Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(generateInformation(room));
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }

    private String generateInformation(Room room){
        return "INSERT INTO rooms(roomNumber, roomCapacity, schedule) VALUES('" + room.getRoomNumber() + "', '"
                + room.getRoomCapacity() + "', '" + turnHashMap2IntoString(room.getRoomSchedule()) + "')";
    }

    private String turnHashMap2IntoString(HashMap<ArrayList<LocalDateTime>, String> map){
        StringBuilder stringBuilder = new StringBuilder();
        if(map.size()>0){
            for(ArrayList<LocalDateTime> arrayList: map.keySet()){
                stringBuilder.append(arrayList.get(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).
                        append("&").append(arrayList.get(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).
                        append("=").append(map.get(arrayList)).append("_");
            }
            return stringBuilder.toString();
        }
        return "";
    }

    private HashMap<ArrayList<LocalDateTime>, String> turnStringToHashMap(String s) {
        HashMap<ArrayList<LocalDateTime>, String> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (s != null && s.length() > 0) {
            String[] strings = s.split("_");
            for (String string : strings) {
                String[] s1 = string.split("=");
                String[] time = s1[0].split("&");
                LocalDateTime start = LocalDateTime.parse(time[0], formatter);
                LocalDateTime end = LocalDateTime.parse(time[1], formatter);
                String event = s1[1];
                ArrayList<LocalDateTime> arrayList = new ArrayList<>();
                arrayList.add(start);
                arrayList.add(end);
                map.put(arrayList, event);
            }
            return map;
        } else {
            return new HashMap<>();
        }
    }
}
