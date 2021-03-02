package Entities.Users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountCreatorFactory implements Serializable {
    /**
     * Constructor for the OrganizerAccountCreatorFactory
     */
    public AccountCreatorFactory() {
    }

    /**
     * Returns a user depending on the accountType given
     *
     * @param name        name of the user
     * @param password    password of the user
     * @param accountType type of account
     * @return returns the type of account given by accountType, otherwise returns null if the input is wrong
     */
    public User createAccountFactory(int id, String name, String password, String accountType, HashMap<String,
            LocalDateTime> schedule, ArrayList<Integer> friends, HashMap<String, LocalDateTime> eventsOrganizing,
                                     HashMap<String, LocalDateTime> speakingSchedule) {
        if (accountType.equals("Speaker") || accountType.equals("speaker")) {
            if (password.contains(" ")) {
                return null;
            } else {
                return new Speaker(id, name, password, schedule, friends, speakingSchedule);
            }
        }
        if (accountType.equals("Organizer") || accountType.equals("organizer")) {
            if (password.contains(" ")) {
                return null;
            } else {
                return new Organizer(id, name, password, schedule, friends, eventsOrganizing);
            }
        }
        if (accountType.equals("Attendee") || accountType.equals("attendee")) {
            if (password.contains(" ")) {
                return null;
            } else {
                return new Attendee(id, name, password, schedule, friends);
            }
        }
        if (accountType.equals("Admin") || accountType.equals("admin")) {
            if (password.contains(" ")) {
                return null;
            } else {
                return new Admin(id, name, password, schedule, friends);
            }
        }
        return null;
    }
}


