package UseCases;

public class SpeakerFriendManager {

    /**
     * SpeakerFriendManager constructor
     * @param currentUser - the current user
     */

    public SpeakerFriendManager(User currentUser) {
        super(currentUser);
    }


    /**
     * Speakers can send an Announcement to all Attendees in an event
     * @param message - the content of the announcement
     * @param event - the Event to which the announcement is being sent
     */

    public void sendingAnnouncement(String message, Event event) {
        for(User user : event.getAttendees()) {
            this.SpeakerFriendManager.sendMessageTo(message, user);
        }
    }
}