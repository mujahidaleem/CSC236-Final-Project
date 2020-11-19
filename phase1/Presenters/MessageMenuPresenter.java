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

    public MessageMenuPresenter(UserFriendListController UserFriendListController, UserManager userManager, UserFriendManager userFriendManager) {
        this.userFriendListcontroller = UserFriendListController;
        this.userManager = userManager;
        this.userFriendManager = userFriendManager;
    }

    public void run() {
        while (true) {
            printFriends();
            printCommands();
            System.out.println("do you want to return to main menu? (yes or no)");
            Scanner userInput0 = new Scanner(System.in);
            String[] answer1 = userInput0.nextLine().split("_");
            if (answer1[0].equals("0")) {
                break;
            }
            User user = userManager.users.get(Integer.parseInt(answer1[1]) - 1000);
            switch (answer1[0]) {
                case "1":
                    sendMessage(user);
                    break;
                case "2":
                    addFriend(user);
                    break;
                case "3":
                    removeFriend(user);
                    break;
                default:
                    System.out.println("input is invalid");
                    break;
            }
        }
    }

    protected void printFriends(){
        System.out.println("Friend List");
        userFriendManager.displayFriend();
    }

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

    protected void addFriend(User anotherUser){
        if(userFriendListcontroller.addFriend(userManager.getCurrentUser(), anotherUser)){
            System.out.println(anotherUser.getName() + " is now your friend.");
        } else{
            System.out.println("Sorry, this user is already your friend.");
        }
    }

    protected void removeFriend(User anotherUser){
        if(userFriendListcontroller.removeFrom(userManager.getCurrentUser(), anotherUser)){
            System.out.println(anotherUser.getName() + " is no longer your friend.");
        } else {
            System.out.println("This user is already not your friend.");
        }
    }

    protected void printCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println(("To send a message to a user, type 1_userID"));
        System.out.println("To add a user, type 2_userID");
        System.out.println("TO remove a user, type 3_userID");
    }
}



