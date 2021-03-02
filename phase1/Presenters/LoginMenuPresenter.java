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

    /**
     * First, calls the greeting message with the commands available to the user
     * The user can create an account or login
     * Depending on the command, a switch case will be triggered
     * Logging in command will push the user to the loginCommand method
     * The user can exit by typing "exit"
     * The create account is done by inputting a name (it can be anything) and then password, the id is given at the end
     * The id is used to login, to allow for users who have the same name
     */
    public void run() {
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
                    System.out.println("Please enter your first and last name separated by _");
                    String name = scanner.next();
                    System.out.println("Please enter your password");
                    String password = scanner.next();
                    System.out.println("Are you an attendee or an organizer? Please input \"attendee\" or \"organizer\" in lower case.");
                    while (true) {
                        String type = scanner.next();
                        if (type.equals("attendee") || type.equals("organizer")) {
                            loginMenuController.signUp(name, password, type);
                            String id = String.valueOf(loginMenuController.return_id());
                            System.out.println("Your id is " + id + ". Please remember it for logging in.");
                            break;
                        }

                        printTryAgain();
                    }
                    break;
            }
        }
    }

    /**
     * The login security checkpoint
     * Inputted USERID_PASSWORD is checked. If correct the user moves to their main menu
     * Otherwise, go back to run()
     *
     * @param command inputted USERID_PASSWORD from run method
     */
    public void loginCommand(String command) {
        if (command.equals("exit")) {
            return;
        }
        if (loginMenuController.checkLogin(command) != null) {
            loginMenuController.login().run();
        } else {
            System.out.println("Your username or password is incorrect or does not exist. Please try again");
        }
    }

    /**
     * Generic Greeting message
     */
    public static void greetingMessage() {
        System.out.println("Welcome to the conference! Would you like to log into your existing account or" +
                " create a new account?\n" +
                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
                "Enter 'exit' at to exit the program.");
    }

    /**
     * Generic try again message for invalid inputs
     */
    public static void printTryAgain() {
        System.out.println("Input invalid. Please try again.");
    }
}

