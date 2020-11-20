package Presenters;

import Controllers.OrganizerFriendListController;
import UseCases.OrganizerFriendManager;
import UseCases.OrganizerManager;
import UseCases.UserManager;

import java.util.Scanner;

public class OrganizerMessagePresenter extends MessageMenuPresenter{
    public OrganizerFriendListController organizerFriendListController;
    public OrganizerManager organizerManager;
    public OrganizerFriendManager organizerFriendManager;

    public OrganizerMessagePresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, OrganizerManager organizerManager, OrganizerFriendManager organizerFriendManager){
        super(organizerFriendListController, userManager, organizerFriendManager);
        this.organizerFriendListController= organizerFriendListController;
        this.organizerManager = organizerManager;
    }

    @Override
    public void extraCommands(String[] answer){
        Scanner message = new Scanner(System.in);
        switch (answer[0]){
            case "4":
                System.out.println("Please type the message you want to send to all Attendees.");
                String messageContent = message.nextLine();
                organizerFriendListController.messageAllAttendees(messageContent);
                System.out.println("Message has been sent.");
                break;
            case "5":
                System.out.println("Please type the message you want to send to all Speakers.");
                messageContent = message.nextLine();
                organizerFriendListController.messageAllSpeakers(messageContent);
                System.out.println("Message has been sent.");
                break;
        }
    }

    @Override
    protected void printCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println(("To send a message to a user, type 1_userID"));
        System.out.println("To add a user, type 2_userID");
        System.out.println("To remove a user, type 3_userID");
        System.out.println("To send a message to all the attendees, type 4");
        System.out.println("To send a message to all the speakers, type 5");
    }

}
