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
    public static boolean password_matches_id(int id, String password) {
        //checks system to see if password matches username
        User user = UserManager.finduser(id);
        if (user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }


    //exit program (cancel login)
    public void exit() {
    }






}

