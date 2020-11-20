package Presenters;

import Controllers.OrganizerFriendListController;
import UseCases.OrganizerFriendManager;
import UseCases.OrganizerManager;
import UseCases.UserManager;

public class OrganizerMessagePresenter extends MessageMenuPresenter{
    public OrganizerFriendListController organizerFriendListController;
    public OrganizerManager organizerManager;
    public OrganizerFriendManager organizerFriendManager;

    public OrganizerMessagePresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, OrganizerManager organizerManager, OrganizerFriendManager organizerFriendManager){
        super(organizerFriendListController, userManager, organizerFriendManager);
        this.organizerFriendListController= organizerFriendListController;
        this.organizerManager = organizerManager;
    }

    /**
     * Display the messageable Users of Organizer
     */
    @Override
    public void printFriends(){
        System.out.println("Friend List");
        organizerFriendManager.displayFriend();
    }

    @Override
    protected void printCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println(("To send a message to a user, type 1_userID"));
        System.out.println("To add a user, type 2_userID");
        System.out.println("To remove a user, type 3_userID");
        System.out.println("To send a message to all the attendees, type 4_\"message content\"");
        System.out.println("To send a message to all the speakers, type 5_\"message content\"");
    }

}
