package Presenters;

import Controllers.LoginMenuController;
import java.util.Scanner;

import Presenters.MainMenuPresenter;




//TODO:import the various menus for the different types of users.

public class LoginMenuPresenter {

    public LoginMenuController loginMenuController;

    /**
     * LoginMenuPresenter constructor
     *
     * @param loginMenuController need to call loginMenuController

     */

    public LoginMenuPresenter(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
    }

    public void run(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            greetingMessage();
            switch (scanner.next()) {
                case "1":
                    loginCommand(scanner.next());
                    break;
                case "2":
                    System.out.println("Please enter your first and last name");
                    String name = scanner.next();
                    System.out.println("Please enter your password");
                    String password = scanner.next();
                    System.out.println("Are you an attendee or an organizer?");
                    String type = scanner.next();
                    //TODO FIX THIS COMPARISION
                    if (type.equals("attendee") || type.equals("organizer")){

                    }
                    else
                        while (type != "attendee" || type != "organizer") {
                            printTryAgain();
                            String userType = scanner.next();
                            loginMenuController.signUp(name, password, userType);
                            String id = String.valueOf(loginMenuController.return_id());
                            System.out.println("Your id is"+ id +". Please remember it!!!");
                            break;
                        }
            }
        }

    }

    public void loginCommand(String command) {
        System.out.println("Please enter your id number and password, separated by a _");
        if (loginMenuController.checkLogin(command) != null) {
            loginMenuController.login().run();
        } else {
            System.out.println("Your username or password is incorrect. Please try again");
        }
    }


    public static void greetingMessage(){
        System.out.println("Welcome to the conference! Would you like to log into your existing account or" +
                " create a new account?\n" +
                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
                "Enter 'exit' at to exit the program.");
    }

    public static void printTryAgain(){
        System.out.println("Input invalid. Please try again.");
    }
}

