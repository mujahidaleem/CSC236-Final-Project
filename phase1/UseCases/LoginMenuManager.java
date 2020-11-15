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

    //check if user exists
    public boolean check_exists(String username) {

    }
}

