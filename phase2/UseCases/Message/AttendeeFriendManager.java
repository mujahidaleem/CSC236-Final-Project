package UseCases.Message;

import Entities.Users.Attendee;
import Entities.Message;
import Entities.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendeeFriendManager extends UserFriendManager {

    /**
     * AttendeeFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */
    public AttendeeFriendManager(HashMap<ArrayList<User>, ArrayList<Message>> userToMessages, Attendee attendee) {
        super(userToMessages, attendee);
    }
}