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
        outer:
        while (true) {
            greetingMessage();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {
                case "exit":
                    break outer;
                case "1":
                    System.out.println("Please enter your ID NUMBER and PASSWORD, separated by a _ \n or input \"exit\" to go back.");
                    loginCommand(scanner.next());
                    break;
                case "2":
                    System.out.println("Please enter your first and last name");
                    String name = scanner.next();
                    System.out.println("Please enter your password");
                    String password = scanner.next();
                    System.out.println("Are you an attendee or an organizer? Please input \"attendee\" or \"organizer\" in lower case.");
                    while (true) {
                        String type = scanner.next();
                        if (type.equals("attendee") || type.equals("organizer")){
                            loginMenuController.signUp(name, password, type);
                            String id = String.valueOf(loginMenuController.return_id());
                            System.out.println("Your id is "+ id +". Please remember it for logging in.");
                            break;
                        }

                        printTryAgain();
                    }
                    break;
            }
        }
    }

    public void loginCommand(String command) {
        if (command.equals("exit")){
            return;
        }
        try {
            System.out.println(loginMenuController.checkLogin(command));
            if (loginMenuController.checkLogin(command) != null) {
                loginMenuController.login().run();
            } else {
                System.out.println("Your username or password is incorrect or does not exist. Please try again");
            }
        } catch (Exception e) {
            printTryAgain();
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

