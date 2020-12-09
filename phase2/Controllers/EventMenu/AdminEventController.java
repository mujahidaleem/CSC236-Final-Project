package Controllers.EventMenu;

import Entities.Events.Event;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import javax.swing.*;

public class AdminEventController extends EventMenuController{
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    public AdminEventController (UserManager userManager, EventManager eventManager, RoomManager roomManager, LanguageManager languageManager,
                                 SpeakerManager speakerManager, AdminManager adminManager, JFrame frame){
        super(userManager, eventManager, roomManager, languageManager, frame);
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
    }

    public boolean deleteEventWithNoAttendees(Event event){
        return adminManager.cancelEventWithoutAttendee(event, eventManager, userManager, speakerManager);
    }
}
