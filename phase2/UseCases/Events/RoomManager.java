package UseCases.Events;

import Entities.Events.Event;
import Entities.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager {
    ArrayList<Room> rooms;

    /**
     * RoomManager constructor; has a list of Rooms if manages
     */

    public RoomManager(){
        this.rooms = new ArrayList<>();
    }

    /**
     * adds a room with a set capacity
     * @param capacity
     * @return a Room with set capacity
     */

    public Room addRoom(int capacity){
        Room newRoom = new Room(rooms.size(), capacity, new HashMap<>());
        return newRoom;
    }

    /**
     * checks whether if Room with roomNum exists
     * @param roomNum
     * @return boolean
     */

    public boolean hasRoom(int roomNum){
        return this.roomsToRoomNums().contains(roomNum);
    }

    /**
     * Finds a Room and return it given a room number
     * @param roomNum
     * @return Room
     */

    public Room findRoom(int roomNum){
        for (Room r: this.rooms){
            if (roomNum == r.getRoomNumber()){
                return r;
            }
        }
        return null;
    }

    /**
     * Helper function for converting an arraylist of rooms to a list of room numbers
     *
     * @return an Arraylist of room numbers
     */

    public ArrayList<Integer> roomsToRoomNums(){
        ArrayList<Integer> roomNums = new ArrayList<>();
        for (Room room:rooms){
            roomNums.add(room.getRoomNumber());
        }
        return roomNums;
    }

    /**
     * gets room Capacity given a room number
     * @param roomNum
     * @return room capacity for the room
     */

    public int getRoomCapacity(int roomNum){
        Room r = this.findRoom(roomNum);
        return r.getRoomCapacity();
    }

    /**
     * removes a Room from rooms
     * @param roomNum
     * @return whether a room can be removed
     */

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

    /**
     * schedules a new event in the given room
     *
     * @param room
     * @param dateTime
     * @param duration
     * @param event
     */

    public void scheduleEvent(Room room, LocalDateTime dateTime, int duration, Event event){
        room.scheduleEvent(dateTime, dateTime.plusMinutes(duration), event);
    }

    /**
     * for changing room of event or placing an event in a room, checks if room is bookable in a given time range
     * @param roomNum
     * @param dateTime
     * @param duration
     * @return
     */
    public boolean bookable(int roomNum, LocalDateTime dateTime, int duration){ // checks if a room is available for booking from dateTime to dateTime + duration
        Room room = findRoom(roomNum);
        return room.slotAvailable(dateTime, duration);
    }

    /**
     *
     * removes an event from the room and the time range for that event
     *
     * @param room
     * @param event
     * @return boolean
     */

    public boolean removeEvent(Room room, Event event){
        room.removeEvent(event);
        return true;
    }

    /**
     * return a list of rooms
     * @return Arraylist<Room>
     */

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    /**
     * adds a room for roomManager to manage
     * @param room
     */

    public void addRoom(Room room){
        rooms.add(room);
    }

}
