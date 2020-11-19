package UseCases;

import Entities.User;
import Entities.Event;
import java.util.ArrayList;

public class SpeakerFriendManager extends UserFriendManager {

    /**
     * SpeakerFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages;

    public SpeakerFriendManager(HashMap<User, Hashmap<User, ArrayList<Message>>> userToMessages) {
        super(userToMessages);
    }


    /**
     * Speakers can send an Announcement to all Attendees in an event
     * @param speaker - the Speaker sending the announcement
     * @param event - the Event to which the announcement is being sent
     * @param messageContent - the content of the announcement being sent
     */

    public void sendingAnnouncement(Speaker speaker, Event event, String messageContent) {
        for(User user : event.getAttendees()) {
            sendMessageTo(speaker, user, messageContent);
        }
    }
}