package Entities.Events;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AttendeeOnlyEvent extends Event{
    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *

     * @param name            name of the event
     * @param num             room number of the event
     * @param maxCapacity
     * @param time            time of the event
     * @param duration
     * @param event_organizer organizer of the event
     */
    public AttendeeOnlyEvent(String name, int num, int maxCapacity, LocalDateTime time, int duration, int event_organizer, ArrayList<Integer> attendees) {
        super(name, num, maxCapacity, time, duration, event_organizer, attendees);
    }
}
