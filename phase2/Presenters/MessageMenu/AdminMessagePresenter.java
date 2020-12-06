package Presenters.MessageMenu;

public class AdminMessagePresenter extends MessageMenuPresenter {
    AdminFriendListController adminFriendListController;

    /**
     * AdminMessagePresenter constructor
     * Presenter for the attendee's messages
     *
     * @param adminFriendListController Friend list controller for the attendees
     * @param userManager                  Use case for the attendee
     * @param userFriendManager            Use case for the attendee's friend list
     */

    public AdminMessagePresenter(AdminFriendListController adminFriendListController, UserManager userManager,
                                    UserFriendManager userFriendManager, LanguageManager languageManager) {
        super(adminFriendListController, userManager, userFriendManager, languageManager);
        this.adminFriendListController = adminFriendListController;
    }
    public displayCancelEventWithoutAttendee(){
        if
    }
}
