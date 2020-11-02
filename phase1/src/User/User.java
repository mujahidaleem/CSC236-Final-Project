package User;

import Event.Event;
import java.util.List;

abstract class User {
    private String _name;
    private String _password;
    private List<Event> _eventList;
    private List<User> _friendList;

    public User(String name, String password, List<Event> events, List<User> friends){
        _name = name;
        _password = password;
        _eventList = events;
        _friendList = friends;
    }

    public String get_name() {
        return _name;
    }

    public String get_password() {
        return _password;
    }

    public List<Event> get_eventList() {
        return _eventList;
    }

    public List<User> get_friendList() {
        return _friendList;
    }


}
