package Entities;

import java.time.LocalDateTime;

public class AttendeeOnlyEvent extends Event{
    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *

     * @param id              id of the event
     * @param name            name of the event
     * @param num             room number of the event
     * @param maxCapacity
     * @param time            time of the event
     * @param duration
     * @param event_organizer organizer of the event
     */
    public AttendeeOnlyEvent(int id, String name, int num, int maxCapacity, LocalDateTime time, int duration, int event_organizer) {
        super(id, name, num, maxCapacity, time, duration, event_organizer);
    }
}
