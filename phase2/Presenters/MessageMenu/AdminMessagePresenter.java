package Presenters.MessageMenu;

import GUI.MainMenuPanel;
import GUI.Messages.ChatPanel;
import GUI.Messages.MessageMenuPanel;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

public class AdminMessagePresenter extends MessageMenuPresenter {

    /**
     * Constructor for AdminMessagePresenter
     * @param userManager contains the users
     * @param userFriendManager contains the messages
     * @param languageManager contains the strings for generating text
     * @param messageMenuPanel the panel representing the message menu
     * @param mainMenuPanel the main menu
     * @param chatPanel the chat panel
     */
    public AdminMessagePresenter(UserManager userManager, UserFriendManager userFriendManager,
                                 LanguageManager languageManager, MessageMenuPanel messageMenuPanel, MainMenuPanel mainMenuPanel, ChatPanel chatPanel) {
        super(userManager, userFriendManager, languageManager, messageMenuPanel, mainMenuPanel, chatPanel);
    }
}
