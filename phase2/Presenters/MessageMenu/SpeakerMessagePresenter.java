package Presenters.MessageMenu;

import Controllers.MessageMenu.SpeakerFriendListController;
import GUI.MainMenuPanel;
import GUI.Messages.ChatPanel;
import GUI.Messages.MessageMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.SpeakerFriendManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SpeakerMessagePresenter extends MessageMenuPresenter {
    private SpeakerFriendManager speakerFriendManager;
    private EventManager eventManager;

    /**
     * Constructor for SpeakerMessagePresenter
     *
     * @param userManager          contains the users
     * @param speakerFriendManager contains the messages
     * @param languageManager      contains the strings for generating text
     * @param messageMenuPanel     the panel representing the message menu
     * @param mainMenuPanel        the main menu
     * @param chatPanel            the chat panel
     */
    public SpeakerMessagePresenter(UserManager userManager, SpeakerFriendManager speakerFriendManager, EventManager eventManager,
                                   LanguageManager languageManager, MessageMenuPanel messageMenuPanel, MainMenuPanel mainMenuPanel, ChatPanel chatPanel) {
        super(userManager, speakerFriendManager, languageManager, messageMenuPanel, mainMenuPanel, chatPanel);
        this.speakerFriendManager = speakerFriendManager;
        this.eventManager = eventManager;
    }

    @Override
    public void extraCommands(String[] answer) {
        Scanner message = new Scanner(System.in);
        if ("4".equals(answer[0])) {
            if (speakerFriendManager.getCurrentSpeaker().getSpeakingSchedule().containsKey(answer[1])) {
                System.out.println(languageManager.languagePack.messageAllAttendeeForOneEventPrompt());
                String messageContent = message.nextLine();
                speakerFriendListController.sendingAnnouncement(eventManager.findEvent(answer[1]), messageContent, LocalDateTime.now());
                System.out.println(languageManager.languagePack.messageSuccessful());
            } else if ("5".equals(answer[0])) {
                System.out.println(languageManager.languagePack.messageAllAttendeePrompt());
                String messageContent = message.nextLine();
                speakerFriendListController.sendAnnouncementToAll(messageContent, LocalDateTime.now());
                System.out.println(languageManager.languagePack.messageSuccessful());
            } else {
                System.out.println(languageManager.languagePack.notSpeakerAtEvent());
            }
        } else {
            System.out.println(languageManager.languagePack.unknownCommand());
        }
    }

    @Override
    protected void printCommands() {
        languageManager.languagePack.printSpeakerMessageCommands();
    }
}
