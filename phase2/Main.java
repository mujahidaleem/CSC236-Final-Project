import Controllers.LoginMenuController;
import Gateways.UserReader;
import Gateways.EventReader;
import Gateways.MessageReader;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.UserFriendManager;
import UseCases.UserManager;


public class Main {
    public static void main(String[] args) {




//        initialize();
        UserReader userReader = new UserReader("userManager.ser");
        EventReader eventReader = new EventReader("eventManager.ser");
        MessageReader messageReader = new MessageReader("userFriendManager.ser");

        UserManager userManager = userReader.readFile();
        EventManager eventManager = eventReader.readFile();
        UserFriendManager userFriendManager = messageReader.readFile();
        LanguageManager languageManager = new LanguageManager("french");

        LoginMenuController loginMenuController = new LoginMenuController(userManager, eventManager, userFriendManager, languageManager);
        LoginMenuPresenter loginMenuPresenter = new LoginMenuPresenter(loginMenuController, languageManager);
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
        initializer.setUpLanguage();
        initializer.setUpLanguage2();
    }
}
