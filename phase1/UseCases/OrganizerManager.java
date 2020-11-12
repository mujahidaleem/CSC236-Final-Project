package UseCases;

import Entities.Event;
import Entities.Organizer;
import Entities.Speaker;

import java.time.LocalDateTime;
import java.util.List;

public class OrganizerManager extends UserManager {
    public Organizer currentOrganizer;
    public List<Organizer> organizers;

    public OrganizerManager(Organizer currentUser, List<Organizer> organizers){
        super(currentUser);
        this.currentOrganizer = currentUser;
        this.organizers = organizers;
    }

    public boolean eventCreatable(String name, LocalDateTime date, Organizer organizer){
        return !organizer._eventsOrganizing.containsKey(name) && !organizer._eventsOrganizing.containsValue(date);
    }

    public void createEvent(Organizer organizer, Event event){
        organizer._eventsOrganizing.put(event.getEvent_name(), event.getEvent_time());
    }

    public void createSpeaker(int id, String name, String password, SpeakerManager speakerManager, UserManager userManager){
        Speaker newSpeaker = new Speaker(id, name, password, null, null, null);
        userManager.users.add(newSpeaker);
        speakerManager.speakers.add(newSpeaker);
    }
}
