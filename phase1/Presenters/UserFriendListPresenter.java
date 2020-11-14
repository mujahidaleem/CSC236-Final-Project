package Presenters;

import UseCases.UserFriendManager;


import java.util.ArrayList;

public abstract class UserFriendListPresenter {
    /**
     * Stores an instance of User, UserFriendManager, and MessageReader
     * Display the list of messageable Users
     * Display the command to add/remove an User from messageable list
     * Display the chat log between User and another User
     * Display the command to send a message to another User
     * Display the command to return to the menu
     */
    public User currentUser;
    public UserFriendManager UserFriendManager;
    public MessageReader MessageReader;

    /**
     * Display the list of messageable Users
     */
    public UserFriendListPresenter(User currentUser, UserFriendManager UserFriendManager,
                                   MessageReader MessageReader){
        this.currentUser=currentUser;
        this.MessageReader=MessageReader;
        this.UserFriendManager=UserFriendManager;
    }
    public abstract void DisplayMessageable();

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
    public void DisplayChatLog(User anotherUser){
        ArrayList<String> Chatlog=this.UserFriendManager.getHistoryMessage(anotherUser);
        for(String message : Chatlog){
        System.out.println(message);
    }

    /**
     * * Display the command to send a message to another User
     */

    public void DisplaySengdingMessage(User anotherUser){
        String name= anotherUser.get_name();
        System.out.println("you send a message to "+name);

    }

    /**
     * * Display the command to return to the menu
     */

    public void DisplayReturningMenu(){
        System.out.println("return to menu");
    }








}
