package UseCases;

public class AttendeeFriendManager extends UserFriendManager {

    /**
     * AttendeeFriendManager constructor
     * @param currentUser - the current user
     */

    User currentUser;

    public AttendeeFriendManager(User currentUser) {
        super(currentUser);
    }
}