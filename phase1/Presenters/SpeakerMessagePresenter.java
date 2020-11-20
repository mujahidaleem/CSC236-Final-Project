package Presenters;

import Controllers.SpeakerFriendListController;
import UseCases.SpeakerFriendManager;
import UseCases.UserManager;

public class SpeakerMessagePresenter extends MessageMenuPresenter{

    public SpeakerMessagePresenter(SpeakerFriendListController speakerFriendListController, UserManager userManager, SpeakerFriendManager speakerFriendManager){
        super(speakerFriendListController,userManager, speakerFriendManager);
    }
}
