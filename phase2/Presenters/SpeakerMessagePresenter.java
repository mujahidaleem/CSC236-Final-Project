package Presenters;

import Controllers.SpeakerFriendListController;
import UseCases.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.SpeakerFriendManager;
import UseCases.UserManager;

import java.util.Scanner;

public class SpeakerMessagePresenter extends MessageMenuPresenter {
    private SpeakerFriendListController speakerFriendListController;
    private SpeakerFriendManager speakerFriendManager;
    private EventManager eventManager;

    /**
     * Constructor of the SpeakerMessagePresenter
     * Presents the message menu of the speaker this is the same for a MessageMenuPresenter
     * *Created for extension in phase 2*
     *
     * @param speakerFriendListController Controller of the speaker's friend list
     * @param userManager                 Use case for a user's functions
     * @param speakerFriendManager        Use case for the speaker's friend list's functions
     */
    public SpeakerMessagePresenter(SpeakerFriendListController speakerFriendListController, UserManager userManager, SpeakerFriendManager speakerFriendManager, EventManager eventManager, LanguageManager languageManager) {
        super(speakerFriendListController, userManager, speakerFriendManager, languageManager);
        this.speakerFriendListController = speakerFriendListController;
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
                speakerFriendListController.sendingAnnouncement(eventManager.findEvent(answer[1]), messageContent);
                System.out.println(languageManager.languagePack.messageSuccessful());
            } else if ("5".equals(answer[0])) {
                System.out.println(languageManager.languagePack.messageAllAttendeePrompt());
                String messageContent = message.nextLine();
                speakerFriendListController.sendAnnouncementToAll(messageContent);
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
