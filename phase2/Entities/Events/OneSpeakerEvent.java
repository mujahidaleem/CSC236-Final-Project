package Entities.Events;

import Entities.Users.Speaker;

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
    public OneSpeakerEvent(String name, int num, int maxCapacity, LocalDateTime time, int duration, int eventOrganizer) {
        super(name, num, maxCapacity, time, duration, eventOrganizer);
    }

    @Override
    public int getTotalNum() {
        //TODO:
        return 0;
    }

    @Override
    public boolean addSpeaker(int speaker) {
        //TODO:
        return true;
    }

    @Override
    public boolean removeSpeaker(int speaker){
        //TODO:
        return true;
    }

    @Override
    public boolean hasSpeaker() {
        //TODO:
        return true;
    }

    @Override
    public int getSpeaker() {
        //TODO:
        return 0;
    }
}
