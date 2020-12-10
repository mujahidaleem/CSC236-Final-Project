package Entities;

import Entities.Events.Event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * an instance of this represents a room, it has its schedule, its unique room number and a set capacity
 */

public class Room implements Serializable {
    private int roomNumber;
    private int roomCapacity;
    private HashMap<ArrayList<LocalDateTime>, String> roomSchedule; // mapping (start time to end time) to event name
    // eg. {[9:30am, 10:30am]:"Christmas", [11:00am, 12:30pm]:"Halloween"}

    /**
     * a constructor for a room
     *
     * @param roomNumber
     * @param roomCapacity
     * @param map
     */
    public Room(int roomNumber, int roomCapacity, HashMap<ArrayList<LocalDateTime>, String> map){
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.roomSchedule = map;
    }

    /**
     * returns room number for this room
     * @return int
     */


    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * returns capacity for the room
     * @return int
     */

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

    /**
     * schedules an event with a given time frame to its schedule
     *
     * @param startTime
     * @param endTime
     * @param event
     */

    public void scheduleEvent(LocalDateTime startTime, LocalDateTime endTime, Event event){
        ArrayList<LocalDateTime> start_to_end = new ArrayList<>();
        start_to_end.add(startTime);
        start_to_end.add(endTime);
        roomSchedule.put(start_to_end, event.getEventName());
    }

    /**
     * checks whether the time frame is available for booking
     *
     * @param startTime
     * @param duration
     * @return boolean
     */

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

    /**
     * gets a mapping for the room schedule
     *
     * @return HashMap<ArrayList<LocalDateTime>
     */

    public HashMap<ArrayList<LocalDateTime>, String> getRoomSchedule() {
        return roomSchedule;
    }

    /**
     * removes key and value given a unique event
     *
     * @param event
     */

    public void removeEvent(Event event){
        this.roomSchedule.values().remove(event.getEventName());
    }

}
