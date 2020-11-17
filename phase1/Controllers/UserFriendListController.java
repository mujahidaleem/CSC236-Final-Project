package Controllers;

import UseCases.UserFriendManager;
//import Entities.User;
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
        if(this.userFriendManager.messageable(user1, user2)){
            result = this.userFriendManager.checkHistoryMessage(user1, user2);
        }
        return result;
    }

    /**
     * currentUser Send a message to another User
     * @param anotherUser User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User sender, User recepient, String messageContent){
        if(this.userFriendManager.messageable(sender, recepient)){
            this.userFriendManager.SendMessageTo(sender, recepient, messageContent);
        }
    }

    /**
     * Remove User from messageable Users
     * @param anotherUser The user who is removed from the Current User's friend list
     */

    public void removeFrom(User user1, User friend){
       if(this.userFriendManager.messageable(user1, friend)){
        this.userFriendManager.removeFromFriendlist(user1, friend);
       }
    }


    /**
     * Add a User to list of messageable Users
     * @param anotherUser The User who will be added into the friend list of current User
     */
    public void Add(User user1, User newFriend){
        if(this.UserFriendManager.messageable(user1, newFriend) == false){
            this.UserFriendManager.addNewFriend(anotherUser);
        }
    }

    /**
     * Allow User to return to the menu
     */
    public void returnToMenu(){;

        }





}
