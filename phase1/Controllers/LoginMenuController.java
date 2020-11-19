package Controllers;

import Presenters.MainMenuPresenter;
import UseCases.LoginMenuManager;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import Entities.User;
import UseCases.UserManager;
import Gateways.UserReader;
import sun.font.TrueTypeFont;

//import database from gateway?


public class  LoginMenuController {
    protected LoginMenuManager loginMenuManager;
    protected MainMenuPresenter mainMenuPresenter;
    protected LoginMenuPresenter loginMenuPresenter;
    protected UserManager userManager;

    /**
     * LoginMenuPresenter constructor
     * @param loginMenuManager need to call loginMenuManager
     * @param loginMenuPresenter need to call loginMenuPresenter
     */
    public LoginMenuController(UserManager userManager, LoginMenuManager loginMenuManager, LoginMenuPresenter
                               loginMenuPresenter, MainMenuPresenter mainMenuPresenter) {
    this.loginMenuManager = LoginMenuManager;
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

        if (LoginMenuManager.password_matches_id(id, password)) {
            String o = "success";
            return o;
        } else {
            String e = "failure";
            return e;
        }



    public void signUp(String name, String password, String type) {
        UserManager.addUser(name, password, type);
    }

    public void logout(){
            // this method brings us back to the login menu.
        loginMenuPresenter.run([" "]);
        }

    public void exit(){
        //TODO: how to close the program
    }


}

