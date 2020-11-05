package src.UseCases;
import src.Entities.Speaker;
import src.Entities.Event;

import java.time.LocalDateTime;
import java.util.List;

public class SpeakerManager extends UserManager{
    public List<Speaker> speakers;

    public SpeakerManager(List<Speaker> speakers){
        this.speakers = speakers;
    }

    public boolean available(Speaker speaker, LocalDateTime date){
        return !speaker._speakingSchedule.containsValue(date) || !speaker._personalSchedule.containsValue(date);
    }

    public boolean dateChangeable(Event event, LocalDateTime date){
        if (event.hasSpeaker()){
            return available(findSpeaker(event.getSpeaker()), date);
        } else {
            return true;
        }
    }

    public void changeDate(Event event, LocalDateTime date){
        Speaker speaker = findSpeaker(event.getSpeaker());
        speaker._speakingSchedule.replace(event.getName(), date);
    }

    public void setSpeaker(Speaker speaker, Event event){
        speaker._speakingSchedule.put(event.getName(), event.getDate());
        System.out.println(speaker._speakingSchedule);
    }

    public Speaker findSpeaker(String speaker){
        for(Speaker s: speakers){
            if (s.get_name().equals(speaker)){
                return s;
            }
        }
        return null;
    }
}
