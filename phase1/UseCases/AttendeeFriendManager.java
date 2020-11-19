package UseCases;

import Entities.User;

public class AttendeeFriendManager extends UserFriendManager {

    /**
     * AttendeeFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages;

    public AttendeeFriendManager(HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages) {
        super(userToMessages);
    }
}