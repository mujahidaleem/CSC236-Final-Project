package Presenters;

import Controllers.OrganizerFriendListController;
import Controllers.UserFriendListController;
import Entities.User;
import UseCases.UserFriendManager;
import UseCases.UserManager;

import java.util.ArrayList;

public class OrganizerMessagePresenter extends MessageMenuPresenter{
    public OrganizerFriendListController organizerFriendListController;

    public OrganizerMessagePresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, UserFriendManager userFriendManager){
        super(organizerFriendListController, userManager, userFriendManager);
        this.organizerFriendListController = organizerFriendListController;
    }

    /**
     * Display the messageable Users of Organizer
     */
    @Override
    public void printFriends(){
        ArrayList<User> messageableList = this.organizerFriendListController.getMessageableList();
        for(User friend: messageableList){
            System.out.println(friend);
        }
    }

}
