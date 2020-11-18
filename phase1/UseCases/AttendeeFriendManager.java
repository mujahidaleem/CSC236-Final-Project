package UseCases;

import Entities.User;

public class AttendeeFriendManager extends UserFriendManager {

    /**
     * AttendeeFriendManager constructor
     * @param currentUser - the current user
     */

    HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages;

    public AttendeeFriendManager(HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages) {
        super(userToMessages);
    }
}