package UseCases;

import Entities.Admin;

import java.util.List;

public class AdminManager extends UserManager{

    /**
     * An instance of this stores all the AdminUsers
     */
        private Admin currentAdmin;
        public ArrayList<Admin> admins;

        /**
         * AdminManager constructor
         *
         * @param currentUser the Admin that is logged in
         * @param Admins  the list of all Admin
         */
        public AdminManager(Admin currentUser, List<Admin> Admins) {
            super(currentUser);
            this.currentAdmin = currentUser;
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
        if(this.admins.contains(admin)== false){
            this.admins.add(admin);
        }
    }

    /**
     * get Attendees of a event
     */
    public ArraryLisy<Attendee> getEventAttendees(Event event){
        return event.getAttendees();
    }

    /**
     * get Speaker of a event
     */
    public Speaker getEventSpeakers(Event event){
        Speaker result=null;
        if(event.hasSpeaker()==true){
            result = event.getSpeaker();
        }
        return result;
    }

    /**
     * get eventTime
     */
    public DateTime getTime(Event event){
        return event.getEventTime();
    }

    /**
     * check attendency
     */
    public int ckeckSpeakerAttendency(Speaker Speaker){
        return speaker.getAbsent();
    }

    public int ckeckAttendeeAttendency(Attendee attendee){
        return attendee.getAbsent();
    }

}

