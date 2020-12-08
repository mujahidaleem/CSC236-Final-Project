package UseCases.Message;

import Entities.Users.User;
import Entities.Message;
import UseCases.Users.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserFriendManager implements Serializable {
    HashMap<ArrayList<User>, ArrayList<Message>> userToMessages;
    User currentUser;

    /**
     * UserFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    public UserFriendManager(HashMap<ArrayList<User>, ArrayList<Message>> userToMessages, User user) {
        this.userToMessages = userToMessages;
        this.currentUser = user;
    }

    /**
     * Prints out the friends of the user
     */
    public void displayFriend(UserManager userManager) {
        for (int userId : currentUser.getFriendList()) {
            System.out.println(userManager.getUsers().get(userId-1000));
        }
    }

    /**
     * Prints out the chats between one user and another
     *
     * @param user   The current user
     * @param friend Friend of the user
     */
    public void displayChatLog(User user, User friend) {
        for (Message message : userToMessages.get(createKey(user, friend))) {
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
    private ArrayList<User> createKey(User user, User friend) {
        ArrayList<User> users = new ArrayList<>();
        if (user.getId() < friend.getId()) {
            users.add(user);
            users.add(friend);
        } else {
            users.add(friend);
            users.add(user);
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
        currentUser.getFriendList().remove(friend);
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

    /**
     * Get a list of messages by a user
     *
     * @return A list of messages by a user
     * For extension purposes, not used right now
     */
    public HashMap<ArrayList<User>, ArrayList<Message>> getUserToMessages() {
        return userToMessages;
    }
}

