package GUI.Events;

import Controllers.EventMenu.EventMenuController;
import Entities.Events.Event;
import Entities.Users.User;
import GUI.GUIPanel;
import GUI.MainMenuPanel;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EventMenuPanel extends GUIPanel {
    private int buttonLayerX;
    private int buttonLayerY;
    private int buttonLayerHeight;
    private int buttonWidth;
    private int textFieldWidth;

    private JButton returnToMainMenuButton;
    private JLabel signUpForLabel;
    private JTextField signUpTextField;
    private JButton signUpButton;

    private JLabel leaveLabel;
    private JTextField leaveTextField;
    private JButton leaveButton;

    private JTextField mainHeading;
    private JTextField heading1;
    private JTextField heading2;
    private JTextField eventsAttendingTextField;
    private JTextField otherEventsTextField;

    private EventMenuController eventMenuController;

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
        mainHeading = new JTextField();
        mainHeading.setEditable(false);
        mainHeading.setBounds(0,0,0,0); //TODO: Set up bounds

        panel.add(mainHeading);
    }

    private void showEvents(){

        eventsAttendingTextField = new JTextField();
        eventsAttendingTextField.setBounds(0,0,0,0); //TODO: set bounds
        eventsAttendingTextField.setEditable(false);

        panel.add(eventsAttendingTextField);
    }

    private void createLabels(){
        signUpForLabel = new JLabel();
        signUpForLabel.setBounds(buttonLayerX,buttonLayerY,buttonWidth,buttonLayerHeight); //TODO: set bounds

        signUpTextField = new JTextField();
        signUpTextField.setBounds(buttonLayerX + 30, buttonLayerY, textFieldWidth, buttonLayerHeight);

        leaveLabel = new JLabel();
        leaveLabel.setBounds(buttonLayerX + 200, buttonLayerY, buttonWidth, buttonLayerHeight);

        leaveTextField = new JTextField();
        leaveTextField.setBounds(buttonLayerX + 260, buttonLayerY, textFieldWidth, buttonLayerHeight);

        panel.add(signUpForLabel);
        panel.add(signUpTextField);
        panel.add(leaveLabel);
        panel.add(leaveTextField);
    }

    private void createButtons(){
        returnToMainMenuButton = new JButton();
        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventMenuController.returnToMainMenu();
            }
        });

        signUpButton = new JButton();
        signUpButton.setBounds(buttonLayerX + 60, buttonLayerY, buttonWidth, buttonLayerHeight);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventMenuController.signUpForEvent(eventMenuController.eventManager.findEvent(signUpTextField.getText()));
            }
        });

        leaveButton = new JButton();
        leaveButton.setBounds(buttonLayerX + 300, buttonLayerY, buttonWidth, buttonLayerHeight);
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventMenuController.removeSpotFromEvent(eventMenuController.eventManager.findEvent(leaveTextField.getText()));
            }
        });

        panel.add(returnToMainMenuButton);
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
    }

}
