package Controllers;

import Presenters.*;
import UseCases.UserManager;

public class MainMenuController {
    private EventMenuPresenter eventMenuPresenter;
    private MessageMenuPresenter messageMenuPresenter;
    private LoginMenuController loginMenuController;
    public MainMenuController(EventMenuPresenter eventMenuPresenter, MessageMenuPresenter messageMenuPresenter,
                              LoginMenuController loginMenuController){
        this.eventMenuPresenter = eventMenuPresenter;
        this.messageMenuPresenter = messageMenuPresenter;
        this.loginMenuController = loginMenuController;
    }

    public void printEventMenu(){eventMenuPresenter.run();}
    public void printMessageMenu(){messageMenuPresenter.run();}
    public void logOut(){loginMenuController.logout();}
    public void changePw(String password){
        UserManager.changePassword(password);}
    public void sysExit(){System.exit(0);}
    }

