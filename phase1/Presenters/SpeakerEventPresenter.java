package Presenters;

import Controllers.SpeakerEventController;
import UseCases.EventManager;
import UseCases.SpeakerManager;

/**
 * Displays the event menu for a speaker and interprets the coomands
 * inputted by the speaker on this menu
 */
public class SpeakerEventPresenter extends EventMenuPresenter {

    /**
     * EventMenuPresenter constructor
     *
     * @param manager                stores the current user
     * @param speakerEventController the controller that performs the commands inputted
     * @param language               decides which language is used in the UI
     */
    public SpeakerEventPresenter(SpeakerManager manager,
                                 SpeakerEventController speakerEventController, EventManager eventManager, String language) {
        super(manager, speakerEventController, eventManager, language);
    }
}
