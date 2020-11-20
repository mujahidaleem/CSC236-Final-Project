package UseCases;

import Entities.Event;
import Entities.Speaker;

import java.time.LocalDateTime;
import java.util.List;

/**
 * An instance of this stores all of the speakers
 */
public class SpeakerManager extends UserManager {
    private Speaker currentSpeaker;
    public List<Speaker> speakers;

    /**
     * SpeakerManager Constructor
     *
     * @param currentUser stores the current speaker
     * @param speakers    stores a list of all speakers
     */
    public SpeakerManager(Speaker currentUser, List<Speaker> speakers) {
        super(currentUser);
        this.currentSpeaker = currentUser;
        this.speakers = speakers;
    }

    /**
     * Returns the current speaker
     *
     * @return currentSpeaker
     */
    public Speaker getCurrentSpeaker() {
        return currentSpeaker;
    }

    /**
     * Returns the list of speakers
     *
     * @return speakers
     */
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    /**
     * Checks if the a specific speaker is available at a given time
     *
     * @param speaker contains an instance of speaker
     * @param date    contains the specified time
     * @return returns if the speaker is free during the given time
     */
    public boolean available(Speaker speaker, LocalDateTime date) {
        return !speaker.getSpeakingSchedule().containsValue(date) || !speaker.getPersonalSchedule().containsValue(date);
    }

    /**
     * Checks if a change in time for an event will affect the speaker assigned to the event
     *
     * @param event stores an instance of Event
     * @param date  the date in which the event will be changed to
     * @return returns true if the speaker is free on the new date and false otherwise
     */
    public boolean dateChangeable(Event event, LocalDateTime date) {
        if (event.hasSpeaker()) {
            return available(findSpeaker(event.getSpeaker()), date);
        } else {
            return true;
        }
    }

    /**
     * Changes the date on the schedule of the speaker assigned to an event
     *
     * @param event the event that is having its date changed
     * @param date  the new date of the event
     */
    public void changeDate(Event event, LocalDateTime date) {
        Speaker speaker = findSpeaker(event.getSpeaker());
        speaker._speakingSchedule.replace(event.getEventName(), date);
    }

    /**
     * Assigns a speaker to an event
     *
     * @param speaker the speaker that will be assigned to the event
     * @param event   the even in which the speaker is being assigned to
     */
    public void setSpeaker(Speaker speaker, Event event) {
        speaker._speakingSchedule.put(event.getEventName(), event.getEventTime());
    }

    /**
     * Removes an event from the schedule of the speaker that was speaking at it
     *
     * @param event the event that is being removed
     */
    public void removeEvent(Event event) {
        findSpeaker(event.getSpeaker())._speakingSchedule.remove(event.getEventName());
    }

    /**
     * Finds the speaker that has the same name as the given string
     *
     * @param id A string of the name of a speaker.
     * @return returns a speaker if a speaker with the same name exists, otherwise will return null
     */
    public Speaker findSpeaker(int id) {
        for (Speaker speaker : speakers) {
            if (speaker.getId() == (id)) {
                return speaker;
            }
        }
        return null;
    }
}
