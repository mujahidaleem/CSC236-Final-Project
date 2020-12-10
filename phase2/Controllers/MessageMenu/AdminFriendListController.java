package Controllers.MessageMenu;

import GUI.MainMenuPanel;
import UseCases.Message.AdminFriendManager;

public class AdminFriendListController extends UserFriendListController {
    /**
     * AdminFriendList constructor
     *
     * @param adminFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AdminFriendListController(AdminFriendManager adminFriendManager, MainMenuPanel mainMenuPanel) {
        super(adminFriendManager, mainMenuPanel);
    }
}