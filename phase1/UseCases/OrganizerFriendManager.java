package UseCases;

import Entities.User;

public class OrganizerFriendManager extends UserFriendManager {

    /**
     * OrganizerFriendManager constructor
     * @param currentUser - the current user
     */

    HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages;

    public OrganizerFriendManager(HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages) {
        super(userToMessages);
    }
}