package Controllers;

import Entities.User;
import UseCases.UserManager;

public class LoginMenuController {
    protected UserManager userManager;

    /**
     * LoginMenuPresenter constructor
     *
     * @param userManager stores the list of users
     */
    public LoginMenuController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Checks if the login credentials are correct.
     *
     * @param command the inputted login credentials
     * @return The user with those login credentials or nothing if the credentials are wrong
     */
    public User login(String command) {
        int id = Integer.parseInt(command.split("_")[0]);
        String password = command.split("_")[1];
        for (User user : userManager.users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
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
}

