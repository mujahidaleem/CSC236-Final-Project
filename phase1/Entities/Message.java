package Entities;

import Entities.User;

public class Message {

    /**
     * Message constructor
     * @param sender - the user sending the message
     * @param recepient - the user receiving the message
     * @param content - the content of the message
     */

    User sender;
    User recepient;
    String content;

    public Message(User sender, User recepient, String content) {
        this.sender = sender;
        this.recepient = recepient;
        this.content = content;
    }

    /**
     * getter for the sender of the message
     */

    public User getSender() {
        return this.sender;
    }

    /**
     * getter for the recepient of the message
     */

    public User getRecepient() {
        return this.recepient;
    }

    /**
     * getter for the content of the message
     */

    public String getContent() {
        return this.content;
    }
}