package UseCases;

import Entities.User;

public class OrganizerFriendManager extends UserFriendManager {

    /**
     * OrganizerFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages;

    public OrganizerFriendManager(HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages) {
        super(userToMessages);
    }
}