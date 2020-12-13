package GUI.Events;

import Controllers.EventMenu.AdminEventController;

import Entities.Events.Event;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEventMenuPanel extends EventMenuPanel{
    private JButton deleteEventButton;
    private JButton showEventsWithNoAttendeesButton;

    private JTextArea eventsWithNoAttendeesTextField;
    private JTextArea instructions;
    private JTextField deleteEventTextField;

    private AdminEventController adminEventController;

    public AdminEventMenuPanel(AdminEventController adminEventController, JFrame frame, LanguageManager languageManager){
        super(adminEventController, frame, languageManager);
        this.adminEventController = adminEventController;
    }

    @Override
    public void printExtraComponents(){
        createShowEventsTextField();
        createExtraButtons();
    }

    public void createShowEventsTextField(){
        eventsWithNoAttendeesTextField = new JTextArea();
        eventsWithNoAttendeesTextField.setBounds(sideBarX, sideBarY + 350, 100, 100);
        eventsWithNoAttendeesTextField.setEditable(false);
        panel.add(eventsWithNoAttendeesTextField);

        instructions = new JTextArea();
        instructions.setBounds(sideBarX, sideBarY + 200, 180, 100);
        instructions.setLineWrap(true);
        instructions.setEditable(false);
        panel.add(instructions);

        deleteEventTextField = new JTextField();
        deleteEventTextField.setBounds(sideBarX, sideBarY + 470, 100, 20);
        panel.add(deleteEventTextField);
    }

    public void createExtraButtons(){
        showEventsWithNoAttendeesButton = new JButton();
        showEventsWithNoAttendeesButton.setBounds(sideBarX, sideBarY+320, buttonWidth+50, buttonLayerHeight);
        showEventsWithNoAttendeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder = new StringBuilder();
                for(Event event: adminEventController.eventManager.getEvents()){
                    if (event.getAttendees().size() == 0){
                        stringBuilder.append(event.getEventName()).append("\n");
                    }
                }
                eventsWithNoAttendeesTextField.setText(stringBuilder.toString());
            }
        });
        panel.add(showEventsWithNoAttendeesButton);

        deleteEventButton = new JButton();
        deleteEventButton.setBounds(sideBarX, sideBarY + 500, buttonWidth + 50, buttonLayerHeight);
        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminEventController.deleteEventWithNoAttendees(adminEventController.eventManager.findEvent(deleteEventTextField.getText()));
                clearAdditionalText();
                adminEventController.reprintEvents();
            }
        });
        panel.add(deleteEventButton);
    }

    public void setAdditionalText(LanguagePack languagePack){
        deleteEventButton.setText(languagePack.adminEventMenuPrompts()[0]); //TODO:
        showEventsWithNoAttendeesButton.setText(languagePack.adminEventMenuPrompts()[1]); //TODO:
        instructions.setText(languagePack.adminEventMenuPrompts()[2]);
    }

    public void clearAdditionalText(){
        deleteEventTextField.setText("");
    }

    public void changeColourOfExtraComponents(){
        deleteEventButton.setForeground(textColour);
        showEventsWithNoAttendeesButton.setForeground(textColour);
        eventsWithNoAttendeesTextField.setForeground(textColour);
        instructions.setForeground(textColour);
        deleteEventTextField.setForeground(textColour);

        deleteEventButton.setBackground(buttonColour1);
        showEventsWithNoAttendeesButton.setBackground(buttonColour1);

        eventsWithNoAttendeesTextField.setBackground(textFieldColour);
        instructions.setBackground(textFieldColour);
        deleteEventTextField.setBackground(textFieldColour);
    }
}
