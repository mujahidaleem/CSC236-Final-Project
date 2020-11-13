package Presenters;

import Controllers.SpeakerEventController;
import UseCases.SpeakerManager;

public class SpeakerEventPresenter extends EventMenuPresenter {

    /**
     * EventMenuPresenter constructor
     * @param manager stores the current user
     * @param speakerEventController the controller that performs the commands inputted
     * @param language decides which language is used in the UI
     */
    public SpeakerEventPresenter(SpeakerManager manager,
                                 SpeakerEventController speakerEventController, String language) {
        super(manager, speakerEventController, language);
    }
}
