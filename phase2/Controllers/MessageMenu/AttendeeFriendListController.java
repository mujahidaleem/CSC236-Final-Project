package Controllers.MessageMenu;

import GUI.MainMenuPanel;
import GUI.Messages.MessageMenuPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Message.*;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import javax.swing.*;

/**
 * Controls data specifically for attendees' friend lists.
 */
public class AttendeeFriendListController extends UserFriendListController {
    public UserManager userManager;
    public OrganizerFriendManager organizerFriendManager;
    public SpeakerFriendManager speakerFriendManager;
    public SpeakerManager speakerManager;
    private AdminFriendManager adminFriendManager;
    public UserFriendManager userFriendManager;
    private AttendeeFriendManager attendeeFriendManager;
    private MainMenuPanel mainMenuPanel;
    private MessageMenuPresenter messageMenuPresenter;
    private MessageMenuPanel messageMenuPanel;

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
