package UseCases;

import Entities.User;
import Entities.Message;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserFriendManager {

    /**
     * UserFriendManager constructor
     * @param userToFriends - a dictionary mapping users to their friends
     */

    HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages;

    public UserFriendManager(HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages) {
        this.userToMessages = userToMessages;
    }

    /**
     * @return true iff otherUser is in the friend list of the current user
     */

    public boolean messagable(User user1, User user2) {
        return user1.getFriendList().contains(user2);
        }

    }

    /**
     * @return a list of all messages sent between the current user and otherUser
     */

    public ArrayList<Message> checkHistoryMessage(User user1, User user2) {

    }

    /**
     * send a message containing the messageContent from user1 to user2
     */

    public void sendMessageTo(User sender, User recepient, String messageContent) {
        Message message = Message(sender, recepient, messageContent);

    }

    /**
     * add newFriend to user1's friend list
     */

    public void addNewFriend(User user1, User newFriend) {
        user1.getFriendList().add(newFriend);
    }

    /**
     * remove friend from user1's friend list
     */

    public void removeFromFriendList(User user1, User friend) {
        user1.getFriendList().remove(friend);
    }

}