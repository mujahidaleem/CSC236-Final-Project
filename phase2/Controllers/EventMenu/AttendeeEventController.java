package Controllers.EventMenu;

import GUI.MainMenuPanel;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AttendeeManager;
import UseCases.Events.EventManager;

import javax.swing.*;

/**
 * Performs the commands inputted on the attendee event menu
 */
public class AttendeeEventController extends EventMenuController {

    /**
     * AttendeeEventController constructor
     *
     * @param manager      contains the attendee using the current session
     * @param eventManager contains the list of events
     */
    public AttendeeEventController(AttendeeManager manager, EventManager eventManager, RoomManager roomManager,
                                   LanguageManager languageManager, JFrame frame, MainMenuPanel mainMenuPanel) {
        super(manager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
    }
}