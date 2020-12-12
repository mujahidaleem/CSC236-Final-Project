package UseCases.Message;

import Entities.Message;
import Entities.Users.Admin;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminFriendManager extends UserFriendManager {
    private Admin currentAdmin;

    /**
     * AdminFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */
    public AdminFriendManager(HashMap<ArrayList<Integer>, ArrayList<Message>> userToMessages, Admin admin) {
        super(userToMessages, admin);
    }

    public void setCurrentAdmin(Admin admin) {
        this.currentAdmin = admin;
    }
    public Admin getCurrentAdmin(){
        return currentAdmin;
    }

    /**
     * Checks what users are messageable
     *
     * @param user given user
     * @return Return if the user is in the admin's friends list or an attendee/speaker/organizer
     */

    @Override
    public boolean messageable(User user) {
        if (user.getClass().equals(Attendee.class) || user.getClass().equals(Speaker.class) || user.getClass().equals(Organizer.class)) {
            return true;
        } else {
            return currentAdmin.getFriendList().contains(user);
        }
    }

    /**
     * Removes message from chat and returns true, or returns false if message was not successfully removed.
     */
    public boolean removeMessage(Message message) {
        if (message.getSenderId() == currentAdmin.getId()) {
            int otherUserId = message.getRecepientId();
            for (User otherUser : userToMessages.keySet()) {
                if (otherUser.getId() == otherUserId) {
                    userToMessages.get(otherUser).remove(message);
                    return true;
                }
            }
        }
        if (message.getRecepientId() == currentAdmin.getId()) {
            int otherUserId = message.getSenderId();
            for (User otherUser : userToMessages.keySet()) {
                if (otherUser.getId() == otherUserId) {
                    userToMessages.get(otherUser).remove(message);
                    return true;
                }
            }
        }
        return false;
    }
}

