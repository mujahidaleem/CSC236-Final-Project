package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * An instance of this represents an event in the system. The event can
 * have at most 1 speaker and 2 non-speakers attending the event
 */
public class Event implements Serializable {
    private String eventName;
    private int roomNumber;
    private int id;
    private LocalDateTime eventTime;
    public ArrayList<Integer> attendees;
    private int organizer;
    private int speaker;
    private int duration;

    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *
     * @param name            name of the event
     * @param num             room number of the event
     * @param time            time of the event
     * @param event_organizer organizer of the event
     * @param duration        the duration of the event in minutes
     */
    public Event(String name, int num, LocalDateTime time,
                 int event_organizer, int duration) {
        this.eventName = name;
        this.roomNumber = num;
        this.eventTime = time;
        this.attendees = new ArrayList<>();
        this.organizer = event_organizer;
        this.duration = duration;
    }

    /**
     * Returns the name of the event
     *
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the event's room number
     *
     * @return roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the time of the event
     *
     * @return eventTime
     */
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    /**
     * Change the room number of the event
     *
     * @param roomNumber A room number for the event
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Change the time of the event
     *
     * @param event_time A time for the event
     */
    public void setEventTime(LocalDateTime event_time) {
        this.eventTime = event_time;
    }

    /**
     * Return the attendees signed up for this event
     *
     * @return attendees
     */
    public ArrayList<Integer> getAttendees() {
        return attendees;
    }

    /**
     * Return the organizer of the event
     *
     * @return organizer of the event
     */
    public int getOrganizer() {
        return organizer;
    }

    /**
     * Checks if the event has a speaker
     *
     * @return boolean of if the speaker is in the event
     */
    public boolean hasSpeaker() {
        return !(speaker == 0);
    }

    /**
     * Return the speaker of the event
     *
     * @return speaker of the event
     */
    public int getSpeaker() {
        return speaker;
    }

    /**
     * Return the duration of the event
     *
     * @return the duration of the event
     */
    public int getDuration(){return duration;}

    /**
     * Change the speaker of the event
     *
     * @param speaker speaker of the event
     */
    public void setSpeaker(int speaker) {
        this.speaker = speaker;
    }

    /**
     * Add an attendee to the event, checks if the attendee is already signed up first
     *
     * @param attendee Attendee user class
     * @return boolean, check if the attendee is in the event or not
     */
    public boolean add(User attendee) {
        if (attendees.contains(attendee.getId())) {
            return false;
        }
        attendees.add(attendee.getId());
        return true;
    }

    /**
     * Remove an attendee from the event
     *
     * @param attendee Attendee user class
     * @return boolean, check if the attendee is in the event or not
     */
    public boolean remove(User attendee) {
        if (!attendees.contains(attendee.getId())) {
            return false;
        }
        attendees.remove(Integer.valueOf(attendee.getId()));
        return true;
    }

    /**
     * Returns the name, time, and speaker for the event
     *
     * @return Return the event name with the time and speaker in a string
     */
    @Override
    public String toString() {
        return eventName + " " + eventTime + " : " + speaker;
    }

}
