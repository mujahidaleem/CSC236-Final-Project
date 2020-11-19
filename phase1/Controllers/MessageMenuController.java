package Controllers;

import Entities.User;
import Entities.Speaker;
import Entities.Attendee;
import Entities.Organizer;
import UseCases.UserFriendManager;
import UseCases.UserManager;

public class MessageMenuController {
    public UserFriendManager userFriendManager;
    public UserManager userManager;
    public UserFriendListController UserFriendListController;


    public MessageMenuController(UserFriendManager userFriendManager, UserFriendListController UserFriendListController){
        this.userFriendManager = userFriendManager;
        this.UserFriendListController=UserFriendListController;
    }


    public User matchNameWithUser(String name){
        User result=new User();
        if(this.UserFriendListController instanceof AttendeeFriendListController){
            AttendeeFriendListController AFC=(AttendeeFriendListController) this.UserFriendListController;
            for(User user:AFC.getManageableList()){
                if(user.getName()==name){
                    result= user;
                }
            }
        }
        if(this.UserFriendListController instanceof SpeakerFriendListController){
            SpeakerFriendListController SFC=(SpeakerFriendListController) this.UserFriendListController;
            for(User user:SFC.getManageableList()){
                if(user.getName()==name){
                    result= user;
                }
            }
        }
        if(this.UserFriendListController instanceof OrganizerFriendListController){
            OrganizerFriendListController OFC=(OrganizerFriendListController) this.UserFriendListController;
            for(User user:OFC.getManageableList()){
                if(user.getName()==name){
                    result= user;
                }
            }
        }
        return result;

    }



    public void factoryMethod(User user){
        if(user instanceof Attendee){
            Attendee attendee=(Attendee) user;
            attendee.AttendeeFriendListPresenter.DisplayMessageable();}
        if(user instanceof Speaker){
            Speaker speaker=(Speaker) user;
            speaker.SpeakerFriendListPresenter.DisplayMessageable();}
        if(user instanceof Organizer){
            Organizer organizer=(Organizer) user;
            organizer.OrganizerFriendListPresenter.DisplayMessageable();}
    }

}