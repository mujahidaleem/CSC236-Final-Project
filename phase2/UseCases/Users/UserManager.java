package UseCases.Users;

import Entities.Events.Event;
import Entities.Users.AccountCreatorFactory;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An instance of this stores all the users
 */
public class UserManager implements Serializable {
    private User currentUser;
    public ArrayList<User> users;
    private AccountCreatorFactory factory;

    /**
     * UserManager constructor
     *
     * @param currentUser the user using the current session
     * @param users       the list of all users
     */
    public UserManager(User currentUser, ArrayList<User> users) {
        this.currentUser = currentUser;
        this.users = users;
        this.factory = new AccountCreatorFactory();
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
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * adds a new instance of user to the list of users
     *
     * @param name     the name of the new user
     * @param password the password of the new user
     * @param type     the type of the new user
     */
    public void addUser(int id, String name, String password, String type, HashMap<String, LocalDateTime> schedule,
                        ArrayList<Integer> friends, HashMap<String, LocalDateTime> eventsOrganizing, HashMap<String, LocalDateTime> speakingSchedule){
        users.add(factory.createAccountFactory(id, name, password, type, schedule, friends, eventsOrganizing, speakingSchedule));
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
            if (user.getId() == event.getOrganizer()){
                ((Organizer) user).get_eventsOrganizing().remove(event.getEventName());
            }
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

}
