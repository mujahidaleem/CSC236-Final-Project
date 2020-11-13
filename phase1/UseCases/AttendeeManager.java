package UseCases;

import Entities.Attendee;
import Entities.User;

import java.util.List;

public class AttendeeManager extends UserManager {

    public AttendeeManager(Attendee currentUser, List<User> attendees) {
        super(currentUser, attendees);
    }
}
