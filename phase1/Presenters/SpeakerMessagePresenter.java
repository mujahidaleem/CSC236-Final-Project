package Presenters;

import Controllers.SpeakerFriendListController;
import Entities.Event;
import UseCases.EventManager;
import UseCases.SpeakerFriendManager;
import UseCases.UserManager;

import java.util.Scanner;

public class SpeakerMessagePresenter extends MessageMenuPresenter{
    private SpeakerFriendListController speakerFriendListController;
    private SpeakerFriendManager speakerFriendManager;
    private EventManager eventManager;

    /**
     * Constructor of the SpeakerMessagePresenter
     * Presents the message menu of the speaker this is the same for a MessageMenuPresenter
     * *Created for extension in phase 2*
     * @param speakerFriendListController Controller of the speaker's friend list
     * @param userManager Use case for a user's functions
     * @param speakerFriendManager Use case for the speaker's friend list's functions
     */
    public SpeakerMessagePresenter(SpeakerFriendListController speakerFriendListController, UserManager userManager, SpeakerFriendManager speakerFriendManager, EventManager eventManager){
        super(speakerFriendListController,userManager, speakerFriendManager);
        this.speakerFriendListController = speakerFriendListController;
        this.speakerFriendManager = speakerFriendManager;
        this.eventManager = eventManager;
    }

    @Override
    public void extraCommands(String[] answer){
        Scanner message = new Scanner(System.in);
        if ("4".equals(answer[0])) {
            if(speakerFriendManager.getCurrentSpeaker().getSpeakingSchedule().containsKey(answer[1])){
                System.out.println("Please type the message you want to send to all Attendees.");
                String messageContent = message.nextLine();
                speakerFriendListController.sendingAnnouncement(eventManager.findEvent(answer[1]), messageContent);
                System.out.println("Message has been sent.");
            } else {
                System.out.println("You do not speak at this event.");
            }
        } else {
            System.out.println("Input is invalid.");
        }
    }

    @Override
    protected void printCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println(("To send a message to a user, type 1_userID"));
        System.out.println("To add a user, type 2_userID");
        System.out.println("To remove a user, type 3_userID");
        System.out.println("To message all users attending your events, type 4_eventName");
    }
}
