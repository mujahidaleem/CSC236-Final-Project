package Controllers.MessageMenu;

import Entities.Events.Event;
import Entities.Users.Speaker;
import UseCases.Message.SpeakerFriendManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;


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

    public SpeakerFriendListController(SpeakerFriendManager speakerFriendManager, SpeakerManager speakerManager, UserManager userManager) {
        super(speakerFriendManager);
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
    public void sendingAnnouncement(Event event, String messageContent) {
        Speaker speaker = speakerManager.getCurrentSpeaker();
        speakerFriendManager.sendingAnnouncement(speaker, event, messageContent, speakerManager);
    }

    /**
     * Sends an announcement to the attendees of any of the speaker's event
     *
     * @param messageContent Content of the message
     */
    public void sendAnnouncementToAll(String messageContent) {
        speakerFriendManager.sendingAnnouncementToAll(speakerManager.getCurrentSpeaker(), messageContent, userManager);
    }
}
