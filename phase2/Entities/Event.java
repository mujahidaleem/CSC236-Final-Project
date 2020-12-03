package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private ArrayList<Integer> speakers;
    private int maxCapacity;
    private int duration;

    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *
     * @param id              id of the event
     * @param name            name of the event
     * @param num             room number of the event
     * @param time            time of the event
     * @param event_organizer organizer of the event
     */
    public Event(int id, String name, int num, int maxCapacity, LocalDateTime time, int duration,
                 int event_organizer) {
        this.id = id;
        this.eventName = name;
        this.roomNumber = num;
        this.eventTime = time;
        this.duration = duration;
        this.maxCapacity = maxCapacity;
        this.attendees = new ArrayList<>();
        this.speakers = new ArrayList<>();
        this.organizer = event_organizer;
    }


    /**
     * Get id of the event
     *
     * @return id of the event
     */
    public int getId() {
        return id;
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

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public LocalDateTime getEndEventTime(){
        return eventTime.plusMinutes(duration);
    }

    public ArrayList<LocalDateTime> getEventTimeRange(){
        ArrayList<LocalDateTime> t = new ArrayList<>();
        t.add(getEventTime());
        t.add(getEndEventTime());
        return t;
    }

    /**
     * Change the room number of the event
     *
     * @param roomNumber A roomnumber for the event
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

    public int getTotalNum(){
        return speakers.size() + attendees.size();
    }

    /**
     * Checks if the event has a speaker
     *
     * @return boolean of if the speaker is in the event
     */

    public void setMaxCapacity(int new_maxCapacity){
        maxCapacity = new_maxCapacity;
    }

    public void setDuration(int new_duration){
        duration = new_duration;
    }

    /**
     * checks if this event has any speakers
     *
     *
     * @return boolean: if a there are any speaker(s) in this event
     */

    public boolean hasSpeaker(){
        return (!speakers.isEmpty());
    };


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

}
