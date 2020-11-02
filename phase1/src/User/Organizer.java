package User;

import Event.Event;
import Schedule.Schedule;

import java.util.List;
/*Only difference between this and the user class is it has access to the whole schedule, so you could implement the
check here if you wanted to, or in the schedule section of the program (ie check if organizer in the edit_schedule method
 * NOTE: If you decide to combine the broadcast methods in the message use case, then you would override message here*/
public class Organizer extends User {

    public Organizer(String name, String password, Schedule schedule, List<User> friends){
        super(name, password, schedule, friends);
    }
}
