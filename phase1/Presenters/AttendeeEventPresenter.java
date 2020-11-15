package Presenters;

import Controllers.EventMenuController;
import UseCases.AttendeeManager;
import UseCases.EventManager;

public class AttendeeEventPresenter extends EventMenuPresenter {

    /**
     * AttendeeEventPresenter constructor
     *
     * @param attendeeManager stores the current user
     */
    public AttendeeEventPresenter(AttendeeManager attendeeManager, EventMenuController eventMenuController, EventManager eventManager, String language) {
        super(attendeeManager, eventMenuController, eventManager, language);
    }

}
