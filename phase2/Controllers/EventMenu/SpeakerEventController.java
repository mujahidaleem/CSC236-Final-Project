package Controllers.EventMenu;

import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.SpeakerManager;
import UseCases.Events.EventManager;

import javax.swing.*;

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
    public SpeakerEventController(SpeakerManager manager, EventManager eventManager, RoomManager roomManager,
                                  LanguageManager languageManager, JFrame frame) {
        super(manager, eventManager, roomManager, languageManager, frame);
        this.speakerManager = manager;
    }
}