package Presenters.EventMenu;

import Controllers.EventMenu.EventMenuController;
import GUI.Events.AttendeeEventMenuPanel;
import UseCases.Users.AttendeeManager;
import UseCases.Events.EventManager;
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
     * @param languageManager prints the strings
     */
    public AttendeeEventPresenter(AttendeeManager attendeeManager, EventManager eventManager,
                                  LanguageManager languageManager, AttendeeEventMenuPanel attendeeEventMenuPanel) {
        super(attendeeManager, eventManager, languageManager, attendeeEventMenuPanel);
    }

}
