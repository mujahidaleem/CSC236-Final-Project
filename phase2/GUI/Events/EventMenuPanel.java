package GUI.Events;

import Controllers.EventMenu.EventMenuController;
import Entities.Events.Event;
import Entities.Users.User;
import GUI.GUIPanel;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;

import java.util.ArrayList;

public class EventMenuPanel extends GUIPanel {
    protected final int labelLayerX = 20;
    protected final int labelLayerY = 40;
    protected final int labelWidth = 120;
    protected final int labelHeight = 20;
    protected final int sideBarY = 20;
    protected final int sideBarX = 500;

    protected final int buttonLayerX = 10;
    protected final int buttonLayerY = 10;
    protected final int buttonLayerHeight = 20;
    protected final int buttonWidth = 120;
    protected int textFieldWidth = 60;

    private JLabel eventNameLabel;
    private JTextField eventNameTextField;

    private JButton returnToMainMenuButton;
    private JButton signUpButton;
    private JButton leaveButton;

    private JLabel mainHeading;
    private JLabel heading1;
    private JLabel heading2;
    private JTextArea eventsAttendingTextField;
    private JTextArea otherEventsTextField;

    private final EventMenuController eventMenuController;

    /**
     * Constructor for EventMenuPanel
     *
     * @param eventMenuController the controller that executes all the commands
     * @param frame               the original frame of the program
     */
    public EventMenuPanel(EventMenuController eventMenuController, JFrame frame, LanguageManager languageManager) {
        super(frame, languageManager);
        this.eventMenuController = eventMenuController;
    }

    /**
     * Sets up the GUI components
     */
    public void printMenu(String theme) {
        createHeading();
        showEvents();
        createLabels();
        createButtons();
        printExtraComponents();
        changeTheme(theme);
    }

    /**
     * Prints extra components special to this menu
     */
    public void printExtraComponents() {
    }

    /**
     * Creates the headings in the GUI
     */
    private void createHeading() {
        mainHeading = new JLabel();
        mainHeading.setBounds(labelLayerX, labelLayerY, labelWidth, labelHeight); //TODO: Set up bounds

        heading1 = new JLabel();
        heading1.setBounds(labelLayerX, labelLayerY + 20, labelWidth, labelHeight);

        heading2 = new JLabel();
        heading2.setBounds(labelLayerX, labelLayerY + 200, labelWidth, labelHeight);

        panel.add(mainHeading);
        panel.add(heading1);
        panel.add(heading2);
    }

    /**
     * Shows the events in the system to the user
     */
    private void showEvents() {
        eventsAttendingTextField = new JTextArea();
        eventsAttendingTextField.setBounds(labelLayerX + 20, labelLayerY + 2 * labelHeight, 400, 150);
        eventsAttendingTextField.setEditable(false);

        otherEventsTextField = new JTextArea();
        otherEventsTextField.setBounds(labelLayerX + 20, labelLayerY + 220, 400, 150);
        otherEventsTextField.setEditable(false);

        panel.add(eventsAttendingTextField);
        panel.add(otherEventsTextField);
    }

    /**
     * Creates the labels for the GUI
     */
    private void createLabels() {
        eventNameLabel = new JLabel();
        eventNameLabel.setBounds(sideBarX, 4 * sideBarY, labelWidth, labelHeight);

        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(sideBarX + 120, 4 * sideBarY, textFieldWidth, buttonLayerHeight);

        panel.add(eventNameLabel);
        panel.add(eventNameTextField);
    }

