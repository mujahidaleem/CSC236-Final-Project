package src.Controllers;

import src.Entities.Speaker;

public class SpeakerEventController extends EventMenuController {
    Speaker currentUser;
    SpeakerManager manager;
    SpeakerEventPresenter presenter;
    EventManager eventManager;
    UserManager userManager;

    public SpeakerEventController(Speaker currentUser, SpeakerManager manager, EventManager eventmanager,
                                  UserManager userManager, SpeakerEventPresenter presenter) {
        this.currentUser = currentUser;
        this.manager = manager;
        this.eventManager = eventmanager;
        this.presenter = presenter;
        this.userManager = userManager;
    }
}