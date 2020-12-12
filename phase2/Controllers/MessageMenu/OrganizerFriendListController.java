package Controllers.MessageMenu;

import Entities.Users.Attendee;
import Entities.Users.Speaker;
import Entities.Users.User;
import GUI.MainMenuPanel;
import GUI.Messages.MessageMenuPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Message.*;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.time.LocalDateTime;

public class OrganizerFriendListController extends UserFriendListController {
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
     * OrganizerFriendList Constructor
     * Controls the organizer's friend list, as well as allows the organizer to message all speakers/attendees
     *
     * @param organizerFriendManager Use case for the friends of an organizer
     * @param userManager            Use case for a user's options
     */

    public OrganizerFriendListController(UserFriendManager userFriendManager,
                                         AdminFriendManager adminFriendManager,
                                         AttendeeFriendManager attendeeFriendManager,
                                         MessageMenuPresenter messageMenuPresenter,
                                         JFrame frame,
                                         LanguageManager languageManager, OrganizerFriendManager organizerFriendManager, UserManager userManager, MainMenuPanel mainMenuPanel) {
        super(userFriendManager,
                adminFriendManager,
                attendeeFriendManager,
                organizerFriendManager,
                mainMenuPanel,
                messageMenuPresenter,
                userManager,
                frame,
                languageManager);
        this.organizerFriendManager = organizerFriendManager;
        this.userManager = userManager;
    }

    /**
     * Message all attendees
     *
     * @param messageContent Content of the message
     */
    public void messageAllAttendees(String messageContent, LocalDateTime dateTime) {
        for (User user : userManager.users) {
            if (user.getClass().equals(Attendee.class)) {
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(), user, messageContent, dateTime);
            }
        }
    }

    /**
     * Message all speakers
     *
     * @param messageContent Content of the message
     */
    public void messageAllSpeakers(String messageContent, LocalDateTime dateTime) {
        for (User user : userManager.users) {
            if (user.getClass().equals(Speaker.class)) {
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(), user, messageContent, dateTime);
            }
        }
    }
}