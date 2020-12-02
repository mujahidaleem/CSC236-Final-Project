package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OneSpeakerEvent extends Event implements SpeakerModifiable{
    ArrayList<Integer> speaker;
    /**
     * Event Constructor
     * An event carries a unique id, name, room number, time, attendee list and organizer
     *
     * @param id              id of the event
     * @param name            name of the event
     * @param num             room number of the event
     * @param maxCapacity
     * @param time            time of the event
     * @param duration
     * @param event_organizer organizer of the event
     */
    public OneSpeakerEvent(int id, String name, int num, int maxCapacity, LocalDateTime time, int duration, int event_organizer) {
        super(id, name, num, maxCapacity, time, duration, event_organizer);
        this.speaker = new ArrayList<>();
    }

    @Override
    public void addSpeaker(int speakerId) {
        speaker.add(speakerId);
    }


    @Override
    public ArrayList<Integer> getSpeaker() {
        return speaker;
    }

    @Override
    public void removeSpeaker(int speakerId) {
        speaker.remove(0);
    }


}
