package Controllers.EventMenu;

import Entities.Events.Event;
import GUI.Events.EventMenuPanel;
import GUI.MainMenuPanel;
import Presenters.EventMenu.EventMenuPresenter;
import UseCases.Events.EventManager;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import javax.swing.*;

/**
 * Performs the commands inputted on the event menu
 */
public abstract class EventMenuController {
    public UserManager userManager;
    public EventManager eventManager;
    public RoomManager roomManager;

    private EventMenuPresenter presenter;
    private MainMenuPanel mainMenuPanel;
    private EventMenuPanel eventMenuPanel;

    protected boolean menuCreated = false;
    protected String currentTheme = "lightTheme";
    protected String currentLanguage = "english";

    /**
     * AttendeeEventController constructor
     *
     * @param languageManager stores the strings for the GUI
     * @param roomManager     stores the list of rooms
     * @param frame           the initial frame of the program
     * @param mainMenuPanel   the main menu
     * @param userManager     contains the attendee using the current session
     * @param eventManager    contains the list of events
     */
    public EventMenuController(UserManager userManager, EventManager eventManager, RoomManager roomManager, LanguageManager languageManager, JFrame frame, MainMenuPanel mainMenuPanel) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.mainMenuPanel = mainMenuPanel;
        this.eventMenuPanel = new EventMenuPanel(this, frame, languageManager);
        this.presenter = new EventMenuPresenter(userManager, eventManager, languageManager, eventMenuPanel, mainMenuPanel);
    }

    /**
     * Displays the GUI representing the event Menu for this user
     *
     * @param theme the theme of the GUI
     */
    public void printMenu(String theme) {
        currentTheme = theme;
        if (menuCreated) {
            presenter.showEventMenu();
        } else {
            presenter.setUpMenu(currentTheme);
            menuCreated = true;
        }
    }

    /**
     * Checks if the current user can sign up for an event and signs them up if they can
     *
     * @param event the event the user is trying to attend
     */
    public void signUpForEvent(Event event) {
        boolean canBook = eventManager.spaceAvailable(event) &&                                     // if event still Event and Room still has space
                roomManager.getRoomCapacity(event.getRoomNumber()) >= event.getTotalNum() + 1;

        if (canBook && eventManager.addUser(event, userManager.getCurrentUser())) {
            userManager.attendEvent(event);
            presenter.signUpResult(true, event);
        } else {
            presenter.signUpResult(false, event);
        }
    }

    /**
     * Checks if the user is currently attending an event and removes their spot if they are
     *
     * @param event the event the user is trying to cancel their spot from
     */
    public void removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            presenter.removalResult(true, event);
        } else {
            presenter.removalResult(false, event);
        }
    }

    /**
     * Tells the user that the event they typed could not be found
     */
    public void showNullEventError() {
        presenter.showNullEventError();
    }

    /**
     * Displays the GUI of the main menu
     */
    public void returnToMainMenu() {
        presenter.returnToMainMenu();
    }

    /**
     * Changes the theme of the current GUI
     *
     * @param theme the new theme
     */
    public void changeTheme(String theme) {
        presenter.changeTheme(theme);
        currentTheme = theme;
    }

    /**
     * Changes the language of the GUI
     * @param language the new language
     */
    public void changeLanguage(String language){
        currentLanguage = language;
        presenter.changeLanguage(language);
    }

    /**
     * Checks if the menu has been already set up
     *
     * @return if the menu has already been set up
     */
    public boolean isMenuCreated() {
        return menuCreated;
    }
}
