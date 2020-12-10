package GUI.Events;

import Controllers.EventMenu.EventMenuController;
import Entities.Events.Event;
import Entities.Users.User;
import GUI.GUIPanel;
import UseCases.Language.LanguagePack;

import javax.swing.*;

import java.util.ArrayList;

public class EventMenuPanel extends GUIPanel {
    private final int labelLayerX = 20;
    private final int labelLayerY = 40;
    private final int labelWidth = 120;
    private final int labelHeight = 20;
    private final int sideBarY = 20;
    private final int sideBarX = 500;

    private final int buttonLayerX = 10;
    private final int buttonLayerY = 10;
    private final int buttonLayerHeight = 20;
    private final int buttonWidth = 100;
    private int textFieldWidth = 60;

    private JLabel eventNameLabel;
    private JTextField eventNameTextField;

    private JButton returnToMainMenuButton;
    private JButton signUpButton;
    private JButton leaveButton;

    private JLabel mainHeading;
    private JLabel heading1;
    private JLabel heading2;
    private JTextField eventsAttendingTextField;
    private JTextField otherEventsTextField;

    private final EventMenuController eventMenuController;

    public EventMenuPanel(EventMenuController eventMenuController, JFrame frame){
        super(frame);
        this.eventMenuController = eventMenuController;
    }

    public void printMenu(){
        createHeading();
        showEvents();
        createLabels();
        createButtons();
    }

    private void createHeading(){
        mainHeading = new JLabel();
        mainHeading.setBounds(labelLayerX,labelLayerY,labelWidth,labelHeight); //TODO: Set up bounds

        heading1 = new JLabel();
        heading1.setBounds(labelLayerX, labelLayerY + 20, labelWidth, labelHeight);

        heading2 = new JLabel();
        heading2.setBounds(labelLayerX, labelLayerY + 350, labelWidth, labelHeight);

        panel.add(mainHeading);
        panel.add(heading1);
        panel.add(heading2);
    }

    private void showEvents(){
        eventsAttendingTextField = new JTextField();
        eventsAttendingTextField.setBounds(labelLayerX + 20,labelLayerY + 2*labelHeight,400,300); //TODO: set bounds
        eventsAttendingTextField.setEditable(false);

        otherEventsTextField = new JTextField();
        otherEventsTextField.setBounds(labelLayerX + 20,labelLayerY + 400,400,300); //TODO: set bounds
        otherEventsTextField.setEditable(false);

        panel.add(eventsAttendingTextField);
        panel.add(otherEventsTextField);
    }

    private void createLabels(){
        eventNameLabel = new JLabel();
        eventNameLabel.setBounds(sideBarX, 4*sideBarY, labelWidth, labelHeight);

        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(sideBarX + 120, 4*sideBarY, textFieldWidth, buttonLayerHeight);

        panel.add(eventNameLabel);
        panel.add(eventNameTextField);
    }

    private void createButtons(){
        returnToMainMenuButton = new JButton();
        returnToMainMenuButton.setBounds(sideBarX, sideBarY, buttonWidth, buttonLayerHeight);
        returnToMainMenuButton.addActionListener(e -> eventMenuController.returnToMainMenu());

        signUpButton = new JButton();
        signUpButton.setBounds(sideBarX, 6*sideBarY, buttonWidth, buttonLayerHeight);
        signUpButton.addActionListener(e -> eventMenuController.signUpForEvent(eventMenuController.eventManager.findEvent(eventNameTextField.getText())));

        leaveButton = new JButton();
        leaveButton.setBounds( sideBarX, 8*sideBarY, buttonWidth, buttonLayerHeight);
        leaveButton.addActionListener(e -> eventMenuController.removeSpotFromEvent(eventMenuController.eventManager.findEvent(eventNameTextField.getText())));

        panel.add(returnToMainMenuButton);
        panel.add(signUpButton);
        panel.add(leaveButton);
    }

    public void setText(ArrayList<Event> events, User user, LanguagePack languagePack){
        //TODO: Edit this
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

        mainHeading.setText("EVENTS");
        heading1.setText(languagePack.eventMenuHeadings()[0]);
        heading2.setText(languagePack.eventMenuHeadings()[1]);

        returnToMainMenuButton.setText(languagePack.eventStandardCommands()[0]);
        signUpButton.setText(languagePack.eventStandardCommands()[1]);
        leaveButton.setText(languagePack.eventStandardCommands()[2]);
        eventNameLabel.setText(languagePack.eventStandardCommands()[3]);

    }

}
