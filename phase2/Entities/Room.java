package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private int roomNumber;
    private int roomCapacity;
    private HashMap<ArrayList<LocalDateTime>, Integer> roomSchedule; // mapping (start time to end time) to event_id

    public Room(int roomNumber, int roomCapacity){
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.roomSchedule = new HashMap();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public boolean setRoomCapacity(int newRoomCapacity){
        if (newRoomCapacity > 0){
            roomCapacity = newRoomCapacity;
            return true;
        }
        return false;
    }

    public void scheduleEvent(LocalDateTime startTime, LocalDateTime endTime, int eventId){
        ArrayList<LocalDateTime> start_to_end = new ArrayList<>();
        start_to_end.add(startTime);
        start_to_end.add(endTime);
        roomSchedule.put(start_to_end, eventId);
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

    public HashMap<ArrayList<LocalDateTime>, Integer> getRoomSchedule() {
        return roomSchedule;
    }

    public HashMap<ArrayList<LocalDateTime>, Integer> getShallowCopyRoomSchedule(){
        return (HashMap<ArrayList<LocalDateTime>, Integer>) this.roomSchedule.clone();
    }


}
