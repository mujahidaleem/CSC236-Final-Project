
package Presenters;


import Controllers.OrganizerFriendListController;
import Controllers.UserFriendListController;

public class OrganizerFriendListPresenter extends UserFriendListPresenter {
    public OrganizerFriendListController OrganizerFriendListController;
    public MessageReader MessageReader;
    public Organizer currentOrganizer;
    public User currentUser;
    public UserFriendManager UserFriendManager;
    public OrganizerFriendManager OrganizerFriendManager;



    public OrganizerFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                      MessageReader MessageReader,
                                        OrganizerFriendListController OrganizerFriendListController) {
        super(currentUser, UserFriendManager, MessageReader);
        this.OrganizerFriendListController= OrganizerFriendListController;
        if(currentUser instanceof Organizer){
            Organizer currentOrganizer=(Organizer) currentUser;
            this.currentOrganizer=currentOrganizer;
        }
        if(UserFriendManager instanceof OrganizerFriendManager){
            OrganizerFriendManager OrganizerFriendManager=(OrganizerFriendManager) UserFriendManager;
            this.OrganizerFriendManager= OrganizerFriendManager;}
    }

    /**
     * Display the messageable Users of Organizer
     */
    @Override
    public void DisplayMessageable(){
        ArrayList<User> messageableList = this.OrganizerFriendListController.getMessageableList();
        for(User messageable: messageableList){
            System.out.println(messageable.get_name());
        }
    }

    /**
     * Display the command to add/remove another User from messageable list
     */

    public void RemoveMessage(User anotherUser){
        String name=anotherUser.getname();
        System.out.println(name+"is removed from your friend list");
    }

    /**
     * * Display the chat log between User and another User
     */
    public void DisplayChatLog(User anotherUser) {
        ArrayList<String> Chatlog = this.UserFriendManager.checkHistoryMessage(anotherUser);
        for (String message : Chatlog) {
            System.out.println(message);
        }}

    /**
     * * Display the command to send a message to another User
     */

    public void DisplaySengdingMessage (User anotherUser){
        String name = anotherUser.get_name();
        System.out.println("you send a message to " + name);

    }

    /**
     * * Display the command to return to the menu
     */

    public void DisplayReturningMenu(){
        System.out.println("return to menu");
    }
}

