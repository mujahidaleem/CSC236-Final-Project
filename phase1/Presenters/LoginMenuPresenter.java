package Presenters;

import Controllers.LoginMenuController;
import UseCases.LoginMenuManager;
import UseCases.UserManager;
import Entities.User;
import java.util.Scanner;
import Presenters.MainMenuPresenter;


import sun.lwawt.macosx.CSystemTray;

//TODO:import the various menus for the different types of users.

public class LoginMenuPresenter {

    protected LoginMenuManager loginMenuManager;
    protected LoginMenuController loginMenuController;
    protected MainMenuPresenter mainMenuPresenter;

    /**
     * LoginMenuPresenter constructor
     * @param loginMenuManager need to call loginMenuManager
     * @param loginMenuController need to call loginMenuController
     */

    public LoginMenuPresenter(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
        this.loginMenuManager = loginMenuManager;
        this.mainMenuPresenter = mainMenuPresenter;
    }


    public void run(String [] args){
        Scanner scanner = new Scanner(System.in);
        greetingMessage();
        switch(scanner.next()){
            case "1":
                System.out.println("Please enter your id number and password, separated by a space");
                if (loginMenuController.login(scanner.next()).equals("success")) {
                    mainMenuPresenter.run();
                }
                else{
                    System.out.println("Your username or password is incorrect. Please try again");
                    //TODO figure out how to get this to method to run again?
                }
            case "2":
                System.out.println("Please enter your first and last name");
                String name = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                System.out.println("Are you an attendee or an organizer?");
                while (scanner.next()!= "attendee"&& scanner.next()!= "organizer"){
                    printTryAgain();
                }
                loginMenuController.signup(name, password,scanner.next());
                mainMenuPresenter.run();
        }
    }

    public static void greetingMessage(){
        System.out.println("Welcome to the conference! Would you like to log into your existing account or" +
                " create a new account?\n" +
                "Please enter "1" to log into your existing account, and "2" to create a new account");
    }
    public static void printLoginMenu(String[] command) {
        if (command.equals("1")) {
            System.out.println("Please enter your id and password, separated by a space");
        } else if (command.equals("2")) {
            // ask user to input name, password, and type
            // assign id and say "your id is _. Please remember it for login purposes!"
        }
    }
    public static void printTryAgain(){
        System.out.println("Input invalid, please try again.");
    }
}
