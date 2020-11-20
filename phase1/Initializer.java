import Controllers.EnglishLanguagePack;
import Controllers.LanguagePack;
import Entities.Attendee;
import Entities.Event;
import Entities.Organizer;
import Entities.Speaker;
import Gateways.EventReader;
import Gateways.MessageReader;
import Gateways.UserReader;
import UseCases.AttendeeFriendManager;
import UseCases.EventManager;
import UseCases.UserFriendManager;
import UseCases.UserManager;

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

        Event christmas = new Event(1, "Christmas", 100, LocalDateTime.parse("2020-12-25T18:00:00"), 10001);
        Event halloween = new Event(2, "Halloween", 100, LocalDateTime.parse("2020-10-31T18:00:00"), 10001);

        EventManager eventManager = new EventManager(new ArrayList<>());
        eventManager.getEvents().add(christmas);
        eventManager.getEvents().add(halloween);

        halloween.add(jason);
        halloween.add(job);
        jason.getPersonalSchedule().put(halloween.getEventName(), halloween.getEventTime());
        job.getPersonalSchedule().put(halloween.getEventName(), halloween.getEventTime());

        christmas.add(james);
        james.getPersonalSchedule().put(christmas.getEventName(), christmas.getEventTime());

        UserReader userReader = new UserReader("D:\\userManager.ser");
        EventReader eventReader = new EventReader("D:\\eventManager.ser");
        MessageReader messageReader = new MessageReader("D:\\userFriendManager.ser");

        AttendeeFriendManager attendeeFriendManager = new AttendeeFriendManager(new HashMap<>(), null);

        userReader.saveFile(userManager);
        eventReader.saveFile(eventManager);
        messageReader.saveFile(attendeeFriendManager);
    }

    public void setUpLanguage(){
        EnglishLanguagePack englishLanguagePack = new EnglishLanguagePack("english");
        try {
            FileOutputStream fi = new FileOutputStream(new File("D:\\english.ser"));
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
}
