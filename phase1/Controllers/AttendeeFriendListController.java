package Controllers;

import Presenters.AttendeeFriendListPresenter;
import Presenters.UserFriendListPresenter;
import UseCases.UserFriendManager;
//import Entities.Event;

import java.util.ArrayList;
//import UseCases.AttendeeFriendManager;

public class AttendeeFriendListController extends UserFriendListController {
        public UserFriendListPresenter userFriendListPresenter;
        public UserFriendManager userFriendManager;
//        public AttendeeFriendListPresenter attendeeFriendListPresenter;
//        public AttendeeFriendManager attendeeFriendManager;


        public AttendeeFriendListController(UserFriendListPresenter userFriendListPresenter,
                                            UserFriendManager userFriendManager){
            super(userFriendListPresenter,userFriendManager);
            if(userFriendListPresenter instanceof AttendeeFriendListPresenter){
                AttendeeFriendListPresenter aflp = (AttendeeFriendListPresenter) userFriendListPresenter;
                this.attendeeFriendListPresenter = aflp;
            }
            if(userFriendManager instanceof AttendeeFriendManager){
                AttendeeFriendManager afm = (AttendeeFriendManager) userFriendManager;
                this.attendeeFriendManager= afm;
            }
        }

//    /**
//     * For Attendee, messageable Users are All users in FriendList and Other Attendees / Speakers
//     * in the event that he sign up
//     * @return the messageable users of Attendee
//     */

//    @Override
//        public ArrayList<User> getMessageableList(){
//            ArrayList<User> result= new ArrayList<String>();
//            int i = 0;
//            while(i < this.Attendee.get_friendList().length()){
//                result.add(this.Attendee.get_friendList[i]);
//                i=i+1; }
//            for(Event event: this.Attendee.get_eventList()){
//                result.add(event.getSpeaker());
//                for(User user :event.getUserList()){
//                    result.add(user);
//                }
//            }
//            return result;
//        }


//    /**
//     *  Show the chat log of User and another User
//     */
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
//
//
//
//
//}
