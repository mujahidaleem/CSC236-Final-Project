package UseCases.Events;

import Entities.Events.Event;
import Entities.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager implements Serializable {
    ArrayList<Room> rooms;

    public RoomManager(){
        this.rooms = new ArrayList<>();
    }

    public Room addRoom(int capacity){
        Room newRoom = new Room(rooms.size(), capacity, new HashMap<>());
        rooms.add(newRoom);
        return newRoom;
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

    public int getRoomCapacity(int roomNum){
        Room r = this.findRoom(roomNum);
        return r.getRoomCapacity();
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

    public void scheduleEvent(Room room, LocalDateTime dateTime, int duration, Event event){
        room.scheduleEvent(dateTime, dateTime.plusMinutes(duration), event);
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


    public boolean removeEvent(Room room, Event event){
        room.removeEvent(event);
        return true;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

}
