package Presenters;

import Controllers.AttendeeFriendListController;

public class MessageMenuController {
    public MessageMenuPresenter




    public User matchNameWithUser(String name){

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