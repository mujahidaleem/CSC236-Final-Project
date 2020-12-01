package Presenters;

import Controllers.EventMenuController;
import UseCases.AttendeeManager;
import UseCases.EventManager;
import UseCases.Language.LanguageManager;

/**
 * Displays the event menu for an attendee and interprets
 * the commands inputted by the attendee on this menu
 */
public class AttendeeEventPresenter extends EventMenuPresenter {

    /**
     * AttendeeEventPresenter constructor
     *
     * @param attendeeManager stores the current user
     * @param eventManager stores the list of events
     * @param eventMenuController executes the commands
     * @param languageManager prints the strings
     */
    public AttendeeEventPresenter(AttendeeManager attendeeManager, EventMenuController eventMenuController, EventManager eventManager, LanguageManager languageManager) {
        super(attendeeManager, eventMenuController, eventManager, languageManager);
    }

}
