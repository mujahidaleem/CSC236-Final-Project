package Gateways;

import UseCases.AttendeeFriendManager;
import UseCases.UserFriendManager;

import java.io.*;

public class MessageReader {

    /**
     * MessageReader constructor
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

    public AttendeeFriendManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(this.fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            AttendeeFriendManager userFriendManager = (AttendeeFriendManager) oi.readObject();

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
        return new AttendeeFriendManager(null);
    }

    /**
     * Stores the current userFriendManager and all the messages into a ser file
     *
     * @param userFriendManager the userFriendManager being stored into a ser file
     */
    public void saveFile(UserFriendManager userFriendManager){
        try{
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(userFriendManager);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}