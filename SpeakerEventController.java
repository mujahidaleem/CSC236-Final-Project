package Controllers;

import UseCases.SpeakerManager;
import UseCases.EventManager;

/**
 * Performs the commands inputted in the speaker event menu
 */
public class SpeakerEventController extends EventMenuController {
    public SpeakerManager speakerManager;

    /**
     * SpeakerEventController constructor
     *
     * @param manager      contains the current speaker
     * @param eventManager contains the list of events
     */
    public SpeakerEventController(SpeakerManager manager, EventManager eventManager) {
        super(manager, eventManager);
        this.speakerManager = manager;
    }
}