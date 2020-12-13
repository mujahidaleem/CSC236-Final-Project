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
}
