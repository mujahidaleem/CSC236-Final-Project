package Controllers.EventMenu;

import UseCases.Events.RoomManager;
import UseCases.Users.AttendeeManager;
import UseCases.Events.EventManager;

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
    public AttendeeEventController(AttendeeManager manager, EventManager eventManager, RoomManager roomManager) {
        super(manager, eventManager, roomManager);
    }
}