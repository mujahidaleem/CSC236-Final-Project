package Controllers;

import Controllers.EventMenu.EventMenuController;
import Controllers.Factories.EventMenuFactory;
import Controllers.Factories.MessageMenuFactory;
import Controllers.MessageMenu.UserFriendListController;
import GUI.MainMenuPanel;
import Presenters.EventMenu.EventMenuPresenter;
import Presenters.MainMenuPresenter;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import javax.swing.*;

public class MainMenuController {
    private boolean menuCreated = false;

    private EventMenuController eventMenuController;
    private UserFriendListController friendListController;

    private UserManager userManager;
    private EventManager eventManager;
    private MainMenuPanel mainMenuPanel;
    private MainMenuPresenter presenter;

    private LoginMenuController loginMenuController;
    private String currentTheme;
    private String currentLanguage;

    /**
     * @param userManager          attribute userManager
     */
    public MainMenuController(EventMenuFactory eventMenuFactory, MessageMenuFactory messageMenuFactory,
                              UserManager userManager, LanguageManager languageManager, EventManager eventManager,
                              JFrame frame, LoginMenuController loginMenuController) {
        this.mainMenuPanel = new MainMenuPanel(frame, eventManager, this, languageManager);
        this.presenter = new MainMenuPresenter(languageManager, mainMenuPanel);
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.loginMenuController = loginMenuController;

        this.eventMenuController = eventMenuFactory.getEventMenu(mainMenuPanel);
        this.friendListController = messageMenuFactory.createMessageMenu(mainMenuPanel);
    }

    public MainMenuController(EventMenuPresenter eventMenu, MessageMenuPresenter messageMenu, UserManager userManager) {
    }

    /**
     * Calls the Event menu presenter
     */

    public void printEventMenu() {
        eventMenuController.printMenu(currentTheme);
    }

    public void printMenu(String theme) {
        currentTheme = theme;
        presenter.setUpMenu(currentTheme);
        menuCreated = true;
    }

    /**
     * Calls the Message menu presenter
     */
    public void printMessageMenu() {
        friendListController.printMenu();
    }


    /**
     * Tells the user that the date inputted could not be recognized
     */
    public void showIncorrectDate(){
        presenter.showIncorrectDate();
    }

    /**
     * Shows the prompt asking for the new password
     */
    public void showChangePasswordPrompt(){
        changePassword(presenter.changePassword());
        presenter.showChangePasswordResult();
    }

    /**
     * Changes the password of a given user
     *
     * @param password password of a given user
     */

    public void changePassword(String password) {
        userManager.changePassword(password);
    }

    /**
     * Logs the user out of the current session
     */
    public void logout(){
        loginMenuController.saveFiles();
        userManager.setCurrentUser(null);
        loginMenuController.returnToLoginMenu();
    }

    /**
     * Changes the theme of the GUI
     * @param theme the new theme
     */
    public void changeTheme(String theme){
        currentTheme = theme;
        if(menuCreated){
            presenter.changeTheme(theme);
        }
        if(eventMenuController.isMenuCreated()){
            eventMenuController.changeTheme(theme);
        }
        if(friendListController != null){
            friendListController.changeTheme(theme);
        }
    }

    /**
     * Changes the language of the GUI
     * @param language the new language
     */
    public void changeLanguage(String language){
        currentLanguage = language;
        if(menuCreated){
            presenter.changeLanguage(language);
        }
        if(eventMenuController.isMenuCreated()){
            eventMenuController.changeLanguage(language);
        }
        if(friendListController != null){
            friendListController.changeLanguage(language);
        }
    }
}