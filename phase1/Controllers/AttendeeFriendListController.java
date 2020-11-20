package Controllers;

import Presenters.AttendeeMessagePresenter;
import UseCases.AttendeeFriendManager;
//import Entities.Event;

//import UseCases.AttendeeFriendManager;

public class AttendeeFriendListController extends UserFriendListController {
    public AttendeeMessagePresenter attendeeMessagePresenter;

    public AttendeeFriendListController(AttendeeFriendManager attendeeFriendManager) {
        super(attendeeFriendManager);
    }
}

//


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
