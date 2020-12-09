package Controllers;

import Controllers.Factories.EventMenuFactory;
import Controllers.Factories.MessageMenuFactory;
import Entities.Users.User;
import GUI.LoginMenuFrame;
import GUI.MainLoginPanel;
import GUI.UserCreationPanel;
import Presenters.LoginMenuPresenter;
import Presenters.MainMenuPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class LoginMenuController {
    private int width = 800;
    private int height = 800;

    protected UserManager userManager;
    protected EventManager eventManager;
    private EventMenuFactory eventMenuFactory;
    private MessageMenuFactory messageMenuFactory;
    private LoginMenuPresenter presenter;

    private JFrame frame;
    private MainLoginPanel loginPanel;
    private UserCreationPanel creationPanel;


    /**
     * LoginMenuPresenter constructor
     *
     * @param userManager stores the list of users
     */
    public LoginMenuController(UserManager userManager, EventManager eventManager, LanguageManager languageManager, RoomManager roomManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.eventMenuFactory = new EventMenuFactory(userManager, eventManager, languageManager, roomManager);
        this.messageMenuFactory = new MessageMenuFactory(userManager, eventManager, languageManager);
        this.frame = new JFrame();
        this.loginPanel = new MainLoginPanel(frame, this);
        this.creationPanel = new UserCreationPanel(frame, languageManager,this);
        this.presenter = new LoginMenuPresenter(this, languageManager, loginPanel, creationPanel);
    }

    public void printMenu(){
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        presenter.setUpMenu();
        frame.setContentPane(loginPanel.getPanel());
        frame.setVisible(true);
    }

    public void showCreateNewAccountPrompt(){
        presenter.showCreateNewAccountPrompt();
    }

    public void returnToLoginMenu(){
        presenter.showLoginMenu();
    }

    /**
     * Checks if the login credentials are correct.
     *
     * @param id the inputted username
     * @param password the inputted password
     * @return The user with those login credentials or nothing if the credentials are wrong
     */
    public void checkLogin(int id, String password) {
        for (User user : userManager.users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                userManager.setCurrentUser(user);
                MainMenuController mainMenuController = new MainMenuController(eventMenuFactory.getEventMenu(), messageMenuFactory.createMessageMenu(), userManager);
                mainMenuController.printMenu();
            }
        }
        presenter.loginFailed();
    }

    /**
     * Creates a main menu presenter in the event of a login
     *
     * @return MainMenuPresenter
     */
    public MainMenuPresenter login(LanguageManager languageManager) {
        MainMenuController mainMenuController = new MainMenuController(eventMenuFactory.getEventMenu(), messageMenuFactory.createMessageMenu(), userManager);
        return new MainMenuPresenter(eventMenuFactory.getEventMenu(), messageMenuFactory.createMessageMenu(), mainMenuController, languageManager);
    }

    /**
     * Creates a new user.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @param type     the type of the user.
     */
    public void signUp(String name, String password, String type) {
        userManager.addUser(userManager.getUsers().size() + 1000, name, password, type,
                new HashMap<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>());
        presenter.showCreateAccountSuccess(returnId());
    }

    /**
     * returns new id after signup
     */
    public int returnId() {
        return userManager.getUsers().size() + 1000 - 1;
    }
}
