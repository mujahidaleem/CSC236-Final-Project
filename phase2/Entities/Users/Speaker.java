package Entities.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this represents a speaker type user. The speaker can
 * speak at events or attending events like an attendee.
 */
public class Speaker extends User {
    public HashMap<String, LocalDateTime> speakingSchedule;

    /**
     * Speaker constructor
     *
     * @param name             A string representing the name of the speaker
     * @param password         A string representing the password of the speaker
     * @param schedule         A list of events that the speaker is attending
     * @param friends          A list of manageable users of the speaker
     * @param speakingSchedule A list of events that the speaker will be speaking at
     */
    public Speaker(int id, String name, String password, HashMap<String, LocalDateTime> schedule,
                   ArrayList<Integer> friends, HashMap<String, LocalDateTime> speakingSchedule) {
        super(id, name, password, schedule, friends);
        this.speakingSchedule = speakingSchedule;
    }

    /**
     * Getter for the speaking schedule of the speaker
     *
     * @return returns a Hashmap of event names corresponding to the date and time in which the event is happening
     */
    public HashMap<String, LocalDateTime> getSpeakingSchedule() {
        return speakingSchedule;
    }
}