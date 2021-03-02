package Entities;


public class Message {
    int id;
    int senderId;
    String senderName;
    int recipientId;
    String recipientName;
    String content;

    /**
     * Message constructor
     *
     * @param sender    - the user sending the message
     * @param recipient - the user receiving the message
     * @param content   - the content of the message
     */
    public Message(int id, User sender, User recipient, String content) {
        this.id = id;
        this.senderId = sender.getId();
        this.senderName = sender.getName();
        this.recipientId = recipient.getId();
        this.recipientName = recipient.getName();
        this.content = content;
    }

    /**
     * getter for the id of the message
     */

    public int getId() {
        return id;
    }

    /**
     * getter for the sender of the message
     */

    public int getSenderId() {
        return senderId;
    }

    /**
     * getter for the recipient of the message
     */

    public int getRecipientId() {
        return recipientId;
    }

    /**
     * getter for the content of the message
     */

    public String getContent() {
        return this.content;
    }

    /**
     * returns a string representation of the message
     */

    @Override
    public String toString() {
        return this.senderName + " to " + this.recipientName + ": " + this.getContent();
    }
}