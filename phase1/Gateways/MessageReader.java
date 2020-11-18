package Gateways;

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

    public UserFriendManager readFile() {
        try {
            FileInputStream fi = new FileInputStream(new File(this.fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            UserFriendListManager userFriendListManager = (UserFriendListManager) oi.readObject();

            oi.close();
            fi.close();

            return userFriendListManager;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}