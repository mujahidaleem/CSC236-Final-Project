package Controllers.MessageMenu;

import GUI.MainMenuPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Message.AdminFriendManager;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Message.OrganizerFriendManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;

/**
 * Controls data specifically for attendees' friend lists.
 */
public class AttendeeFriendListController extends UserFriendListController {

    /**
     * AttendeeFriendList constructor
     *
     * @param attendeeFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AttendeeFriendListController(UserFriendManager userFriendManager,
                                        AdminFriendManager adminFriendManager,
                                        OrganizerFriendManager organizerFriendManager,
                                        MessageMenuPresenter messageMenuPresenter,
                                        UserManager userManager,
                                        JFrame frame,
                                        LanguageManager languageManager, AttendeeFriendManager attendeeFriendManager, MainMenuPanel mainMenuPanel) {
        super(userFriendManager,
                adminFriendManager,
                attendeeFriendManager,
                organizerFriendManager,
                mainMenuPanel,
                messageMenuPresenter,
                userManager,
                frame,
                languageManager);
    }
}
