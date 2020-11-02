package User;

import Event.Event;
import Schedule.Schedule;

import java.util.List;
/*Only difference between this and the user class is that it cant broadcast at all
* NOTE: If you decide to combine the broadcast methods in the message use case, then you would override message here*/
public class Attendee extends User {

    /*Initalizes Attendee*/
    public Attendee(String name, String password, Schedule schedule, List<User> friends){
        super(name, password, schedule, friends);
    }

    /*Attendee can't broadcast to events*/
    @Override
    public void broadcast_event(String message, Event event) {
        System.out.println("Can't do this");
    }
    /*Cant broadcast to all*/
    @Override
    public void broadcast_all(String message) {
        System.out.println("Can't do this");
    }

    /*only edit their own schedule*/
    @Override
    public void edit_schedule(Schedule schedule) {
        if (schedule == this.get_personalSchedule()){
            super.edit_schedule(schedule);
        }
        else{
            System.out.println("Can't do this");

        }
    }
}
