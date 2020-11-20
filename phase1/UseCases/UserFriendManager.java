package UseCases;

import Entities.User;
import Entities.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserFriendManager implements Serializable {
    HashMap<ArrayList<User>, ArrayList<Message>> userToMessages;
    User currentUser;

    /**
     * UserFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */


    public UserFriendManager(HashMap<ArrayList<User>, ArrayList<Message>> userToMessages, User currentUser) {
        this.userToMessages = userToMessages;
        this.currentUser = currentUser;
    }

    public void displayFriend(){
        for(User user: currentUser.getFriendList()){
            System.out.println(user);
        }
    }

    public void displayChatLog(User user, User friend){
        for(Message message: userToMessages.get(createKey(user, friend))){
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
     * send a message containing the messageContent from user1 to user2
     */

    public void sendMessageTo(User sender, User recipient, String messageContent) {
        Message message = new Message(sender, recipient, messageContent);
        if (userToMessages.containsKey(createKey(sender, recipient))){
            userToMessages.get(createKey(sender, recipient)).add(message);
        } else {
            ArrayList<Message> messages = new ArrayList<Message>();
            messages.add(message);
            userToMessages.put(createKey(sender, recipient), messages);
        }
    }

    private ArrayList<User> createKey(User user, User friend){
        ArrayList<User> users = new ArrayList<User>();
        if(user.getId()<friend.getId()){
            users.add(user);
            users.add(friend);
        } else{
            users.add(friend);
            users.add(user);
        }
        return users;
    }

    /**
     * add newFriend to user1's friend list
     */

    public void addNewFriend(User newFriend) {
        currentUser.getFriendList().add(newFriend);
        userToMessages.put(createKey(currentUser, newFriend), new ArrayList<Message>());
    }

    /**
     * remove friend from user1's friend list
     * @param friend the user being removed
     */
    public void removeFromFriendList(User friend) {
        currentUser.getFriendList().remove(friend);
        userToMessages.remove(createKey(currentUser, friend));
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public HashMap<ArrayList<User>, ArrayList<Message>> getUserToMessages() {
        return userToMessages;
    }
}

