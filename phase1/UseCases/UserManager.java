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
            if (user.get_id() == id){
                return false;
            }
        }
        return true;
    }

    public boolean canAttendEvent(Event event) {
        return !(currentUser.get_personalSchedule().containsKey(event.getEvent_name()));
    }

    public boolean canLeaveEvent(Event event) {
        return currentUser.get_personalSchedule().containsKey(event.getEvent_name());
    }

    public void attendEvent(Event event) {
        currentUser.get_personalSchedule().put(event.getEvent_name(), event.getEvent_time());
    }

    public void leaveEvent(Event event) {
        currentUser.get_personalSchedule().remove(event.getEvent_name());
    }

    public void deleteEvent(Event event){
        for (User user :users) {
            user.get_personalSchedule().remove(event.getEvent_name());
        }
    }

    }
}
