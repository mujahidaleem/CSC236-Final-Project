package Controllers;

import Entities.Attendee;
import Entities.Speaker;
import Entities.User;
import UseCases.OrganizerFriendManager;
import UseCases.UserManager;

public class OrganizerFriendListController extends UserFriendListController {
    public UserManager userManager;
    public OrganizerFriendManager organizerFriendManager;

    public OrganizerFriendListController(OrganizerFriendManager organizerFriendManager, UserManager userManager) {
        super(organizerFriendManager);
        this.organizerFriendManager = organizerFriendManager;
        this.userManager = userManager;
    }

    public void messageAllAttendees(String messageContent){
        for (User user:userManager.users){
            if(user.getClass().equals(Attendee.class)){
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(),user, messageContent);
            }
        }
    }

    public void messageAllSpeakers(String messageContent){
        for (User user:userManager.users){
            if(user.getClass().equals(Speaker.class)){
                organizerFriendManager.sendMessageTo(organizerFriendManager.getCurrentOrganizer(),user, messageContent);
            }
        }
    }
}