package Controllers.Factories;

import Controllers.EventMenu.AttendeeEventController;
import Controllers.EventMenu.OrganizerEventController;
import Controllers.EventMenu.SpeakerEventController;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Presenters.EventMenu.AttendeeEventPresenter;
import Presenters.EventMenu.EventMenuPresenter;
import Presenters.EventMenu.OrganizerEventPresenter;
import Presenters.EventMenu.SpeakerEventPresenter;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.AttendeeManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

public class EventMenuFactory {
    private UserManager userManager;
    private EventManager eventManager;
    private UserFriendManager userFriendManager;
    private LanguageManager languageManager;
    private FactoryUseCaseHelper factoryUseCaseHelper;

    /**
     * A factory for creating the use case managers and menus needed to run the program
     *
     * @param userManager       Use case functions for a user
     * @param eventManager      Use case functions of an event
     * @param userFriendManager Use case functions of a friend list
     */
    public EventMenuFactory(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.languageManager = languageManager;
        this.factoryUseCaseHelper = new FactoryUseCaseHelper(userManager);
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
            AttendeeEventController attendeeEventController = new AttendeeEventController(attendeeManager, eventManager);
            return new AttendeeEventPresenter(attendeeManager, attendeeEventController, eventManager, languageManager);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerManager organizerManager = factoryUseCaseHelper.createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            OrganizerEventController organizerEventController = new OrganizerEventController(organizerManager, eventManager, userManager, speakerManager);
            return new OrganizerEventPresenter(organizerManager, speakerManager, organizerEventController, eventManager, languageManager);
        } else {
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            speakerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerEventController speakerEventController = new SpeakerEventController(speakerManager, eventManager);
            return new SpeakerEventPresenter(speakerManager, speakerEventController, eventManager, languageManager);
        }
    }
}
