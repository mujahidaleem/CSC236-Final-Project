package Presenters.MessageMenu;

import Controllers.MessageMenu.OrganizerFriendListController;
import GUI.MainMenuPanel;
import GUI.Messages.ChatPanel;
import GUI.Messages.MessageMenuPanel;
import UseCases.Language.LanguageManager;
import UseCases.Message.OrganizerFriendManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrganizerMessagePresenter extends MessageMenuPresenter {
    public OrganizerFriendListController organizerFriendListController;
    public OrganizerManager organizerManager;

    /**
     * Constructor for OrganizerMessagePresenter
     * @param userManager contains the users
     * @param organizerFriendManager contains the messages
     * @param languageManager contains the strings for generating text
     * @param messageMenuPanel the panel representing the message menu
     * @param mainMenuPanel the main menu
     * @param chatPanel the chat panel
     */
    public OrganizerMessagePresenter(UserManager userManager, OrganizerManager organizerManager,
                                     OrganizerFriendManager organizerFriendManager, LanguageManager languageManager,
                                     MessageMenuPanel messageMenuPanel, MainMenuPanel mainMenuPanel, ChatPanel chatPanel) {
        super(userManager, organizerFriendManager, languageManager, messageMenuPanel, mainMenuPanel, chatPanel);
        this.organizerManager = organizerManager;
    }

    /**
     * Additional wwitch cases for the commands the organizer can enter in the message menu
     * You can message all attendees or all speakers
     *
     * @param answer Inputted command by the user
     */
    @Override
    public void extraCommands(String[] answer) {
        Scanner message = new Scanner(System.in);
        switch (answer[0]) {
            case "4":
                System.out.println(languageManager.languagePack.messageAllAttendeePrompt());
                String messageContent = message.nextLine();
                organizerFriendListController.messageAllAttendees(messageContent, LocalDateTime.now());
                System.out.println(languageManager.languagePack.messageSuccessful());
                break;
            case "5":
                System.out.println(languageManager.languagePack.messageAllSpeakerPrompt());
                messageContent = message.nextLine();
                organizerFriendListController.messageAllSpeakers(messageContent, LocalDateTime.now());
                System.out.println(languageManager.languagePack.messageSuccessful());
                break;
        }
    }

    /**
     * Generic commands for the friend list of a user, with specific commands for the organizer at type 4 and 5
     */
    @Override
    protected void printCommands() {
        languageManager.languagePack.printOrganizerMessageCommands();
    }

}
