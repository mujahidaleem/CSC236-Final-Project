package Entities;

import Entities.Attendee;
import Entities.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Time, and users are contained in event
public class Event {
    String eventName;
    int roomNumber;
    int id;
    LocalDateTime eventTime;
    ArrayList<Integer> attendees;
    int organizer;
    int speaker;

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
        attendees.remove(attendee.getId());
        return true;
    }
}
