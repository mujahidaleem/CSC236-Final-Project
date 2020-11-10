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
    ArrayList<Attendee> attendees;
    Organizer organizer;

    public Event(String name, int num, LocalDateTime time,
                 Organizer event_organizer){
        event_name = name;
        room_num = num;
        event_time = time;
        attendees = new ArrayList<Attendee>();
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

    public ArrayList<Attendee> getAttendees(){
        return attendees;
    }

    public Organizer getOrganizer(){
        return organizer;
    }

    public boolean add(Attendee attendee){
        if (attendees.contains(attendee)){
            return false;
        }
        attendees.add(attendee);
        return true;
    }

    public boolean remove(Attendee attendee){
        if (!attendees.contains(attendee)){
            return false;
        }
        attendees.remove(attendee);
        return true;
    }
}
