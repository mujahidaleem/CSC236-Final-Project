package Controllers;

import Entities.Users.User;
import Presenters.MainMenuPresenter;
import Presenters.MenuFactory;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;


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
    public LoginMenuController(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.menuFactory = new MenuFactory(userManager, eventManager, userFriendManager, languageManager);
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

    /**
     * Creates a main menu presenter in the event of a login
     *
     * @return MainMenuPresenter
     */
    public MainMenuPresenter login(LanguageManager languageManager) {
        MainMenuController mainMenuController = new MainMenuController(menuFactory.createEventMenu(), menuFactory.createMessageMenu(), userManager);
        return new MainMenuPresenter(menuFactory.createEventMenu(), menuFactory.createMessageMenu(), mainMenuController, languageManager);
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
     * returns new id after signup
     */
    public int return_id() {
        return userManager.getUsers().size() + 1000 - 1;
    }
}
