package Presenters.EventMenu;

import Entities.Events.Event;
import GUI.Events.EventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import javax.swing.*;

/**
 * Displays the event menu and interprets inputs made by the user on this menu
 */
public class EventMenuPresenter {
    protected UserManager manager;
    protected EventManager eventManager;
    protected LanguageManager languageManager;

    private EventMenuPanel eventMenuPanel;
    protected MainMenuPanel mainMenuPanel;


    /**
     * EventMenuPresenter constructor
     *
     * @param eventManager    contains all the events
     * @param mainMenuPanel   contains the GUI of the main menu
     * @param eventMenuPanel  contains the GUI of hte event menu
     * @param manager         stores the current user
     * @param languageManager prints the strings in the current language of the system
     */
    public EventMenuPresenter(UserManager manager, EventManager eventManager, LanguageManager languageManager, EventMenuPanel eventMenuPanel, MainMenuPanel mainMenuPanel) {
        this.manager = manager;
        this.eventManager = eventManager;
        this.languageManager = languageManager;
        this.eventMenuPanel = eventMenuPanel;
        this.mainMenuPanel = mainMenuPanel;
    }

    /**
     * Sets up the components of the GUI
     */
    public void setUpMenu(String theme) {
        eventMenuPanel.printMenu(theme);
        showEventMenu();
    }

    public void showEventMenu(){
        eventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        eventMenuPanel.changePanel(eventMenuPanel.getPanel());
    }

    public void showNullEventError(){
        JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.unknownEvent(),"Warning", JOptionPane.WARNING_MESSAGE); //TODO:
    }

    /**
     * Prints the result of trying to sign up for an event
     *
     * @param i     whether the signup was successful or not
     * @param event the event that the user is trying to sign up for
     */
    public void signUpResult(boolean i, Event event) {
        if (i) {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsSuccess(event)[1]);
            eventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
        } else {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsFailure(event)[1]);
        }
    }

    /**
     * Prints the result of trying to cancel the users spot from an event
     *
     * @param i     whether the removal was successful or not
     * @param event the event that the user is trying to cancel their spot from
     */
    public void removalResult(boolean i, Event event) {
        if (i) {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsSuccess(event)[2]);
            eventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
        } else {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsFailure(event)[2]);
        }
    }

    /**
     * Tells the GUI to return to the main menu panel
     */
    public void returnToMainMenu() {
        eventMenuPanel.changePanel(mainMenuPanel.getPanel());
    }

    public void changeTheme(String theme){
        eventMenuPanel.changeTheme(theme);
    }

    public void changeLanguage(String language){
        eventMenuPanel.changeLanguage(language);
    }

}
