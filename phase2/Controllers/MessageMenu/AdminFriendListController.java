package Controllers.MessageMenu;

public class AdminFriendListController extends UserFriendListController {
    public AdminMessagePresenter adminMessagePresenter;

    /**
     * AttendeeFriendList constructor
     *
     * @param adminFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AdminFriendListController(AdminFriendManager adminFriendManager,AdminMessagePresenter adminMessagePresenter) {
        super(adminFriendManager);
        this.adminMessagePresenter=adminMessagePresenter;
    }

    /**
     * getter fot AdminMessagePresenter
     * @return
     */

    public AdminMessagePresenter getAdminMessagePresenter() {
        return adminMessagePresenter;
    }
}


