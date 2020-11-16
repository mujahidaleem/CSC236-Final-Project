package Entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * An instance of this represents an organizer type user. They can create events,
 * modify events, create speakers,
 */
public class Organizer extends User {
    public HashMap<String, LocalDateTime> _eventsOrganizing;

    /**
     * Organizer constructor
     * @param id A unique id representing the
     * @param name A string representing the name of the Organizer
     * @param password A string representing the password of the Organizer
     * @param schedule A list of events that the speaker is attending
     * @param friends A list of manageable Organizers of the speaker
     */
    public Organizer(int id, String name, String password, HashMap<String, LocalDateTime> schedule,
                     List<User> friends, HashMap<String, LocalDateTime> eventsOrganizing) {
        super(id, name, password, schedule, friends);
        _eventsOrganizing = eventsOrganizing;
    }
}