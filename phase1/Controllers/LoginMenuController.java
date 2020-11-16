package Controllers;

import UseCases.LoginMenuManager;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import Entities.User;
import UseCases.UserManager;
import Gateways.UserReader;
import sun.font.TrueTypeFont;

//import database from gateway?


public class  LoginMenuController {
    // initialize login menu

    public LoginMenuController(UserManager UserManager;) {

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


    public void signup(String name, String password, String type){
            UserManager.adduser(name, password, type);
        }
    }

    public void logout() {
    }

    }
}

