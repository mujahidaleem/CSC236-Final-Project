package Entities.Events;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MultiSpeakerEvent extends Event {
    ArrayList<Integer> speakers;

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
    public MultiSpeakerEvent(int id, String name, int num, int maxCapacity, LocalDateTime time, int duration, int event_organizer) {
        super(id, name, num, maxCapacity, time, duration, event_organizer);
        this.speakers = new ArrayList<>();
    }

    @Override
    public int getTotalNum() {
        //TODO:
        return 0;
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
        //TODO:
        return true;
    }

    @Override
    public int getSpeaker() {
        //TODO:
        return 0;
    }
}
