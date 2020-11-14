package UseCases;

import Entities.Event;
import Entities.Organizer;
import Entities.Speaker;

import java.time.LocalDateTime;
import java.util.List;

public class OrganizerManager extends UserManager {
    private Organizer currentOrganizer;
    public List<Organizer> organizers;

    /**
     * OrganizerManager constructor
     * @param currentUser the organizer that is logged in
     * @param organizers the list of all organizers
     */
    public OrganizerManager(Organizer currentUser, List<Organizer> organizers){
        super(currentUser);
        this.currentOrganizer = currentUser;
        this.organizers = organizers;
    }

    /**
     * Getter for currentOrganizer
     * @return the currentOrganizer
     */
    public Organizer getCurrentOrganizer(){
        return currentOrganizer;
    }

    /**
     * Checks if an event is creatable
     * @param name the name of the new event
     * @param date when the new event will happen
     * @param organizer the organizer that is trying to create this event
     * @return whether or not the event can be created
     */
    public boolean eventCreatable(String name, LocalDateTime date, Organizer organizer){
        return !organizer._eventsOrganizing.containsKey(name) && !organizer._eventsOrganizing.containsValue(date);
    }

    /**
     * Creates a new speaker account with the given name and password
     * @param name name of the speaker
     * @param password password of the speaker
     * @param speakerManager stores a list of speakers
     * @param userManager stores a list of users
     * @return a new speaker account
     */
    public Speaker createSpeaker(String name, String password, SpeakerManager speakerManager, UserManager userManager){
        Speaker speaker = new Speaker(userManager.users.size(), name, password, null, null, null);
        speakerManager.speakers.add(speaker);
        userManager.users.add(speaker);
        return speaker;
    }

    /**
     * If the event is organized by the current organizer, remove the event from the list of events and from all
     * attendee's schedule.
     * @param event the event that the organizer is trying to remove
     * @return whether the event has been removed or not
     */
    public boolean cancelEvent(Event event){
        if (currentOrganizer._eventsOrganizing.containsKey(event.getEventName()) &&
                event.getEventTime().isAfter(LocalDateTime.now())){
            currentOrganizer._eventsOrganizing.remove(event.getEventName());
            return true;
        } else {
            return false;
        }
    }
}
