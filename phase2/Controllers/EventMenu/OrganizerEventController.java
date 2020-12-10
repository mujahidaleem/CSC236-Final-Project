package Controllers.EventMenu;

import Entities.Users.AccountCreatorFactory;
import Entities.Events.Event;
import Entities.Room;
import Entities.Users.*;
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
                                    AccountCreatorFactory accountCreatorFactory, JFrame frame){
        super(userManager, eventManager, roomManager, languageManager, frame);
        this.organizerManager = manager;
        this.speakerManager = speakerManager;
        this.accountCreatorFactory = accountCreatorFactory;
        this.eventFactory = new EventFactory();
    }

    public Event createEvent(String name, LocalDateTime dateTime, int roomNumber, int maxCapacity, int duration, String type){
        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNumber)
                && roomManager.bookable(roomNumber, dateTime, duration)) {
            ArrayList<Integer> speakers = new ArrayList<>();
            speakers.add(0);
            return eventManager.createEvent(type, name,dateTime,duration,organizerManager.getCurrentOrganizer().getId(), roomNumber, maxCapacity, new ArrayList<>(), speakers);
        } else {
            return null;
        }
    }

//    /**
//     * Tries to create an event with no speakers and add it to the list of events if event name is available and
//     * if the event created does not create double booking.
//     *
//     * @param name       the name of the new event
//     * @param dateTime       when the new event will happen
//     * @param roomNum where the new event will happen
//     * @return whether or not the event can be created
//     */
//
//    public Event createAttendeeOnlyEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
//                                                     int duration) {
//        Event eventCreated = eventManager.createAttendeeOnlyEvent(name, dateTime, duration,
//                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);
//
//        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
//                && roomManager.bookable(roomNum, dateTime, duration)) {
//            return eventCreated;
//        }
//        return null;
//    }
//
//    /**
//     * Tries to create an event with more than 1 speaker and add it to the list of events if event name is available and
//     * if the event created does not create double booking.
//     *
//     * @param name       the name of the new event
//     * @param dateTime       when the new event will happen
//     * @param roomNum where the new event will happen
//     * @return whether or not the event can be created
//     */
//
//    public MultiSpeakerEvent createMultiSpeakerEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
//                                         int duration) {
//        MultiSpeakerEvent eventCreated = eventManager.createMultiSpeakerEvent(name, dateTime, duration,
//                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);
//
//        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
//                && roomManager.bookable(roomNum, dateTime, duration)) {
//            return eventCreated;
//        }
//        return null;
//    }
//
//    /**
//     * Tries to create an event with 1 speaker and add it to the list of events if event name is available and
//     * if the event created does not create double booking.
//     *
//     * @param name       the name of the new event
//     * @param dateTime       when the new event will happen
//     * @param roomNum where the new event will happen
//     * @return whether or not the event can be created
//     */
//
//    public OneSpeakerEvent createOneSpeakerEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
//                                                     int duration) {
//        OneSpeakerEvent eventCreated = eventManager.createOneSpeakerEvent(name, dateTime, duration,
//                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);
//
//        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
//                && roomManager.bookable(roomNum, dateTime, duration)) {
//            return eventCreated;
//        }
//        return null;
//    }

    /**
     * creates a room
     *
     * @param roomCapacity
     * @return Room
     */

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
        ArrayList<Integer> newSpeaker = new ArrayList<>();
        newSpeaker.add(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException();
        } else {
            if (speakerManager.available(newSpeaker, event.getEventTime())) {
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
     * Checks if the date of an event can be changed and if so, changes the date of the event
     *
     * @param event the event that the organizer is trying to change the date
     * @param newDateTime  the new date of the event
     * @return whether or not the date of the event has been changed
     */
    public boolean changeEventDate(Event event, LocalDateTime newDateTime) {
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());

        if (roomManager.bookable(event.getRoomNumber(), newDateTime, event.getDuration()) && speakerManager.dateChangeable(event, newDateTime)){
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
        if (roomManager.hasRoom(roomNumber) && roomManager.bookable(roomNumber, startTime, duration)){
            Room newRoom = roomManager.findRoom(roomNumber);
            roomManager.removeEvent(currentRoom, event);
            roomManager.scheduleEvent(newRoom, startTime, duration, event);
            return true;
        }
        return false;
    }

    /**
     * Checks if duration of the event can be changed and if so, changes the event duration
     * @param event
     * @param newDuration
     * @return whether duration of an event is changed
     */

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

    /**
     * sets max capacity for an event
     * @param event
     * @param newMax
     * @return
     */

    public void setMaxCapacity(Event event, int newMax){
        eventManager.setMaxCapacity(event, newMax);
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
        User user = accountCreatorFactory.createAccountFactory(userManager.getUsers().size() + 1000, name, password,
                accountType, new HashMap<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>());
        this.userManager.getUsers().add(user);
        return user;
    }

}
