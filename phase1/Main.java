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
        UserReader userReader = new UserReader("D:\\userManager.ser");
        EventReader eventReader = new EventReader("D:\\eventManager.ser");
        MessageReader messageReader = new MessageReader("D:\\userFriendManager.ser");

        UserManager userManager = userReader.readFile();
        EventManager eventManager = eventReader.readFile();
        UserFriendManager userFriendManager = messageReader.readFile();

        LoginMenuController loginMenuController = new LoginMenuController(userManager, eventManager, userFriendManager);
        LoginMenuPresenter loginMenuPresenter = new LoginMenuPresenter(loginMenuController);
        loginMenuPresenter.run();
    }

    public static void initialize(){
        Initializer initializer = new Initializer();
         initializer.initialize();
    }
}
