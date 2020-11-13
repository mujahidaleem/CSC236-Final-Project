package Presenters;

import Controllers.LoginMenuController;
import UseCases.UserLoginManager;
//import the various menus for the different types of users.

public class LoginMenuPresenter{

    public static void printLoginMenu(String[] command){
        System.out.println("Please enter your username and password, separated by a space");
        //make sure in use cases usernames and passwords cannot contain spaces
    }
}
    public static void login(String[] command){
        String username = command.split(" ")[0];
        String password = command.split(" ")[1];

        if (username_exists(command)){
            //check if password matches username
            if (password_matches_username(username, password)){
                //check type of user, then present appropriate screen
                if (type_of_user(username) == "Attendee") {
                    //display attendee event presenter
                }
                else if (type_of_user(username) == "Organizer"){
                }
                //display organizer screen
                else if (type_of_user(username) == "Speaker"){
                    //display speaker screen;
                }
                else{
                    System.out.println("Your username or password is incorrect. Please try again");
                    //restart this process
                    login();
                }
        else{
                    System.out.println("This username does not exist. Please sign up or try again")
                    //add option to create new account then add to this.
                }
            }


        }