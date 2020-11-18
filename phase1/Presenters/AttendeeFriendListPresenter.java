package Presenters;

import Controllers.AttendeeFriendListController;
import Entities.Attendee;
import UseCases.AttendeeFriendManager;

import java.util.ArrayList;

public class AttendeeFriendListPresenter extends UserFriendListPresenter {
    public AttendeeFriendListController AttendeeFriendListController;
    public AttendeeFriendManager AttendeeFriendManager;
    public UserFriendManager UserFriendManager;
    public User currentUser;
    public MessageReader MessageReader;

    public AttendeeFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                       MessageReader MessageReader,AttendeeFriendListController
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
       ArrayList<User> messageableList = this.AttendeeFriendListController.getMessageableList();
       for(User messageable: messageableList){
           System.out.println(messageable.get_name());
       }
    }

    /**
     * Display the command to add/remove an User from messageable list
     */

    public void RemoveMessage(User anotherUser){
        String name=anotherUser.getname();
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
            String name = anotherUser.get_name();
            System.out.println("you send a message to " + name);

        }

        /**
         * * Display the command to return to the menu
         */

        public void DisplayReturningMenu(){
            System.out.println("return to menu");
        }
    }


