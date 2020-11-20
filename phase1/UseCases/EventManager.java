package UseCases;

import Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventManager implements Serializable {
    private ArrayList<Event> events;

    /**
     * Event manager constructor, sets the events for the manager to manage
     *
     * @param events the events needing to be managed
     */
    public EventManager(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Add an attendee to an event
     *
     * @param event    given event
     * @param attendee given attendee
     * @return boolean - if the attendee was added
     */
    public boolean addAttendee(Event event, User attendee) {
        if (event.getAttendees().size() < 2) {
            if (!event.getAttendees().contains(attendee.getId())) {
                event.add(attendee);
                return true;
            }
        }
        return false;
    }

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

    /**
     * Create an event (used by organizer)
     *
     * @param name       name of the event
     * @param date       date of the event
     * @param organizer  organizer of the event
     * @param roomNumber room number of the event
     * @return newEvent - a new event
     */
    public Event createEvent(String name, LocalDateTime date, Organizer organizer, int roomNumber) {
        for (Event event : events) {
            if ((event.getEventTime().isEqual(date) && event.getRoomNumber() == roomNumber)) {
                return null;
            }
        }
        Event newEvent = new Event(events.size(), name, roomNumber, date, organizer.getId());
        events.add(newEvent);
        organizer._eventsOrganizing.put(name, date);
        return newEvent;
    }

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
    public void removeSpeaker(Event event) {
        event.setSpeaker(0);
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

    /**
     * Change the room number of an event
     *
     * @param event      given event
     * @param roomNumber room number of an event
     * @return boolean - if the event was moved
     */
    public boolean changeRoom(Event event, int roomNumber) {
        for (Event e : events) {
            if (e.getRoomNumber() == roomNumber && e.getEventTime().isEqual(event.getEventTime())) {
                return false;
            }
        }
        event.setRoomNumber(roomNumber);
        return true;
    }
}