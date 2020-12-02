package UseCases;

import Entities.Room;
import Entities.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoomManager {
    ArrayList<Room> rooms;

    public RoomManager(ArrayList<Room> rooms){
        this.rooms = rooms;
    }

    public boolean hasRoom(int roomNum){
        return rooms.contains(roomNum);
    }

    public Room findRoom(int roomNum){
        for (Room r: rooms){
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

    public boolean bookable(int roomNum, LocalDateTime dateTime, int duration){
        Room room = findRoom(roomNum);
        return room.slotAvailable(dateTime, duration);
    }

}
