package Presenters;

import Controllers.MessageMenuController;
import Controllers.UserFriendListController;
import Entities.User;

import java.util.Scanner;

public class MessageMenuPresenter {
    public UserFriendListPresenter UserFriendListPresenter;
    public UserFriendListController UserFriendListcontroller;
    public User currentUser;
    public MessageMenuController messageMenuController;

    public MessageMenuPresenter(UserFriendListController UserFriendListController,
                                UserFriendListPresenter UserFriendListPresenter, User currentUser,
                                MessageMenuController MessageMenuController) {
        this.UserFriendListPresenter = UserFriendListPresenter;
        this.UserFriendListcontroller = UserFriendListController;
        this.currentUser = currentUser;
        this.messageMenuController = MessageMenuController;
    }


    public void run() {
        while (true) {
            System.out.println("do you want to return to main menu? (yes or no)");
            Scanner userInput0 = new Scanner(System.in);
            String answer1 = userInput0.nextLine();
            if (answer1 == "yes") {
                break;
            }
            if (answer1 == "no") {
                continue;
            } else {
                System.out.println("input is invalid");
            }
            this.messageMenuController.factoryMethod(this.currentUser);
            System.out.println("------------------------------------------------------------");
            Scanner userInput = new Scanner(System.in);
            System.out.println("please select a User and type in his/her User name: ");
            String UserName = userInput.nextLine();
            //match username with User
            User anotherUser = this.messageMenuController.matchNameWithUser(UserName);
            System.out.println("please select and type in the command number: ");
            System.out.println(("1. send message"));
            System.out.println("2. add friend");
            System.out.println("3. remove from friend list");
            Scanner userInput2 = new Scanner((System.in));
            int num = userInput2.nextInt();
            if (num == 1) {
                while (true) {
                    this.UserFriendListPresenter.DisplayChatLog(anotherUser);
                    System.out.println("please enter the message:");
                    Scanner message = new Scanner(System.in);
                    String messageContent = message.nextLine();
                    this.UserFriendListcontroller.sendingMessage(anotherUser, messageContent);
                    this.UserFriendListPresenter.DisplaySendingMessage(anotherUser);
                    System.out.println("do you want to return to friend list?(yes or no): ");
                    Scanner userInput3 = new Scanner(System.in);
                    String answer = userInput3.nextLine();
                    if (answer == "yes") {
                        break;
                    }
                    if (answer == "no") {
                        continue;
                    } else {
                        System.out.println("input is invalid");
                    }
                }}
                if (num == 2) {
                    this.UserFriendListcontroller.Add(anotherUser);
                    this.UserFriendListPresenter.AddMessage(anotherUser);
                    continue;
                }
                if (num == 3) {
                    this.UserFriendListcontroller.removeFrom(anotherUser);
                    this.UserFriendListPresenter.RemoveMessage(anotherUser);
                    continue;

                } else {
                    System.out.println("input is invalid");
                }
            }
        }
    }



