package User;

import Event.Event;
import Schedule.Schedule;

import java.util.List;
/*Only difference between this and the user class is that it can only message to their events, and not edit their schedule
 * NOTE: If you decide to combine the broadcast methods in the message use case, then you would override message here*/
public class Speaker extends User {

    public Speaker(String name, String password, Schedule schedule, List<User> friends){
        super(name, password, schedule, friends);
    }

    /*Cant broadcast to all*/
    @Override
    public void broadcast_all(String message) {
        System.out.println("Can't do this");
    }

    /*cant edit schedule at all*/
    @Override
    public void edit_schedule(Schedule schedule) {
        System.out.println("Can't do this");
    }

}
