package Controllers.MessageMenu;

public class AdminFriendListController extends UserFriendListController {
    public AdminMessagePresenter adminMessagePresenter;

    /**
     * AttendeeFriendList constructor
     *
     * @param attendeeFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AdminFriendListController(AdminFriendListController userFriendManager) {
        super(UserFriendManager);
        AdminFriendManager adminFriendManager = (AdminFriendManager)  userFriendManager;
        this.Admin

    }
}

