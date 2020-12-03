package Controllers.Factories;

import Entities.Users.*;
import UseCases.Users.*;

public class OrganizerAccountCreatorFactory {
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;
    private AdminManager adminManager;
    private UserManager userManager;

    public OrganizerAccountCreatorFactory(OrganizerManager organizerManager,
                                          SpeakerManager speakerManager,
                                          AttendeeManager attendeeManager,
                                          AdminManager adminManager, UserManager userManager){
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this. attendeeManager = attendeeManager;
        this.adminManager = adminManager;
        this.userManager = userManager;
    }
    public User createAccountFactory(String name, String password, String accountType){
        if (accountType.equals("Speaker") || accountType.equals("speaker")){
            if (password.contains(" ")) {
                return null;
            } else {
                Speaker speaker = new Speaker(userManager.users.size() + 1000, name, password, null, null, null);
                speakerManager.speakers.add(speaker);
                userManager.users.add(speaker);
                return speaker;
            }
        }
        if (accountType.equals("Organizer") || accountType.equals("organizer")){
            if (password.contains(" ")){
                return null;
            } else {
                Organizer organizer = new Organizer(userManager.users.size() + 1000, name, password, null, null, null);
                organizerManager.organizers.add(organizer);
                userManager.users.add(organizer);
                return organizer;
            }
        }
        if (accountType.equals("Attendee") || accountType.equals("attendee")){
            if (password.contains(" ")){
                return null;
            } else {
                Attendee attendee = new Attendee(userManager.users.size() + 1000, name, password, null, null);
                attendeeManager.users.add(attendee);
                userManager.users.add(attendee);
                return attendee;
            }
        }
        if (accountType.equals("Admin") || accountType.equals("admin")){
            if (password.contains(" ")){
                return null;
            } else {
                Admin admin = new Admin(userManager.users.size() + 1000, name, password, null, null, 1, 1);
                adminManager.users.add(admin);
                userManager.users.add(admin);
                return admin;
            }

        }
        return null;
    }
}


