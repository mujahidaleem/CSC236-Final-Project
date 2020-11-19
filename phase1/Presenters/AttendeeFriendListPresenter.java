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
    public UseCases.UserFriendManager UserFriendManager;
    public User currentUser;
    public MessageReader MessageReader;

    public AttendeeFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                       Gateways.MessageReader MessageReader, AttendeeFriendListController
                                       AttendeeFriendListController) {
        super(currentUser, UserFriendManager, MessageReader);
        if(currentUser instanceof Attendee){
            Attendee currentattendee=(Attendee) currentUser;
            this.currentattendee=currentattendee;
        }
        if(UserFriendManager instanceof AttendeeFriendManager){
            AttendeeFriendManager AttendeeFriendManager=(AttendeeFriendManager) UserFriendManager;
            this.AttendeeFriendManager= AttendeeFriendManager;}
        this.AttendeeFriendListController=AttendeeFriendListController;
    }




    /**
     * * Display the list of messageable Users
     */
    @Override
    public void DisplayMessageable(){
       ArrayList<User> messageableList = this.AttendeeFriendListController.getManageableList();
       for(User messageable: messageableList){
           System.out.println(messageable.getName());
       }
    }

    /**
     * Display the command to add/remove an User from messageable list
     */

    public void RemoveMessage(User anotherUser){
        String name=anotherUser.getName();
        System.out.println(name+"is removed from your friend list");
    }

    /**
     * * Display the chat log between User and another User
     */
    public void DisplayChatLog(User anotherUser) {
        ArrayList<String> Chatlog = this.UserFriendManager.checkHistoryMessage(anotherUser);
        for (String message : Chatlog) {
            System.out.println(message);
        }}

        /**
         * * Display the command to send a message to another User
         */

        public void DisplaySengdingMessage (User anotherUser){
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


