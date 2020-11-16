package UseCases;

public class OrganizerFriendManager extends UserFriendManager {

    /**
     * OrganizerFriendManager constructor
     * @param currentUser - the current user
     */

    User currentUser;

    public OrganizerFriendManager(User currentUser) {
        super(currentUser);
    }
}