package Controllers;

import Controllers.EventMenu.EventMenuController;
import Controllers.MessageMenu.UserFriendListController;
import GUI.MainMenuPanel;
import Presenters.EventMenu.EventMenuPresenter;
import Presenters.MainMenuPresenter;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Users.UserManager;

public class MainMenuController {
    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;


    private EventMenuController eventMenuController;
    private UserFriendListController friendListController;
    private UserManager userManager;
    private MainMenuPanel mainMenuPanel;
    private MainMenuPresenter presenter;

    /**
     * @param eventMenuPresenter   attribute eventMenuPresenter
     * @param messageMenuPresenter attribute messageMenuPresenter
     * @param userManager          attribute userManager
     */
    public MainMenuController(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter,
                              UserManager userManager) {
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.userManager = userManager;
    }

    /**
     * Calls the Event menu presenter
     */

    public void printEventMenu() {
        eventMenuPresenter.printMenu();
    }

    /**
     * main method, called by the main code to initialize MainMenuPresenter
     */

    public void printMenu() {
        presenter.printMenu();

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

    /**
     * Changes the password of a given user
     *
     * @param password password of a given user
     */

    public void changePw(String password) {
        userManager.changePassword(password);
    }

    public void logout(){
        //TODO
    }
}


