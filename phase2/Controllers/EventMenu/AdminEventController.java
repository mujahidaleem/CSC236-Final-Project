package Controllers.EventMenu;

import Entities.Events.Event;
import GUI.Events.AdminEventMenuPanel;
import GUI.MainMenuPanel;
import Presenters.EventMenu.AdminEventPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;
import UseCases.Users.SpeakerManager;
import UseCases.Users.UserManager;

import javax.swing.*;

public class AdminEventController extends EventMenuController {
    private SpeakerManager speakerManager;
    private AdminManager adminManager;

    private AdminEventPresenter adminEventPresenter;

    /**
     * Constructor for AdminEventController
     *
     * @param userManager     stores the list of users
     * @param eventManager    stores the list of events
     * @param roomManager     stores the list of rooms
     * @param languageManager stores the strings to be displayed on the GUI
     * @param speakerManager  stores the list of speakers
     * @param adminManager    stores the list of admins
     * @param frame           the orignial frame of the program
     * @param mainMenuPanel   the main menu
     */
    public AdminEventController(UserManager userManager, EventManager eventManager, RoomManager roomManager, LanguageManager languageManager,
                                SpeakerManager speakerManager, AdminManager adminManager, JFrame frame, MainMenuPanel mainMenuPanel) {
        super(userManager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        this.speakerManager = speakerManager;
        this.adminManager = adminManager;
        this.adminEventPresenter = new AdminEventPresenter(adminManager, eventManager, languageManager,
                new AdminEventMenuPanel(this, frame, languageManager), mainMenuPanel);
    }

    /**
     * Deletes the event if it has no attendees
     *
     * @param event the event that may be deleted
     */
    public void deleteEventWithNoAttendees(Event event) {
        try {
            if (adminManager.cancelEventWithoutAttendee(event, eventManager, userManager, speakerManager)) {
                adminEventPresenter.deleteEventResult(true);
            } else {
                adminEventPresenter.deleteEventResult(false);
            }
        } catch (NullEventException e) {
            adminEventPresenter.showNullEventError();
        }
    }

    /**
     * Prints the GUI for the event menu of a admin user
     */
    @Override
    public void printMenu(String theme) {
        currentTheme = theme;
        if (menuCreated) {
            adminEventPresenter.showEventMenu();
        } else {
            adminEventPresenter.setUpMenu(currentTheme);
            menuCreated = true;
        }
    }

    /**
     * @param event the event the user is trying to cancel their spot from
     */
    @Override
    public void removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            adminEventPresenter.removalResult(true, event);
        } else {
            adminEventPresenter.removalResult(false, event);
        }
    }

    /**
     * Tries to sign up the user to an event
     *
     * @param event the event the user is trying to attend
     */
    @Override
    public void signUpForEvent(Event event) {
        boolean canBook = eventManager.spaceAvailable(event) &&                                     // if event still Event and Room still has space
                roomManager.getRoomCapacity(event.getRoomNumber()) >= event.getTotalNum() + 1;
        if (canBook && eventManager.addUser(event, userManager.getCurrentUser())) {
            userManager.attendEvent(event);
            adminEventPresenter.signUpResult(true, event);
        } else {
            adminEventPresenter.signUpResult(false, event);
        }
    }

    /**
     * Reprints the GUI of the current panel
     */
    public void reprintEvents() {
        adminEventPresenter.reprintEvents();
    }


    /**
     * Changes the language of the GUI
     * @param language the new language
     */
    public void changeLanguage(String language){
        adminEventPresenter.changeLanguage(language);
    }

    /**
     * Changes the theme of the current GUI
     *
     * @param theme the new theme
     */
    public void changeTheme(String theme) {
        adminEventPresenter.changeTheme(theme);
    }
}
