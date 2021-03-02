package Controllers;

import Entities.Attendee;
import Entities.Speaker;
import Entities.User;
import UseCases.OrganizerFriendManager;
import UseCases.UserManager;

public class OrganizerFriendListController extends UserFriendListController {
    public UserManager userManager;
    public OrganizerFriendManager organizerFriendManager;

    /**
     * OrganizerFriendList Constructor
     * Controls the organizer's friend list, as well as allows the organizer to message all speakers/attendees
     *
     * @param organizerFriendManager Use case for the friends of an organizer
     * @param userManager            Use case for a user's options
     */

    public OrganizerFriendListController(OrganizerFriendManager organizerFriendManager, UserManager userManager) {
        super(organizerFriendManager);
        this.organizerFriendManager = organizerFriendManager;
        this.userManager = userManager;
    }

    /**
     * Message all attendees
     *
     * @param messageContent Content of the message
     */
    public void messageAllAttendees(String messageContent) {
        for (User user : userManager.users) {
            if (user.getClass().equals(Attendee.class)) {
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(), user, messageContent);
            }
        }
    }

    /**
     * Message all speakers
     *
     * @param messageContent Content of the message
     */
    public void messageAllSpeakers(String messageContent) {
        for (User user : userManager.users) {
            if (user.getClass().equals(Speaker.class)) {
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(), user, messageContent);
            }
        }
    }
}