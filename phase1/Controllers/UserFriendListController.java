package Controllers;

import UseCases.UserFriendManager;
//import Entities.User;
import Presenters.UserFriendListPresenter;

import java.util.ArrayList;

public abstract class UserFriendListController {
    public User TargetUser;
    public UserFriendListPresenter UserFriendListPresenter;
    public UserFriendManager UserFriendManager;

    public UserFriendListController(User targetUser, UserFriendListPresenter UserFriendListPresenter,
                                    UserFriendManager UserFriendManager){
        this.UserFriendListPresenter=UserFriendListPresenter;
        this.UserFriendManager=UserFriendManager;
        this.TargetUser=targetUser;
    }

    /**
     * Show list of messageable Users
     */
    abstract ArrayList<String> getMessageableList();

    /**
     *  Show the chat log of User and another User
     */
    public ArrayList<String> showChatLog(User anotherUser){
        ArrayList<String> result = new ArrayList<String>();
        if(this.UserFriendManager.messageable(anotherUser)){
        result = this.UserFriendManager.checkHistoryMessage(anotherUser);
    }
    return result;
    }

    /**
     * Send a message to the User
     */
    public void sendingMessage(User anotherUser,String messageContent){
        if(this.UserFriendManager.messageable(anotherUser)){
            this.UserFriendManager.SendMessageTo(messageContent,anotherUser);
        }}

/**
 * Remove User from messageable Users
 */

    public void removeFrom(User anotherUser){
       if(this.UserFriendManager.messageable(anotherUser)){
        this.UserFriendManager.removeFromFriendlist(anotherUser);
       }}


    /**
     * Add a User to list of messageable Users
     */
    public void Add(User anotherUser){
        if(this.UserFriendManager.messageable(anotherUser)==false){
        this.UserFriendManager.addNewFriend(anotherUser);
    }}

        /**
         * Allow User to return to the menu
          */
        public void returnToMenu(){;

        }





}
