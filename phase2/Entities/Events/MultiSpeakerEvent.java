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

    @Override
    public int getTotalNum() {
        return this.speakers.size() + this.attendees.size();
    }

    @Override
    public boolean addSpeaker(int speaker) {
        if(speakers.contains(speaker)){
            return false;
        } else {
            speakers.add(speaker);
            return true;
        }
    }

    @Override
    public boolean removeSpeaker(int speaker){
        if (speakers.contains(speaker)){
            speakers.remove(speaker);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> getSpeakers() {
        return speakers;
    }

    @Override
    public boolean hasSpeaker() {
        if(!speakers.isEmpty()) {
            return true;
        }
        return false;
    }

    public int getSpeaker(int speakerId) {
        for (int i:speakers){
            if (i == speakerId){
                return i;
            }
        }
        return 0;
    }
}
