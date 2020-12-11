package Presenters.EventMenu;

import Controllers.EventMenu.NullSpeakerException;
import Entities.Events.Event;
import Entities.Users.Speaker;
import Controllers.EventMenu.OrganizerEventController;
import Entities.Users.User;
import GUI.Events.CreateAccountPanel;
import GUI.Events.EditEventPanel;
import GUI.Events.OrganizerEventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Events.SameEventNameException;
import UseCases.Language.LanguageManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Displays the event menu for an organizer and interprets the
 * commands inputted by the organizer
 */
public class OrganizerEventPresenter extends EventMenuPresenter {
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;

    private OrganizerEventMenuPanel organizerEventMenuPanel;
    private EditEventPanel editEventPanel;
    private CreateAccountPanel createAccountPanel;

    /**
     * EventMenuPresenter constructor
     *
     * @param organizerManager         stores the current user
     * @param speakerManager           stores a list of speakers
     * @param languageManager          decides which language is used in the UI
     */
    public OrganizerEventPresenter(OrganizerManager organizerManager, SpeakerManager speakerManager,
                                   EventManager eventManager, LanguageManager languageManager,
                                   OrganizerEventMenuPanel organizerEventMenuPanel, EditEventPanel editEventPanel,
                                   MainMenuPanel mainMenuPanel, CreateAccountPanel createAccountPanel) {
        super(organizerManager, eventManager, languageManager, organizerEventMenuPanel, mainMenuPanel);
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.organizerEventMenuPanel = organizerEventMenuPanel;
        this.editEventPanel = editEventPanel;
        this.createAccountPanel = createAccountPanel;
    }

    public void changeEventInformationResults(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), ""); //TODO: change this
    }

    public void changeEventDurationFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), ""); //TODO: change this
    }

    public void changeEventCapacityFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), ""); //TODO: change this
    }

    public void changeEventRoomFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), ""); //TODO: change this
    }

    public void setUpMenu(String theme){
        organizerEventMenuPanel.printMenu(theme);
        showEventMenu();
    }

    public void showEventMenu(){
        organizerEventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        editEventPanel.changePanel(organizerEventMenuPanel.getPanel());
    }

    public void showEditMenu(Event event, boolean i){
        editEventPanel.setText(event, languageManager.languagePack);
        organizerEventMenuPanel.changePanel(editEventPanel.getPanel());
    }

    public void setUpEditMenu(Event event, boolean i, String theme){
        editEventPanel.printMenu(i, theme);
        showEditMenu(event, i);
    }

    public void setUpCreateAccountMenu(String theme){
        createAccountPanel.setUpMenu(theme);
        showCreateAccountMenu();
    }

    public void showCreateAccountMenu(){
        organizerEventMenuPanel.changePanel(createAccountPanel.getPanel());
        createAccountPanel.setText(languageManager.languagePack);
    }

    public int showAddSpeakerPrompt(){
        return Integer.parseInt(JOptionPane.showInputDialog("")); //TODO: Change the text
    }

    public void showNullSpeaker(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), "", "Warning", JOptionPane.WARNING_MESSAGE); //TODO: change the text
    }

    public void showNonModifiableEventPrompt(String string){
        JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), "", "Warning", JOptionPane.WARNING_MESSAGE); //TODO: change the text
    }

    public void showIncorrectDate(){
        JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), "", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Prints the result of trying to create a new event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that the organizer is trying to create
     */
    public void createEventResults(boolean i, Event event) {
        if (i) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), event.getEventName() + " was successfully created"); //TODO: change this
        } else {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), "The event could not be created", "Error", JOptionPane.WARNING_MESSAGE); //TODO: change this
        }
    }

    /**
     * Prints the result of trying to assign a speaker to an event
     *
     * @param i     determines whether the command was successful or not
     */
    public void addSpeakerResults(boolean i, Speaker speaker) {
        if (i) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), speaker.getName() + ""); //TODO: change this
        } else {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), speaker.getName() + ""); //TODO: change this
        }
    }

    /**
     * Prints the results of trying to remove a speaker from an event
     *
     * @param i     Determines whether the command was successful or not
     */
    public void removeSpeakerResults(boolean i, Speaker speaker) {
        if (i) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), speaker.getName() + ""); //TODO: change this
        } else {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), speaker.getName() + ""); //TODO: change this
        }
    }

    /**
     * Prints the result of trying to change the time of an event
     *
     */
    public void changeEventDateFailure() {
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), ""); //TODO: change this
    }

    /**
     * Prints the result of trying to change the room of an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event whose date is trying to be changed by the organizer
     */
    public void changeEventRoomResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[5]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[5]);
        }
    }

    /**
     * Prints the result of trying to remove an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that is being deleted by the organizer
     */
    public void deleteEvent(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[4]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[4]);
        }
    }

    /**
     * Prints the result of trying to create a speaker account
     *
     * @param i       determines whether the command was successful or not
     * @param user    the new user account that has been created
     */
    public void createUserAccountResults(boolean i, User user, String type) {
        if (i) {
            JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), languageManager.languagePack.organizerAccountCreationSuccess(user, type));
        } else {
            JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), languageManager.languagePack.accountCreationFailure());
        }
    }

    /**
     * Prints the list of speakers
     */
    private void printSpeakers() {
        System.out.println("------------------------------------------------------");
        for (Speaker speaker : speakerManager.getSpeakers()) {
            System.out.println(speaker);
        }
    }

    public void reprintEvents(){
        organizerEventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
    }

    public void changeTheme(String theme){
        organizerEventMenuPanel.changeTheme(theme);
    }
    public void changeEditPanelTheme(String theme){
        editEventPanel.changeTheme(theme);
    }
    public void changeAccountPanelTheme(String theme){
        createAccountPanel.changeTheme(theme);
    }

    public void changeLanguage(String language){
        organizerEventMenuPanel.changeLanguage(language);
    }
    public void changeEditPanelLanguage(String theme){
        editEventPanel.changeLanguage(theme);
    }
    public void changeAccountPanelLanguage(String theme){
        createAccountPanel.changeLanguage(theme);
    }
}
