package Presenters;

import Controllers.SpeakerFriendListController;
import UseCases.SpeakerFriendManager;
import UseCases.UserManager;

public class SpeakerMessagePresenter extends MessageMenuPresenter{
    /**
     * Constructor of the SpeakerMessagePresenter
     * Presents the message menu of the speaker this is the same for a MessageMenuPresenter
     * *Created for extension in phase 2*
     * @param speakerFriendListController Controller of the speaker's friend list
     * @param userManager Use case for a user's functions
     * @param speakerFriendManager Use case for the speaker's friend list's functions
     */
    public SpeakerMessagePresenter(SpeakerFriendListController speakerFriendListController, UserManager userManager, SpeakerFriendManager speakerFriendManager){
        super(speakerFriendListController,userManager, speakerFriendManager);
    }
}
