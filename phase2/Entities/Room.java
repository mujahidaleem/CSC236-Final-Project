package Entities;

import Entities.Events.Event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements Serializable {
    private int roomNumber;
    private int roomCapacity;
    private HashMap<ArrayList<LocalDateTime>, String> roomSchedule; // mapping (start time to end time) to event name
    // eg. {[9:30am, 10:30am]:"Christmas", [11:00am, 12:30pm]:"Halloween"}
    public Room(int roomNumber, int roomCapacity, HashMap<ArrayList<LocalDateTime>, String> map){
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.roomSchedule = map;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

//    public boolean setRoomCapacity(int newRoomCapacity){
//        if (newRoomCapacity > 0){
//            roomCapacity = newRoomCapacity;
//            return true;
//        }
//        return false;
//    }

    public void scheduleEvent(LocalDateTime startTime, LocalDateTime endTime, Event event){
        ArrayList<LocalDateTime> start_to_end = new ArrayList<>();
        start_to_end.add(startTime);
        start_to_end.add(endTime);
        roomSchedule.put(start_to_end, event.getEventName());
    }


    public boolean slotAvailable(LocalDateTime startTime, int duration){
        LocalDateTime endTime = startTime.plusMinutes(duration);
        for (ArrayList<LocalDateTime> timeRange: this.getRoomSchedule().keySet()){
            LocalDateTime timeRangeStart = timeRange.get(0);
            LocalDateTime timeRangeEnd = timeRange.get(1);
            if (startTime.isBefore(timeRangeEnd) && endTime.isAfter(timeRangeStart)){
                return false;
            }
        }
        return true;
    }

    public HashMap<ArrayList<LocalDateTime>, String> getRoomSchedule() {
        return roomSchedule;
    }

    public void removeEvent(Event event){
        this.roomSchedule.values().remove(event.getEventName());
    }

}
