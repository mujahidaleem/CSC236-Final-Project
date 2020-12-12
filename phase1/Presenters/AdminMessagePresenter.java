package Presenters;

import Controllers.AdminFriendListController;
import UseCases.AdminFriendManager;
import UseCases.AdminManager;
import UseCases.UserManager;

import java.util.Scanner;

public class AdminMessagePresenter extends MessageMenuPresenter {
    public Controllers.AdminFriendListController adminFriendListController;
    public UseCases.AdminManager adminManager;
    public UseCases.AdminFriendManager adminFriendManager;

    /**
     * Constructor of AdminMessagePresenter
     * AdminMessagePresenter presents the admin's message menu (friend list, messages etc.)
     *
     * @param adminFriendListController Controller for the friend list of the admin
     * @param userManager               Use case for the functions of the user
     * @param adminManager              Use case for the functions of the admin
     * @param adminFriendManager        Use case for the functions of the admin's friend list
     */
    public AdminMessagePresenter(Controllers.AdminFriendListController adminFriendListController, UserManager userManager, UseCases.AdminManager adminManager, UseCases.AdminFriendManager adminFriendManager) {
        super(adminFriendListController, userManager, adminFriendManager);
        this.adminFriendListController = adminFriendListController;
        this.adminManager = adminManager;
    }
}