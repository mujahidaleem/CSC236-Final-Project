package Presenters;

import Controllers.SpeakerEventController;
import UseCases.EventManager;
import UseCases.SpeakerManager;

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
