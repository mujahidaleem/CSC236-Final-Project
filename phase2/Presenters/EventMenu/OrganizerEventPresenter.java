package Presenters.EventMenu;

import Entities.Events.Event;
import Entities.Users.Speaker;
import Entities.Users.User;
import GUI.Events.CreateAccountPanel;
import GUI.Events.EditEventPanel;
import GUI.Events.OrganizerEventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.OrganizerManager;
import UseCases.Users.SpeakerManager;

import javax.swing.*;


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

    /**
     * Change the information regarding events
     * @param event given event that needs to be changed
     */
    public void changeEventInformationResults(Event event){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.changeEventInfoResults(event));
    }

    /**
     * Show an event duration failure prompt on failure to change the duration of an event
     */
    public void changeEventDurationFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.changeEventDurationFailure());
    }

    /**
     * Show an event capacity failure prompt on failure to change the capacity of an event
     */
    public void changeEventCapacityFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.changeEventDurationFailure());
    }

    /**
     * how an event room failure prompt on failure to change the room of an event
     */
    public void changeEventRoomFailure(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.changeEventRoomFailure());
    }

    /**
     * Sets up the menu and themes it
     * @param theme theme of the program
     */
    public void setUpMenu(String theme){
        organizerEventMenuPanel.printMenu(theme);
        showEventMenu();
    }

    /**
     * Shows the event menu
     */
    public void showEventMenu(){
        organizerEventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        editEventPanel.changePanel(organizerEventMenuPanel.getPanel());
    }

    /**
     * Shows the edit menu options for an event
     * @param event event that is being edited
     * @param i boolean that sets the mode of the event panel
     */
    public void showEditMenu(Event event, boolean i){
        editEventPanel.setMode(i);
        editEventPanel.setText(event, languageManager.languagePack);
        organizerEventMenuPanel.changePanel(editEventPanel.getPanel());
    }

    /**
     * Sets up the edit menu to be displayed
     * @param event event that is being edited
     * @param i boolean that sets the mode of the event panel
     * @param theme theme of the event panel
     */
    public void setUpEditMenu(Event event, boolean i, String theme){
        editEventPanel.printMenu(i, theme);
        showEditMenu(event, i);
    }

    /**
     * Sets up the account create menu
     * @param theme theme of the program
     */
    public void setUpCreateAccountMenu(String theme){
        createAccountPanel.setUpMenu(theme);
        showCreateAccountMenu();
    }

    /**
     * Shows the account create menu
     */
    public void showCreateAccountMenu(){
        organizerEventMenuPanel.changePanel(createAccountPanel.getPanel());
        createAccountPanel.setText(languageManager.languagePack);
    }

    public int showAddSpeakerPrompt(){
        return Integer.parseInt(JOptionPane.showInputDialog(languageManager.languagePack.changeEventPrompts()[19]));
    }

    public void showNullSpeaker(){
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.unknownSpeaker(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void showNonModifiableEventPrompt(String string){
        JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), languageManager.languagePack.eventUnchangeable(string), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void showIncorrectDate(){
        JOptionPane.showMessageDialog(organizerEventMenuPanel.getPanel(), languageManager.languagePack.unknownDate(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Prints the result of trying to create a new event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that the organizer is trying to create
     */
    public void createEventResults(int i, Event event) {
        if (i == 3) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.organizerEventResultsSuccess(event));
        } else if (i==0) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.organizerEventResultsFailure(event)[0], "Error", JOptionPane.WARNING_MESSAGE);
        } else if (i==1){
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.organizerEventResultsFailure(event)[1], "Error", JOptionPane.WARNING_MESSAGE);
        } else if (i==2){
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.organizerEventResultsFailure(event)[2], "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Prints the result of trying to assign a speaker to an event
     *
     * @param i     determines whether the command was successful or not
     */
    public void addSpeakerResults(boolean i, Speaker speaker) {
        if (i) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.speakerAdditionSuccess(speaker));
        } else {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.speakerAdditionFailure(speaker));
        }
    }

    /**
     * Prints the results of trying to remove a speaker from an event
     *
     * @param i     Determines whether the command was successful or not
     */
    public void removeSpeakerResults(boolean i, Speaker speaker) {
        if (i) {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.speakerRemovalSuccess(speaker));
        } else {
            JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.speakerRemovalFailure(speaker));
        }
    }

    /**
     * Prints the result of trying to change the time of an event
     *
     */
    public void changeEventDateFailure() {
        JOptionPane.showMessageDialog(editEventPanel.getPanel(), languageManager.languagePack.changeEventTimeFailure());
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
     * Changes the theme of the program
     * @param theme Theme of the program
     */
    public void changeTheme(String theme){
        organizerEventMenuPanel.changeTheme(theme);
    }

    /**
     * Change the theme from the edit panel
     * @param theme Theme of the program
     */
    public void changeEditPanelTheme(String theme){
        editEventPanel.changeTheme(theme);
    }

    /**
     * Change theme from the account panel
     * @param theme Theme of the program
     */
    public void changeAccountPanelTheme(String theme){
        createAccountPanel.changeTheme(theme);
    }

    /**
     * Change language of the program button
     * @param language Language of the program
     */
    public void changeLanguage(String language){
        organizerEventMenuPanel.changeLanguage(language);
    }

    /**
     * Change language from edit panel
     * @param theme Theme of the program
     */
    public void changeEditPanelLanguage(String theme){
        editEventPanel.changeLanguage(theme);
    }

    /**
     * Change theme from account panel
     * @param theme theme of the program
     */
    public void changeAccountPanelLanguage(String theme){
        createAccountPanel.changeLanguage(theme);
    }
}
