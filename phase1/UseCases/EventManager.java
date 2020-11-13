package UseCases;

import Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void addUser(Event event, User user){
        event.getAttendees().add(user.get_id());
    }

    public void removeUser(Event event, User user){
        event.getAttendees().remove(new Integer(user.get_id()));
    }

    public boolean userCanSignUp(User user, Event event) {
        if (event.getAttendees().size() >= 2) {
            return false;
        } else {
            return !event.getAttendees().contains(user.get_id());
        }
    }

    public boolean userCanLeave(User user, Event event){
        return event.getAttendees().contains(user.get_id());
    }

    public boolean eventCreatable(String name, LocalDateTime date, Organizer organizer, int roomNumber, int id){
        for(Event e:events){
            if (e.getId() == id || (e.getEvent_time().isEqual(date) && e.getRoom_num() == roomNumber)){
                return false;
            }
        }
        return true;
    }

    public void setSpeaker(Speaker speaker, Event event){
        event.setSpeaker(speaker.get_id());
    }

    public boolean hasSpeaker(Event event){
        return event.hasSpeaker();
    }

}