package Presenters;

import Controllers.LoginMenuController;
import java.util.Scanner;


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
        greetingMessage();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {
                case "1":
                    System.out.println("Please enter your ID NUMBER and PASSWORD, separated by a _");
                    loginCommand(scanner.next());
                    break;
                case "2":
                    System.out.println("Please enter your first and last name");
                    String name = scanner.next();
                    System.out.println("Please enter your password");
                    String password = scanner.next();
                    System.out.println("Are you an attendee or an organizer?");
                    while (true) {
                        String type = scanner.next();
                        if (type.equals("attendee") || type.equals("organizer")){
                            loginMenuController.signUp(name, password, type);
                            String id = String.valueOf(loginMenuController.return_id());
                            System.out.println("Your id is"+ id +". Please remember it for logging in.");
                            System.out.println("Please enter '1' to log into your existing account, and '2' to create a new account\n" +
                                    "Enter 'exit' at to exit the program.");
                            break;
                        }
                        printTryAgain();
                    }
            }
        }

    }

    public void loginCommand(String command) {
        try {
            if (loginMenuController.checkLogin(command) != null) {
                loginMenuController.login().run();
            } else {
                System.out.println("Your username or password is incorrect or does not exist. Please try again");
            }
        } catch (Exception e) {
            printTryAgain();
        }
        //TODO fix this part
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

