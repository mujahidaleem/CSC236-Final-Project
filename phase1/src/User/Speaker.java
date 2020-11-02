package User;

import Event.Event;

import java.util.List;

public class Speaker extends User {

    public Speaker(String name, String password, List<Event> events, List<User> friends){
        super(name, password, events, friends);
    }

}
