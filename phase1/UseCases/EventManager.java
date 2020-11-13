package UseCases;

import Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventManager implements Serializable {
    private ArrayList<Event> events;

    public EventManager(ArrayList<Event> events){
        this.events = events;
    }

    public boolean addAttendee(Event event, User attendee){
        if (event.getAttendees().size() < 2) {
            if (!event.getAttendees().contains(attendee.get_id())) {
                event.add(attendee);
                return true;
            }
        }
        return false;
    }

    public boolean removeAttendee(Event event, Attendee attendee){
        return event.remove(attendee);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public int getOrganizer(Event event){
        return event.getOrganizer();
    }

    public Event createEvent(String eventName, int roomNumber,
                                LocalDateTime startDateTime, Organizer organizer){
        for (Event event : events){
            if ((startDateTime.isAfter(event.getEventTime()) &&
                    startDateTime.isBefore(event.getEventTime().plusMinutes(59)) &&
                    event.getRoomNumber() == roomNumber)){
                return null;
            }
        }
        Event e = new Event(eventName, roomNumber, startDateTime,organizer.get_id());
        events.add(e);
        organizer._eventsOrganizing.put(eventName, startDateTime);
        return e;
    }

    public ArrayList<Event> getEvents(){
        return events;
    }

    public void removeSpeaker(Event event){
        event.setSpeaker(0);
    }

    public boolean changeDate(Event event, LocalDateTime date){
        if(event.getEventTime().isAfter(LocalDateTime.now()) && !date.isBefore(LocalDateTime.now())){
            event.setEventTime(date);
            return true;
        } else{
            return false;
        }
    }
    public Event findEvent(String name){
        for (Event event : events) {
            if (event.getEventName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    public void addUser(Event event, User user){
        event.getAttendees().add(user.get_id());
    }

    public boolean removeUser(User user, Event event){
        if(event.getAttendees().contains(user.get_id())){
            event.getAttendees().remove(user.get_id());
            return true;
        } else{
            return false;
        }
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

    public void setSpeaker(Speaker speaker, Event event){
        event.setSpeaker(speaker.get_id());
    }

    public boolean hasSpeaker(Event event){
        return event.hasSpeaker();
    }

}