package Presenters;

import Controllers.EventMenuController;
import UseCases.AdminManager;
import UseCases.EventManager;

/**
 * Displays the event menu for an admin and interprets
 * the commands inputted by the admin on this menu
 */

public class AdminEventPresenter extends EventMenuPresenter {

    /**
     * AdminEventPresenter constructor
     *
     * @param adminManager stores the current user
     */
    public AdminEventPresenter(UseCases.AdminManager adminManager, EventMenuController eventMenuController, EventManager eventManager, String language) {
        super(adminManager, eventMenuController, eventManager, language);
    }

}