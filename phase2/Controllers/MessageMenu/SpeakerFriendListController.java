package Controllers.MessageMenu;

import Entities.Events.Event;
import Entities.Users.Speaker;
import GUI.MainMenuPanel;
import UseCases.Message.SpeakerFriendManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;


public class SpeakerFriendListController extends UserFriendListController {
    public SpeakerFriendManager speakerFriendManager;
    public SpeakerManager speakerManager;
    public UserManager userManager;

    /**
     * Constructor of the SpeakerFriendListController
     * Controls the speakers' friend list, as well as allows the speaker to message all attendees in their event
     *
     * @param speakerFriendManager Use case for the friends of an speaker
     * @param speakerManager       Use case for the actions of speaker
     * @param userManager          contains the list of users
     */

    public SpeakerFriendListController(SpeakerFriendManager speakerFriendManager, SpeakerManager speakerManager, UserManager userManager, MainMenuPanel mainMenuPanel) {
        super(speakerFriendManager, mainMenuPanel);
        this.speakerFriendManager = speakerFriendManager;
        this.speakerManager = speakerManager;
        this.userManager = userManager;
    }

    /**
     * Sends an announcement to the attendees of the speaker's event
     *
     * @param event          Event of the speaker
     * @param messageContent Content of the message
     */
    public void sendingAnnouncement(Event event, String messageContent, LocalDateTime dateTime) {
        Speaker speaker = speakerManager.getCurrentSpeaker();
        speakerFriendManager.sendingAnnouncement(speaker, event, messageContent, speakerManager, dateTime);
    }

    /**
     * Sends an announcement to the attendees of any of the speaker's event
     *
     * @param messageContent Content of the message
     */
    public void sendAnnouncementToAll(String messageContent, LocalDateTime dateTime) {
        speakerFriendManager.sendingAnnouncementToAll(speakerManager.getCurrentSpeaker(), messageContent, userManager, dateTime);
    }
}
