package Presenters;

import Controllers.LoginMenuController;
import UseCases.UserLoginManager;
import Entities.User;

//TODO:import the various menus for the different types of users.

public class LoginMenuPresenter{
    private final LoginMenuController controller;

    /**
    LoginMenuPresenter constructor
     controller
     **/
    public LoginMenuPresenter(LoginMenuController loginMenuController){
        this.controller = loginMenuController;
    }
}

    public static void printLoginMenu(String[] command){
        System.out.println("Please enter your username and password, separated by a space");

}/** command should follow format of "username password"
 */
    public static void login(String command) {
        String username = command.split(" ")[0];
        String password = command.substring(username.length()+1);

        if (LoginMenuController.username_exists(command)){
            //check if password matches username
            if (LoginMenuController.password_matches_username(username, password)) {
                //check type of user, then present appropriate screen
                if (LoginMenuController.type_of_user(username) == "Attendee") {
                    //display attendee event presenter
                } else if (LoginMenuController.type_of_user(username) == "Organizer") {
                }
                //display organizer screen
                else if (LoginMenuController.type_of_user(username) == "Speaker") {
                    //display speaker screen;
                } else {
                    System.out.println("Your username or password is incorrect. Please try again");
                    //restart this process
                    login();
                }
        else{
                    System.out.println("This username does not exist. Please sign up or try again");
                    //add option to create new account then add to this.
                }
            }


        }
