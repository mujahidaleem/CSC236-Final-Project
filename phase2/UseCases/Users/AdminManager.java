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

        /**
         * AdminManager constructor
         *
         * @param currentUser the Admin that is logged in
         */
        public AdminManager(Admin currentUser, ArrayList<Admin> Admins) {
            super(currentUser);
            this.currentAdmin = currentUser;
        }

    /**
     * getter for current admin
     */
    public Admin getAdmin(){
        return this.currentAdmin;
    }


    /**
     * cancel a event if there is no attendee
     */
    public boolean cancelEventWithoutAttendee(Event event){
       if(length(event.getAttendees)==0 && (event.getEventTime().isAfter(LocalDateTime.now()))){
           event.removeEvent();
           return true;
       }
       else
           return false;

    }

}



