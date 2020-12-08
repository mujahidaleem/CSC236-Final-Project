package Entities.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User {

    /**
     * An instance of this represents a Admin user in the system
     */
    private String name;
    private int id;
    private String password;
    private HashMap<String, LocalDateTime> personalSchedule;
    private ArrayList<User> friendList;
    private int attendee;
    private int speaker;


    /**
     * AdminUser constructor
     *
     * @param id       A unique id representing the User
     * @param name     A string representing the name of the User
     * @param password A string representing the password of the User
     * @param schedule A list of events that the speaker is attending
     * @param friends  A list of manageable users of the speaker
     */

    public Admin(int id, String name,
                 String password,
                 HashMap<String, LocalDateTime> schedule,
                 ArrayList<Integer> friends,ArrayList<Integer> attendees, ArrayList<Integer> speakers) {
        super(id, name, password, schedule, friends);
    }
}



