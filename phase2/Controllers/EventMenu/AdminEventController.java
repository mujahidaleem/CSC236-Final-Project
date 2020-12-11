package Controllers.EventMenu;

import Entities.Events.Event;
import GUI.Events.AdminEventMenuPanel;
import GUI.MainMenuPanel;
import Presenters.EventMenu.AdminEventPresenter;
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

    private AdminEventPresenter adminEventPresenter;

    public AdminEventController (UserManager userManager, EventManager eventManager, RoomManager roomManager, LanguageManager languageManager,
                                 SpeakerManager speakerManager, AdminManager adminManager, JFrame frame, MainMenuPanel mainMenuPanel){
        super(userManager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.adminEventPresenter = new AdminEventPresenter(adminManager, eventManager,languageManager, new AdminEventMenuPanel(this, frame), mainMenuPanel);
    }

    public void deleteEventWithNoAttendees(Event event){
        try{
            if(adminManager.cancelEventWithoutAttendee(event, eventManager, userManager, speakerManager)){
                adminEventPresenter.deleteEventResult(true);
            } else {
                adminEventPresenter.deleteEventResult(false);
            }
        } catch (NullEventException e){
            adminEventPresenter.showNullEventError();
        }
    }

    public void printMenu(){
        adminEventPresenter.setUpMenu();
    }

    public void reprintEvents(){
        adminEventPresenter.reprintEvents();
    }
}
