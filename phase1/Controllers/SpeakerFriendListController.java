package Controllers;

import Presenters.AttendeeFriendListPresenter;
import Presenters.SpeakerFriendListPresenter;
import Presenters.UserFriendListPresenter;
import UseCases.SpeakerFriendManager;
import UseCases.UserFriendManager;
//import Entities.Event;

import java.util.ArrayList;
//import UseCases.AttendeeFriendManager;

public class SpeakerFriendListController extends UserFriendListController {
    public UserFriendListPresenter userFriendListPresenter;
    public UserFriendManager userFriendManager;
//    public SpeakerFriendListPresenter speakerFriendListPresenter;
//    public SpeakerFriendManager speakerFriendManager;


    public SpeakerFriendListController(UserFriendListPresenter userFriendListPresenter,
                                       UserFriendManager userFriendManager) {
        super(userFriendListPresenter, userFriendManager);
        if (userFriendListPresenter instanceof SpeakerFriendListPresenter) {
            SpeakerFriendListPresenter sflp = (SpeakerFriendListPresenter) userFriendListPresenter;
            this.speakerFriendListPresenter = sflp;
        }
        if (userFriendManager instanceof SpeakerFriendManager) {
            SpeakerFriendManager sfm = (SpeakerFriendManager) userFriendManager;
            this.speakerFriendManager = sfm;
        }
    }
}

//    /**
//     * Messageable Users for Speakers are all Users in Friendlist and all attendees in each of his event
//     * @return the messageable users of Speaker.
//     */
//
//    @Override
//    public ArrayList<User> getMessageableList(){
//        ArrayList<User> result= new ArrayList<String>();
//        int i = 0;
//        while(i < this.Speaker.get_friendList().length()){
//            result.add(this.Speaker.get_friendList[i]);
//            i=i+1; }
//        for(Event event: this.Speaker.get_eventList()){
//            for(User user :event.getAttendees()){
//                result.add(user);
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * @param anotherUser
//     * @return the chat log of User and another User
//     */
//
//    public ArrayList<String> showChatLog(User anotherUser){
//        ArrayList<String> result = new ArrayList<String>();
//        if(this.UserFriendManager.messageable(anotherUser)){
//            result = this.UserFriendManager.checkHistoryMessage(anotherUser);
//        }
//        return result;
//    }
//
//    /**
//     * Send a message to the User
//     */
//    public void sendingMessage(User anotherUser,String messageContent){
//        if(this.UserFriendManager.messageable(anotherUser)){
//            this.UserFriendManager.SendMessageTo(messageContent,anotherUser);
//        }}
//
//    /**
//     * Remove User from messageable Users
//     */
//
//    public void removeFrom(User anotherUser){
//        if(this.UserFriendManager.messageable(anotherUser)){
//            this.UserFriendManager.removeFromFriendlist(anotherUser);
//        }}
//
//
//    /**
//     * Add a User to list of messageable Users
//     */
//    public void Add(User anotherUser){
//        if(this.UserFriendManager.messageable(anotherUser)==false){
//            this.UserFriendManager.addNewFriend(anotherUser);
//        }}
//
//    /**
//     * Allow User to return to the menu
//     */
//    public void returnToMenu(){;
//
//    }


