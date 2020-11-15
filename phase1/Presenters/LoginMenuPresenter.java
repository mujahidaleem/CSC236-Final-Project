package Presenters;

import Controllers.LoginMenuController;
import UseCases.LoginMenuManager;
import UseCases.UserManager;
import Entities.User;

//TODO:import the various menus for the different types of users.

public class LoginMenuPresenter {
    private final LoginMenuController controller;

    /**
     * LoginMenuPresenter constructor
     * controller
     **/
    public LoginMenuPresenter(LoginMenuController loginMenuController) {
        this.controller = loginMenuController;
    }


    public static void printLoginMenu(String[] command) {
        System.out.println("Please enter your id and password, separated by a space");

    }

    /**
     * command should follow format of "id, password"
     */

    public static void loginpresenter(String command) {
        // reads command to LoginMenuController
        if (LoginMenuController.login(command)).equals("incorrect");
            System.out.println("Your username or password is incorrect. Please try again");

        }
    }
}
