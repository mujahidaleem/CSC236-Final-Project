package Entities;

import Entities.Attendee;
import Entities.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

//Time, and users are contained in event
public class Event {
    String event_name;
    int event_id;
    int room_num;
    LocalDateTime event_time;
    ArrayList<Integer> attendees;
    int organizer;
    int speaker;

    public Event(String name, int num, LocalDateTime time,
                 int event_organizer){
        event_name = name;
        room_num = num;
        event_time = time;
        attendees = new ArrayList<Integer>();
        organizer = event_organizer;
    }

    public String getEvent_name(){
        return event_name;
    }

    public int getId(){
        return event_id;
    }

    public int getRoom_num(){
        return room_num;
    }

    public LocalDateTime getEvent_time(){
        return event_time;
    }

    public void setEvent_time(LocalDateTime event_time) {
        this.event_time = event_time;
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
        if (attendees.contains(attendee.get_id())){
            return false;
        }
        attendees.add(attendee.get_id());
        return true;
    }

    public boolean remove(User attendee){
        if (!attendees.contains(attendee.get_id())){
            return false;
        }
        attendees.remove(attendee.get_id());
        return true;
    }
}
