import Entities.Events.AttendeeOnlyEvent;
import Entities.Users.Attendee;
import Entities.Events.Event;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Gateways.EventReader;
import Gateways.MessageReader;
import Gateways.RoomReader;
import Gateways.UserReader;
import UseCases.Events.RoomManager;
import UseCases.Language.EnglishLanguagePack;
import UseCases.Message.AttendeeFriendManager;
import UseCases.Events.EventManager;
import UseCases.Language.FrenchLanguagePack;
import UseCases.Users.UserManager;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Initializer {

    /**
     * Initializer constructor
     */
    public Initializer() {

    }

    /**
     * Generates a ser file of previously created users and events.
     */
    public void initialize() {
        Attendee james = new Attendee(1000, "James", "password", new HashMap<String, LocalDateTime>(), new ArrayList<>());
        Attendee jason = new Attendee(1003, "jason", "whyNot", new HashMap<String, LocalDateTime>(), new ArrayList<>());
        Attendee job = new Attendee(1004, "job", "whyNot", new HashMap<String, LocalDateTime>(), new ArrayList<>());
        Organizer john = new Organizer(1001, "john", "wordPass", new HashMap<String, LocalDateTime>(), new ArrayList<>(), new HashMap<>());
        Speaker will = new Speaker(1002, "will", "CSC", new HashMap<String, LocalDateTime>(), new ArrayList<>(), new HashMap<String, LocalDateTime>());

        UserManager userManager = new UserManager(null, new ArrayList<>());
        userManager.users.add(james);
        userManager.users.add(john);
        userManager.users.add(will);
        userManager.users.add(jason);
        userManager.users.add(job);

        AttendeeOnlyEvent christmas = new AttendeeOnlyEvent("Christmas", 0, 3, LocalDateTime.parse("2020-11-27T18:00:00"), 60, 1001, new ArrayList<>());
        AttendeeOnlyEvent halloween = new AttendeeOnlyEvent("Halloween", 1, 3, LocalDateTime.parse("2020-11-28T18:00:00"), 60, 1001, new ArrayList<>());
        AttendeeOnlyEvent birthday = new AttendeeOnlyEvent("Birthday", 2, 3, LocalDateTime.parse("2020-11-27T19:00:00"),120,1001, new ArrayList<>());

        EventManager eventManager = new EventManager(new ArrayList<>());
        eventManager.getEvents().add(christmas);
        eventManager.getEvents().add(halloween);
        eventManager.getEvents().add(birthday);

        john.get_eventsOrganizing().put(christmas.getEventName(), christmas.getEventTime());
        john.get_eventsOrganizing().put(halloween.getEventName(), halloween.getEventTime());
        john.get_eventsOrganizing().put(birthday.getEventName(), birthday.getEventTime());

        halloween.add(jason);
        halloween.add(job);
        jason.getPersonalSchedule().put(halloween.getEventName(), halloween.getEventTime());
        job.getPersonalSchedule().put(halloween.getEventName(), halloween.getEventTime());

        christmas.add(james);
        james.getPersonalSchedule().put(christmas.getEventName(), christmas.getEventTime());

        UserReader userReader = new UserReader("userManager.ser");
        EventReader eventReader = new EventReader("eventManager.ser");
        MessageReader messageReader = new MessageReader("userFriendManager.ser");

        AttendeeFriendManager attendeeFriendManager = new AttendeeFriendManager(new HashMap<>(), null);
        attendeeFriendManager.setCurrentUser(james);
        attendeeFriendManager.addNewFriend(jason);
        attendeeFriendManager.sendMessageTo(james, jason, "HELLO", LocalDateTime.now());

        userReader.saveFile(userManager);
        eventReader.saveFile(eventManager);
        messageReader.saveFile(attendeeFriendManager);

        RoomManager roomManager = new RoomManager();
        roomManager.addRoom(3);
        roomManager.addRoom(4);
        roomManager.addRoom(5);

        roomManager.scheduleEvent(roomManager.getRooms().get(0), christmas.getEventTime(), christmas.getDuration(), christmas);
        roomManager.scheduleEvent(roomManager.getRooms().get(1), halloween.getEventTime(), halloween.getDuration(), halloween);
        roomManager.scheduleEvent(roomManager.getRooms().get(2), birthday.getEventTime(), birthday.getDuration(), birthday);



        RoomReader roomReader = new RoomReader("roomManager.ser");
        roomReader.saveFile(roomManager);
    }

    public void setUpLanguage(){
        EnglishLanguagePack englishLanguagePack = new EnglishLanguagePack("english");
        try {
            FileOutputStream fi = new FileOutputStream(new File("english.ser"));
            ObjectOutputStream oi = new ObjectOutputStream(fi);

            oi.writeObject(englishLanguagePack);

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }

    public void setUpLanguage2(){
        FrenchLanguagePack frenchlanguagePack = new FrenchLanguagePack("french");
        try {
            FileOutputStream fi = new FileOutputStream(new File("french.ser"));
            ObjectOutputStream oi = new ObjectOutputStream(fi);

            oi.writeObject(frenchlanguagePack);

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        }
    }
}
