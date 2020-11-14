package UseCases;

import Entities.Event;
import Entities.User;

import java.io.Serializable;
import java.util.List;

public class UserManager implements Serializable {
    private User currentUser;
    public List<User> users;

    public UserManager(User currentUser, List<User> users) {
        this.currentUser = currentUser;
        this.users = users;
    }

    public UserManager(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean userIsCreatable(int id, String username, String password) {
        for (User user : users) {
            if (user.getId() == id){
                return false;
            }
        }
        return true;
    }

    public boolean canAttendEvent(Event event) {
        return !(currentUser.getPersonalSchedule().containsKey(event.getEventName()));
    }

    public boolean canLeaveEvent(Event event) {
        return currentUser.getPersonalSchedule().containsKey(event.getEventName());
    }

    public void attendEvent(Event event) {
        currentUser.getPersonalSchedule().put(event.getEventName(), event.getEventTime());
    }

    public void leaveEvent(Event event) {
        currentUser.getPersonalSchedule().remove(event.getEventName());
    }

    public void deleteEvent(Event event){
        for (User user :users) {
            user.getPersonalSchedule().remove(event.getEventName());
        }
    }
}
