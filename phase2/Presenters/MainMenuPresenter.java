package Presenters;

import Controllers.MainMenuController;
import Presenters.EventMenu.EventMenuPresenter;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;

import java.util.Scanner;

public class MainMenuPresenter {

    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private MainMenuController mainMenuController;
    private LanguageManager languageManager;

    /**
     * MainMenuPresenter constructor
     *
     * @param eventMenuPresenter   need to call eventMenuPresenter
     * @param messageMenuPresenter need to call messageMenuPresenter
     * @param languageManager      need to display strings
     */

    public MainMenuPresenter(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter, MainMenuController mainMenuController, LanguageManager languageManager) {
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.mainMenuController = mainMenuController;
        this.languageManager = languageManager;
    }

    /**
     * main method, called by the main code to initialize MainMenuPresenter
     */

    public void run() {
        while (true) {
            printMenu();
            String userOption = takeInput();
            if (userOption.equals("3")) {
                break;
            } else {
                switch (userOption) {
                    case "1":
                        mainMenuController.printEventMenu();
                        break;
                    case "2":
                        mainMenuController.printMessageMenu();
                        break;
                    case "4":
                        System.out.println(languageManager.languagePack.changePassword());
                        String userPwChange = takeInput();
                        mainMenuController.changePw(userPwChange);
                        break;
                    default:
                        System.out.println(languageManager.languagePack.unknownCommand());
                }
            }
        }
    }

    /**
     * To print command line menu
     */

    public void printMenu() {
        System.out.println(languageManager.languagePack.mainMenuCommands());
    }

    /**
     * To return user's input
     *
     * @return user's input value
     */
    public static String takeInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

}
