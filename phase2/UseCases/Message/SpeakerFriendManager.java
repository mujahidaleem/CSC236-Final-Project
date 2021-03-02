package UseCases.Message;

import Entities.Message;
import Entities.Users.Speaker;
import Entities.Users.User;
import Entities.Events.Event;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakerFriendManager extends UserFriendManager {
    private Speaker currentSpeaker;

    /**
     * SpeakerFriendManager constructor
     *
     * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
     */

    public SpeakerFriendManager(HashMap<ArrayList<Integer>, ArrayList<Message>> userToMessages, Speaker speaker) {
        super(userToMessages, speaker);
        this.currentSpeaker = speaker;
    }


    /**
     * Speakers can send an Announcement to all Attendees in an event
     *
     * @param speaker        - the Speaker sending the announcement
     * @param event          - the Event to which the announcement is being sent
     * @param messageContent - the content of the announcement being sent
     * @param userManager    - contains a list of users
     */

    public void sendingAnnouncement(Speaker speaker, Event event, String messageContent, UserManager userManager, LocalDateTime dateTime) {
        for (Integer id : event.getAttendees()) {
            User user = userManager.users.get(id - 1000);
            sendMessageTo(speaker, user, messageContent, dateTime);
        }
    }

    /**
     * Speakers can send an Announcement to all Attendees in an event
     *
     * @param speaker        - the Speaker sending the announcement
     * @param messageContent - the content of the announcement being sent
     * @param userManager    - contains a list of users
     */
    public void sendingAnnouncementToAll(Speaker speaker, String messageContent, UserManager userManager, LocalDateTime dateTime) {
        for (User user : userManager.getUsers()) {
            for (String event : speaker.getSpeakingSchedule().keySet()) {
                if (user.getPersonalSchedule().containsKey(event)) {
                    sendMessageTo(speaker, user, messageContent, dateTime);
                }
            }
        }
    }

    /**
     * Getter for current speaker
     *
     * @return currentSpeaker
     */
    public Speaker getCurrentSpeaker() {
        return currentSpeaker;
    }

    /**
     * Sets the current speaker of the manager
     *
     * @param speaker given speaker
     */
    public void setCurrentSpeaker(Speaker speaker) {
        this.currentSpeaker = speaker;
    }
}