package UseCases.Events;

import Entities.Events.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Events.OneSpeakerEvent;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Entities.Users.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventManager implements Serializable {
    private ArrayList<Event> events;
    private EventFactory eventFactory;

    /**
     * Event manager constructor, sets the events for the manager to manage
     *
     * @param events the events needing to be managed
     */
    public EventManager(ArrayList<Event> events) {
        this.events = events;
        this.eventFactory = new EventFactory();
    }

    public boolean spaceAvailable(Event event){
        return event.getTotalNum() < event.getMaxCapacity();
    }

    /**
     * Add an attendee to an event
     *
     * @param event    given event
     * @param attendee given attendee
     * @return boolean - if the attendee was added
     */
    public void addAttendee(Event event, User attendee) {
        event.add(attendee);
    }

    /**
     * Remove an attendee from an event
     *
     * @param event    given event
     * @param attendee given attendee
     * @return return if the attendee was removed or not
     */
    public void removeAttendee(Event event, Attendee attendee) {
        event.remove(attendee);
    }

    /**
     * Remove an event
     *
     * @param event given event
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Get the organizer of an event
     *
     * @param event given event
     * @return organizer of the given event
     */
    public int getOrganizer(Event event) {
        return event.getOrganizer();
    }

    public Event createEvent(String type, String name, LocalDateTime dateTime, int duration, int organizerID, int roomNumber,
                             int maxCapacity, ArrayList<Integer> attendees, ArrayList<Integer> speakers){
        Event event = eventFactory.createEvent(name, roomNumber, maxCapacity, dateTime, duration, organizerID, type, attendees, speakers);
        events.add(event);
        return event;
    }

//    /**
//     * Creates an event with no speakers (used by organizer)
//     *
//     * @param name       name of the event
//     * @param dateTime       date of the event
//     * @param organizer  organizer of the event
//     * @param roomNumber room number of the event
//     * @return newEvent - a new event
//     */
//
//    public Event createAttendeeOnlyEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
//                                                     int roomNumber, int maxCapacity){
//        int organizerId = organizer.getId();
//        return new Event(name, roomNumber, maxCapacity, dateTime, duration, organizerId, new ArrayList<>());
//    }
//
//    /**
//     * Creates an event with multiple speakers (used by organizer)
//     *
//     * @param name       name of the event
//     * @param dateTime       date of the event
//     * @param organizer  organizer of the event
//     * @param roomNumber room number of the event
//     * @return newEvent - a new event
//     */
//
//    public MultiSpeakerEvent createMultiSpeakerEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
//                                                     int roomNumber, int maxCapacity){
//        int organizerId = organizer.getId();
//        return new MultiSpeakerEvent(name, roomNumber, maxCapacity, dateTime, duration, organizerId);
//    }
//
//    /**
//     * Creates an event with multiple speakers (used by organizer)
//     *
//     * @param name       name of the event
//     * @param dateTime       date of the event
//     * @param organizer  organizer of the event
//     * @param roomNumber room number of the event
//     * @return newEvent - a new event
//     */
//
//    public OneSpeakerEvent createOneSpeakerEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
//                                                     int roomNumber, int maxCapacity){
//        int organizerId = organizer.getId();
//        return new OneSpeakerEvent(name, roomNumber, maxCapacity, dateTime, duration, organizerId);
//    }

    /**
     * Get events that the manager is managing
     *
     * @return events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Remove the speaker from an event
     *
     * @param event given event
     */
    public void removeSpeaker(Event event, int speaker) {
        event.removeSpeaker(speaker);
    }

    /**
     * Change the date of an event
     *
     * @param event given event
     * @param date  new date
     * @return returns if the date was changed
     */
    public boolean changeDate(Event event, LocalDateTime date) {
        if (event.getEventTime().isAfter(LocalDateTime.now()) && !date.isBefore(LocalDateTime.now())) {
            event.setEventTime(date);
            return true;
        } else {
            return false;
        }
    }

    /**
     * See if an event exists in the manager
     *
     * @param name name of the event
     * @return the event, if it is there - otherwise null
     */
    public Event findEvent(String name) {
        for (Event event : events) {
            if (event.getEventName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Add a user to an event (generic method)
     *
     * @param event given event
     * @param user  given user
     */
    public boolean addUser(Event event, User user) {
        if(event.getAttendees().contains(user.getId())){
            return false;
        } else{
            event.getAttendees().add(user.getId());
            return true;
        }
    }

    /**
     * Remove a user from an event (generic method)
     *
     * @param user  given user
     * @param event given event
     * @return if the user was removed or not
     */
    public boolean removeUser(User user, Event event) {
        if (event.getAttendees().contains(user.getId())) {
            event.getAttendees().remove(Integer.valueOf(user.getId()));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the speaker of the event
     *
     * @param speaker given speaker of the event
     * @param event   given event
     */
    public void addSpeaker(Speaker speaker, Event event) {
        event.addSpeaker(speaker.getId());
    }

    /**
     * Checks if the event has a speaker
     *
     * @param event given event
     * @return boolean - if a speaker exists
     */
    public boolean hasSpeaker(Event event) {
        return event.hasSpeaker();
    }

//    /**
//     * Change the room number of an event
//     *
//     * @param event      given event
//     * @param roomNumber room number of an event
//     * @return boolean - if the event was moved
//     */
//    public boolean changeRoom(Event event, int roomNumber) {
//        for (Event e : events) {
//            if (e.getRoomNumber() == roomNumber && e.getEventTime().isEqual(event.getEventTime())) {
//                return false;
//            }
//        }
//        event.setRoomNumber(roomNumber);
//        return true;
//    }

    public void setDuration(Event event, int duration){
        event.setDuration(duration);
    }

    public void setMaxCapacity(Event event, int newMax){
        event.setMaxCapacity(newMax);
    }


    public boolean nameAvailable(String name) {
        for (Event e:events){
            if (e.getEventName().equals(name)){
                return false;
            }
        }
        return true;
    }
}