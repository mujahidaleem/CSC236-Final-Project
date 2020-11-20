package Presenters;

import Controllers.UserFriendListController;
import Entities.User;
import UseCases.UserFriendManager;
import UseCases.UserManager;

import java.util.Scanner;

public class MessageMenuPresenter {
    public UserFriendListController userFriendListcontroller;
    public UserManager userManager;
    public UserFriendManager userFriendManager;

    /**
     * MessageMenuPresenter Constructor
     * Presents the message menu of the user
     *
     * @param UserFriendListController Friend list controller for users
     * @param userManager              Use case for user functions
     * @param userFriendManager        Use case for user friend list functions
     */
    public MessageMenuPresenter(UserFriendListController UserFriendListController, UserManager userManager, UserFriendManager userFriendManager) {
        this.userFriendListcontroller = UserFriendListController;
        this.userManager = userManager;
        this.userFriendManager = userFriendManager;
    }

    /**
     * Runs a while loop and calls commands in order to switch "states"
     * First calls the printFriends method, and then shows the commands the user can input
     * Standard commands include; messaging others, adding friends, and removing friends
     * You can also exit this menu
     */
    public void run() {
        while (true) {
            printFriends();
            printCommands();
            Scanner userInput0 = new Scanner(System.in);
            String[] answer1 = userInput0.nextLine().split("_");
            if (answer1[0].equals("0")) {
                break;
            } else {
                if (!standardCommands(answer1)) {
                    extraCommands(answer1);
                }
            }
        }
    }

    /**
     * Standard commands that the user can input
     * Can input the following;
     * Send message: Send a message to a user
     * Add friend: Add a friend to the user's friend list
     * Remove friend: Remove a friend from the user's friend list
     *
     * @param answer Inputted command by the user
     * @return Returns if the command went through
     */
    public boolean standardCommands(String[] answer) {
        switch (answer[0]) {
            case "1":
                sendMessage(userManager.users.get(Integer.parseInt(answer[1]) - 1000));
                return true;
            case "2":
                addFriend(userManager.users.get(Integer.parseInt(answer[1]) - 1000));
                return true;
            case "3":
                removeFriend(userManager.users.get(Integer.parseInt(answer[1]) - 1000));
                return true;
            default:
                return false;
        }
    }

    /**
     * Prints back if the input is invalid
     *
     * @param answer Inputted command by the user
     */
    public void extraCommands(String[] answer) {
        System.out.println("Input is invalid.");
    }

    /**
     * Print the friend list of the user
     */
    //TODO: have a sign showing if there are new messages from that friend
    protected void printFriends() {
        System.out.println("Friend List");
        userFriendManager.displayFriend();
    }

    /**
     * Send a message to a user
     *
     * @param anotherUser The user that the current user wants to send a message to
     */
    protected void sendMessage(User anotherUser) {
        while (true) {
            userFriendManager.displayChatLog(userManager.getCurrentUser(), anotherUser);
            System.out.println("please enter the message, otherwise enter \"close chat log\".");
            Scanner message = new Scanner(System.in);
            String messageContent = message.nextLine();
            if (messageContent.equals("close chat log")) {
                break;
            } else {
                userFriendListcontroller.sendingMessage(userManager.getCurrentUser(), anotherUser, messageContent);
            }
        }
    }

    /**
     * Add a friend to the current user's friend list
     *
     * @param anotherUser the wanted user friend
     */
    protected void addFriend(User anotherUser) {
        if (userFriendListcontroller.addFriend(anotherUser)) {
            System.out.println(anotherUser.getName() + " is now your friend.");
        } else {
            System.out.println("Sorry, this user is already your friend.");
        }
    }

    /**
     * Remove a friend from the current user's friend list
     *
     * @param anotherUser the unwanted user
     */
    protected void removeFriend(User anotherUser) {
        if (userFriendListcontroller.removeFrom(anotherUser)) {
            System.out.println(anotherUser.getName() + " is no longer your friend.");
        } else {
            System.out.println("This user is already not your friend.");
        }
    }

    /**
     * Print's the command prompts that the user will see
     */
    protected void printCommands() {
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println(("To send a message to a user, type 1_userID"));
        System.out.println("To add a user, type 2_userID");
        System.out.println("TO remove a user, type 3_userID");
    }
}



