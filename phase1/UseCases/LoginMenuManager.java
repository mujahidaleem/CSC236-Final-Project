package UseCases;

import Entities.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Entities.User;
import Gateways.UserReader;

public class LoginMenuManager {

    private ArrayList<User> users;
    protected User user;
    protected String command;


    public LoginMenuManager(User user) {
        this.user = user;
    }
    public boolean password_matches_id(int id, String password) {
        //checks system to see if password matches username
        User user = UserManager.finduser(id);
        if (user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    public String type_of_user(String username) {
        //check database to return a string of "Attendee", "Organizer" or "Speaker"
    }

    //exit program (cancel login)
    public void exit() {
    }

    //logout
    public void logout() {
    }



}

