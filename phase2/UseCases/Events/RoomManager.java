package UseCases.Events;

import Entities.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager {
    ArrayList<Room> rooms;

    public RoomManager(){
        this.rooms = new ArrayList<>();
    }

    public boolean hasRoom(int roomNum){
        return this.roomsToRoomNums().contains(roomNum);
    }

    public Room findRoom(int roomNum){
        for (Room r: this.rooms){
            if (roomNum == r.getRoomNumber()){
                return r;
            }
        }
        return null;
    }

    public ArrayList<Integer> roomsToRoomNums(){
        ArrayList<Integer> roomNums = new ArrayList<>();
        for (Room room:rooms){
            roomNums.add(room.getRoomNumber());
        }
        return roomNums;
    }

    public boolean addRoom(int roomNum, int capacity){
        ArrayList<Integer> roomNums = this.roomsToRoomNums();
        if (roomNums.contains(roomNum)){
            return false;
        }
        rooms.add(new Room(roomNum, capacity));
        return true;
    }

    public boolean removeRoom(int roomNum){
        ArrayList<Integer> roomNums = this.roomsToRoomNums();
        for (Room r: rooms){
            if (r.getRoomNumber() == roomNum){
                rooms.remove(r);
                return true;
            }
        }
        return false;
    }



    public void scheduleEvent(Room room, LocalDateTime dateTime, int duration, int eventId){
        room.scheduleEvent(dateTime, dateTime.plusMinutes(duration), eventId);
    }

    public HashMap<ArrayList<LocalDateTime>, Integer> getRoomScheduleCopy(Room room){
        return room.getRoomScheduleCopy();
    }

    /**
     * for changing room of event or placing an event in a room
     * @param roomNum
     * @param dateTime
     * @param duration
     * @return
     */
    public boolean bookable(int roomNum, LocalDateTime dateTime, int duration){ // checks if a room is available for booking from dateTime to dateTime + duration
        Room room = findRoom(roomNum);
        return room.slotAvailable(dateTime, duration);
    }


    public boolean removeEvent(Room room, int eventId){
        room.removeEvent(eventId);
        return true;
    }

}
