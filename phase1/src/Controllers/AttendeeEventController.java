package src.Controllers;
import src.Entities.Attendee;
import src.Presenters.AttendeeEventPresenter;

public class AttendeeEventController extends EventMenuController {
    Attendee currentUser;
    AttendeeManager manager;
    AttendeeEventPresenter presenter;
    EventManager eventManager;

    public AttendeeEventController(Attendee currentUser, AttendeeManager manager,
                                   EventManager eventmanager,AttendeeEventPresenter presenter) {
        super();
        this.currentUser = currentUser;
        this.eventManager = eventmanager;
        this.presenter = presenter;
        this.manager = manager;
    }
}

