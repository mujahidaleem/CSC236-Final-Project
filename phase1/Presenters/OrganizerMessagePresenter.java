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

    /**
     * Constructor of OrganizerMessagePresenter
     * OrganizerMessagePresenter presents the organizer's message menu (friend list, messages etc.)
     * @param organizerFriendListController Controller for the friend list of the organizer
     * @param userManager Use case for the functions of the user
     * @param organizerManager Use case for the functions of the organizer
     * @param organizerFriendManager Use case for the functions of the organizer's friend list
     */
    public OrganizerMessagePresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, OrganizerManager organizerManager, OrganizerFriendManager organizerFriendManager){
        super(organizerFriendListController, userManager, organizerFriendManager);
        this.organizerFriendListController= organizerFriendListController;
        this.organizerManager = organizerManager;
    }

    /**
     * Additional wwitch cases for the commands the organizer can enter in the message menu
     * You can message all attendees or all speakers
     * @param answer Inputted command by the user
     */
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

    /**
     * Generic commands for the friend list of a user, with specific commands for the organizer at type 4 and 5
     */
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
