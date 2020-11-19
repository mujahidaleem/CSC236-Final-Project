package Controllers;

import Presenters.MainMenuPresenter;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import Entities.User;
import UseCases.UserManager;
import Gateways.UserReader;
import sun.font.TrueTypeFont;

//import database from gateway?


public class  LoginMenuController {
    private final MainMenuPresenter mainMenuPresenter;
    private LoginMenuPresenter loginMenuPresenter;
    private UserManager userManager;

    /**
     * LoginMenuPresenter constructor
     *
     * @param loginMenuPresenter need to call loginMenuPresenter
     */
    public LoginMenuController(UserManager userManager, LoginMenuPresenter
            loginMenuPresenter, MainMenuPresenter mainMenuPresenter) {
        this.mainMenuPresenter = mainMenuPresenter;
        this.loginMenuPresenter = loginMenuPresenter;
        this.userManager = userManager;
    }

    /**
     * command should follow format of "id, password"
     */

    public String login(String command) {
        // read command to see if user exists
        String strid = command.split(" ")[0];
        int id = Integer.parseInt(strid);
        String password = command.substring(strid.length() + 1);

        if (password_matches_id(id, password)) {
            return "success";
        } else {
            return "failure";
        }

    }

    public void signUp(String name, String password, String type) {
        UserManager.addUser(name, password, type);
    }


    public boolean password_matches_id(int id, String password) {
        //checks system to see if password matches username
        User user = UserManager.finduser(id);
        return user.getPassword().equals(password);
    }

    public void logout() {
        // this method brings us back to the login menu.
        loginMenuPresenter.run();
    }

    public void exit(){

    }


}

