package Presenters;

import Controllers.AttendeeFriendListController;
import Controllers.SpeakerFriendListController;
import Controllers.UserFriendListController;
import Controllers.OrganizerFriendListController;
import Presenters.AttendeeFriendListPresenter;
import Presenters.OrganizerFriendListPresenter;
import Presenters.SpeakerFriendListPresenter;
import Entities.User;
import Entities.Speaker;
import Entities.Attendee;
import Entities.Organizer;

public class MessageMenuController {
    public UserFriendListController UserFriendListController;


    public MessageMenuController(UserFriendListController UserFriendListController){
        this.UserFriendListController=UserFriendListController;
    }


    public User matchNameWithUser(String name){
        User result=new User();
        if(this.UserFriendListController instanceof AttendeeFriendListController){
            AttendeeFriendListController AFC=(AttendeeFriendListController) this.UserFriendListController;
            for(User user:AFC.getMessageableList()){
                if(user.get_name()==name){
                    result= user;
                }
            }
        }
        if(this.UserFriendListController instanceof SpeakerFriendListController){
            SpeakerFriendListController SFC=(SpeakerFriendListController) this.UserFriendListController;
            for(User user:SFC.getMessageableList()){
                if(user.get_name()==name){
                    result= user;
                }
            }
        }
        if(this.UserFriendListController instanceof OrganizerFriendListController){
            OrganizerFriendListController OFC=(OrganizerFriendListController) this.UserFriendListController;
            for(User user:OFC.getMessageableList()){
                if(user.get_name()==name){
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