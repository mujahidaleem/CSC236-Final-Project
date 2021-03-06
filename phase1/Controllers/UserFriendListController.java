package Controllers;

import Entities.Organizer;
import Entities.User;
import UseCases.UserFriendManager;

public abstract class UserFriendListController {
    public UserFriendManager userFriendManager;

    public UserFriendListController(UserFriendManager userFriendManager) {
        this.userFriendManager = userFriendManager;
    }

    /**
     * currentUser Send a message to another User
     *
     * @param recipient      User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User sender, User recipient, String messageContent) {
        if (this.userFriendManager.messageable(recipient)) {
            this.userFriendManager.sendMessageTo(sender, recipient, messageContent);
        }
    }

    /**
     * Remove User from messageable Users
     *
     * @param friend The user who is removed from the Current User's friend list
     */

    public boolean removeFrom(User friend) {
        if (this.userFriendManager.messageable(friend)) {
            this.userFriendManager.removeFromFriendList(friend);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Add a User to list of manageable Users if the User is not an Organizer
     *
     * @param newFriend The User who will be added into the friend list of current User
     */
    public boolean addFriend(User newFriend) {
        if (!this.userFriendManager.messageable(newFriend) && !newFriend.getClass().equals(Organizer.class)) {
            this.userFriendManager.addNewFriend(newFriend);
            return true;
        } else {
            return false;
        }
    }
}
