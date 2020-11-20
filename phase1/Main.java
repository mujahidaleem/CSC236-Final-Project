import Controllers.LoginMenuController;
import Gateways.UserReader;
import Gateways.EventReader;
import Gateways.MessageReader;
import Presenters.LoginMenuPresenter;
import Presenters.MainMenuPresenter;
import UseCases.EventManager;
import UseCases.UserManager;

public class Main {
    public static void main(String[] args) {
        UserReader userReader = new UserReader("D:\\userManager.ser");
        EventReader eventReader = new EventReader("D:\\eventManager.ser");
        MessageReader messageReader = new MessageReader("D:\\userFriendManager.ser");

        UserManager userManager = userReader.readFile();
        EventManager eventManager = eventReader.readFile();
        //TODO create messages

        LoginMenuController loginMenuController = new LoginMenuController(userManager);
        LoginMenuPresenter loginMenuPresenter = new LoginMenuPresenter(loginMenuController);
        loginMenuPresenter.run();
        //TODO the presenter should take in the userManager, then create everything else
    }

    public void initialize(){
        Initializer initializer = new Initializer();
         initializer.initialize();
    }
}
