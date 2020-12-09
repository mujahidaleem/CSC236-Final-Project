package Controllers.Factories;

import Controllers.MessageMenu.*;

import Entities.Users.Admin;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Gateways.MessageReader;
import Presenters.MessageMenu.*;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Message.AdminFriendManager;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Message.OrganizerFriendManager;
import UseCases.Message.SpeakerFriendManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.UserManager;

public class MessageMenuFactory {
    private UserManager userManager;
    private EventManager eventManager;
    private LanguageManager languageManager;
    private FactoryUseCaseHelper factoryUseCaseHelper;
    private MessageReader messageReader;

    /**
     * A factory for creating the use case managers and menus needed to run the program
     *
     * @param userManager       Use case functions for a user
     * @param eventManager      Use case functions of an event
     */
    public MessageMenuFactory(UserManager userManager, EventManager eventManager, LanguageManager languageManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.languageManager = languageManager;
        this.factoryUseCaseHelper = new FactoryUseCaseHelper(userManager);
        this.messageReader = new MessageReader("userFriendManager.ser");
    }

    /**
     * Creates specific message managers, friend list controllers, and presenters
     * depending on if the user is an attendee, organizer, or speaker
     *
     * @return A new message presenter depending on if the user is an attendee, organizer, or speaker
     */
    public UserFriendListController createMessageMenu() {
        if (userManager.getCurrentUser().getClass().equals(Attendee.class)) {
            AttendeeFriendManager attendeeFriendManager = new AttendeeFriendManager(messageReader.readData(userManager.getCurrentUser(), userManager), null);
            attendeeFriendManager.setCurrentUser(userManager.getCurrentUser());
            return new AttendeeFriendListController(attendeeFriendManager);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerFriendManager organizerFriendManager = new OrganizerFriendManager(messageReader.readData(userManager.getCurrentUser(), userManager), null, eventManager);
            organizerFriendManager.setCurrentUser(userManager.getCurrentUser());
            organizerFriendManager.setCurrentOrganizer((Organizer) userManager.getCurrentUser());
            OrganizerManager organizerManager = factoryUseCaseHelper.createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            return new OrganizerFriendListController(organizerFriendManager, userManager);
        } else if(userManager.getCurrentUser().getClass().equals(Speaker.class)){
            SpeakerFriendManager speakerFriendManager = new SpeakerFriendManager(messageReader.readData(userManager.getCurrentUser(), userManager), null);
            speakerFriendManager.setCurrentUser(userManager.getCurrentUser());
            speakerFriendManager.setCurrentSpeaker((Speaker) userManager.getCurrentUser());
            return new SpeakerFriendListController(speakerFriendManager, factoryUseCaseHelper.createSpeakerManager(), userManager);
        } else {
            AdminFriendManager adminFriendManager = new AdminFriendManager(messageReader.readData(userManager.getCurrentUser(),userManager), null);
            adminFriendManager.setCurrentUser(userManager.getCurrentUser());
            adminFriendManager.setCurrentAdmin((Admin) userManager.getCurrentUser());
            return new AdminFriendListController(adminFriendManager);
        }
    }
}
