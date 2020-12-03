package Controllers.Factories;

import Entities.Users.Attendee;
import Entities.Users.Organizer;
import Entities.Users.Speaker;
import Entities.Users.User;
import UseCases.Users.AttendeeManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

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
}
