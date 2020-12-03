package UseCases.Events;

import Entities.Events.*;
import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Entities.Users.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventManager implements Serializable {
    private ArrayList<Event> events;
    private ArrayList<SpeakerModifiable> speakerModifiables;

    /**
     * Event manager constructor, sets the events for the manager to manage
     *
     * @param events the events needing to be managed
     */
    public EventManager(ArrayList<Event> events) {
        this.events = events;
        this.speakerModifiables = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void addSpeakerModifiables(SpeakerModifiable speakerModifiable){
        speakerModifiables.add(speakerModifiable);
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

//    public boolean attendeeAddable(Event event, User attendee) {
//        int totalNum = event.getAttendees().size() + event.getSpeaker().size();
//        if (totalNum < event.getMaxCapacity()){
//            return !event.getAttendees().contains(attendee.getId());
//        }
//        return false;
//    }

    /**
     * Remove an attendee from an event
     *
     * @param event    given event
     * @param attendee given attendee
     * @return return if the attendee was removed or not
     */
    public boolean removeAttendee(Event event, Attendee attendee) {
        return event.remove(attendee);
    }

    public int getRoom(Event event){
        return event.getRoomNumber();
    }

    /**
     * Remove an event
     *
     * @param event given event
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void setMaxCapacity(Event event, int maxCapacity){
        event.setMaxCapacity(maxCapacity);
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

    public boolean nameAvailable(String name){
        for (Event event: events){
            if (name.equals(event.getEventName())){
                return false;
            }
        }
        return true;
    }

    /**
     * Create an event (used by organizer)
     *
     * @param name       name of the event
     * @param dateTime       date of the event
     * @param organizer  organizer of the event
     * @param roomNumber room number of the event
     * @return newEvent - a new event
     */
    public OneSpeakerEvent createOneSpeakerEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
                                                 int roomNumber, int maxCapacity) {

        int eventId = events.size();
        int organizerId = organizer.getId();
        return new OneSpeakerEvent(eventId, name, roomNumber, maxCapacity, dateTime, duration, organizerId);

    }

    public MultiSpeakerEvent createMultiSpeakerEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
                                                     int roomNumber, int maxCapacity){
        int eventId = events.size();
        int organizerId = organizer.getId();
        return new MultiSpeakerEvent(eventId, name, roomNumber, maxCapacity, dateTime, duration, organizerId);
    }

    public AttendeeOnlyEvent createAttendeeOnlyEvent(String name, LocalDateTime dateTime, int duration, Organizer organizer,
                                                     int roomNumber, int maxCapacity){
        int eventId = events.size();
        int organizerId = organizer.getId();
        return new AttendeeOnlyEvent(eventId, name, roomNumber, maxCapacity, dateTime, duration, organizerId);
    }

//    public Event createEvent(String type, String name, int roomNumber, int maxCapacity, LocalDateTime dateTime,
//                             int duration, Organizer organizer){
//        return new Event(type, events.size(), name, roomNumber, maxCapacity, dateTime, duration, organizer.getId());
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
     * Change the date of an event
     *
     * @param event given event
     * @param dateTime  new date
     * @return returns if the date was changed
     */
    public void changeDate(Event event, LocalDateTime dateTime) {
//        if (event.getEventTime().isAfter(LocalDateTime.now()) && !date.isBefore(LocalDateTime.now())) {
//            event.setEventTime(date);
//            return true;
//        } else {
//            return false;
//        }
        event.setEventTime(dateTime);
        event.setEventTime(dateTime);
    }

    public void setDuration(Event event, int duration){
        event.setDuration(duration);
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
    public void addUser(Event event, User user) {
        event.getAttendees().add(user.getId());
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
     * Checks if the user can sign up for an event
     *
     * @param user  given user
     * @param event given event
     * @return returns if the user can sign up for the event
     */
    public boolean userCanSignUp(User user, Event event) {
        if (event.getAttendees().size() >= 2) {
            return false;
        } else {
            return !event.getAttendees().contains(user.getId());
        }
    }

    /**
     * Checks if the user can leave the event
     *
     * @param user  given user
     * @param event given event
     * @return boolean - if the user can leave the event
     */
    public boolean userCanLeave(User user, Event event) {
        return event.getAttendees().contains(user.getId());
    }

    /**
     * Sets the speaker of the event
     *
     * @param speaker given speaker of the event
     * @param event   given event
     */
    public void setSpeaker(Speaker speaker, Event event) {
        event.setSpeaker(speaker.getId());
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


    public boolean addSpeaker(Speaker speaker, Event event){

        if (speakerModifiables.contains(event)){
            SpeakerModifiable e = (SpeakerModifiable) event;
            e.addSpeaker(speaker.getId());
            return true;
        }
        return false;
    }

    /**
     * Remove the speaker from an event
     *
     * @param event given event
     * @return
     */
    public boolean removeSpeaker(Event event, Speaker speaker) {
//        event.removeSpeaker(speaker.getId());
//        if (!event.hasSpeaker() || !event.getSpeaker().contains(speaker.getId())){
//            return false;
//        }
//        else{
//            event.removeSpeaker(speaker.getId());
//            return true;
//        }
        if (speakerModifiables.contains(event) && event.hasSpeaker()){
            SpeakerModifiable e = (SpeakerModifiable) event;
            e.removeSpeaker(speaker.getId());
            return true;
        }
        return false;

    }


    /**
     * Change the room number of an event
     *
     * @param event      given event
     * @param roomNumber room number of an event
     * @return boolean - if the event was moved
     */
    public boolean changeRoom(Event event, int roomNumber) {
//        for (Event e : events) {
//            if (e.getRoomNumber() == roomNumber && e.getEventTime().isEqual(event.getEventTime())) {
//                return false;
//            }
//        }
//        event.setRoomNumber(roomNumber);
//        return true;
        event.setRoomNumber(roomNumber);
        return true;
    }


}