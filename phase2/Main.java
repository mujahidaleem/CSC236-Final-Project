import Controllers.LoginMenuController;
import Entities.Events.Event;
import Entities.Users.Attendee;
import Entities.Users.User;
import GUI.LoginMenuFrame;
import Gateways.RoomReader;
import Gateways.UserReader;
import Gateways.EventReader;
import Gateways.MessageReader;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;


public class Main {
    public static void main(String[] args) {
//
        initialize();
        UserReader userReader = new UserReader("userManager.ser");
        EventReader eventReader = new EventReader("eventManager.ser");
//        MessageReader messageReader = new MessageReader("userFriendManager.ser");
        RoomReader roomReader = new RoomReader("roomManager.ser");
        roomReader.createTable();
//
//        UserManager userManager = userReader.readFile();
//        EventManager eventManager = eventReader.readFile();
//        eventReader.saveEventManager(eventManager);
//        EventManager eventManager1 = eventReader.readData();

//        UserFriendManager userFriendManager = messageReader.readFile();
//        LanguageManager languageManager = new LanguageManager("english");
//        RoomManager roomManager = roomReader.readFile();
//
//        LoginMenuController loginMenuController = new LoginMenuController(userManager, eventManager, userFriendManager, languageManager, roomManager);
//        LoginMenuPresenter loginMenuPresenter = new LoginMenuPresenter(loginMenuController, languageManager);
//        loginMenuPresenter.run();
//
//        userFriendManager.setCurrentUser(null);
//        userManager.setCurrentUser(null);
//        userReader.saveFile(userManager);
//        eventReader.saveFile(eventManager);
//        messageReader.saveFile(userFriendManager);

//        LoginMenuFrame loginMenuFrame = new LoginMenuFrame(languageManager, loginMenuController, userManager);
//        loginMenuFrame.showMenu();

//        MainMenuGUI mainMenuGUI = new MainMenuGUI();
//        mainMenuGUI.showMenu();
    }

    public static void initialize() {
        Initializer initializer = new Initializer();
        initializer.initialize();
        initializer.setUpLanguage();
        initializer.setUpLanguage2();
    }
}
