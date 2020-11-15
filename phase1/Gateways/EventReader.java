package Gateways;

import UseCases.EventManager;

import java.io.*;

/**
 * An instance of this reads files and returns information on events
 */
public class EventReader {
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
     * Reads the ser file to get the events from the previous session
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
        return new EventManager(null);
    }


    /**
     * Stores the current eventManager will all the events into a ser file
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
}
