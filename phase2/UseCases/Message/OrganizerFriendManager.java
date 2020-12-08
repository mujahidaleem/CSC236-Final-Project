package UseCases.Message;

import Entities.*;
import Entities.Events.Event;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Entities.Users.User;
import UseCases.Events.EventManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class OrganizerFriendManager extends UserFriendManager {
    private Organizer currentOrganizer;
    private EventManager eventManager;

    /**
     * OrganizerFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */
    public OrganizerFriendManager(HashMap<ArrayList<Integer>, ArrayList<Message>> userToMessages, Organizer organizer, EventManager eventManager) {
        super(userToMessages, organizer);
    }

    /**
     * Sets a new currentOrganizer
     *
     * @param currentOrganizer the new currentOrganizer
     */
    public void setCurrentOrganizer(Organizer currentOrganizer) {
        this.currentOrganizer = currentOrganizer;
    }

    /**
     * Organizers can send an Announcement to all Attendees in an event
     *
     * @param speaker        - the Speaker sending the announcement
     * @param event          - the Event to which the announcement is being sent
     * @param messageContent - the content of the announcement being sent
     * @param userManager    - contains a list of users
     */

    public void sendingAnnouncement(Speaker speaker, Event event, String messageContent, UserManager userManager, LocalDateTime dateTime) {
        for (Integer id : event.getAttendees()) {
            User user = userManager.users.get(id - 1000);
            sendMessageTo(speaker, user, messageContent, dateTime);
        }
    }

    /**
     * Checks what users are messageable
     *
     * @param user given user
     * @return Return if the user is in the friends list or an attendee/speaker
     */
    @Override
    public boolean messageable(User user) {
        if (user.getClass().equals(Attendee.class) || user.getClass().equals(Speaker.class)) {
            return true;
        } else {
            return currentOrganizer.getFriendList().contains(user);
        }
    }

    /**
     * Getter for the current organizer
     *
     * @return currentOrganizer
     */
    public Organizer getCurrentOrganizer() {
        return currentOrganizer;
    }
}