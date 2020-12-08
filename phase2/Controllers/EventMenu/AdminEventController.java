package Controllers.EventMenu;

import Entities.Events.Event;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Users.AdminManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

public class AdminEventController extends EventMenuController{
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    public AdminEventController (UserManager userManager, EventManager eventManager, RoomManager roomManager, SpeakerManager speakerManager, AdminManager adminManager){
        super(userManager, eventManager, roomManager);
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
    }

    public boolean deleteEventWithNoAttendees(Event event){
        return adminManager.cancelEventWithoutAttendee(event, eventManager, userManager, speakerManager);
    }
}
