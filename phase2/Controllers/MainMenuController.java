package Controllers;

import Controllers.EventMenu.EventMenuController;
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

    private EventMenuController eventMenuController;
    private UserFriendListController friendListController;

    private UserManager userManager;
    private EventManager eventManager;
    private MainMenuPanel mainMenuPanel;
    private MainMenuPresenter presenter;

    /**
     * @param eventMenuController   attribute eventMenuPresenter
     * @param friendListController attribute messageMenuPresenter
     * @param userManager          attribute userManager
     */
    public MainMenuController(EventMenuController eventMenuController, UserFriendListController friendListController, UserManager userManager, LanguageManager languageManager, EventManager eventManager, JFrame frame) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.eventMenuController = eventMenuController;
        this.friendListController = friendListController;
        this.mainMenuPanel = new MainMenuPanel(frame, eventManager, this);
        this.presenter = new MainMenuPresenter(languageManager, mainMenuPanel);
    }

    /**
     * Calls the Event menu presenter
     */

    public void printEventMenu() {
        eventMenuController.printMenu();
    }

    /**
     * main method, called by the main code to initialize MainMenuPresenter
     */

    public void printMenu() {
        presenter.setUpMenu();
    }

    /**
     * Calls the Message menu presenter
     */
    public void printMessageMenu() {
        friendListController.printMenu();
    }

    public void changeLanguage(String language){

        presenter.changeLanguage(language);
    }

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

    public void logout(){

    }
}


