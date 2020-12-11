package Controllers.MessageMenu;

import Entities.Users.Organizer;
import Entities.Users.User;
import GUI.MainMenuPanel;
import UseCases.Message.UserFriendManager;

import java.time.LocalDateTime;

public abstract class UserFriendListController {
    public UserFriendManager userFriendManager;

    private MainMenuPanel mainMenuPanel;

    public UserFriendListController(UserFriendManager userFriendManager, MainMenuPanel mainMenuPanel) {
        this.userFriendManager = userFriendManager;
        this.mainMenuPanel = mainMenuPanel;
    }

    /**
     * currentUser Send a message to another User
     *
     * @param recipient      User who receive the Message
     * @param messageContent the content of the message
     */
    public void sendingMessage(User sender, User recipient, String messageContent, LocalDateTime dateTime) {
        if (this.userFriendManager.messageable(recipient)) {
            this.userFriendManager.sendMessageTo(sender, recipient, messageContent, dateTime);
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

    public void printMenu(){

    }

    public void setMainMenuPanel(MainMenuPanel mainMenuPanel){
        this.mainMenuPanel = mainMenuPanel;
    }

    public void changeTheme(String theme){
        //TODO: change this
    }
}
