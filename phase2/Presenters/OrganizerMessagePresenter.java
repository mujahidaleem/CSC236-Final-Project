package Presenters;

import Controllers.OrganizerFriendListController;
import UseCases.Language.LanguageManager;
import UseCases.OrganizerFriendManager;
import UseCases.OrganizerManager;
import UseCases.UserManager;

import java.util.Scanner;

public class OrganizerMessagePresenter extends MessageMenuPresenter {
    public OrganizerFriendListController organizerFriendListController;
    public OrganizerManager organizerManager;
    public OrganizerFriendManager organizerFriendManager;

    /**
     * Constructor of OrganizerMessagePresenter
     * OrganizerMessagePresenter presents the organizer's message menu (friend list, messages etc.)
     *
     * @param organizerFriendListController Controller for the friend list of the organizer
     * @param userManager                   Use case for the functions of the user
     * @param organizerManager              Use case for the functions of the organizer
     * @param organizerFriendManager        Use case for the functions of the organizer's friend list
     * @param languageManager               Prints the strings in the current system language
     */
    public OrganizerMessagePresenter(OrganizerFriendListController organizerFriendListController, UserManager userManager, OrganizerManager organizerManager, OrganizerFriendManager organizerFriendManager, LanguageManager languageManager) {
        super(organizerFriendListController, userManager, organizerFriendManager, languageManager);
        this.organizerFriendListController = organizerFriendListController;
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
                organizerFriendListController.messageAllAttendees(messageContent);
                System.out.println(languageManager.languagePack.messageSuccessful());
                break;
            case "5":
                System.out.println(languageManager.languagePack.messageAllSpeakerPrompt());
                messageContent = message.nextLine();
                organizerFriendListController.messageAllSpeakers(messageContent);
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
