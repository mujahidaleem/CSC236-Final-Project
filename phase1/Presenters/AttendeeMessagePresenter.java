package Presenters;

import Controllers.AttendeeFriendListController;
import UseCases.UserFriendManager;
import UseCases.UserManager;

public class AttendeeMessagePresenter extends MessageMenuPresenter{
    AttendeeFriendListController attendeeFriendListController;


    public AttendeeMessagePresenter(AttendeeFriendListController attendeeFriendListController, UserManager userManager,
                                    UserFriendManager userFriendManager){
        super(attendeeFriendListController, userManager, userFriendManager);
        this.attendeeFriendListController = attendeeFriendListController;
    }
}
