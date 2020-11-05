package Entities;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class Speaker extends User{
    public HashMap<String, LocalDateTime> _speakingSchedule;

    public Speaker(String name, String password, HashMap<String, LocalDateTime> schedule,
                   List<User> friends, HashMap<String, LocalDateTime> speakingSchedule){
        super(name, password, schedule, friends);
        _speakingSchedule = speakingSchedule;
    }

    public HashMap<String, LocalDateTime> get_speakingSchedule() {
        return _speakingSchedule;
    }
}