package Presenters;

import Controllers.LoginMenuController;
import Controllers.MainMenuController;
import UseCases.UserManager;
import sun.applet.Main;

import java.util.Scanner;

public class MainMenuPresenter {

    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private LoginMenuController loginMenuController;
    private MainMenuController mainMenuController;
    /**
     * MainMenuPresenter constructor
     * @param eventMenuPresenter need to call eventMenuPresenter
     * @param messageMenuPresenter need to call messageMenuPresenter
     * @param loginMenuController need to call loginMenuController
     */

    public MainMenuPresenter(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter, LoginMenuController loginMenuController, MainMenuController mainMenuController){
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.loginMenuController = loginMenuController;
        this.mainMenuController = mainMenuController;
    }

    /**
     * main method, called by the main code to initialize MainMenuPresenter
     */

    public void run() {
        printMenu();
        String userOption = takeInput();
        while (!userOption.equals("1") && !userOption.equals("2") && !userOption.equals("3") && !userOption.equals("4")){
            MainMenuPresenter.printTryAgain();
            MainMenuPresenter.takeInput();
        }
        switch (userOption){
            case "1":
                mainMenuController.printEventMenu();
            case "2":
                mainMenuController.printMessageMenu();
            case "3":
                mainMenuController.logOut();
            case "4":
                String userPwChange = takeInput();
                mainMenuController.changePw(userPwChange);
            case "5":
                mainMenuController.sysExit();
        }
    }

    /**
     * To print command line menu
     */

    public static void printMenu(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Please select a sub menu. Type the number and press enter:");
        System.out.println("1. Event Manager");
        System.out.println("2. Messages");
        System.out.println("3. Log out");
        System.out.println("4. Change Password");
        System.out.println("5. Quit");
    }

    /**
     * To print try again prompt
     */

    public static void printTryAgain(){
        System.out.println("Please enter a valid option:");
    }

    /**
     * To return user's input
     * @return user's input value
     */
    public static String takeInput(){
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

}
