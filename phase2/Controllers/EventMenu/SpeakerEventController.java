package Controllers.EventMenu;

import Entities.Events.Event;
import GUI.Events.SpeakerEventMenuPanel;
import GUI.MainMenuPanel;
import Presenters.EventMenu.SpeakerEventPresenter;
import UseCases.Events.RoomManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.SpeakerManager;
import UseCases.Events.EventManager;

import javax.swing.*;

/**
 * Performs the commands inputted in the speaker event menu
 */
public class SpeakerEventController extends EventMenuController {
    private SpeakerManager speakerManager;
    private SpeakerEventPresenter eventPresenter;


    /**
     * SpeakerEventController constructor
     *
     * @param manager      contains the current speaker
     * @param eventManager contains the list of events
     */
    public SpeakerEventController(SpeakerManager manager, EventManager eventManager, RoomManager roomManager,
                                  LanguageManager languageManager, JFrame frame, MainMenuPanel mainMenuPanel) {
        super(manager, eventManager, roomManager, languageManager, frame, mainMenuPanel);
        this.speakerManager = manager;
        this.eventPresenter = new SpeakerEventPresenter(speakerManager, eventManager, languageManager,
                new SpeakerEventMenuPanel(this, frame, speakerManager, eventManager, languageManager), mainMenuPanel);
    }

    /**
     * Displays the event menu GUI and initializes it if it has not been initialized
     *
     * @param theme the theme of the GUI
     */
    public void printMenu(String theme) {
        currentTheme = theme;
        if (menuCreated) {
            eventPresenter.showEventMenu();
        } else {
            eventPresenter.setUpMenu(currentTheme);
            menuCreated = true;
        }
    }

    /**
     * Changes the theme of the current GUI
     *
     * @param theme the new theme
     */
    public void changeTheme(String theme) {
        eventPresenter.changeTheme(theme);
    }

    /**
     * Changes the language of the current GUI
     *
     * @param language the new theme
     */
    public void changeLanguage(String language) {
        eventPresenter.changeLanguage(language);
    }


    /**
     * Tries to removes the user from an event
     *
     * @param event the event the user is trying to cancel their spot from
     */
    @Override
    public void removeSpotFromEvent(Event event) {
        if (eventManager.removeUser(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            eventPresenter.removalResult(true, event);
        } else {
            eventPresenter.removalResult(false, event);
        }
    }

    /**
     * Tries to sign the user up for an event
     *
     * @param event the event the user is trying to attend
     */
    @Override
    public void signUpForEvent(Event event) {
        boolean canBook = eventManager.spaceAvailable(event) &&                                     // if event still Event and Room still has space
                roomManager.getRoomCapacity(event.getRoomNumber()) >= event.getTotalNum() + 1;
        if (canBook && eventManager.addUser(event, userManager.getCurrentUser())) {
            userManager.attendEvent(event);
            eventPresenter.signUpResult(true, event);
        } else {
            eventPresenter.signUpResult(false, event);
        }
    }
}