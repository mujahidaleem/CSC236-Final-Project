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
     * @return list of messageable Users（abstract）
     */
    abstract ArrayList<String> getMessageableList();

    /**
     *  @return the chat log of currentUser and another User
     */
    public ArrayList<String> showChatLog(User anotherUser){
        ArrayList<String> result = new ArrayList<String>();
        if(this.UserFriendManager.messageable(anotherUser)){
        result = this.UserFriendManager.checkHistoryMessage(anotherUser);
    }
    return result;
    }

    /**
     * currentUser Send a message to another User
     * @param anotherUser User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User anotherUser,String messageContent){
        if(this.UserFriendManager.messageable(anotherUser)){
            this.UserFriendManager.SendMessageTo(messageContent,anotherUser);
        }}

    /**
     * Remove User from messageable Users
     * @param anotherUser The user who is removed from the Current User's friend list
     */

    public void removeFrom(User anotherUser){
       if(this.UserFriendManager.messageable(anotherUser)){
        this.UserFriendManager.removeFromFriendlist(anotherUser);
       }}


    /**
     * Add a User to list of messageable Users
     * @param anotherUser The User who will be added into the friend list of current User
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
