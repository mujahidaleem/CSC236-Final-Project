package Controllers;

import Presenters.AttendeeFriendListPresenter;
import Presenters.UserFriendListPresenter;
import UseCases.UserFriendManager;
//import Entities.Event;

import java.util.ArrayList;
//import USseCases.AttendeeFriendManager;

public class OrganizerFriendListController extends UserFriendListController {
    public User TargetUser;
    public Presenters.UserFriendListPresenter UserFriendListPresenter;
    public UseCases.UserFriendManager UserFriendManager;
    public Organizer currentOrganizer;
    public OrganizerFriendListPresenter OrganizerFriendListPresenter;
    public OrganizerFriendManager OrganizerFriendManager;


    public OrganizerFriendListController(User targetUser, UserFriendListPresenter UserFriendListPresenter,
                                        UserFriendManager UserFriendManager){
        super(targetUser,UserFriendListPresenter,UserFriendManager);
        if(currentUser instanceof Organizer){
            Organizer currentOrganizer=(Orgenizer) currentUser;
            this.currentOrganizer=currentOrganizer;
        }
        if(UserFriendManager instanceof OrganizerFriendManager){
            OrganizerFriendManager OrganizerFriendManager=(OrganizerFriendManager) UserFriendManager;
            this.OrganizerFriendManager= OrganizerFriendManager;}}

    /**
     * for Organizer, the messageable Users are all users in friendlist and all Attendees and Speaker.
     * @return the messageable users of Organizers
     */
    @Override
    public ArrayList<String> getMessageableList(){
        ArrayList<String> result= new ArrayList<String>();
        int i = 0;
        while(i < this.Organizer.get_friendList().length()){
            result.add(this.Organizer.get_friendList[i].get_name());
            i=i+1; }
        for(Event event: this.Organizer.get_eventList()){
            result.add(event.getSpeaker());
            for(User user :event.getUserList()){
                result.add(user.get_name());
            }
        }
        return result;
    }


    /**
     *  Show the chat log of User and another User
     */
    public ArrayList<String> showChatLog(User anotherUser){
        ArrayList<String> result = new ArrayList<String>();
        if(this.UserFriendManager.messageable(anotherUser)){
            result = this.UserFriendManager.checkHistoryMessage(anotherUser);
        }
        return result;
    }

    /**
     * Send a message to the User
     */
    public void sendingMessage(User anotherUser,String messageContent){
        if(this.UserFriendManager.messageable(anotherUser)){
            this.UserFriendManager.SendMessageTo(messageContent,anotherUser);
        }}

    /**
     * Remove User from messageable Users
     */

    public void removeFrom(User anotherUser){
        if(this.UserFriendManager.messageable(anotherUser)){
            this.UserFriendManager.removeFromFriendlist(anotherUser);
        }}


    /**
     * Add a User to list of messageable Users
     */
    public void Add(User anotherUser){
        if(this.UserFriendManager.messageable(anotherUser)==false){
            this.UserFriendManager.addNewFriend(anotherUser);
        }}

    /**
     * Allow User to return to the menu
     */
    public void returnToMenu(){;

    }