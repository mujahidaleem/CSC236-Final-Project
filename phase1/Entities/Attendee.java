package Entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Attendee extends User {

    /**
     * Attendee constructor
     * @param id A unique id representing the
     * @param name A string representing the name of the Attendee
     * @param password A string representing the password of the Attendee
     * @param schedule A list of events that the speaker is attending
     * @param friends A list of manageable Attendees of the speaker
     */
    public Attendee(int id, String name, String password, HashMap<String, LocalDateTime> schedule, List<User> friends){
        super(id, name, password, schedule, friends);
    }

}
