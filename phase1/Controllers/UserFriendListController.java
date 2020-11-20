package Controllers;

import Entities.User;
import Presenters.MessageMenuPresenter;
import UseCases.UserFriendManager;

import java.util.ArrayList;

public abstract class UserFriendListController {

    public MessageMenuPresenter messageMenuPresenter;
    public UserFriendManager userFriendManager;

    public UserFriendListController(MessageMenuPresenter messageMenuPresenter,
                                    UserFriendManager userFriendManager){
        this.messageMenuPresenter = messageMenuPresenter;
        this.userFriendManager = userFriendManager;
    }

    /**
     *  @return the chat log of currentUser and another User
     */
    public void showChatLog(User user1, User user2){
        userFriendManager.displayChatLog(user1, user2);
        ArrayList<String> result = new ArrayList<String>();
        if(this.userFriendManager.messageable(user2)){
            result = userFriendManager.checkHistoryMessage(user1, user2);
        }
    }

    /**
     * currentUser Send a message to another User
     * @param recipient User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User sender, User recipient, String messageContent){
        if(this.userFriendManager.messageable(recipient)){
            this.userFriendManager.sendMessageTo(sender, recipient, messageContent);
        }
    }

    /**
     * Remove User from messageable Users
     * @param friend The user who is removed from the Current User's friend list
     */

    public boolean removeFrom(User friend){
       if(this.userFriendManager.messageable(friend)) {
           this.userFriendManager.removeFromFriendList(friend);
           return true;
       } else {
           return false;
       }
    }


    /**
     * Add a User to list of manageable Users
     * @param newFriend The User who will be added into the friend list of current User
     */
    public boolean addFriend(User newFriend){
        if(!this.userFriendManager.messageable(newFriend)){
            this.userFriendManager.addNewFriend(newFriend);
            return true;
        } else {
            return false;
        }
    }
}
