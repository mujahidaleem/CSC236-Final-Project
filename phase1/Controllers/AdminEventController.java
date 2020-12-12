package Controllers;

public class AdminEventController extends EventMenuController {
    private UseCases.AdminManager adminManager;

    /**
     * AdminEventController constructor
     *
     * @param manager        contains the Admin using the current session
     * @param eventManager   contains the list of events
     * @param userManager    contains the list of users
     */
    public AdminEventController(UseCases.AdminManager manager,
                                          UseCases.EventManager eventManager, UseCases.UserManager userManager) {
        super(userManager, eventManager);
        this.adminManager = manager;
    }

    /**
     * Checks if the event can be deleted and if so, deletes the event and removes if from all user's schedule
     *
     * @param event the event that the admin is trying to delete
     * @return whether or not the event has been deleted
     */
    public boolean deleteEvent(Entities.Event event) {
        if (event.getAttendees().isEmpty()) {
            userManager.deleteEvent(event);
            eventManager.getEvents().remove(event);
            return true;
        } else {
            return false;
        }
    }
}
