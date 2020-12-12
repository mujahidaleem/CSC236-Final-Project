package Entities.Events;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MultiSpeakerEvent extends Event {
    ArrayList<Integer> speakers;

    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *
     * @param name            name of the event
     * @param num             room number of the event
     * @param maxCapacity
     * @param time            time of the event
     * @param duration
     * @param eventOrganizer organizer of the event
     */
    public MultiSpeakerEvent(String name, int num, int maxCapacity, LocalDateTime time, int duration,
                             int eventOrganizer, ArrayList<Integer> attendees, ArrayList<Integer> speakers) {
        super(name, num, maxCapacity, time, duration, eventOrganizer, attendees);
        this.speakers = speakers;
    }

    /**
     * Get the total number of individuals in the meeting
     * @return the total number of individuals
     */
    @Override
    public int getTotalNum() {
        return this.speakers.size() + this.attendees.size();
    }

    /**
     * tries to add a speaker to an event
     * @param speaker speaker of the event
     * @return if the speaker has been added to the event
     */
    @Override
    public boolean addSpeaker(int speaker) {
        if(speakers.contains(speaker)){
            return false;
        } else {
            speakers.add(speaker);
            return true;
        }
    }

    /**
     * tries to remove a speaker from an event
     * @param speaker the speaker
     * @return if the spaker has been removed
     */
    @Override
    public boolean removeSpeaker(int speaker){
        if (speakers.contains(speaker)){
            speakers.remove(speaker);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets all the speakers of the event
     * @return a list of speakers
     */
    @Override
    public ArrayList<Integer> getSpeakers() {
        return speakers;
    }

    /**
     * Checks if the event has any speakers
     * @return if the envet has any speakers
     */
    @Override
    public boolean hasSpeaker() {
        if(!speakers.isEmpty()) {
            return true;
        }
        return false;
    }
}
