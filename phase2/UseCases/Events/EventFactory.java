package UseCases.Events;

import Entities.Events.AttendeeOnlyEvent;
import Entities.Events.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Events.OneSpeakerEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventFactory implements Serializable {

    /**
     * Constructor for EventFactory
     */
    public EventFactory(){}

    /**
     * Creates a new event of the required type
     * @param name the name of the event
     * @param roomNumber where the event will take place
     * @param maxCapacity how many people can be in the event
     * @param time when the event will happen
     * @param duration how long the event will run
     * @param organizer the organizer of the event
     * @param type the type of the event
     * @return the new event
     */
    public Event createEvent(String name, int roomNumber, int maxCapacity, LocalDateTime time, int duration, int organizer, String type, ArrayList<Integer> attendees,ArrayList<Integer> speakers){
        switch (type) {
            case "attendeeOnlyEvent":
                return new AttendeeOnlyEvent(name, roomNumber, maxCapacity, time, duration, organizer, attendees);
            case "oneSpeakerEvent":
                return new OneSpeakerEvent(name, roomNumber, maxCapacity, time, duration, organizer, attendees, speakers.get(0));
            case "multipleSpeakerEvent":
                return new MultiSpeakerEvent(name, roomNumber, maxCapacity, time, duration, organizer, attendees, speakers);
            default:
                return null;
        }
    }
}
