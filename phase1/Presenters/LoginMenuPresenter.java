package Presenters;

import Controllers.LoginMenuController;
import java.util.Scanner;

import Controllers.MainMenuController;
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


    //public void run()
    // figure out how to take multiple command line commands without needing arguments for run()
    public void run() {
        Scanner scanner = new Scanner(System.in);
        greetingMessage();
        switch (scanner.next()) {
            case "1":
                loginCommand(scanner.next());
            case "2":
                System.out.println("Please enter your first and last name");
                String name = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                System.out.println("Are you an attendee or an organizer?");
                String type = scanner.next();
                while (type != "attendee" && type != "organizer") {
                    printTryAgain();
                    String userType = scanner.next();
            loginMenuController.signUp(name, password, userType);
            MainMenuPresenter.run();
                }
        }


        //TODO get the id of this new user, and print "This is your id. please remember it for login purposes"
    }


    public void loginCommand(String command) {
        System.out.println("Please enter your id number and password, separated by a space");
        if (loginMenuController.login(command).equals("success")) {
            MainMenuPresenter.run();
        } else {
            System.out.println("Your username or password is incorrect. Please try again");
            this.run();

            }
        }


    public static void greetingMessage(){
        System.out.println("Welcome to the conference! Would you like to log into your existing account or" +
                " create a new account?\n" +
                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
                "Enter 'exit' at any time to exit the program.");
    }

    public static void printTryAgain(){
        System.out.println("Input invalid. Please try again.");
    }
}