    /**
     * Creates the buttons for the GUI
     */
    private void createButtons() {
        returnToMainMenuButton = new JButton();
        returnToMainMenuButton.setBounds(sideBarX, sideBarY, buttonWidth, buttonLayerHeight);
        returnToMainMenuButton.addActionListener(e -> {
            eventMenuController.returnToMainMenu();
            clearText();
        });

        signUpButton = new JButton();
        signUpButton.setBounds(sideBarX, 6 * sideBarY, buttonWidth, buttonLayerHeight);
        signUpButton.addActionListener(e -> {
                    Event event = eventMenuController.eventManager.findEvent(eventNameTextField.getText());
                    if (event != null) {
                        eventMenuController.signUpForEvent(eventMenuController.eventManager.findEvent(eventNameTextField.getText()));
                    } else {
                        eventMenuController.showNullEventError();
                    }
                }
        );

        leaveButton = new JButton();
        leaveButton.setBounds(sideBarX, 8 * sideBarY, buttonWidth, buttonLayerHeight);
        leaveButton.addActionListener(e -> {
            Event event = eventMenuController.eventManager.findEvent(eventNameTextField.getText());
            if (event != null) {
                eventMenuController.removeSpotFromEvent(eventMenuController.eventManager.findEvent(eventNameTextField.getText()));
            } else {
                eventMenuController.showNullEventError();
            }
        });

        panel.add(returnToMainMenuButton);
        panel.add(signUpButton);
        panel.add(leaveButton);
    }

    /**
     * Generates the text for the GUI
     *
     * @param events       the list of events
     * @param user         the current user
     * @param languagePack contains the strings used for the text
     */
    public void setText(ArrayList<Event> events, User user, LanguagePack languagePack) {
        reprintEvents(events, user);
        setStrings(languagePack);
    }

    public void setStrings(LanguagePack languagePack){
        mainHeading.setText(languagePack.eventMenuHeadings()[0]);
        heading1.setText(languagePack.eventMenuHeadings()[1]);
        heading2.setText(languagePack.eventMenuHeadings()[2]);

        returnToMainMenuButton.setText(languagePack.eventStandardCommands()[0]);
        signUpButton.setText(languagePack.eventStandardCommands()[1]);
        leaveButton.setText(languagePack.eventStandardCommands()[2]);
        eventNameLabel.setText(languagePack.eventStandardCommands()[3]);
        setAdditionalText(languagePack);
    }

    public void reprintEvents(ArrayList<Event> events, User user) {
        StringBuilder eventsAttending = new StringBuilder();
        for (Event event : events) {
            if (user.getPersonalSchedule().containsKey(event.getEventName())) {
                eventsAttending.append(event.toString()).append("\n");
            }
        }
        StringBuilder otherEvents = new StringBuilder();
        for (Event event : events) {
            if (!user.getPersonalSchedule().containsKey(event.getEventName())) {
                otherEvents.append(event.toString()).append("\n");
            }
        }
        eventsAttendingTextField.setText(eventsAttending.toString());
        otherEventsTextField.setText(otherEvents.toString());
    }

    /**
     * Creates text for additional components special to this menu
     */
    public void setAdditionalText(LanguagePack languagePack) {
    }

    public void changeColours() {
        panel.setBackground(backgroundColour);
        eventNameLabel.setForeground(textColour);
        eventNameTextField.setForeground(textColour);
        returnToMainMenuButton.setForeground(textColour);
        signUpButton.setForeground(textColour);
        leaveButton.setForeground(textColour);
        mainHeading.setForeground(textColour);
        heading1.setForeground(textColour);
        heading2.setForeground(textColour);
        eventsAttendingTextField.setForeground(textColour);
        otherEventsTextField.setForeground(textColour);

        eventNameTextField.setBackground(textFieldColour);
        eventsAttendingTextField.setBackground(textFieldColour);
        otherEventsTextField.setBackground(textFieldColour);

        returnToMainMenuButton.setBackground(buttonColour1);
        signUpButton.setBackground(buttonColour1);
        leaveButton.setBackground(buttonColour1);
        changeColourOfExtraComponents();

    }

    public void changeText(LanguageManager languageManager){
        setStrings(languageManager.languagePack);
        setAdditionalText(languageManager.languagePack);
    }

    public void changeColourOfExtraComponents() {
    }

    public void clearText() {
        eventNameTextField.setText("");
        clearAdditionalText();
    }

    public void clearAdditionalText(){
    }
}
