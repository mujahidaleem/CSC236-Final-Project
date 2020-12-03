package Controllers.Factories;

import Controllers.MessageMenu.AttendeeFriendListController;
import Controllers.MessageMenu.OrganizerFriendListController;
import Controllers.MessageMenu.SpeakerFriendListController;

import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Presenters.MessageMenu.AttendeeMessagePresenter;
import Presenters.MessageMenu.MessageMenuPresenter;
import Presenters.MessageMenu.OrganizerMessagePresenter;
import Presenters.MessageMenu.SpeakerMessagePresenter;
import UseCases.*;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Message.OrganizerFriendManager;
import UseCases.Message.SpeakerFriendManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

public class MessageMenuFactory {
    private UserManager userManager;
    private SpeakerManager speakerManager;
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
    public MessageMenuFactory(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.languageManager = languageManager;
        this.factoryUseCaseHelper = new FactoryUseCaseHelper(userManager);
    }

    /**
     * Creates specific message managers, friend list controllers, and presenters
     * depending on if the user is an attendee, organizer, or speaker
     *
     * @return A new message presenter depending on if the user is an attendee, organizer, or speaker
     */
    public MessageMenuPresenter createMessageMenu() {
        if (userManager.getCurrentUser().getClass().equals(Attendee.class)) {
            AttendeeFriendManager attendeeFriendManager = new AttendeeFriendManager(userFriendManager.getUserToMessages(), null);
            attendeeFriendManager.setCurrentUser(userManager.getCurrentUser());
            AttendeeFriendListController attendeeFriendListController = new AttendeeFriendListController(attendeeFriendManager);
            return new AttendeeMessagePresenter(attendeeFriendListController, userManager, attendeeFriendManager, languageManager);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerFriendManager organizerFriendManager = new OrganizerFriendManager(userFriendManager.getUserToMessages(), null, eventManager);
            organizerFriendManager.setCurrentUser(userManager.getCurrentUser());
            organizerFriendManager.setCurrentOrganizer((Organizer) userManager.getCurrentUser());
            OrganizerFriendListController organizerFriendListController = new OrganizerFriendListController(organizerFriendManager, userManager);
            OrganizerManager organizerManager = factoryUseCaseHelper.createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            return new OrganizerMessagePresenter(organizerFriendListController, userManager, organizerManager, organizerFriendManager, languageManager);
        } else {
            SpeakerFriendManager speakerFriendManager = new SpeakerFriendManager(userFriendManager.getUserToMessages(), null);
            speakerFriendManager.setCurrentUser(userManager.getCurrentUser());
            speakerFriendManager.setCurrentSpeaker((Speaker) userManager.getCurrentUser());
            SpeakerFriendListController speakerFriendListController = new SpeakerFriendListController(speakerFriendManager, speakerManager, userManager);
            return new SpeakerMessagePresenter(speakerFriendListController, userManager, speakerFriendManager, eventManager, languageManager);
        }
    }
}
