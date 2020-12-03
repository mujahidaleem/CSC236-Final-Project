package Gateways;

import UseCases.Users.UserManager;

import java.io.*;
import java.util.ArrayList;

/**
 * An instance of this reads files and returns information on users
 */
public class UserReader {
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
}
