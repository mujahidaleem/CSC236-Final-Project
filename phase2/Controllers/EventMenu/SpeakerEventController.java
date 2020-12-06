package Controllers.EventMenu;

import UseCases.Events.RoomManager;
import UseCases.Users.SpeakerManager;
import UseCases.Events.EventManager;

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
    public SpeakerEventController(SpeakerManager manager, EventManager eventManager, RoomManager roomManager) {
        super(manager, eventManager, roomManager);
        this.speakerManager = manager;
    }
}