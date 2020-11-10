package Controllers;

import UseCases.AttendeeManager;
import UseCases.EventManager;
import Presenters.AttendeeEventPresenter;

public class AttendeeEventController extends EventMenuController{
    private AttendeeEventPresenter presenter;

    /**
     * AttendeeEventController constructor
     * @param manager contains the attendee using the current session
     * @param eventManager contains the list of events
     * @param presenter displays the commands and the results of the commands on the UI
     */
    public AttendeeEventController(AttendeeManager manager,
                                   EventManager eventManager, AttendeeEventPresenter presenter) {
        super(manager, eventManager, presenter);
        this.presenter = presenter;
    }
}