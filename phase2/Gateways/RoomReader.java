package Gateways;

import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;

import java.io.*;
import java.util.ArrayList;

/**
 * An instance of this reads files and returns information on events
 */
public class RoomReader {
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
}
