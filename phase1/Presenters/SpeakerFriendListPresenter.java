package Presenters;

import Controllers.SpeakerFriendListController;
import Entities.Speaker;
import Gateways.MessageReader;
import UseCases.SpeakerFriendManager;
import UseCases.UserFriendManager;
import Entities.User;

import java.util.ArrayList;

public class SpeakerFriendListPresenter extends UserFriendListPresenter {
    public SpeakerFriendManager speakerFriendManager;
    public MessageReader messageReader;
    public Speaker currentSpeaker;
    public User currentUser;
    public UserFriendManager UserFriendManager;
    public SpeakerFriendListController SpeakerFriendListController;


    //TODO: make this a subclass of MessageMenuPresenter, allow speakers to message all attendees of an event, or a specific attendee


    public SpeakerFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                       MessageReader messageReader,
                                      SpeakerFriendListController SpeakerFriendListController) {
        super(currentUser, UserFriendManager, messageReader);
        if(currentUser instanceof Speaker){
            this.currentSpeaker= (Speaker) currentUser;
        }
        if(UserFriendManager instanceof SpeakerFriendManager){
            this.speakerFriendManager= (SpeakerFriendManager) UserFriendManager;}
        this.SpeakerFriendListController=SpeakerFriendListController;
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




