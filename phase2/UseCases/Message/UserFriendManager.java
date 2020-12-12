package UseCases.Message;

import Entities.Users.User;
import Entities.Message;
import UseCases.Users.UserManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserFriendManager implements Serializable {
    HashMap<ArrayList<Integer>, ArrayList<Message>> userToMessages;
    User currentUser;

    /**
     * UserFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    public UserFriendManager(HashMap<ArrayList<Integer>, ArrayList<Message>> userToMessages, User user) {
        this.userToMessages = userToMessages;
        this.currentUser = user;
    }

    /**
     * Prints out the friends of the user
     */
    public String displayFriend(UserManager userManager) {
        StringBuilder returnStr = null;
        for (int userId : currentUser.getFriendList()) {
            returnStr.append(userManager.getUsers().get(userId-1000)).append("\n");
        }
        return returnStr.toString();
    }

    /**
     * Prints out the chats between one user and another
     *
     * @param user   The current user
     * @param friend Friend of the user
     */
    public String displayChatLog(User user, User friend) {
        StringBuilder returnStr = null;
        for (Message message : userToMessages.get(createKey(user, friend))) {
            returnStr.append(message).append("\n");
        }
        return returnStr.toString();
    }

    /**
     * @return true iff otherUser is in the friend list of the current user
     */

    public boolean messageable(User user) {
        return currentUser.getFriendList().contains(user.getId());
    }

    /**
     * send a message containing the messageContent from user1 to user2
     */

    public void sendMessageTo(User sender, User recipient, String messageContent, LocalDateTime dateTime) {
        Message message = new Message(sender, recipient, messageContent, dateTime);
        if (userToMessages.containsKey(createKey(sender, recipient))) {
            userToMessages.get(createKey(sender, recipient)).add(message);
        } else {
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message);
            userToMessages.put(createKey(sender, recipient), messages);
        }
    }

    /**
     * Create a key of users and friends (like a dictionary)
     * Helper method
     *
     * @param user   Current user
     * @param friend Friend of the user
     * @return A list of users
     */
    private ArrayList<Integer> createKey(User user, User friend) {
        ArrayList<Integer> users = new ArrayList<>();
        if (user.getId() < friend.getId()) {
            users.add(user.getId());
            users.add(friend.getId());
        } else {
            users.add(friend.getId());
            users.add(user.getId());
        }
        return users;
    }

    /**
     * add newFriend to user1's friend list
     */

    public void addNewFriend(User newFriend) {
        currentUser.getFriendList().add(newFriend.getId());
        userToMessages.put(createKey(currentUser, newFriend), new ArrayList<>());
    }

    /**
     * remove friend from user1's friend list
     *
     * @param friend the user being removed
     */
    public void removeFromFriendList(User friend) {
        currentUser.getFriendList().remove(friend.getId());
        userToMessages.remove(createKey(currentUser, friend));
    }

    /**
     * Sets the current user for the manager to manage
     *
     * @param currentUser current user of the manager
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return currentUser;
    }
    /**
     * Get a list of messages by a user
     *
     * @return A list of messages by a user
     * For extension purposes, not used right now
     */
    public HashMap<ArrayList<Integer>, ArrayList<Message>> getUserToMessages() {
        return userToMessages;
    }


}

