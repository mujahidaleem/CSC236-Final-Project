package Controllers.MessageMenu;

import Entities.Users.Organizer;
import Entities.Users.User;
import GUI.MainMenuPanel;
import GUI.Messages.MessageMenuPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.time.LocalDateTime;

public abstract class UserFriendListController {
    public UserFriendManager userFriendManager;

    private MainMenuPanel mainMenuPanel;
    private MessageMenuPresenter messageMenuPresenter;
    private MessageMenuPanel messageMenuPanel;
    private UserManager userManager;

    public UserFriendListController(UserFriendManager userFriendManager,
                                    MainMenuPanel mainMenuPanel,
                                    MessageMenuPresenter messageMenuPresenter,
                                    UserManager userManager,
                                    JFrame frame,
                                    LanguageManager languageManager) {
        this.userFriendManager = userFriendManager;
        this.mainMenuPanel = mainMenuPanel;
        this.messageMenuPresenter = messageMenuPresenter;
        this.userManager = userManager;
        this.messageMenuPanel = new MessageMenuPanel(this, userFriendManager, userManager, frame, languageManager);
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

    public void printMenu(){messageMenuPresenter.setUpMenu();}

    public void showNullUserError(){messageMenuPresenter.showNullUserError(); }

    public void showInvalidIDInput(){messageMenuPresenter.showInvalidIDInput();}

    public void returnToMainMenu(){messageMenuPresenter.returnToMainMenu();}

    public void printChatPanel(User targetUser){messageMenuPresenter.printChatPanel(targetUser);}

    public User getCurrentUser(){
        return userFriendManager.getCurrentUser();
    }

    public void setMainMenuPanel(MainMenuPanel mainMenuPanel){
        this.mainMenuPanel = mainMenuPanel;
    }

    public void changeTheme(String theme){
        //TODO: change this
    }


    public void changeLanguage(String language){
        //TODO: change this
    }
}
