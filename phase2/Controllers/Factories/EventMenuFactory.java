package Controllers.Factories;

import Controllers.EventMenu.AttendeeEventController;
import Controllers.EventMenu.OrganizerEventController;
import Controllers.EventMenu.SpeakerEventController;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.AccountCreatorFactory;
import Presenters.EventMenu.AttendeeEventPresenter;
import Presenters.EventMenu.EventMenuPresenter;
import Presenters.EventMenu.OrganizerEventPresenter;
import Presenters.EventMenu.SpeakerEventPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
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
     * @param userFriendManager Use case functions of a friend list
     */
    public EventMenuFactory(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager, RoomManager roomManager) {
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
        } else {
            SpeakerManager speakerManager = factoryUseCaseHelper.createSpeakerManager();
            speakerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerEventController speakerEventController = new SpeakerEventController(speakerManager, eventManager, roomManager);
            return new SpeakerEventPresenter(speakerManager, speakerEventController, eventManager, languageManager);
        }
    }
}
