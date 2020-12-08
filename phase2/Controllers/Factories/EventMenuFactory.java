package Controllers.Factories;

import Controllers.EventMenu.AdminEventController;
import Controllers.EventMenu.AttendeeEventController;
import Controllers.EventMenu.OrganizerEventController;
import Controllers.EventMenu.SpeakerEventController;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.AccountCreatorFactory;
import Entities.Users.Speaker;
import Presenters.EventMenu.*;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.*;

public class EventMenuFactory {
    private UserManager userManager;
    private EventManager eventManager;
    private LanguageManager languageManager;
    private FactoryUseCaseHelper factoryUseCaseHelper;
    private RoomManager roomManager;

    /**
     * A factory for creating the use case managers and menus needed to run the program
     *
     * @param userManager       Use case functions for a user
     * @param eventManager      Use case functions of an event
     */
    public EventMenuFactory(UserManager userManager, EventManager eventManager, LanguageManager languageManager, RoomManager roomManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.languageManager = languageManager;
        this.factoryUseCaseHelper = new FactoryUseCaseHelper(userManager);
        this.roomManager = roomManager;
    }


    /**
     * Creates specific managers, event controllers, and presenters
     * depending on if the user is an attendee, organizer, or speaker
     *
     * @return A new event presenter depending on if the user is an attendee, organizer, or speaker
     */
    public EventMenuPresenter getEventMenu() {
        if (userManager.getCurrentUser().getClass().equals(Attendee.class)) {
            AttendeeManager attendeeManager = factoryUseCaseHelper.createAttendeeManager();
            attendeeManager.setCurrentUser(userManager.getCurrentUser());
            AttendeeEventController attendeeEventController = new AttendeeEventController(attendeeManager, eventManager, roomManager);
            return new AttendeeEventPresenter(attendeeManager, attendeeEventController, eventManager, languageManager);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerManager organizerManager = factoryUseCaseHelper.createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            AccountCreatorFactory accountCreatorFactory = new AccountCreatorFactory();
            OrganizerEventController organizerEventController = new OrganizerEventController(organizerManager, roomManager, eventManager, userManager, speakerManager, accountCreatorFactory);
            return new OrganizerEventPresenter(organizerManager, speakerManager, organizerEventController, eventManager, languageManager);
        } else if(userManager.getCurrentUser().getClass().equals(Speaker.class)){
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            speakerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerEventController speakerEventController = new SpeakerEventController(speakerManager, eventManager, roomManager);
            return new SpeakerEventPresenter(speakerManager, speakerEventController, eventManager, languageManager);
        } else {
            AdminManager adminManager = factoryUseCaseHelper.createAdminManager();
            adminManager.setCurrentUser(userManager.getCurrentUser());
            AdminEventController adminEventController = new AdminEventController(userManager, eventManager, roomManager, factoryUseCaseHelper.createSpeakerManager(), adminManager);
            return new AdminEventPresenter(adminManager, adminEventController, eventManager, languageManager);
        }
    }
}
