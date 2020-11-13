package UseCases;

import Entities.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Entities.Attendee;
import Entities.User;
import Entities.Organizer;

public class UserLoginManager {

    private ArrayList<User> users;
    protected User user;
    protected String command;


    public UserLoginManager(User user) {
        this.user = user;
    }

    //check if user exists
    public boolean check_exists(String username) {

    }
}

