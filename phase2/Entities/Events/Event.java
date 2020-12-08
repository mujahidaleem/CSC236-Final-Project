package Entities.Events;

import Entities.Users.Speaker;
import Entities.Users.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

/**
 * An instance of this represents an event in the system. The event can
 * have at most 1 speaker and 2 non-speakers attending the event
 */
public class Event implements Serializable {
    private String eventName;
    private int roomNumber;
    private LocalDateTime eventTime;
    public ArrayList<Integer> attendees;
    private int organizer;
    private int maxCapacity;
    private int duration;

    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *
     * @param name            name of the event
     * @param num             room number of the event
     * @param time            time of the event
     * @param event_organizer organizer of the event
     */
    public Event(String name, int num, int maxCapacity, LocalDateTime time, int duration,
                 int event_organizer, ArrayList<Integer> attendees) {
        this.eventName = name;
        this.roomNumber = num;
        this.eventTime = time;
        this.duration = duration;
        this.maxCapacity = maxCapacity;
        this.attendees = attendees;
        this.organizer = event_organizer;
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
    public int getDuration(){
        return duration;
    }

    /**
     * Returns the maximum capacity of the event.
     * @return maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
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
     * Returns the time of when the event ends.
     * @return the event time plus the duration of the event
     */
    public LocalDateTime getEndEventTime(){
        return eventTime.plusMinutes(duration);
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
     * Returns the total number of people that will be present in the event.
     * @return
     */
    public int getTotalNum(){
        return attendees.size();
    }

    /**
     * Changes the maximum capacity of the event
     * @param new_maxCapacity the new maximum capacity
     */
    public void setMaxCapacity(int new_maxCapacity){
        maxCapacity = new_maxCapacity;
    }

    /**
     * Changes the duration of the event
     * @param newDuration the new duration of the event
     */
    public void setDuration(int newDuration){
        duration = newDuration;
    }

    /**
     * Checks if the event has a speaker
     *
     * @return boolean of if the speaker is in the event
     */
    public boolean hasSpeaker() {
        return false;
    }

    /**
     * Return the speaker of the event
     *
     * @return speaker of the event
     */
    public int getSpeaker() {
        return 0;
    }

    /**
     * Change the speaker of the event
     *
     * @param speaker speaker of the event
     */
    public boolean addSpeaker(int speaker) {
        return false;
    }

    public boolean removeSpeaker(int speaker){
        return false;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return eventName + " " + eventTime.format(formatter);
    }

}
