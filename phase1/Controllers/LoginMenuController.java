package Controllers;

import Entities.Attendee;
import Entities.Event;
import Entities.Organizer;
import Entities.User;
import Presenters.AttendeeEventPresenter;
import Presenters.EventMenuPresenter;
import Presenters.MainMenuPresenter;
import Presenters.OrganizerMessagePresenter;
import UseCases.*;

import java.util.ArrayList;

public class LoginMenuController {
    protected UserManager userManager;
    protected EventManager eventManager;
    protected UserFriendManager userFriendManager;
    private MenuFactory menuFactory;

    /**
     * LoginMenuPresenter constructor
     *
     * @param userManager stores the list of users
     */
    public LoginMenuController(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.menuFactory = new MenuFactory(userManager, eventManager, userFriendManager);
    }

    /**
     * Checks if the login credentials are correct.
     *
     * @param command the inputted login credentials
     * @return The user with those login credentials or nothing if the credentials are wrong
     */
    public User checkLogin(String command) {
        int id = Integer.parseInt(command.split("_")[0]);
        String password = command.split("_")[1];
        for (User user : userManager.users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                userManager.setCurrentUser(user);
                return user;
            }
        }
        return null;
    }

    public MainMenuPresenter login(){
        MainMenuController mainMenuController = new MainMenuController(menuFactory.createEventMenu(), menuFactory.createMessageMenu(),userManager);
        return new MainMenuPresenter(menuFactory.createEventMenu(), menuFactory.createMessageMenu(),mainMenuController);
    }

    /**
     * Creates a new user.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @param type     the type of the user.
     */
    public void signUp(String name, String password, String type) {
        userManager.addUser(name, password, type);
    }
    /**
     *   returns new id after signup 
     */
    public int return_id(){
        return userManager.getUsers().size();
    }
}
