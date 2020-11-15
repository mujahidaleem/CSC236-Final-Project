package UseCases;

import Entities.*;

import java.io.Serializable;
import java.util.List;

public class UserManager implements Serializable {
    private User currentUser;
    public List<User> users;

    /**
     * UserManager constructor
     *
     * @param currentUser the user using the current session
     * @param users       the list of all users
     */
    public UserManager(User currentUser, List<User> users) {
        this.currentUser = currentUser;
        this.users = users;
    }

    /**
     * Special constructor so OrganizerManager can use the methods of UserManager
     *
     * @param currentUser the user using the current session
     */
    public UserManager(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Getter for currentUser
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Setter for currentUser
     *
     * @param user the new currentUser
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Getter for users
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * adds a new instance of user to the list of users
     *
     * @param name     the name of the new user
     * @param password the password of the new user
     * @param type     the type of the new user
     */
    public void addUser(String name, String password, String type) {
        switch (type) {
            case "Attendee":
                Attendee attendee = new Attendee(1000 + users.size(), name, password, null, null);
                users.add(attendee);
                break;
            case "Organizer":
                Organizer organizer = new Organizer(1000 + users.size(), name, password, null, null, null);
                users.add(organizer);
                break;
            case "Speaker":
                Speaker speaker = new Speaker(1000 + users.size(), name, password, null, null, null);
                users.add(speaker);
                break;
        }
    }

    /**
     * Adds the current user to the list of attendees of this event
     *
     * @param event the event that the current user is going to attend
     */
    public void attendEvent(Event event) {
        currentUser.getPersonalSchedule().put(event.getEventName(), event.getEventTime());
    }

    /**
     * Removes the current user from the list of attendees of this event
     *
     * @param event the event that the current user is leaving
     */
    public void leaveEvent(Event event) {
        currentUser.getPersonalSchedule().remove(event.getEventName());
    }

    /**
     * Deletes this event from the schedule of each user attending it
     *
     * @param event the event that will be deleted
     */
    public void deleteEvent(Event event) {
        for (User user : users) {
            user.getPersonalSchedule().remove(event.getEventName());
        }
    }

    /**
     * Finds the user based on the given id number
     *
     * @param id the id of the user that is searched for
     * @return the speaker with this id
     */
    public User findUser(int id) {
        return users.get(id - 1000);
    }

    /**
     * Changes the password of the current user
     *
     * @param password the new password
     */
    public void changePassword(String password) {
        currentUser.setPassword(password);
    }


    /** Use case that ensures there are no spaces in usernames.
     *
    if theres a space in username, throw exception
    */
    public void cannotcontainspace(User user){
        if (user.getName().contains(" ")){
            throw new Exception("Spaces are not allowed in usernames");
        }


    }
}
