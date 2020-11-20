package UseCases;

import Entities.User;
import Entities.Message;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserFriendManager {
    HashMap<User, HashMap<User, ArrayList<Message>>> userToMessages;
    User currentUser;

    /**
     * UserFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */


    public UserFriendManager(HashMap<User, HashMap<User, ArrayList<Message>>> userToMessages, User currentUser) {
        this.userToMessages = userToMessages;
        this.currentUser = currentUser;
    }

    public void displayFriend(){
        for (User user: userToMessages.get(currentUser).keySet()){
            System.out.println(user);
        }
    }

    public void displayChatLog(User user, User friend){
        for(Message message: userToMessages.get(user).get(friend)){
            System.out.println(message);
        }
    }

    /**
     * @return true iff otherUser is in the friend list of the current user
     */

    public boolean messageable(User user) {
        return currentUser.getFriendList().contains(user);
        }

    /**
     * @return a list of all messages sent between the current user and otherUser
     */

    public ArrayList<String> checkHistoryMessage(User user1, User user2) {
        ArrayList<String> result = new ArrayList<String>();

        for(Message message : this.userToMessages.get(user1).get(user2)) {
            String messageString = message.getString();
            result.add(messageString);
        }
        return result;
    }

    /**
     * send a message containing the messageContent from user1 to user2
     */

    public void sendMessageTo(User sender, User recipient, String messageContent) {
        Message message = new Message(sender, recipient, messageContent);
        this.userToMessages.get(sender).get(recipient).add(message);
        this.userToMessages.get(recipient).get(sender).add(message);
    }

    /**
     * add newFriend to user1's friend list
     */

    public void addNewFriend(User newFriend) {
        currentUser.getFriendList().add(newFriend);
    }

    /**
     * remove friend from user1's friend list
     * @param friend the user being removed
     */
    public void removeFromFriendList(User friend) {
        currentUser.getFriendList().remove(friend);
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
}

