package UseCases;

import Entities.User;
import Entities.Event;
import java.util.ArrayList;

public class SpeakerFriendManager extends UserFriendManager {

    /**
     * SpeakerFriendManager constructor
     * @param currentUser - the current user
     */

    HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages;

    public SpeakerFriendManager(HashMap<User, ArrayList<Hashmap<User, ArrayList<Message>>>> userToMessages) {
        super(userToMessages);
    }


    /**
     * Speakers can send an Announcement to all Attendees in an event
     * @param message - the content of the announcement
     * @param event - the Event to which the announcement is being sent
     */

    public void sendingAnnouncement(Speaker speaker, Event event, String messageContent) {
        for(User user : event.getAttendees()) {
            sendMessageTo(speaker, user, messageContent);
        }
    }
}