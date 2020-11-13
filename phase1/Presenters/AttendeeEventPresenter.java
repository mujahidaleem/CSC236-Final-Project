package Presenters;

import Controllers.EventMenuController;
import UseCases.AttendeeManager;

public class AttendeeEventPresenter extends EventMenuPresenter {

    /**
     * AttendeeEventPresenter constructor
     * @param attendeeManager stores the current user
     */
    public AttendeeEventPresenter(AttendeeManager attendeeManager, EventMenuController eventMenuController, String language) {
        super(attendeeManager, eventMenuController, language);
    }

}
