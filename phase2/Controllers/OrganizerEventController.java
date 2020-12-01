package Controllers;

import Entities.Event;
import Entities.Speaker;
import UseCases.*;

import java.time.LocalDateTime;

public class OrganizerEventController extends EventMenuController {
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;

    /**
     * OrganizerEventController constructor
     *
     * @param manager        contains the organizer using the current session
     * @param eventManager   contains the list of events
     * @param userManager    contains the list of users
     * @param speakerManager contains the list of speakers
     */
    public OrganizerEventController(OrganizerManager manager,
                                    EventManager eventManager, UserManager userManager, SpeakerManager speakerManager) {
        super(userManager, eventManager);
        this.organizerManager = manager;
        this.speakerManager = speakerManager;
    }

    /**
     * Tries to create an event and add it to the list of events.
     *
     * @param name       the name of the new event
     * @param date       when the new event will happen
     * @param roomNumber where the new event will happen
     * @return whether or not the event can be created
     */
    public Event createEvent(String name, LocalDateTime date, int roomNumber, int duration) throws SameEventNameException {
        return eventManager.createEvent(name, date, organizerManager.getCurrentOrganizer(), roomNumber, duration);
    }

    /**
     * Checks if the chosen speaker can be assigned to an event
     *
     * @param event   the event that the organizer is trying to assign the speaker to
     * @param speaker the speaker that the organizer is trying to assign to an event
     * @return whether or not the speaker can be assigned to the event
     */
    public boolean assignSpeaker(Event event, int speaker) throws NullSpeakerException {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        if (speaker1 == null) {
            throw new NullSpeakerException();
        } else {
            if (speakerManager.available(speaker1, event.getEventTime())) {
                eventManager.setSpeaker(speaker1, event);
                speakerManager.setSpeaker(speaker1, event);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Checks if the speaker of an event can be removed and removes the speaker
     *
     * @param event the event that the organizer is trying to remove the speaker from
     * @return whether or not the speaker has been removed
     */
    public boolean removeSpeaker(Event event) {
        if (eventManager.hasSpeaker(event)) {
            speakerManager.removeEvent(event);
            eventManager.removeSpeaker(event);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the date of an event can be changed and if so, changes the event
     *
     * @param event the event that the organizer is trying to change the date
     * @param date  the new date of the event
     * @return whether or not the date of the event has been changed
     */
    public boolean changeEventDate(Event event, LocalDateTime date) {
        if (speakerManager.dateChangeable(event, date) && eventManager.changeDate(event, date)) {
            speakerManager.changeDate(event, date);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the room of an event can be changed and if so, changes the event room
     *
     * @param event      the event that the organizer is trying to change the date
     * @param roomNumber the new room for the event
     * @return whether or not the room of the event has been changed
     */
    public boolean changeEventRoom(Event event, int roomNumber) {
        return eventManager.changeRoom(event, roomNumber);
    }

    /**
     * Checks if the event can be deleted and if so, deletes the event and removes if from all user's schedule
     *
     * @param event the event that the organizer is trying to delete
     * @return whether or not the event has been deleted
     */
    public boolean deleteEvent(Event event) {
        if (organizerManager.cancelEvent(event)) {
            userManager.deleteEvent(event);
            eventManager.getEvents().remove(event);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if an event is an event is being organized by the current organizer
     *
     * @param event the event that is being checked
     * @return if the current organizer is organizing this event
     */
    public boolean eventModifiable(Event event) {
        return event.getOrganizer() == organizerManager.getCurrentOrganizer().getId();
    }

    /**
     * Checks if a speaker account is creatable and if so, creates a speaker account
     *
     * @param name     the name of the new speaker account
     * @param password the password of the new speaker account
     * @return null if the speaker cannot be created, otherwise it returns the new speaker account
     */
    public Speaker createSpeaker(String name, String password) {
        return organizerManager.createSpeaker(name, password, speakerManager, userManager);
    }
}
