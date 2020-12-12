import Controllers.LoginMenuController;
import Gateways.*;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;


public class Main {
    public static void main(String[] args) {
//
        initialize();
        UserReader userReader = new UserReader("userManager.ser");
        EventReader eventReader = new EventReader("eventManager.ser");
        MessageReader messageReader = new MessageReader("userFriendManager.ser");
        RoomReader roomReader = new RoomReader("roomManager.ser");

//        UserManager userManager = userReader.readFile();
//        userReader.createTable();
//        userReader.saveUserManager(userManager);
        UserManager userManager = userReader.readData();

        EventManager eventManager = eventReader.readFile();
        eventReader.createTable();
        eventReader.saveEventManager(eventManager);
        EventManager eventManager1 = eventReader.readData();

        RoomManager roomManager = roomReader.readFile();
        roomReader.createTable();
        roomReader.saveRoomManager(roomManager);
        RoomManager roomManager1 = roomReader.readData();

        LanguageManager languageManager = new LanguageManager("english");

        LoginMenuController loginMenuController = new LoginMenuController(languageManager, userReader, eventReader, roomReader, messageReader);
        loginMenuController.printMenu();
//        ScheduleSaver scheduleSaver = new ScheduleSaver(eventManager);

//        try {
//            scheduleSaver.generatePDF(LocalDateTime.now());
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
