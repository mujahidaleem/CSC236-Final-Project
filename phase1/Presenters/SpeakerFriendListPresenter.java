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
                                       MessageReader MessageReader,
                                      SpeakerFriendListController SpeakerFriendListController) {
        super(currentUser, UserFriendManager, MessageReader);
        if(currentUser instanceof Speaker){
            Speaker currentspeaker=(Speaker) currentUser;
            this.currentSpeaker=currentspeaker;
        }
        if(UserFriendManager instanceof SpeakerFriendManager){
            SpeakerFriendManager SpeakerFriendManager=(SpeakerFriendManager) UserFriendManager;
            this.SpeakerFriendManager= SpeakerFriendManager;}
        this.SpeakerFriendListController=SpeakerFriendListController;
        }

    /**
     * Display the messageable Users of speaker
      */
    @Override
    public void DisplayMessageable(){
        ArrayList<User> messageableList = this.SpeakerFriendListController.getMessageableList();
        for(User messageable: messageableList){
            System.out.println(messageable.getName());
        }
    }

    /**
     * Display the command to add/remove an User from messageable list
     */

    public void DisplayRemove(User anotherUser){
        String name=anotherUser.getName();
        System.out.println(name+"is removed from your friend list");
    }

    /**
     * * Display the chat log between User and another User
     */
    public void DisplayChatLog(User anotherUser) {
        ArrayList<String> Chatlog = this.UserFriendManager.getHistoryMessage(anotherUser);
        for (String message : Chatlog) {
            System.out.println(message);
        }}

    /**
     * * Display the command to send a message to another User
     */

    public void DisplaySendingMessage (User anotherUser){
        String name = anotherUser.getName();
        System.out.println("you send a message to " + name);

    }

    /**
     * * Display the command to return to the menu
     */

    public void DisplayReturningMenu(){
        System.out.println("return to menu");
    }
}




