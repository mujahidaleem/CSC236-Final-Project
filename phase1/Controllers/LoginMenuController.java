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
    private static MainMenuPresenter mainMenuPresenter;
    private static LoginMenuPresenter loginMenuPresenter;
    private static UserManager userManager;

    /**
     * LoginMenuPresenter constructor
     *
     * @param loginMenuPresenter need to call loginMenuPresenter
     */
    public LoginMenuController(UserManager userManager, LoginMenuPresenter
            loginMenuPresenter, MainMenuPresenter mainMenuPresenter) {
        this.mainMenuPresenter = MainMenuPresenter;
        this.loginMenuPresenter = LoginMenuPresenter;
        this.userManager = UserManager;
    }

    /**
     * command should follow format of "id, password"
     */

    public String login(String command) {
        // read command to see if user exists
        int id = Integer.parseInt(command.split(" ")[0]);
        String password = command.substring(id.length() + 1);

        if (password_matches_id(id, password)) {
            String o = "success";
            return o;
        } else {
            String e = "failure";
            return e;
        }

    }

    public void signUp(String name, String password, String type) {
        UserManager.addUser(name, password, type);
    }


    public static boolean password_matches_id(int id, String password) {
        //checks system to see if password matches username
        User user = UserManager.finduser(id);
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        // this method brings us back to the login menu.
        loginMenuPresenter.run();
    }

    public void exit(){
        //TODO: how to close the program
    }


}

