package Controllers.Factories;

import Entities.Users.*;
import UseCases.Users.*;

import java.util.ArrayList;

public class FactoryUseCaseHelper {
    private UserManager userManager;

    public FactoryUseCaseHelper(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * Create an organizer manager
     *
     * @return organizer manager
     */
    OrganizerManager createOrganizerManager() {
        OrganizerManager organizerManager = new OrganizerManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Organizer.class)) {
                organizerManager.organizers.add((Organizer) user);
            }
        }
        return organizerManager;
    }

    /**
     * Create an speaker manager
     *
     * @return speaker manager
     */
    SpeakerManager createSpeakerManager() {
        SpeakerManager speakerManager = new SpeakerManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Speaker.class)) {
                speakerManager.speakers.add((Speaker) user);
            }
        }
        return speakerManager;
    }

    /**
     * Create an attendee manager
     *
     * @return attendee manager
     */
    AttendeeManager createAttendeeManager() {
        AttendeeManager attendeeManager = new AttendeeManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Attendee.class)) {
                attendeeManager.users.add(user);
            }
        }
        return attendeeManager;
    }

    /**
     * Create an admin manager
     *
     * @return admin manager
     */
    AdminManager createAdminManager() {
        AdminManager adminManager = new AdminManager(null, new ArrayList<>());
        for (User user : userManager.users) {
            if (user.getClass().equals(Admin.class)) {
                adminManager.getAdmins().add((Admin) user);
            }
        }
        return adminManager;
    }
}
