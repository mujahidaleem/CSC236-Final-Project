package Controllers;

import Controllers.Factories.EventMenuFactory;
import Controllers.Factories.MessageMenuFactory;
import Entities.Users.User;
import GUI.MainFrame.LoginMenuFrame;
import GUI.MainFrame.ThemeManager;
import GUI.MainLoginPanel;
import GUI.UserCreationPanel;
import Gateways.EventReader;
import Gateways.MessageReader;
import Gateways.RoomReader;
import Gateways.UserReader;
import Presenters.LoginMenuPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;


public class LoginMenuController {
    private int width = 800;
    private int height = 800;

    protected UserManager userManager;
    protected EventManager eventManager;
    protected RoomManager roomManager;
    protected UserFriendManager userFriendManager;

    private EventMenuFactory eventMenuFactory;
    private MessageMenuFactory messageMenuFactory;
    private LoginMenuPresenter presenter;
    private LanguageManager languageManager;
    private ThemeManager themeManager;

    private UserReader userReader;
    private EventReader eventReader;
    private RoomReader roomReader;
    private MessageReader messageReader;

    private LoginMenuFrame frame;
    private MainLoginPanel loginPanel;
    private UserCreationPanel creationPanel;

    private MainMenuController mainMenuController;
    private String currentTheme = "lightTheme";
    private String currentLanguage = "english";

    /**
     * LoginMenuPresenter constructor
     *
     * @param userReader the gateway to read the list of users
     * @param eventReader the gateway to read the list of events
     * @param roomReader the gateway to read the list of rooms
     * @param languageManager stores the strings used in the GUI
     */
    public LoginMenuController(LanguageManager languageManager, UserReader userReader, EventReader eventReader, RoomReader roomReader, MessageReader messageReader) {
        this.userReader = userReader;
        this.eventReader = eventReader;
        this.roomReader = roomReader;
        this.messageReader = messageReader;

        this.userManager = userReader.readData();
        this.eventManager = eventReader.readData();
        this.roomManager = roomReader.readData();
        this.languageManager = languageManager;

        this.frame = new LoginMenuFrame(this);
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e the windowEvent
             */
            @Override
            public void windowClosing(WindowEvent e) {
                if(presenter.confirmExit(frame)){
                    saveFiles();
                    System.exit(0);
                }
            }
        });
        this.frame.setMenuBar();

        this.eventMenuFactory = new EventMenuFactory(userManager, eventManager, languageManager, roomManager, frame);
        this.messageMenuFactory = new MessageMenuFactory(userManager, eventManager, languageManager);

        this.loginPanel = new MainLoginPanel(frame, this, languageManager);
        this.creationPanel = new UserCreationPanel(frame, languageManager,this);

        this.presenter = new LoginMenuPresenter(this, this.languageManager, loginPanel, creationPanel);
    }

    /**
     * Creates the frame of the GUI and the first panel
     */
    public void printMenu(){
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        presenter.setUpMenu();
        frame.setContentPane(loginPanel.getPanel());
        frame.setVisible(true);
    }

    /**
     * Displays the GUI to create an account
     */
    public void showCreateNewAccountPrompt(){
        presenter.showCreateNewAccountPrompt();
    }

    /**
     * Returns to the login GUI
     */
    public void returnToLoginMenu(){
        presenter.showLoginMenu();
    }

    /**
     * Checks if the login credentials are correct.
     *
     * @param id the inputted username
     * @param password the inputted password
     */
    public void checkLogin(int id, String password) {
        boolean status = false;
        for (User user : userManager.users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                userManager.setCurrentUser(user);
                this.mainMenuController = new MainMenuController(eventMenuFactory, messageMenuFactory,
                        userManager, languageManager, eventManager,frame, this);
                mainMenuController.printMenu(currentTheme);
                status = true;
                break;
            }
        }
        if (!status){
            presenter.loginFailed();
        }
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

    public void saveFiles(){
        userReader.saveUserManager(userManager);
        eventReader.saveEventManager(eventManager);
        roomReader.saveRoomManager(roomManager);
        if(userFriendManager != null){
            messageReader.saveMessages(userFriendManager, userManager);
        }
    }

    /**
     * Tells the user that the wrong login credientials were used
     */
    public void showWrongCredentials(){
        presenter.loginFailed();
    }

    /**
     * Changes the language of the system.
     * @param language the new language
     */
    public void changeLanguage(String language){
        currentLanguage = language;
        presenter.changeLanguage(language);
        if(mainMenuController != null){
            mainMenuController.changeLanguage(language);
        }
    }

    /**
     * Changes the theme of the GUI
     * @param string the new theme of the GUI
     */
    public void changeTheme(String string){
        currentTheme = string;
        presenter.changeTheme(string);
        if(mainMenuController != null){
            mainMenuController.changeTheme(string);
        }
    }
}
