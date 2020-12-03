package Controllers;

import Presenters.EventMenu.EventMenuPresenter;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Users.UserManager;

public class MainMenuController {
    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private UserManager userManager;

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
        eventMenuPresenter.run();
    }

    /**
     * Calls the Message menu presenter
     */
    public void printMessageMenu() {
        messageMenuPresenter.run();
    }

    /**
     * Changes the password of a given user
     *
     * @param password password of a given user
     */

    public void changePw(String password) {
        userManager.changePassword(password);
    }

}


