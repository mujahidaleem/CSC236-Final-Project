package Entities;

import Entities.Attendee;
import Entities.Organizer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Event(int id, String name, int num, LocalDateTime time,
                 int event_organizer){
        this.id = id;
        eventName = name;
        roomNumber = num;
        eventTime = time;
        attendees = new ArrayList<Integer>();
        organizer = event_organizer;
    }

    public String getEventName(){
        return eventName;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public LocalDateTime getEventTime(){
        return eventTime;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setEventTime(LocalDateTime event_time) {
        this.eventTime = event_time;
    }

    public ArrayList<Integer> getAttendees(){
        return attendees;
    }

    public int getOrganizer(){
        return organizer;
    }

    public boolean hasSpeaker(){return !(speaker == 0);}

    public int getSpeaker() {
        return speaker;
    }

    public void setSpeaker(int speaker) {
        this.speaker = speaker;
    }

    public boolean add(User attendee){
        if (attendees.contains(attendee.getId())){
            return false;
        }
        attendees.add(attendee.getId());
        return true;
    }

    public boolean remove(User attendee){
        if (!attendees.contains(attendee.getId())){
            return false;
        }
        attendees.remove(new Integer(attendee.getId()));
        return true;
    }

    @Override
    public String toString(){
        return eventName + " " + eventTime + " with speaker " + speaker;
    }
}
