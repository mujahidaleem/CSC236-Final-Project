package Controllers.MessageMenu;

import GUI.MainMenuPanel;
import UseCases.Message.AttendeeFriendManager;

/**
 * Controls data specifically for attendees' friend lists.
 */
public class AttendeeFriendListController extends UserFriendListController {

    /**
     * AttendeeFriendList constructor
     *
     * @param attendeeFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AttendeeFriendListController(AttendeeFriendManager attendeeFriendManager, MainMenuPanel mainMenuPanel) {
        super(attendeeFriendManager, mainMenuPanel);
    }
}
