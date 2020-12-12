package Presenters.MessageMenu;

import Controllers.MessageMenu.AttendeeFriendListController;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

public class AdminMessagePresenter extends MessageMenuPresenter {

    /**
     * AdminMessagePresenter constructor
     * Presenter for the attendee's messages
     *
     * @param adminFriendListController Friend list controller for the attendees
     * @param userManager                  Use case for the attendee
     * @param userFriendManager            Use case for the attendee's friend list
     */

    public AdminMessagePresenter(AttendeeFriendListController adminFriendListController, UserManager userManager,
                                 UserFriendManager userFriendManager, LanguageManager languageManager) {
        super(adminFriendListController, userManager, userFriendManager, languageManager);
    }
}
