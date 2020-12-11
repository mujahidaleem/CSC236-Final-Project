package Controllers.Factories;

import Controllers.EventMenu.*;
import Entities.Users.*;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.*;

import javax.swing.*;

public class EventMenuFactory {
    private UserManager userManager;
    private EventManager eventManager;
    private LanguageManager languageManager;
    private FactoryUseCaseHelper factoryUseCaseHelper;
    private RoomManager roomManager;
    private JFrame frame;

    /**
     * A factory for creating the use case managers and menus needed to run the program
     *
     * @param userManager       Use case functions for a user
     * @param eventManager      Use case functions of an event
     */
    public EventMenuFactory(UserManager userManager, EventManager eventManager, LanguageManager languageManager, RoomManager roomManager, JFrame frame) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.languageManager = languageManager;
        this.factoryUseCaseHelper = new FactoryUseCaseHelper(userManager);
        this.roomManager = roomManager;
        this.frame = frame;
    }

    /**
     * Creates specific managers, event controllers, and presenters
     * depending on if the user is an attendee, organizer, or speaker
     *
     * @return A new event presenter depending on if the user is an attendee, organizer, or speaker
     */
    public EventMenuController getEventMenu(MainMenuPanel mainMenuPanel) {
        if (userManager.getCurrentUser().getClass().equals(Attendee.class)) {
            AttendeeManager attendeeManager = factoryUseCaseHelper.createAttendeeManager();
            attendeeManager.setCurrentUser(userManager.getCurrentUser());
            attendeeManager.setCurrentAttendee((Attendee) userManager.getCurrentUser());
            return new AttendeeEventController(attendeeManager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerManager organizerManager = factoryUseCaseHelper.createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            organizerManager.setCurrentOrganizer((Organizer) userManager.getCurrentUser());
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            AccountCreatorFactory accountCreatorFactory = new AccountCreatorFactory();
            return new OrganizerEventController(organizerManager, roomManager, eventManager, userManager, languageManager, speakerManager, accountCreatorFactory, frame, mainMenuPanel);
        } else if(userManager.getCurrentUser().getClass().equals(Speaker.class)){
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            speakerManager.setCurrentUser(userManager.getCurrentUser());
            speakerManager.setCurrentSpeaker((Speaker) userManager.getCurrentUser());
            return new SpeakerEventController(speakerManager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        } else {
            AdminManager adminManager = factoryUseCaseHelper.createAdminManager();
            adminManager.setCurrentUser(userManager.getCurrentUser());
            adminManager.setCurrentAdmin((Admin) userManager.getCurrentUser());
            return new AdminEventController(userManager, eventManager, roomManager, languageManager, factoryUseCaseHelper.createSpeakerManager(), adminManager, frame, mainMenuPanel);
        }
    }
}
