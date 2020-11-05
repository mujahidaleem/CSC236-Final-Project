package Entities;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class Speaker extends User{
    private String _name;
    private String _password;
    public List<User> _friendList;
    public HashMap<String, LocalDateTime> _personalSchedule;
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