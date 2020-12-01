package Presenters;

import Controllers.*;
import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import Entities.User;
import UseCases.*;
import UseCases.Language.LanguageManager;

import java.util.ArrayList;

public class MenuFactory {
    private UserManager userManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private UserFriendManager userFriendManager;
    private LanguageManager languageManager;

    /**
     * A factory for creating the use case managers and menus needed to run the program
     *
     * @param userManager       Use case functions for a user
     * @param eventManager      Use case functions of an event
     * @param userFriendManager Use case functions of a friend list
     */
    public MenuFactory(UserManager userManager, EventManager eventManager, UserFriendManager userFriendManager, LanguageManager languageManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userFriendManager = userFriendManager;
        this.languageManager = languageManager;
    }

    /**
     * Creates specifc managers, event controllers, and presenters
     * depending on if the user is an attendee, organizer, or speaker
     *
     * @return A new event presenter depending on if the user is an attendee, organizer, or speaker
     */
    public EventMenuPresenter createEventMenu() {
        if (userManager.getCurrentUser().getClass().equals(Attendee.class)) {
            AttendeeManager attendeeManager = createAttendeeManager();
            attendeeManager.setCurrentUser(userManager.getCurrentUser());
            AttendeeEventController attendeeEventController = new AttendeeEventController(attendeeManager, eventManager);
            return new AttendeeEventPresenter(attendeeManager, attendeeEventController, eventManager, languageManager);
        } else if (userManager.getCurrentUser().getClass().equals(Organizer.class)) {
            OrganizerManager organizerManager = createOrganizerManager();
            organizerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerManager speakerManager = createSpeakerManager();
            OrganizerEventController organizerEventController = new OrganizerEventController(organizerManager, eventManager, userManager, speakerManager);
            return new OrganizerEventPresenter(organizerManager, speakerManager, organizerEventController, eventManager, languageManager);
        } else {
            SpeakerManager speakerManager = createSpeakerManager();
            speakerManager.setCurrentUser(userManager.getCurrentUser());
            SpeakerEventController speakerEventController = new SpeakerEventController(speakerManager, eventManager);
            return new SpeakerEventPresenter(speakerManager, speakerEventController, eventManager, languageManager);
        }
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
            OrganizerManager organizerManager = createOrganizerManager();
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

    /**
     * Create an organizer manager
     *
     * @return organizer manager
     */
    private OrganizerManager createOrganizerManager() {
        OrganizerManager organizerManager = new OrganizerManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Organizer.class)) {
                organizerManager.organizers.add((Organizer) user);
            }
        }
        return organizerManager;
    }

    /**
     * Create an speaker manager
     *
     * @return speaker manager
     */
    private SpeakerManager createSpeakerManager() {
        SpeakerManager speakerManager = new SpeakerManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Speaker.class)) {
                speakerManager.speakers.add((Speaker) user);
            }
        }
        return speakerManager;
    }

    /**
     * Create an attendee manager
     *
     * @return attendee manager
     */
    private AttendeeManager createAttendeeManager() {
        AttendeeManager attendeeManager = new AttendeeManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Attendee.class)) {
                attendeeManager.users.add(user);
            }
        }
        return attendeeManager;
    }
}