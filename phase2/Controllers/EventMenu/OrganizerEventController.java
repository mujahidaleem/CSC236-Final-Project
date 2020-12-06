package Controllers.EventMenu;

import Controllers.Factories.OrganizerAccountCreatorFactory;
import Entities.Events.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Events.OneSpeakerEvent;
import Entities.Room;
import Entities.Users.*;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Events.SameEventNameException;
import UseCases.Users.*;

import java.time.LocalDateTime;

public class OrganizerEventController extends EventMenuController {
    private OrganizerManager organizerManager;
    private RoomManager roomManager;
    private SpeakerManager speakerManager;
    private OrganizerAccountCreatorFactory organizerAccountCreatorFactory;

    /**
     * OrganizerEventController constructor
     *
     * @param manager        contains the organizer using the current session
     * @param eventManager   contains the list of events
     * @param userManager    contains the list of users
     * @param speakerManager contains the list of speakers
     */
    public OrganizerEventController(OrganizerManager manager, RoomManager roomManager,
                                    EventManager eventManager, UserManager userManager, SpeakerManager speakerManager,
                                    OrganizerAccountCreatorFactory organizerAccountCreatorFactory){
        super(userManager, eventManager);
        this.organizerManager = manager;
        this.roomManager = roomManager;
        this.speakerManager = speakerManager;
        this.organizerAccountCreatorFactory = organizerAccountCreatorFactory;
    }

    /**
     * Tries to create an event with no speakers and add it to the list of events if event name is available and
     * if the event created does not create double booking.
     *
     * @param name       the name of the new event
     * @param dateTime       when the new event will happen
     * @param roomNum where the new event will happen
     * @return whether or not the event can be created
     */

    public Event createAttendeeOnlyEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                                     int duration) {
        Event eventCreated = eventManager.createAttendeeOnlyEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)) {
            return eventCreated;
        }
        return null;
    }

    /**
     * Tries to create an event with more than 1 speaker and add it to the list of events if event name is available and
     * if the event created does not create double booking.
     *
     * @param name       the name of the new event
     * @param dateTime       when the new event will happen
     * @param roomNum where the new event will happen
     * @return whether or not the event can be created
     */

    public MultiSpeakerEvent createMultiSpeakerEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                         int duration) {
        MultiSpeakerEvent eventCreated = eventManager.createMultiSpeakerEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)) {
            return eventCreated;
        }
        return null;
    }

    /**
     * Tries to create an event with 1 speaker and add it to the list of events if event name is available and
     * if the event created does not create double booking.
     *
     * @param name       the name of the new event
     * @param dateTime       when the new event will happen
     * @param roomNum where the new event will happen
     * @return whether or not the event can be created
     */

    public OneSpeakerEvent createOneSpeakerEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                                     int duration) {
        OneSpeakerEvent eventCreated = eventManager.createOneSpeakerEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)) {
            return eventCreated;
        }
        return null;
    }

    public Room createRoom(int roomCapacity){
        return roomManager.addRoom(roomCapacity);
    }

    /**
     * Checks if the chosen speaker can be assigned to an event
     *
     * @param event   the event that the organizer is trying to assign the speaker to
     * @param speaker the speaker that the organizer is trying to assign to an event
     * @return whether or not the speaker can be assigned to the event
     */
    public boolean addSpeaker(Event event, int speaker) throws NullSpeakerException {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException();
        } else {
            if (speakerManager.available(speaker1, event.getEventTime())) {
                eventManager.addSpeaker(speaker1, event);
                speakerManager.setSpeaker(speaker1, event);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Checks if the speaker of an event can be removed and removes the speaker
     *
     * @param event the event that the organizer is trying to remove the speaker from
     * @return whether or not the speaker has been removed
     */
    public boolean removeSpeaker(Event event, int speaker) {
        if (eventManager.hasSpeaker(event)) {
            speakerManager.removeEvent(event);
            eventManager.removeSpeaker(event, speaker);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the date of an event can be changed and if so, changes the event
     *
     * @param event the event that the organizer is trying to change the date
     * @param newDateTime  the new date of the event
     * @return whether or not the date of the event has been changed
     */
    public boolean changeEventDate(Event event, LocalDateTime newDateTime) {
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());
        LocalDateTime startTime = event.getEventTime();

        roomManager.removeEvent(currentRoom, event);

        if (newDateTime.isAfter(LocalDateTime.now()) && roomManager.bookable(event.getRoomNumber(), newDateTime, event.getDuration())){
            roomManager.scheduleEvent(currentRoom, newDateTime, event.getDuration(), event);
            eventManager.changeDate(event, newDateTime);
            return true;
        }

        roomManager.scheduleEvent(currentRoom, startTime, event.getDuration(), event);
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
        if (roomManager.hasRoom(roomNumber) && roomManager.bookable(roomNumber, startTime, duration)){
            Room newRoom = roomManager.findRoom(roomNumber);
            roomManager.removeEvent(currentRoom, event);
            roomManager.scheduleEvent(newRoom, startTime, duration, event);
            return true;
        }
        return false;
    }

    public boolean setDuration(Event event, int newDuration){
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());
        LocalDateTime startTime = event.getEventTime();

        roomManager.removeEvent(currentRoom, event);

        if (roomManager.bookable(event.getRoomNumber(), startTime, newDuration)){
            roomManager.scheduleEvent(currentRoom, startTime, newDuration, event);
            eventManager.setDuration(event, newDuration);
            return true;
        }

        roomManager.scheduleEvent(currentRoom, startTime, event.getDuration(), event);
        return false;
    }

    public boolean setMaxCapacity(Event event, int newMax){
        eventManager.setMaxCapacity(event, newMax);
        return true;
    }


    /**
     * Checks if the event can be deleted and if so, deletes the event and removes if from all user's schedule
     *
     * @param event the event that the organizer is trying to delete
     * @return whether or not the event has been deleted
     */
    public boolean deleteEvent(Event event) {
        if (organizerManager.cancelEvent(event)) {

            Room currentRoom = roomManager.findRoom(event.getRoomNumber());

            userManager.deleteEvent(event);
            eventManager.removeEvent(event);
            roomManager.removeEvent(currentRoom, event);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if an event is an event is being organized by the current organizer
     *
     * @param event the event that is being checked
     * @return if the current organizer is organizing this event
     */
    public boolean eventModifiable(Event event) {
        return event.getOrganizer() == organizerManager.getCurrentOrganizer().getId();
    }

    /**
     * Creates an account depending on accountType, returns null if accountType password is nothing or accountype DNE
     * @param name name of account
     * @param password password of account
     * @param accountType type of account (admin, attendee, organizer, speaker)
     * @return User account
     */
    public User createAccount(String name, String password, String accountType) {
        return organizerAccountCreatorFactory.createAccountFactory(name, password, accountType);
    }

}
