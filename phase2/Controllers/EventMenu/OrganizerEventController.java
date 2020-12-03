package Controllers.EventMenu;

import Entities.*;
import Entities.Events.AttendeeOnlyEvent;
import Entities.Events.Event;
import Entities.Events.OneSpeakerEvent;
import Entities.Users.Speaker;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;

public class OrganizerEventController extends EventMenuController {
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private RoomManager roomManager;

    /**
     * OrganizerEventController constructor
     *
     * @param manager        contains the organizer using the current session
     * @param eventManager   contains the list of events
     * @param userManager    contains the list of users
     * @param speakerManager contains the list of speakers
     */
    public OrganizerEventController(OrganizerManager manager, RoomManager roomManager,
                                    EventManager eventManager, UserManager userManager, SpeakerManager speakerManager) {
        super(userManager, eventManager);
        this.organizerManager = manager;
        this.speakerManager = speakerManager;
        this.roomManager = roomManager;
    }

    /**
     * Tries to create an event and add it to the list of events by checking duplicate event names, room checks, time conflict
     * if event can be created, put update schedule in room and RoomManager and update events in EventManager
     *
     * @param name       the name of the new event
     * @param dateTime       when the new event will happen
     * @param roomNum where the new event will happen
     * @return whether or not the event can be created
     */
//    public Event createEvent(String name, LocalDateTime date, int roomNumber) {
//        return eventManager.createEvent(name, date, organizerManager.getCurrentOrganizer(), roomNumber);
//    }

    public AttendeeOnlyEvent createAttendeeOnlyEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                                     int duration) {
        AttendeeOnlyEvent eventCreated = eventManager.createAttendeeOnlyEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)) {
            return eventCreated;
        }
        return null;
    }

    public OneSpeakerEvent createOneSpeakerEvent(String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                                 int duration){
        OneSpeakerEvent eventCreated = eventManager.createOneSpeakerEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)){
            return eventCreated;
        }
        return null;

    }

    public Event createMultiSpeakerEvent(String type, String name, int roomNum, int maxCapacity, LocalDateTime dateTime,
                                         int duration){
        Event eventCreated = eventManager.createMultiSpeakerEvent(name, dateTime, duration,
                organizerManager.getCurrentOrganizer(), roomNum, maxCapacity);

        if (dateTime.isAfter(LocalDateTime.now()) && eventManager.nameAvailable(name) && roomManager.hasRoom(roomNum)
                && roomManager.bookable(roomNum, dateTime, duration)){
            return eventCreated;
        }
        return null;

    }



    /**
     * Checks if the chosen speaker can be assigned to an event
     *
     * @param event   the event that the organizer is trying to assign the speaker to
     * @param speaker the speaker that the organizer is trying to assign to an event
     * @return whether or not the speaker can be assigned to the event
     */
    public boolean addSpeaker(Event event, int speaker) {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException("Speaker does not exist");
        } else {
            if (speakerManager.available(speaker1, event.getEventTime())) {
                eventManager.setSpeaker(speaker1, event);
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
    public boolean removeSpeaker(Event event, Speaker speaker) {
        if (eventManager.removeSpeaker(event, speaker)) {
            speakerManager.removeEvent(event);
            return true;
            return false;
        }
    }


///*    *//**
//     * Checks if the date of an event can be changed and if so, changes the event
//     *
//     * @param event the event that the organizer is trying to change the date
//     * @param date  the new date of the event
//     * @return whether or not the date of the event has been changed
//     *//*
////    public boolean changeEventDate(Event event, LocalDateTime date) {
////        if (speakerManager.dateChangeable(event, date) && eventManager.changeDate(event, date)) {
////            speakerManager.changeDate(event, date);
////            return true;
////        } else {
////            return false;
////        }
////    }*/



    public boolean changeEventTime(Event event, LocalDateTime newEventTime){
        Room currentRoom = roomManager.findRoom(event.getRoomNumber());       // current room the event is in

        roomManager.removeEvent(currentRoom, event.getId());

        if (roomManager.bookable(event.getRoomNumber(), newEventTime, event.getDuration())){
            roomManager.scheduleEvent(currentRoom, newEventTime, event.getDuration(), event.getId());
            eventManager.changeDate(event, newEventTime);
            return true;
        }
        else{
            roomManager.scheduleEvent(currentRoom, event.getEventTime(), event.getDuration(), event.getId());
            return false;
        }
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
        Room newRoom = roomManager.findRoom(roomNumber);
        if (currentRoom.getRoomNumber() == roomNumber){                     // enter a different room number
            return false;
        }
        else if(roomManager.bookable(roomNumber, event.getEventTime(), event.getDuration())){
            roomManager.removeEvent(currentRoom, event.getId());
            roomManager.scheduleEvent(newRoom, event.getEventTime(), event.getDuration(), event.getId());
            eventManager.changeRoom(event, roomNumber);
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
    public boolean deleteEvent(Event event) {
        if (organizerManager.cancelEvent(event)) {
            userManager.deleteEvent(event);
            eventManager.getEvents().remove(event);
            return true;
        } else {
            return false;
        }
    }


    public boolean setEventMaxCapacity(Event event, int newMax){
        if (newMax < event.getTotalNum()){
            return false;
        }
        eventManager.setMaxCapacity(event, newMax);
        return true;
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
     * Checks if a speaker account is creatable and if so, creates a speaker account
     *
     * @param name     the name of the new speaker account
     * @param password the password of the new speaker account
     * @return null if the speaker cannot be created, otherwise it returns the new speaker account
     */
    public Speaker createSpeaker(String name, String password) {
        return organizerManager.createSpeaker(name, password, speakerManager, userManager);
    }
}
