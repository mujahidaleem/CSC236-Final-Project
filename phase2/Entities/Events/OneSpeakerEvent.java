package Entities.Events;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class OneSpeakerEvent extends Event {
    private int speaker;
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
    public OneSpeakerEvent(String name, int num, int maxCapacity, LocalDateTime time, int duration, int eventOrganizer,
                           ArrayList<Integer> attendees, int speaker) {
        super(name, num, maxCapacity, time, duration, eventOrganizer, attendees);
        this.speaker = speaker;
    }

    /**
     * returns the total number of individuals attending this event
     * @return the total number of individuals attending this event
     */
    @Override
    public int getTotalNum() {
        if(hasSpeaker()){
            return attendees.size() + 1;
        } else {
            return attendees.size();
        }
    }

    /**
     * tries to add a speaker to an event
     * @param speakerId the id of a speaker
     * @return if the speaker has been added
     */
    @Override
    public boolean addSpeaker(int speakerId) {
        speaker = speakerId;
        return true;
    }

    /**
     * tries to remove a speaker from an event
     * @param speakerId the id of the speaker
     * @return if the speaker has been removed
     */
    @Override
    public boolean removeSpeaker(int speakerId){
        if (speakerId == speaker){
            speaker = 0;
            return true;
        }
        return false;
    }

    /**
     * checks if the event has a speaker
     * @return if the event has a speaker
     */
    @Override
    public boolean hasSpeaker() {
        return speaker != 0;
    }

    /**
     * Gets a list of speakers of this event, there should only be 1 speaker
     * @return an array of 1 or 0 speakers
     */
    @Override
    public ArrayList<Integer> getSpeakers() {
        ArrayList<Integer> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }
}
