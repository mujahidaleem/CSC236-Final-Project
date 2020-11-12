package UseCases;

import Entities.Event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entities.Attendee;
import Entities.User;
import Entities.Organizer;

public class EventManager implements Serializable {
    private ArrayList<Event> events;

    public EventManager(ArrayList<Event> events){
        this.events = events;
    }

    public boolean add_attendee(Event event, User attendee){
        if (event.getAttendees().size() >= 2) {
            return false;
        } else {
            return event.add(attendee);
        }
    }

    public boolean remove_attendee(Event event, Attendee attendee){
        return event.remove(attendee);
    }

    public int getOrganizer(Event event){
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
        Event e = new Event(event_name, room_num, start_date_time,organizer.get_id());
        events.add(e);
        return true;
    }

    public ArrayList<Event> get_events(){
        return events;
    }

    public void removeSpeaker(Event event){
        event.setSpeaker(0);
    }

    public boolean eventDateChangeable(Event event, LocalDateTime date){
        return event.getEvent_time().isAfter(LocalDateTime.now()) && !date.isBefore(LocalDateTime.now());
    }

    public void changeDate(Event event, LocalDateTime date){
        event.setEvent_time(date);
    }

    public Event findEvent(String name){
        for (Event event : events) {
            if (event.getEvent_name().equals(name)) {
                return event;
            }
        }
        return null;
    }
}