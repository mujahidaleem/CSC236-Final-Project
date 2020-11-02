package User;

import Event.Event;

import java.util.List;

public class Attendee extends User {

    public Attendee(String name, String password, List<Event> events, List<User> friends){
        super(name, password, events, friends);
    }


}
