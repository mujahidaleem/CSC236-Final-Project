package Presenters;

import Controllers.AttendeeFriendListController;
import Entities.Attendee;
import Entities.User;
import Gateways.MessageReader;
import UseCases.AttendeeFriendManager;
import UseCases.UserFriendManager;

import java.util.ArrayList;

public class AttendeeFriendListPresenter extends UserFriendListPresenter {
    private Attendee currentattendee;
    public AttendeeFriendListController AttendeeFriendListController;
    public AttendeeFriendManager AttendeeFriendManager;
    public User currentUser;
    public MessageReader MessageReader;

    public AttendeeFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                       Gateways.MessageReader MessageReader, AttendeeFriendListController
                                       AttendeeFriendListController) {
        super(currentUser, UserFriendManager, MessageReader);
        if(currentUser instanceof Attendee){
            this.currentattendee = (Attendee) currentUser;
        }
        if(UserFriendManager instanceof AttendeeFriendManager){
            this.AttendeeFriendManager= (AttendeeFriendManager) UserFriendManager;}
        this.AttendeeFriendListController=AttendeeFriendListController;
    }
    /**
     * * Display the list of messageable Users
     */
    @Override
    public void DisplayMessageable(){
       ArrayList<User> messageableList = this.AttendeeFriendListController.getMessageableList();
       for(User messageable: messageableList){
           System.out.println(messageable.getName());
       }
    }
}


