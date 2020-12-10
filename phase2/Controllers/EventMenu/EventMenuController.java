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

    /**
     * AttendeeEventController constructor
     *
     * @param userManager  contains the attendee using the current session
     * @param eventManager contains the list of events
     */
    public EventMenuController(UserManager userManager, EventManager eventManager, RoomManager roomManager, LanguageManager languageManager, JFrame frame, MainMenuPanel mainMenuPanel) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.mainMenuPanel = mainMenuPanel;
        this.eventMenuPanel = new EventMenuPanel(this, frame);
        this.presenter = new EventMenuPresenter(userManager, eventManager, languageManager, eventMenuPanel, mainMenuPanel);
    }

    public void printMenu(){
        presenter.setUpMenu();
    }

    /**
     * Checks if the current user can sign up for an event and signs them up if they can
     *
     * @param event the event the user is trying to attend
     * @return whether the user has signed up for the event
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
     * @return whether the user's spot has been removed from the event
     */
    public void removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            presenter.removalResult(true, event);
        } else {
            presenter.removalResult(false, event);
        }
    }

    public void showNullEventError(){
        presenter.showNullEventError();
    }

    public void returnToMainMenu(){
        presenter.returnToMainMenu();
    }
}
