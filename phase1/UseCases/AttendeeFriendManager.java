package UseCases;

import Entities.Message;
import Entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendeeFriendManager extends UserFriendManager {

    /**
     * AttendeeFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */
    public AttendeeFriendManager(HashMap<User, HashMap<User, ArrayList<Message>>> userToMessages) {
        super(userToMessages);
    }
}