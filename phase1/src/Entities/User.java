package Entities;

import java.util.List;

abstract class User {
    private String _name; // username/name for user
    private String _password; // password for user
    private Schedule _personalSchedule; // personal event list
    private List<User> _friendList; // personal friend list

/*
Initalize the user class
*/
    public User(String name,
                String password,
                Schedule schedule,
                List<User> friends){
        _name = name;
        _password = password;
        _personalSchedule = schedule;
        _friendList = friends;
    }

    /*Return the name of the user*/
    public String get_name() {
        return _name;
    }

    /*Return the name of the user*/
    public String get_password() {
        return _password;
    }
    /*Return the schedule (events) the user has signed up for*/
    public Schedule get_personalSchedule() {
        return _personalSchedule;
    }

    /*Return the friend list of the user*/
    public List<User> get_friendList() {
        return _friendList;
    }

    /*Sets the username of the user*/
    public void set_name(String _name) {
        this._name = _name;
    }

    /*Return the password of the user*/
    public void set_password(String _password) {
        this._password = _password;
    }


    /*EVERYTHING AFTER HERE IS USE CASE STUFF FOR THE USER/
    THESE ARE JUST EXAMPLES */

    /*Message users within the user friendlist - this can be adapted to fit all cases that i put below*/
    public void Message(String message, List<User> people){
        //Implement checks here for friend list & if the users are in the event
    }

    /*Message all users in a particular event*/
    public void broadcast_event(String message, Event event){
        //Implement checks here for messaging in an event
    }

    /*Message all users present*/
    public void broadcast_all(String message){
        //implement checks here for if the user can message to everyone
    }

    //Edit the schedule that is sent in
    public void edit_schedule(Schedule schedule){
        //override in classes that have limitations
    }
}