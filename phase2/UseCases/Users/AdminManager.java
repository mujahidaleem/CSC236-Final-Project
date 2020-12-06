package UseCases.Users;

import Entities.Users.Admin;
import Entities.Users.Attendee;
import Entities.Events.Event;
import Entities.Users.Speaker;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdminManager extends UserManager {

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
        public AdminManager(Admin currentUser, ArrayList<Admin> Admins) {
            super(currentUser);
            this.currentAdmin = currentUser;
            this.admins = Admins;
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
     * cancel a event if there is no attendee
     */
    public void cancelEventWithoutAttendee(Event event){
       if(length(event.getAttendees)==0 && (event.getEventTime().isAfter(LocalDateTime.now()))){
           event.removeEvent();
       }

    }

}



