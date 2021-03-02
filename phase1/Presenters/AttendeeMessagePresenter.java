package Presenters;

import Controllers.AttendeeFriendListController;
import UseCases.UserFriendManager;
import UseCases.UserManager;

public class AttendeeMessagePresenter extends MessageMenuPresenter {
    AttendeeFriendListController attendeeFriendListController;

    /**
     * AttendeeMessagePresenter constructor
     * Presenter for the attendee's messages
     *
     * @param attendeeFriendListController Friend list controller for the attendees
     * @param userManager                  Use case for the attendee
     * @param userFriendManager            Use case for the attendee's friend list
     */

    public AttendeeMessagePresenter(AttendeeFriendListController attendeeFriendListController, UserManager userManager,
                                    UserFriendManager userFriendManager) {
        super(attendeeFriendListController, userManager, userFriendManager);
        this.attendeeFriendListController = attendeeFriendListController;
    }
}
