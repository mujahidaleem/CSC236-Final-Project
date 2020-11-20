package Controllers;

import Entities.Attendee;
import Presenters.*;
import UseCases.UserManager;

public class MainMenuController {
    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private LoginMenuController loginMenuController;
    private UserManager userManager;

    /**
     *
     * @param eventMenuPresenter attribute eventMenuPresenter
     * @param messageMenuPresenter attribute messageMenuPresenter
     * @param userManager attribute userManager
     */
    public MainMenuController(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter,
                              UserManager userManager){
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.userManager = userManager;
    }

    public void printEventMenu(){
        eventMenuPresenter.run();
    }

    public void printMessageMenu(){
        messageMenuPresenter.run();
    }

    public void logOut(){
        userManager.setCurrentUser(null);
    }

    public void changePw(String password){ userManager.changePassword(password);
    }

    public void sysExit(){
        System.exit(0);
    }
}


