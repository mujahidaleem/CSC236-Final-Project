package Entities.Events;

import java.util.ArrayList;

public interface SpeakerModifiable {
    public void addSpeaker(int speakerId);

    public void removeSpeaker(int speakerId);

    public ArrayList<Integer> getSpeaker();
}
