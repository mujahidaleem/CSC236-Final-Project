package Controllers.EventMenu;

import Entities.Events.Event;
import UseCases.Events.EventManager;
import UseCases.Users.UserManager;

/**
 * Performs the commands inputted on the event menu
 */
public abstract class EventMenuController {
    public UserManager userManager;
    public EventManager eventManager;

    /**
     * AttendeeEventController constructor
     *
     * @param userManager  contains the attendee using the current session
     * @param eventManager contains the list of events
     */
    public EventMenuController(UserManager userManager, EventManager eventManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
    }

    /**
     * Checks if the current user can sign up for an event and signs them up if they can
     *
     * @param event the event the user is trying to attend
     * @return whether the user has signed up for the event
     */
    public boolean signUpForEvent(Event event) {
        if (eventManager.addAttendee(event, userManager.getCurrentUser())) {
            userManager.attendEvent(event);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user is currently attending an event and removes their spot if they are
     *
     * @param event the event the user is trying to cancel their spot from
     * @return whether the user's spot has been removed from the event
     */
    public boolean removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            return true;
        } else {
            return false;
        }
    }
}
