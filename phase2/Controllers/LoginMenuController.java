package Controllers;

import Controllers.Factories.EventMenuFactory;
import Controllers.Factories.MessageMenuFactory;
import Entities.Users.User;
import Presenters.MainMenuPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class LoginMenuController {
    protected UserManager userManager;
    protected EventManager eventManager;
    protected UserFriendManager userFriendManager;
    private EventMenuFactory eventMenuFactory;
    private MessageMenuFactory messageMenuFactory;

    /**
     * LoginMenuPresenter constructor
     *
     * @param userManager stores the list of users
     */
    public LoginMenuController(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager, RoomManager roomManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.eventMenuFactory = new EventMenuFactory(userManager, eventManager, userFriendManager, languageManager, roomManager);
        this.messageMenuFactory = new MessageMenuFactory(userManager, eventManager, userFriendManager, languageManager);
    }

    /**
     * Checks if the login credentials are correct.
     *
     * @param id the inputted username
     * @param password the inputted password
     * @return The user with those login credentials or nothing if the credentials are wrong
     */
    public User checkLogin(int id, String password) {
        for (User user : userManager.users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                userManager.setCurrentUser(user);
                return user;
            }
        }
        return null;
    }

    /**
     * Creates a main menu presenter in the event of a login
     *
     * @return MainMenuPresenter
     */
    public MainMenuPresenter login(LanguageManager languageManager) {
        MainMenuController mainMenuController = new MainMenuController(eventMenuFactory.getEventMenu(), messageMenuFactory.createMessageMenu(), userManager);
        return new MainMenuPresenter(eventMenuFactory.getEventMenu(), messageMenuFactory.createMessageMenu(), mainMenuController, languageManager);
    }

    /**
     * Creates a new user.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @param type     the type of the user.
     */
    public void signUp(String name, String password, String type) {
        userManager.addUser(userManager.getUsers().size() + 1000, name, password, type,
                new HashMap<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>());
    }

    /**
     * returns new id after signup
     */
    public int return_id() {
        return userManager.getUsers().size() + 1000 - 1;
    }
}
