package Controllers;

import Entities.User;
import UseCases.UserFriendManager;
import Presenters.UserFriendListPresenter;

import java.util.ArrayList;

public abstract class UserFriendListController {

    public UserFriendListPresenter userFriendListPresenter;
    public UserFriendManager userFriendManager;

    public UserFriendListController(UserFriendListPresenter userFriendListPresenter,
                                    UserFriendManager userFriendManager){
        this.userFriendListPresenter = userFriendListPresenter;
        this.userFriendManager = userFriendManager;
    }

    /**
     * @return list of messageable Users（abstract）
     */
    abstract ArrayList<User> getMessageableList();

    /**
     *  @return the chat log of currentUser and another User
     */
    public ArrayList<String> showChatLog(User user1, User user2){
        ArrayList<String> result = new ArrayList<String>();
        if(this.userFriendManager.messagable(user1, user2)){
            result = this.userFriendManager.checkHistoryMessage(user1, user2);
        }
        return result;
    }

    /**
     * currentUser Send a message to another User
     * @param recipient User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User sender, User recipient, String messageContent){
        if(this.userFriendManager.messagable(sender, recipient)){
            this.userFriendManager.sendMessageTo(sender, recipient, messageContent);
        }
    }

    /**
     * Remove User from messageable Users
     * @param friend The user who is removed from the Current User's friend list
     */

    public boolean removeFrom(User user1, User friend){
       if(this.userFriendManager.messagable(user1, friend)) {
           this.userFriendManager.removeFromFriendList(user1, friend);
           return true;
       } else {
           return false;
       }
    }


    /**
     * Add a User to list of manageable Users
     * @param newFriend The User who will be added into the friend list of current User
     */
    public boolean Add(User user1, User newFriend){
        if(!this.userFriendManager.messagable(user1, newFriend)){
            this.userFriendManager.addNewFriend(user1, newFriend);
            return true;
        } else {
            return false;
        }
    }
}
