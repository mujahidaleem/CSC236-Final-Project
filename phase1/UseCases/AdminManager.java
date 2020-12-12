package UseCases;

import Entities.Admin;
import Entities.Speaker;
import Entities.Attendee;
import Entities.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdminManager extends UserManager{

    /**
     * An instance of this stores all the AdminUsers
     */
        private Admin currentUser;
        public ArrayList<Admin> admins;

        /**
         * AdminManager constructor
         *
         * @param currentUser the Admin that is logged in
         * @param admins  the list of all Admin
         */
        public AdminManager(Admin currentUser, ArrayList<Admin> admins) {
            super(currentUser);
            this.currentUser = currentUser;
            this.admins = admins;
        }

    /**
     * Getter for Admins
     */
    public ArrayList<Admin> getAdmins(){
        return this.admins;
    }

    /**
     * remove from Admins
     */
    public void removeFromAdmins(Admin admin){
        if(this.admins.contains(admin)){
            this.admins.remove(admin);
        }
    }

    /**
     * add a new Admin
     */
    public void addAdmin(Admin admin){
        if(this.admins.contains(admin) == false){
            this.admins.add(admin);
        }
    }

    /**
     * get Attendees of a event
     */
    public ArrayList<Integer> getEventAttendees(Event event){
        return event.getAttendees();
    }

    /**
     * get Speaker of a event
     */
    public int getEventSpeakers(Event event){
        int result = 0;
        if(event.hasSpeaker() == true){
            result = event.getSpeaker();
        }
        return result;
    }

    /**
     * get eventTime
     */
    public LocalDateTime getTime(Event event){
        return event.getEventTime();
    }

    /**
     * check attendency
     */
    public int checkSpeakerAttendency(Speaker speaker){
        return speaker.getAbsent();
    }

    public int checkAttendeeAttendency(Attendee attendee){
        return attendee.getAbsent();
    }

}

