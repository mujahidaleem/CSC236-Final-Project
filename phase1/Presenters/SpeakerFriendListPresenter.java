package Presenters;

import Controllers.SpeakerFriendListController;
import Controllers.UserFriendListController;
import Entities.Speaker;
import Gateways.MessageReader;
import UseCases.SpeakerFriendManager;
import UseCases.UserFriendManager;
import Entities.User;
import UseCases.UserManager;

import java.util.ArrayList;

public class SpeakerFriendListPresenter extends MessageMenuPresenter {
    public SpeakerFriendManager speakerFriendManager;
    public MessageReader messageReader;
    public Speaker currentSpeaker;
    public User currentUser;
    public UserFriendManager UserFriendManager;
    public SpeakerFriendListController SpeakerFriendListController;


    //TODO: make this a subclass of MessageMenuPresenter, allow speakers to message all attendees of an event, or a specific attendee


    public SpeakerFriendListPresenter(SpeakerFriendListController speakerFriendListController, UserManager userManager, SpeakerFriendManager speakerFriendManager) {
        super(speakerFriendListController, userManager, speakerFriendManager);
    }

    /**
     * Display the messageable Users of speaker
      */
    @Override
    public void DisplayMessageable(){
        ArrayList<User> messageableList = this.SpeakerFriendListController.getMessageableList();
        for(User messagable: messageableList){
            System.out.println(messagable.getName());
        }
    }



}




