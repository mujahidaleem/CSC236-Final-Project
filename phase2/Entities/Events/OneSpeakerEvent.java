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

    @Override
    public int getTotalNum() {
        return attendees.size() + 1;
    }

    @Override
    public boolean addSpeaker(int speakerId) {
        speaker = speakerId;
        return true;
    }

    @Override
    public boolean removeSpeaker(int speakerId){
        if (speakerId == speaker){
            speaker = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasSpeaker() {
        return speaker != 0;
    }

    @Override
    public int getSpeaker() {
        return speaker;
    }
}
