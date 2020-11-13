package Entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public abstract class User {

    /**
     * User constructor
     * @param id A unique id representing the User
     * @param name A string representing the name of the User
     * @param password A string representing the password of the User
     * @param schedule A list of events that the speaker is attending
     * @param friends A list of manageable users of the speaker
     */
    private String _name;
    private int _id;
    private String _password;
    private HashMap<String, LocalDateTime> _personalSchedule;
    private List<User> _friendList;

    public User(int id, String name,
                String password,
                HashMap<String, LocalDateTime> schedule,
                List<User> friends){
        _id = id;
        _name = name;
        _password = password;
        _personalSchedule = schedule;
        _friendList = friends;
    }

    /**
     * Getter for the name of the User
     * @return _name
     */
    public String get_name() {
        return _name;
    }

    /**
     * Getter for the password of the User
     * @return _password
     */
    public String get_password() {
        return _password;
    }

    /**
     * Getter for the schedule of the user
     * @return _personalSchedule
     */
    public HashMap<String, LocalDateTime> get_personalSchedule() {
        return _personalSchedule;
    }

    /**
     * Getter for the friend list of the User
     * @return _friendList
     */
    public List<User> get_friendList() {
        return _friendList;
    }

    /**
     * Sets the name of the User
     * @param _name
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Sets the password of the User
     * @param _password
     */
    public void set_password(String _password) {
        this._password = _password;
    }

    /**
     * Getter for the personal ID of the user
     * @return _id
     */
    public int get_id() {
        return _id;
    }

}
