import Controllers.LoginMenuController;
import Gateways.UserReader;
import Gateways.EventReader;
import Gateways.MessageReader;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import UseCases.UserFriendManager;
import UseCases.UserManager;

public class Main {
    public static void main(String[] args) {
        initialize();
        setUpLanguage();
        UserReader userReader = new UserReader("userManager.ser");
        EventReader eventReader = new EventReader("eventManager.ser");
        MessageReader messageReader = new MessageReader("userFriendManager.ser");

        UserManager userManager = userReader.readFile();
        EventManager eventManager = eventReader.readFile();
        UserFriendManager userFriendManager = messageReader.readFile();

        LoginMenuController loginMenuController = new LoginMenuController(userManager, eventManager, userFriendManager);
        LoginMenuPresenter loginMenuPresenter = new LoginMenuPresenter(loginMenuController);
        loginMenuPresenter.run();

        userFriendManager.setCurrentUser(null);
        userManager.setCurrentUser(null);
        userReader.saveFile(userManager);
        eventReader.saveFile(eventManager);
        messageReader.saveFile(userFriendManager);
    }

    public static void initialize() {
        Initializer initializer = new Initializer();
        initializer.initialize();
    }
    public static void setUpLanguage(){
        Initializer initializer = new Initializer();
        initializer.setUpLanguage();
    }
}
