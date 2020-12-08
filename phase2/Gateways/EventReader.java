package Gateways;

import Entities.Events.AttendeeOnlyEvent;
import Entities.Events.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Events.OneSpeakerEvent;
import UseCases.Events.EventManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * An instance of this reads files and returns information on events
 */
public class EventReader extends MySQLReader {
    String fileName;

    /**
     * EventReader constructor
     *
     * @param fileName the directory of where the ser file of the eventManager is stored
     */
    public EventReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the ser file to get the events from the previous session. If the file is not found, creates a new instance
     * of EventManager will no events.
     *
     * @return an instance of EventManager containing all the events
     */
    public EventManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            EventManager eventManager = (EventManager) oi.readObject();

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
        return new EventManager(new ArrayList<>());
    }


    /**
     * Stores the current eventManager with all the events into a ser file
     *
     * @param eventManager the eventManager being stored into a ser file
     */
    public void saveFile(EventManager eventManager) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(eventManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }

    public void createTable() {
        String myTableName = "CREATE TABLE IF NOT EXISTS events(" +
                "eventName VARCHAR(64) NOT NULL," +
                "eventType VARCHAR(64)," +
                "roomNumber INT(64)," +
                "date Timestamp," +
                "organizer INT(64)," +
                "maxCapacity INT(64)," +
                "duration INT(64)," +
                "speakers VARCHAR(64)," +
                "attendees VARCHAR(64))";
        try {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(myTableName);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public EventManager readData() {
        EventManager eventManager = new EventManager(new ArrayList<>());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from events");
            while (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(rs.getString("date"), formatter);
                System.out.println(date);
                eventManager.createEvent(rs.getString("eventType"), rs.getString("eventName"),
                        date, rs.getInt("duration"), rs.getInt("organizer"), rs.getInt("roomNumber"),
                        rs.getInt("maxCapacity"), turnStringIntoArrayList(rs.getString("attendees")), turnStringIntoArrayList(rs.getString("speakers")));

            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return eventManager;
    }

    public void saveEventManager(EventManager eventManager) {
        cleanTable("events");
        for (Event event : eventManager.getEvents()) {
            try {
                Class.forName(jdbcDriver);
                Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(generateInformation(event));
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }

    private String generateInformation(Event event) {
        if (event.getClass() == AttendeeOnlyEvent.class) {
            return "INSERT INTO events(eventName, eventType, roomNumber, date, organizer, maxCapacity, duration, attendees) " +
                    "VALUES('" + event.getEventName() + "', 'attendeeOnlyEvent', '" + event.getRoomNumber() +"', '" +
                    event.getEventTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', '" + event.getOrganizer() + "', '" + event.getMaxCapacity() +
                    "', '" + event.getDuration() +  "', '" + turnArrayIntoString(event.attendees) + "')";
        } else if (event.getClass() == OneSpeakerEvent.class) {
            return "INSERT INTO events(eventName, eventType, roomNumber, date, organizer, maxCapacity, duration, speakers, attendees) " +
                    "VALUES('" + event.getEventName() + "', 'oneSpeakerEvent', '" + event.getRoomNumber() + "', '" + event.getEventTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', '" + event.getOrganizer() +
                    "', '" + event.getMaxCapacity() + "', '" + event.getDuration() + "', '" + turnArrayIntoString(event.getSpeakers()) + "', '" + turnArrayIntoString(event.attendees) + "')";
        } else {
            return "INSERT INTO events(eventName, eventType, roomNumber, date, organizer, maxCapacity, duration, speakers, attendees) " +
                    "VALUES('" + event.getEventName() + "', 'multipleSpeakerEvent', '" + event.getRoomNumber() + "', '" + event.getEventTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', '" + event.getOrganizer() +
                    "', '" + event.getMaxCapacity() + "', '" + event.getDuration() + "', '" +
                    turnArrayIntoString(event.getSpeakers()) + "', '" + turnArrayIntoString(event.attendees) + "')";
        }
    }

    private String turnArrayIntoString(ArrayList<Integer> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : arrayList) {
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString();
    }

    private ArrayList<Integer> turnStringIntoArrayList(String string){
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] strings = string.split(" ");
        for(String s: strings){
            arrayList.add(Integer.parseInt(s));
        }
        return arrayList;
    }
}
