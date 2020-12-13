package Controllers.EventMenu;

import Entities.Users.AccountCreatorFactory;
import Entities.Events.Event;
import Entities.Room;
import Entities.Users.*;
import GUI.Events.CreateAccountPanel;
import GUI.Events.EditEventPanel;
import GUI.Events.OrganizerEventMenuPanel;
import GUI.MainMenuPanel;
import Presenters.EventMenu.OrganizerEventPresenter;
import UseCases.Events.EventFactory;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class OrganizerEventController extends EventMenuController {
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AccountCreatorFactory accountCreatorFactory;
    private EventFactory eventFactory;
    private OrganizerEventMenuPanel panel;
    private OrganizerEventPresenter eventPresenter;

    private boolean editMenuCreated = false;
    private boolean accountMenuCreated = false;

    /**
     * OrganizerEventController constructor
     *
     * @param manager        contains the organizer using the current session
     * @param eventManager   contains the list of events
     * @param userManager    contains the list of users
     * @param speakerManager contains the list of speakers
     */
    public OrganizerEventController(OrganizerManager manager, RoomManager roomManager,
                                    EventManager eventManager, UserManager userManager, LanguageManager languageManager,
                                    SpeakerManager speakerManager,
                                    AccountCreatorFactory accountCreatorFactory, JFrame frame, MainMenuPanel mainMenuPanel) {
        super(userManager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        this.organizerManager = manager;
        this.speakerManager = speakerManager;
        this.accountCreatorFactory = accountCreatorFactory;
        this.eventFactory = new EventFactory();
        this.panel = new OrganizerEventMenuPanel(this, frame, languageManager);
        this.eventPresenter = new OrganizerEventPresenter(organizerManager, speakerManager, eventManager,
                languageManager, panel, new EditEventPanel(frame, this, languageManager), mainMenuPanel,
                new CreateAccountPanel(frame, this, languageManager));
    }

    /**
     * Displays the event menu of an organizer and sets it up if it hasn't already been set up
     *
     * @param theme the theme of the GUI
     */
    @Override
    public void printMenu(String theme) {
        currentTheme = theme;
        if (menuCreated) {
            eventPresenter.showEventMenu();
        } else {
            eventPresenter.setUpMenu(currentTheme);
            menuCreated = true;
        }
    }

    /**
     * Displays the event menu of an organizer
     */
    public void showEventMenu() {
        eventPresenter.showEventMenu();
    }

    /**
     * Tries to remove a user from an event
     *
     * @param event the event the user is trying to cancel their spot from
     */
    @Override
    public void removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            eventPresenter.removalResult(true, event);
        } else {
            eventPresenter.removalResult(false, event);
        }
    }

    /**
     * Tries to sign a user up for an event
     *
     * @param event the event the user is trying to attend
     */
    @Override
    public void signUpForEvent(Event event) {
        boolean canBook = eventManager.spaceAvailable(event) &&                                     // if event still Event and Room still has space
                roomManager.getRoomCapacity(event.getRoomNumber()) >= event.getTotalNum() + 1;
        if (canBook && eventManager.addUser(event, userManager.getCurrentUser())) {
            userManager.attendEvent(event);
            eventPresenter.signUpResult(true, event);
        } else {
            eventPresenter.signUpResult(false, event);
        }
    }

    /**
     * Creates an event based on the inputted information form the organizer user
     *
     * @param name        the name of the event
     * @param dateTime    the starting time of the event
     * @param roomNumber  the room number of the event
     * @param maxCapacity the maximum capacity of the event
     * @param duration    the duration of the event
     * @param type        the type of event that is being created
     */
    public void createEvent(String name, LocalDateTime dateTime, int roomNumber, int maxCapacity, int duration, String type) {
        if (dateTime.isAfter(LocalDateTime.now())){
            if(eventManager.nameAvailable(name)){
                if(roomManager.hasRoom(roomNumber) && roomManager.bookable(roomNumber, dateTime, duration)){
                    ArrayList<Integer> speakers = new ArrayList<>();
                    speakers.add(0);
                    Event event = eventManager.createEvent(type, name, dateTime, duration, organizerManager.getCurrentOrganizer().getId(), roomNumber, maxCapacity, new ArrayList<>(), speakers);
                    organizerManager.getCurrentOrganizer().get_eventsOrganizing().put(event.getEventName(), event.getEventTime());
                    roomManager.scheduleEvent(roomManager.findRoom(event.getRoomNumber()), event.getEventTime(), event.getDuration(), event);
                    eventPresenter.createEventResults(3, event);
                } else {
                    eventPresenter.createEventResults(2, null);
                }
            }else {
                eventPresenter.createEventResults(1, null);
            }
        } else{
            eventPresenter.createEventResults(0, null);
        }
    }


    /**
     * Changes the information of an event based on what the organizer has inputted
     *
     * @param event       the event being changed
     * @param dateTime    the new starting date of the event
     * @param duration    the new duration of the event
     * @param maxCapacity the new maximum capacity of the event
     * @param roomNumber  the new room number of the event
     */
    public void changeEventInformation(Event event, LocalDateTime dateTime, int duration, int maxCapacity, int roomNumber) {
        if (changeEventDate(event, dateTime)) {
            if (changeEventRoom(event, roomNumber)) {
                if (setMaxCapacity(event, maxCapacity)) {
                    if (setDuration(event, duration)) {
                        eventPresenter.changeEventInformationResults(event);
                    } else {
                        eventPresenter.changeEventDurationFailure();
                    }
                } else {
                    eventPresenter.changeEventCapacityFailure();
                }
            } else {
                eventPresenter.changeEventRoomFailure();
            }
        } else {
            eventPresenter.changeEventDateFailure();
        }
    }

    /**
     * Displays the menu for editing or creating events
     *
     * @param event the event that is being created. It is null if a new event is being created
     * @param i     true if an event is being created, false if an event is being modified
     */
    public void printEditMenu(Event event, boolean i) {
        if (editMenuCreated) {
            eventPresenter.showEditMenu(event, i);
        } else {
            eventPresenter.setUpEditMenu(event, i, currentTheme);
            editMenuCreated = true;
        }
    }

    /**
     * Displays the event for creating new accounts
     */
    public void printAccountMenu() {
        if (accountMenuCreated) {
            eventPresenter.showCreateAccountMenu();
        } else {
            eventPresenter.setUpCreateAccountMenu(currentTheme);
            accountMenuCreated = true;
        }
    }

    /**
     * Displays a pop up menu asking for the id of the speaker
     *
     * @return the id of the speaker
     */
    public int showAddSpeakerPrompt() {
        return eventPresenter.showAddSpeakerPrompt();
    }

    /**
     * changes the theme of the current GUI
     *
     * @param string the new theme
     */
    public void changeTheme(String string) {
        currentTheme = string;
        if (menuCreated) {
            eventPresenter.changeTheme(string);
        }
        if (editMenuCreated) {
            eventPresenter.changeEditPanelTheme(string);
        }
        if (accountMenuCreated) {
            eventPresenter.changeAccountPanelTheme(string);
        }
    }

    /**
     * Changes the language of the GUI
     * @param language the new language
     */
    public void changeLanguage(String language){
        currentLanguage = language;
        if (menuCreated) {
            eventPresenter.changeLanguage(language);
        }
        if (editMenuCreated) {
            eventPresenter.changeEditPanelLanguage(language);
        }
        if (accountMenuCreated) {
            eventPresenter.changeAccountPanelLanguage(language);
        }
    }

    /**
     * Checks if the chosen speaker can be assigned to an event
     *
     * @param event   the event that the organizer is trying to assign the speaker to
     * @param speaker the speaker that the organizer is trying to assign to an event
     */
    public void addSpeaker(Event event, int speaker) throws NullSpeakerException {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        ArrayList<Integer> newSpeaker = new ArrayList<>();
        newSpeaker.add(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException();
        } else {
            if (speakerManager.available(newSpeaker, event.getEventTime())) {
                eventManager.addSpeaker(speaker1, event);
                speakerManager.setSpeaker(speaker1, event);
                eventPresenter.addSpeakerResults(true, speaker1);
            } else {
                eventPresenter.addSpeakerResults(false, speaker1);
            }
        }
    }

    /**
     * Tells the user that the speaker could not be found
     */
    public void showNullSpeaker() {
        eventPresenter.showNullSpeaker();
    }

    /**
     * Checks if the speaker of an event can be removed and removes the speaker
     *
     * @param event the event that the organizer is trying to remove the speaker from
     */
    public void removeSpeaker(Event event, int speaker) throws NullSpeakerException {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException();
        } else {
            if (eventManager.hasSpeaker(event)) {
                speakerManager.removeEvent(speaker1, event);
                eventManager.removeSpeaker(event, speaker);
                eventPresenter.removeSpeakerResults(true, speaker1);
            } else {
                eventPresenter.removeSpeakerResults(false, speaker1);
            }
        }
    }

    /**
     * Checks if the date of an event can be changed and if so, changes the date of the event
     *
     * @param event       the event that the organizer is trying to change the date
     * @param newDateTime the new date of the event
     * @return whether or not the date of the event has been changed
     */
    public boolean changeEventDate(Event event, LocalDateTime newDateTime) {
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());

        if (roomManager.bookable(event.getRoomNumber(), newDateTime, event.getDuration()) && speakerManager.dateChangeable(event, newDateTime)) {
            roomManager.removeEvent(currentRoom, event);
            eventManager.changeDate(event, newDateTime);
            roomManager.scheduleEvent(currentRoom, newDateTime, event.getDuration(), event);
            speakerManager.changeDate(event, newDateTime);
            return true;
        }
        return false;
    }

    /**
     * Checks if the room of an event can be changed and if so, changes the event room
     *
     * @param event      the event that the organizer is trying to change the date
     * @param roomNumber the new room for the event
     * @return whether or not the room of the event has been changed
     */
    public boolean changeEventRoom(Event event, int roomNumber) {
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());
        LocalDateTime startTime = event.getEventTime();
        int duration = event.getDuration();
        if (roomManager.hasRoom(roomNumber) && roomManager.bookable(roomNumber, startTime, duration)) {
            Room newRoom = roomManager.findRoom(roomNumber);
            roomManager.removeEvent(currentRoom, event);
            roomManager.scheduleEvent(newRoom, startTime, duration, event);
            return true;
        }
        return false;
    }

    /**
     * Sets the duration of an event to a new value
     *
     * @param event       the event being changed
     * @param newDuration the new duration of the event
     * @return whether the duration has been successfully changed
     */
    public boolean setDuration(Event event, int newDuration) {
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());
        LocalDateTime startTime = event.getEventTime();

        roomManager.removeEvent(currentRoom, event);

        if (roomManager.bookable(event.getRoomNumber(), startTime, newDuration)) {
            roomManager.scheduleEvent(currentRoom, startTime, newDuration, event);
            eventManager.setDuration(event, newDuration);
            return true;
        }

        roomManager.scheduleEvent(currentRoom, startTime, event.getDuration(), event);
        return false;
    }

    /**
     * Sets the maximum capacity of an event to a new value. The new value must be smaller than the capacity of the
     * room and the number of current people in the event
     *
     * @param event  the event being changed
     * @param newMax the new maximum capacity of the event
     * @return
     */
    public boolean setMaxCapacity(Event event, int newMax) {
        if (newMax <= roomManager.findRoom(event.getRoomNumber()).getRoomCapacity() && newMax <= event.getTotalNum()) {
            event.setMaxCapacity(newMax);
            return true;
        }
        return false;
    }

    /**
     * Checks if the event can be deleted and if so, deletes the event and removes if from all user's schedule
     *
     * @param event the event that the organizer is trying to delete
     * @return whether or not the event has been deleted
     */
    public void deleteEvent(Event event) throws NullEventException {
        if (event == null) {
            throw new NullEventException();
        }
        if (organizerManager.cancelEvent(event)) {

            Room currentRoom = roomManager.findRoom(event.getRoomNumber());

            userManager.deleteEvent(event);
            eventManager.removeEvent(event);
            roomManager.removeEvent(currentRoom, event);
        }
    }

    /**
     * Checks if an event is an event is being organized by the current organizer
     *
     * @param event the event that is being checked
     * @return if the current organizer is organizing this event
     */
    public boolean eventModifiable(Event event) throws NullEventException {
        if (event == null) {
            throw new NullEventException();
        }
        return event.getOrganizer() == organizerManager.getCurrentOrganizer().getId();
    }

    /**
     * Tells the organizer that they cannot modify this event.
     *
     * @param string the name of the event
     */
    public void showNonModifiableEventPrompt(String string) {
        eventPresenter.showNonModifiableEventPrompt(string);
    }

    /**
     * Creates an account depending on accountType, returns null if accountType password is nothing or accountype DNE
     *
     * @param name        name of account
     * @param password    password of account
     * @param accountType type of account (admin, attendee, organizer, speaker)
     */
    public void createAccount(String name, String password, String accountType) {
        User user = accountCreatorFactory.createAccountFactory(userManager.getUsers().size() + 1000, name, password,
                accountType, new HashMap<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>());
        this.userManager.getUsers().add(user);
        eventPresenter.createUserAccountResults(true, user, accountType);
    }

    /**
     * returns the speakerManager
     *
     * @return speakerManager
     */
    public SpeakerManager getSpeakerManager() {
        return speakerManager;
    }

    /**
     * Tells the user that the date inputted could not be recognized
     */
    public void showIncorrectDate() {
        eventPresenter.showIncorrectDate();
    }
}
