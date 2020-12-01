package Presenters;

import Controllers.SpeakerEventController;
import Entities.Event;
import UseCases.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.SpeakerManager;

/**
 * Displays the event menu for a speaker and interprets the coomands
 * inputted by the speaker on this menu
 */
public class SpeakerEventPresenter extends EventMenuPresenter {
    private SpeakerManager speakerManager;

    /**
     * EventMenuPresenter constructor
     *
     * @param manager                stores the current user
     * @param speakerEventController the controller that performs the commands inputted
     * @param languageManager               decides which language is used in the UI
     */
    public SpeakerEventPresenter(SpeakerManager manager,
                                 SpeakerEventController speakerEventController, EventManager eventManager, LanguageManager languageManager) {
        super(manager, speakerEventController, eventManager, languageManager);
        this.speakerManager = manager;
    }

    /**
     * Prints the initial menu of the Event Menu, showing the speaker the list of events they are attending, the list
     * of events they are speaking at , the list of events they are not attending, and what commands can be used
     */
    @Override
    public void printMenu() {
        System.out.println(manager.getCurrentUser());
        System.out.println(languageManager.languagePack.eventMenuHeadings()[0]);
        for (Event event : eventManager.getEvents()) {
            if (manager.getCurrentUser().getPersonalSchedule().containsKey(event.getEventName())) {
                System.out.println(event);
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(languageManager.languagePack.eventMenuHeadings()[2]);
        for (Event event : eventManager.getEvents()) {
            if (speakerManager.getCurrentSpeaker().getSpeakingSchedule().containsKey(event.getEventName())) {
                System.out.println(event);
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println(languageManager.languagePack.eventMenuHeadings()[1]);
        for (Event event : eventManager.getEvents()) {
            if (!manager.getCurrentUser().getPersonalSchedule().containsKey(event.getEventName())) {
                System.out.println(event);
            }
        }
        printCommands();
    }
}
