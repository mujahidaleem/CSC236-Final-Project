package Controllers;

import Presenters.AttendeeMessagePresenter;
import UseCases.AttendeeFriendManager;

/**
 * Controls data specifically for attendees' friend lists.
 */
public class AttendeeFriendListController extends UserFriendListController {
    public AttendeeMessagePresenter attendeeMessagePresenter;

    /**
     * AttendeeFriendList constructor
     *
     * @param attendeeFriendManager The friend list manager use case,
     *                              holds methods relevant to manipulating attendee friend lists
     */
    public AttendeeFriendListController(AttendeeFriendManager attendeeFriendManager) {
        super(attendeeFriendManager);
    }
}
