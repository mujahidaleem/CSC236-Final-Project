package Presenters.MessageMenu;

import Controllers.MessageMenu.UserFriendListController;
import Entities.Users.User;
import UseCases.Language.LanguageManager;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MessageMenuPresenter {
    public UserFriendListController userFriendListcontroller;
    public UserManager userManager;
    public UserFriendManager userFriendManager;
    public LanguageManager languageManager;

    /**
     * MessageMenuPresenter Constructor
     * Presents the message menu of the user
     *
     * @param UserFriendListController Friend list controller for users
     * @param userManager              Use case for user functions
     * @param userFriendManager        Use case for user friend list functions
     * @param languageManager          Displays strings
     */
    public MessageMenuPresenter(UserFriendListController UserFriendListController, UserManager userManager, UserFriendManager userFriendManager, LanguageManager languageManager) {
        this.userFriendListcontroller = UserFriendListController;
        this.userManager = userManager;
        this.userFriendManager = userFriendManager;
        this.languageManager = languageManager;
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
        System.out.println(languageManager.languagePack.messageMenuHeadings()[0]);
        userFriendManager.displayFriend(userManager);
    }

    /**
     * Send a message to a user
     *
     * @param anotherUser The user that the current user wants to send a message to
     */
    protected void sendMessage(User anotherUser) {
        if (userFriendManager.messageable(anotherUser)) {
            while (true) {
                userFriendManager.displayChatLog(userManager.getCurrentUser(), anotherUser);
                System.out.println(languageManager.languagePack.sendMessagePrompt());
                Scanner message = new Scanner(System.in);
                String messageContent = message.nextLine();
                if (messageContent.equals("0")) {
                    break;
                } else {
                    userFriendListcontroller.sendingMessage(userManager.getCurrentUser(), anotherUser, messageContent, LocalDateTime.now());
                }
            }
        } else {
            System.out.println(languageManager.languagePack.unknownFriend());
        }
    }

    /**
     * Add a friend to the current user's friend list
     *
     * @param anotherUser the wanted user friend
     */
    protected void addFriend(User anotherUser) {
        if (userFriendListcontroller.addFriend(anotherUser)) {
            System.out.println(languageManager.languagePack.messageMenuResultsSuccess(anotherUser)[0]);
        } else {
            System.out.println(languageManager.languagePack.messageMenuResultsFailure()[0]);
        }
    }

    /**
     * Remove a friend from the current user's friend list
     *
     * @param anotherUser the unwanted user
     */
    protected void removeFriend(User anotherUser) {
        if (userFriendListcontroller.removeFrom(anotherUser)) {
            System.out.println(languageManager.languagePack.messageMenuResultsSuccess(anotherUser)[1]);
        } else {
            System.out.println(languageManager.languagePack.messageMenuResultsFailure()[1]);
        }
    }

    /**
     * Print's the command prompts that the user will see
     */
    protected void printCommands() {
        System.out.println(languageManager.languagePack.messageMenuHeadings()[1]);
        languageManager.languagePack.printMessageMenuStandardCommands();
    }
}