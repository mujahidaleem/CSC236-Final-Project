
package Presenters;


import Controllers.OrganizerFriendListController;
import Entities.User;
import Gateways.MessageReader;
import UseCases.OrganizerFriendManager;
import UseCases.OrganizerManager;
import UseCases.UserManager;

import java.util.ArrayList;

public class OrganizerFriendListPresenter extends MessageMenuPresenter {
    public OrganizerFriendListController organizerFriendListController;
    public OrganizerManager organizerManager;
    public OrganizerFriendManager organizerFriendManager;
    public MessageReader MessageReader;

    public OrganizerFriendListPresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, OrganizerManager organizerManager, OrganizerFriendManager organizerFriendManager){
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
    /*public void DisplayMessageable(){
        ArrayList<User> messageableList = this.SpeakerFriendListController.getMessageableList();
        for(User messagable: messageableList){
            System.out.println(messagable.getName());
        }
    }*/

}

