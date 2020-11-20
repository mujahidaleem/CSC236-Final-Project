package Controllers;

import Entities.Event;
import Entities.Speaker;
import UseCases.SpeakerFriendManager;
import UseCases.SpeakerManager;
import UseCases.UserFriendManager;


public class SpeakerFriendListController extends UserFriendListController {
    public UserFriendManager userFriendManager;
    public SpeakerFriendManager speakerFriendManager;
    public SpeakerManager speakerManager;

    /**
     * Constructor of the SpeakerFriendListController
     * Controls the speakers' friend list, as well as allows the speaker to message all attendees in their event
     * @param speakerFriendManager Use case for the friends of an speaker
     * @param speakerManager Use case for the actions of speaker
     */

    public SpeakerFriendListController(SpeakerFriendManager speakerFriendManager, SpeakerManager speakerManager) {
        super(speakerFriendManager);
        this.speakerManager = speakerManager;
    }

    /**
     * Sends an announcement to the attendees of the speaker's event
     * @param event Event of the speaker
     * @param messageContent Content of the message
     */
    public void sendingAnnouncement(Event event, String messageContent){
        Speaker speaker = speakerManager.getCurrentSpeaker();
        speakerFriendManager.sendingAnnouncement(speaker, event, messageContent, speakerManager);
    }
}
