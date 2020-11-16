package UseCases;

public abstract class UserFriendManager {

    /**
     * UserFriendManager constructor
     * @param currentUser - the current user
     */

    User currentUser;

    public UserFriendManager(User currentUser) {
        this.user = currentUser;
    }

    /**
     * @return true iff otherUser is in the friend list of the current user
     */

    public boolean messagable(User otherUser) {
        return this.user.getFriendList().contains(otherUser);
        }

    }

    /**
     * @return a list of all messages sent between the current user and otherUser
     */

    public ArrayList<Message> checkHistoryMessage(User otherUser) {

    }

    /**
     * send the Message message from the current user to otherUser
     */

    public void sendMessageTo(String messageContent, User otherUser) {
        Message message = Message(this.user, messageContent);

    }

    /**
     * add newFriend to the current user's friend list
     */

    public void addNewFriend(User newFriend) {
        this.user.getFriendList().add(newFriend);
    }

    /**
     * remove friend from current user's friend list
     */

    public void removeFromFriendList(User friend) {
        this.user.getFriendList().remove(friend);
    }

}