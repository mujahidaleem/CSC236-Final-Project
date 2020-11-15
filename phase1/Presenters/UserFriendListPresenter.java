package Presenters;

import UseCases.UserFriendManager;


import java.util.ArrayList;

public abstract class UserFriendListPresenter {
    public User currentUser;
    public UserFriendManager UserFriendManager;
    public MessageReader MessageReader;

    public UserFriendListPresenter(User currentUser, UserFriendManager UserFriendManager,
                                   MessageReader MessageReader){
        this.currentUser=currentUser;
        this.MessageReader=MessageReader;
        this.UserFriendManager=UserFriendManager;
    }

    /**
     * Display the Messageable List of User
     */

    public abstract void DisplayMessageable();

    /**
     * Display the command to remove an User from messageable list
     * @param anotherUser The user who will be removed from User's friend list
     */

    public void RemoveMessage(User anotherUser){
        String name=anotherUser.get_name();
        System.out.println(name+"is removed from your friend list");
    }

    /**
     * Display the command to remove an User from messageable list
     * @param anotherUser The user who will be removed from User's friend list
     */

    public void AddMessage(User anotherUser){
        String name=anotherUser.get_name();
        System.out.println(name+"is added into your friend list");
    }

    /**
     *  Display the chat log between User and another User
     */
    public void DisplayChatLog(User anotherUser){
        ArrayList<String> Chatlog=this.UserFriendManager.getHistoryMessage(anotherUser);
        for(String message : Chatlog){
        System.out.println(message);
    }}

    /**
     * Display the command to send a message to another User
     */

    public void DisplaySendingMessage(User anotherUser){
        String name= anotherUser.get_name();
        System.out.println("you send a message to "+name);

    }

    /**
     * Display the command to return to the menu
     */

    public void DisplayReturningMenu(){
        System.out.println("return to menu");
    }
}
