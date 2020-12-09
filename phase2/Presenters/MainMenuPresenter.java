package Presenters;

import Controllers.MainMenuController;
import GUI.MainMenuPanel;
import UseCases.Language.LanguageManager;

import javax.swing.*;
import java.util.Scanner;

public class MainMenuPresenter {
    private LanguageManager languageManager;
    private MainMenuPanel mainMenuPanel;

    /**
     * MainMenuPresenter constructor
     *
     * @param languageManager      need to display strings
     */

    public MainMenuPresenter(LanguageManager languageManager, MainMenuPanel mainMenuPanel) {
        this.languageManager = languageManager;
        this.mainMenuPanel = mainMenuPanel;
    }

//    /**
//     * main method, called by the main code to initialize MainMenuPresenter
//     */
//
//    public void run() {
//        while (true) {
//            setUpMenu();
//            String userOption = takeInput();
//            if (userOption.equals("3")) {
//                break;
//            } else {
//                switch (userOption) {
//                    case "1":
//                        mainMenuController.printEventMenu();
//                        break;
//                    case "2":
//                        mainMenuController.printMessageMenu();
//                        break;
//                    case "4":
//                        System.out.println(languageManager.languagePack.changePassword());
//                        String userPwChange = takeInput();
//                        mainMenuController.changePassword(userPwChange);
//                        break;
//                    default:
//                        System.out.println(languageManager.languagePack.unknownCommand());
//                }
//            }
//        }
//    }

    /**
     * To print command line menu
     */

    public void setUpMenu() {
        mainMenuPanel.createButtons();
        mainMenuPanel.setText(languageManager.languagePack);
        mainMenuPanel.changePanel(mainMenuPanel.getPanel());
    }

    public void changeLanguage(String language){
        languageManager.changeLanguage(language);
        mainMenuPanel.setText(languageManager.languagePack);
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

    public String changePassword(){
        return JOptionPane.showInputDialog(mainMenuPanel.getPanel(), "new password"); //TODO: add this to language pack
    }

    public void showChangePasswordResult(){
        JOptionPane.showMessageDialog(mainMenuPanel.getPanel(), "");//TODO: add text
    }

}
