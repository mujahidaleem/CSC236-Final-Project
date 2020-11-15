package Entities;

public class Message {

    /**
     * Message constructor
     * @param sender - the user sending the message
     * @param content - the content of the message
     */

    User sender;
    String content;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    /**
     * getter for the sender of the message
     */

    public User getSender() {
        return this.sender;
    }

    /**
     * getter for the content of the message
     */

    public String getContent() {
        return this.content;
    }
}