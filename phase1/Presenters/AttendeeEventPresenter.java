package Presenters;

import UseCases.AttendeeManager;

public class AttendeeEventPresenter extends EventMenuPresenter {

    /**
     * AttendeeEventPresenter constructor
     * @param attendeeManager stores the current user
     */
    public AttendeeEventPresenter(AttendeeManager attendeeManager) {
        super(attendeeManager);
    }

}
