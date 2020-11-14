package Controllers;

import UseCases.AttendeeManager;
import UseCases.EventManager;

public class AttendeeEventController extends EventMenuController {

    /**
     * AttendeeEventController constructor
     *
     * @param manager      contains the attendee using the current session
     * @param eventManager contains the list of events
     */
    public AttendeeEventController(AttendeeManager manager, EventManager eventManager) {
        super(manager, eventManager);
    }
}