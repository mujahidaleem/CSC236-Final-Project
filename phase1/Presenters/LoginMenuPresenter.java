package Presenters;

import Controllers.LoginMenuController;
import UseCases.UserManager;
import Entities.User;
import java.util.Scanner;
import Presenters.MainMenuPresenter;


import sun.lwawt.macosx.CSystemTray;

//TODO:import the various menus for the different types of users.

public class LoginMenuPresenter {

    private static LoginMenuController loginMenuController;
    private static LoginMenuPresenter mainMenuPresenter;
    protected LoginMenuController loginMenuController;
    protected MainMenuPresenter mainMenuPresenter;

    /**
     * LoginMenuPresenter constructor
     *
     * @param loginMenuController need to call loginMenuController
     */

    public LoginMenuPresenter(LoginMenuController loginMenuController,) {
        this.loginMenuController = loginMenuController;
        this.mainMenuPresenter = mainMenuPresenter;
    }

    public LoginMenuPresenter(LoginMenuController loginMenuController) {
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greetingMessage();
        switch (scanner.next()) {
            case "1":
                logincommand(scanner.next());
            case "2":
                System.out.println("Please enter your first and last name");
                String name = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                System.out.println("Are you an attendee or an organizer?");
                String type = scanner.next();
                while (!type.equals("attendee") && !type.equals("organizer")) {
                    printTryAgain();
                    type = scanner.next();
                }
                loginMenuController.signUp(name, password, type);
                String id = Integer.parseInt(UserManager.users.length());
                System.out.println("Your id is" + id + ". Please remember it for the next time you login");
                mainMenuPresenter.run();
        }
    }


    public void logincommand(String command) {
        System.out.println("Please enter your id number and password, separated by a space");
        if (loginMenuController.login(command).equals("success")) {
            mainMenuPresenter.run();
        } else {
            System.out.println("Your username or password is incorrect. Please try again");
            run();

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