package Controllers.MessageMenu;

import GUI.MainMenuPanel;
import GUI.Messages.MessageMenuPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Message.AdminFriendManager;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Message.OrganizerFriendManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;

public class AdminFriendListController extends UserFriendListController {
    private AdminFriendManager adminFriendManager;
    public UserFriendManager userFriendManager;
    private AttendeeFriendManager attendeeFriendManager;
    private OrganizerFriendManager organizerFriendManager;
    private MainMenuPanel mainMenuPanel;
    private MessageMenuPresenter messageMenuPresenter;
    private MessageMenuPanel messageMenuPanel;
    private UserManager userManager;
    /**
     * AdminFriendList constructor
     *
     * @param adminFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AdminFriendListController(UserFriendManager userFriendManager,
                                     AdminFriendManager adminFriendManager,
                                     AttendeeFriendManager attendeeFriendManager,
                                     OrganizerFriendManager organizerFriendManager,
                                     MainMenuPanel mainMenuPanel,
                                     MessageMenuPresenter messageMenuPresenter,
                                     UserManager userManager,
                                     JFrame frame,
                                     LanguageManager languageManager) {
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