package UseCases;

import Entities.Attendee;
import Entities.User;

import java.util.List;

/**
 * An instance of this stores all the attendees
 */
public class AttendeeManager extends UserManager {

    /**
     * AttendeeManager constructor
     *
     * @param currentUser the current attendee user using the session
     * @param attendees   a list of all attendee type users
     */
    public AttendeeManager(Attendee currentUser, List<User> attendees) {
        super(currentUser, attendees);
    }
}
