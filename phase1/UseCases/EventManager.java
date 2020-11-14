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
            if (!event.getAttendees().contains(attendee.getId())) {
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

    public Event createEvent(String name, LocalDateTime date, Organizer organizer, int roomNumber){
        for(Event event:events){
            if ((event.getEventTime().isEqual(date) && event.getRoomNumber() == roomNumber)){
                return null;
            }
        }
        Event newEvent = new Event(events.size(), name, roomNumber, date, organizer.getId());
        events.add(newEvent);
        organizer._eventsOrganizing.put(name, date);
        return newEvent;
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
        event.getAttendees().add(user.getId());
    }

    public boolean removeUser(User user, Event event){
        if(event.getAttendees().contains(user.getId())){
            event.getAttendees().remove(user.getId());
            return true;
        } else{
            return false;
        }
    }
    public boolean userCanSignUp(User user, Event event) {
        if (event.getAttendees().size() >= 2) {
            return false;
        } else {
            return !event.getAttendees().contains(user.getId());
        }
    }

    public boolean userCanLeave(User user, Event event){
        return event.getAttendees().contains(user.getId());
    }

    public void setSpeaker(Speaker speaker, Event event){
        event.setSpeaker(speaker.getId());
    }

    public boolean hasSpeaker(Event event){
        return event.hasSpeaker();
    }

    public boolean changeRoom(Event event, int roomNumber){
        for(Event e: events){
            if (e.getRoomNumber() == roomNumber && e.getEventTime().isEqual(event.getEventTime())){
                return false;
            }
        }
        event.setRoomNumber(roomNumber);
        return true;
    }
}