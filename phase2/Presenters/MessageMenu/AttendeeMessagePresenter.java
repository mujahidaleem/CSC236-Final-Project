package Presenters.MessageMenu;

import Controllers.MessageMenu.AttendeeFriendListController;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

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
                                    UserFriendManager userFriendManager, LanguageManager languageManager) {
        super(attendeeFriendListController, userManager, userFriendManager, languageManager);
        this.attendeeFriendListController = attendeeFriendListController;
    }
}
