package UseCases.Users;

import Controllers.EventMenu.NullEventException;
import Entities.Users.Admin;
import Entities.Events.Event;
import UseCases.Events.EventManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdminManager extends UserManager {

    /**
     * An instance of this stores all the AdminUsers
     */
    private Admin currentAdmin;
    private ArrayList<Admin> admins;

    /**
     * AdminManager constructor
     *
     * @param currentUser the Admin that is logged in
     */
    public AdminManager(Admin currentUser, ArrayList<Admin> Admins) {
        super(currentUser);
        this.currentAdmin = currentUser;
        this.admins = new ArrayList<>();
    }

    public void setCurrentAdmin(Admin admin){
        this.currentAdmin = admin;
    }

    /**
     * getter for current admin
     */
    public Admin getCurrentAdminAdmin() {
        return this.currentAdmin;
    }


    /**
     * cancel a event if there is no attendee
     */
    public boolean cancelEventWithoutAttendee(Event event, EventManager eventManager, UserManager userManager, SpeakerManager speakerManager) throws NullEventException {
        if(event == null){
            throw new NullEventException();
        }
        if (event.getAttendees().size() == 0 && event.getEventTime().isAfter(LocalDateTime.now())) {
            eventManager.removeEvent(event);
            userManager.deleteEvent(event);
            speakerManager.deleteEvent(event);
            return true;
        } else
            return false;
    }

    public ArrayList<Admin> getAdmins(){
        return admins;
    }
}



