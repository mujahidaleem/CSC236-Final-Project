package User;

import Event.Event;

import java.util.List;

public class Organizer extends User {

    public Organizer(String name, String password, List<Event> events, List<User> friends){
        super(name, password, events, friends);
    }
}
