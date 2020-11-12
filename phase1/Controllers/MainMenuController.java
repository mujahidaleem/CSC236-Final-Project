package Controllers;

import Presenters.AttendeeEventPresenter;
import Presenters.EventMenuPresenter;
import Presenters.OrganizerEventPresenter;
import Presenters.SpeakerEventPresenter;
import Presenters.MainMenuPresenter;
import UseCases.EventManager;
import UseCases.UserManager;

import java.util.Scanner;

public class MainMenuController {
    private EventManager eventManager;
    private MessageManager messageManager;
    private UserManager userManager;

    public MainMenuController(EventManager eventManager, MessageManager messageManager, UserManager userManager){
        this.eventManager = eventManager;
        this. messageManager = messageManager;
        this.userManager = userManager;
    }

    EventMenuPresenter eventPresenter = new EventMenuPresenter(userManager);
    /*
    Call MainMenuPresenter and take import from user to decide what's next
    */
    public void run(){
        MainMenuPresenter.printMainMenu();
        Scanner userInput = new Scanner(System.in);
        String userOption = userInput.nextLine();
        while (!userOption.equals("1") && !userOption.equals("2") && !userOption.equals("3") && !userOption.equals("4")){
            System.out.println("Please enter a valid option:");
            userInput = new Scanner(System.in);
            userOption = userInput.nextLine();
        }
        if (userOption == "1"){
            eventPresenter.printMenu(eventManager);
        } else if (userOption == "2") {
            MessageMenuPresenter.printMenu(messageManager);
        } else if (userOption == "3"){
            LoginManager.logout();
        } else if (userOption == "4"){
            System.exit(0);
        }
    }
}
