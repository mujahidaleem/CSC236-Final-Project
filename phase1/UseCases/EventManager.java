import java.time.LocalDateTime;
import java.util.ArrayList;
import Entities.Attendee;
import Entities.Organizer;
import com.sun.org.apache.xpath.internal.operations.Or;

public class EventManager {
    private ArrayList<Event> events;

    public EventManager(){
        events = new ArrayList<Event>();
    }

    public boolean add_attendee(Event event, Attendee attendee){
        return event.add(attendee);
    }

    public boolean remove_attendee(Event event, Attendee attendee){
        return event.remove(attendee);
    }

    public Organizer getOrganizer(Event event){
        return event.getOrganizer();
    }

    public boolean create_event(String event_name, int room_num,
                                LocalDateTime start_date_time, Organizer organizer){
        for (Event event : events){
            int rn = event.getRoom_num();
            LocalDateTime e_start_time = event.getEvent_time();
            LocalDateTime e_end_time = event.getEvent_time().plusMinutes(59);

            if (rn == room_num && (start_date_time.isAfter(e_start_time)
                    && start_date_time.isBefore(e_end_time))){
                return false;
            }
        }
        Event e = new Event(event_name, room_num, start_date_time,organizer);
        events.add(e);
        return true;
    }

    public ArrayList<Event> get_events(){
        return events;}
}