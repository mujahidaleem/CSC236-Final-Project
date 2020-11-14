
package Presenters;



public class OrganizerFriendListPresenter extends UserFriendListPresenter {
    public OrganizerFriendManager OrganizerFriendManager;
    public MessageReader MessageReader;
    public Organizer currentOrganizer;
    public User currentUser;
    public UseCases.UserFriendManager UserFriendManager;


    public OrganizerFriendListPresenter(User currentUser, UseCases.UserFriendManager UserFriendManager,
                                      MessageReader MessageReader) {
        super(currentUser, UserFriendManager, MessageReader);
        if(currentUser instanceof Organizer){
            Organizer currentOrganizer=(Organizer) currentUser;
            this.currentOrganizer=currentOrganizer;
        }
        if(UserFriendManager instanceof OrganizerFriendManager){
            OrganizerFriendManager OrganizerFriendManager=(OrganizerFriendManager) UserFriendManager;
            this.OrganizerFriendManager= OrganizerFriendManager;}
    }

    @Override
    public void DisplayMessageable(){
        ArrayList<User> messageableList = this.OrganizerFriendManager.get_MessageableList();
        for(User messageable: messageableList){
            System.out.println(messageable.get_name());
        }
    }

    /**
     * Display the command to add/remove an User from messageable list
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

