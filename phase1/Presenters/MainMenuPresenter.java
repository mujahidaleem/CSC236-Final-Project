package Presenters;

import Controllers.LoginMenuController;
import Controllers.MainMenuController;
import UseCases.UserManager;

import java.util.Scanner;

public class MainMenuPresenter {

    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private MainMenuController mainMenuController;
    /**
     * MainMenuPresenter constructor
     * @param eventMenuPresenter need to call eventMenuPresenter
     * @param messageMenuPresenter need to call messageMenuPresenter
     */

    public MainMenuPresenter(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter, MainMenuController mainMenuController){
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.mainMenuController = mainMenuController;
    }

    /**
     * main method, called by the main code to initialize MainMenuPresenter
     */

    public void run() {
        while (true){
            printMenu();
            String userOption = takeInput();
            MainMenuPresenter.printTryAgain();
            MainMenuPresenter.takeInput();
            if (userOption.equals("3")){
                break;
            } else {
                switch (userOption){
                    case "1":
                        mainMenuController.printEventMenu();
                        break;
                    case "2":
                        mainMenuController.printMessageMenu();
                        break;
                    case "4":
                        String userPwChange = takeInput();
                        mainMenuController.changePw(userPwChange);
                        break;
                    case "5":
                        mainMenuController.sysExit();
                        break;
                }
            }
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
