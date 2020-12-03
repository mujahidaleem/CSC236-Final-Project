package UseCases.Users;

import Entities.Events.Event;
import Entities.Users.Organizer;
import Entities.Users.Speaker;

import java.time.LocalDateTime;
import java.util.List;

/**
 * An instance of this stores all the organizers
 */
public class OrganizerManager extends UserManager {
    private Organizer currentOrganizer;
    public List<Organizer> organizers;

    /**
     * OrganizerManager constructor
     *
     * @param currentUser the organizer that is logged in
     * @param organizers  the list of all organizers
     */
    public OrganizerManager(Organizer currentUser, List<Organizer> organizers) {
        super(currentUser);
        this.currentOrganizer = currentUser;
        this.organizers = organizers;
    }

    /**
     * Getter for currentOrganizer
     *
     * @return the currentOrganizer
     */
    public Organizer getCurrentOrganizer() {
        return currentOrganizer;
    }

    /**
     * Creates a new speaker account with the given name and password
     *
     * @param name           name of the speaker
     * @param password       password of the speaker
     * @param speakerManager stores a list of speakers
     * @param userManager    stores a list of users
     * @return a new speaker account
     */
    public Speaker createSpeaker(String name, String password, SpeakerManager speakerManager, UserManager userManager) {
        if (password.contains(" ")) {
            return null;
        } else {
            Speaker speaker = new Speaker(userManager.users.size() + 1000, name, password, null, null, null);
            speakerManager.speakers.add(speaker);
            userManager.users.add(speaker);
            return speaker;
        }
    }

    /**
     * Setter for currentOrganizer
     *
     * @param organizer the new currentOrganizer
     */
    public void setCurrentOrganizer(Organizer organizer){
        this.currentOrganizer = organizer;
    }

    /**
     * If the event is organized by the current organizer, remove the event from the list of events and from all
     * attendee's schedule.
     *
     * @param event the event that the organizer is trying to remove
     * @return whether the event has been removed or not
     */
    public boolean cancelEvent(Event event) {
        if (currentOrganizer._eventsOrganizing.containsKey(event.getEventName()) &&
                event.getEventTime().isAfter(LocalDateTime.now())) {
            currentOrganizer._eventsOrganizing.remove(event.getEventName());
            return true;
        } else {
            return false;
        }
    }
}
