package UseCases;

import Entities.Event;
import Entities.Message;
import Entities.Speaker;
import Entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class OrganizerFriendManager extends UserFriendManager {

    /**
     * OrganizerFriendManager constructor
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */
    public OrganizerFriendManager(HashMap<User, HashMap<User, ArrayList<Message>>> userToMessages) {
        super(userToMessages);
    }

    /**
     * Organizers can send an Announcement to all Attendees in an event
     * @param speaker - the Speaker sending the announcement
     * @param event - the Event to which the announcement is being sent
     * @param messageContent - the content of the announcement being sent
     * @param userManager - contains a list of users
     */

    public void sendingAnnouncement(Speaker speaker, Event event, String messageContent, UserManager userManager) {
        for(Integer id : event.getAttendees()) {
            User user = userManager.users.get(id - 1000);
            sendMessageTo(speaker, user, messageContent);
        }
    }
}