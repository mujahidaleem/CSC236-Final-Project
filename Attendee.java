package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this represents an attendee type user. They can attend
 * events and remove their spot from events.
 */
public class Attendee extends User {

    /**
     * Attendee constructor
     *
     * @param id       A unique id representing the
     * @param name     A string representing the name of the Attendee
     * @param password A string representing the password of the Attendee
     * @param schedule A list of events that the speaker is attending
     * @param friends  A list of manageable Attendees of the speaker
     */
    public Attendee(int id, String name, String password, HashMap<String, LocalDateTime> schedule, ArrayList<User> friends) {
        super(id, name, password, schedule, friends);
    }

}
