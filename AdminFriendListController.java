package Controllers;

import Entities.Attendee;
import Entities.Speaker;
import Entities.User;
import UseCases.AdminFriendManager;
import UseCases.UserManager;

public class AdminFriendListController extends UserFriendListController {
    public UseCases.UserManager userManager;
    public UseCases.AdminFriendManager adminFriendManager;

    /**
     * OrganizerFriendList Constructor
     * Controls the organizer's friend list, as well as allows the organizer to message all speakers/attendees
     *
     * @param adminFriendManager Use case for the friends of an organizer
     * @param userManager            Use case for a user's options
     */

    public AdminFriendListController(UseCases.AdminFriendManager adminFriendManager, UseCases.UserManager userManager) {
        super(adminFriendManager);
        this.adminFriendManager = adminFriendManager;
        this.userManager = userManager;
    }

}
