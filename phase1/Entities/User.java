package Entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * An instance of this represents a user in the system
 */
public abstract class User {

    /**
     * User constructor
     * @param id A unique id representing the User
     * @param name A string representing the name of the User
     * @param password A string representing the password of the User
     * @param schedule A list of events that the speaker is attending
     * @param friends A list of manageable users of the speaker
     */
    private String name;
    private int id;
    private String password;
    private HashMap<String, LocalDateTime> personalSchedule;
    private List<User> friendList;

    public User(int id, String name,
                String password,
                HashMap<String, LocalDateTime> schedule,
                List<User> friends){
        this.id = id;
        this.name = name;
        this.password = password;
        this.personalSchedule = schedule;
        this.friendList = friends;
    }

    /**
     * Getter for the name of the User
     * @return _name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the password of the User
     * @return _password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for the schedule of the user
     * @return _personalSchedule
     */
    public HashMap<String, LocalDateTime> getPersonalSchedule() {
        return personalSchedule;
    }

    /**
     * Getter for the friend list of the User
     * @return _friendList
     */
    public List<User> getFriendList() {
        return friendList;
    }

    /**
     * Sets the name of the User
     * @param _name
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Sets the password of the User
     * @param _password
     */
    public void setPassword(String _password) {
        this.password = _password;
    }

    /**
     * Getter for the personal ID of the user
     * @return _id
     */
    public int getId() {
        return id;
    }

}