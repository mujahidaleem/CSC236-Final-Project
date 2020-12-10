package GUI.Events;

import Controllers.EventMenu.AdminEventController;
import UseCases.Events.EventManager;

import Entities.Events.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEventMenuPanel extends EventMenuPanel{
    private JButton deleteEventButton;
    private JButton showEventsWithNoAttendeesButton;

    private JTextField eventsWithNoAttendeesTextField;
    private JTextField deleteEventTextField;

    private AdminEventController adminEventController;

    public AdminEventMenuPanel(AdminEventController adminEventController, JFrame frame){
        super(adminEventController, frame);
        this.adminEventController = adminEventController;
    }

    @Override
    public void printExtraComponents(){
        createShowEventsTextField();
        createExtraButtons();
    }

    public void createShowEventsTextField(){
        eventsWithNoAttendeesTextField = new JTextField();
        eventsWithNoAttendeesTextField.setBounds(sideBarX, sideBarY + 420, 100, 100);
        eventsWithNoAttendeesTextField.setEditable(false);
        panel.add(eventsWithNoAttendeesTextField);
    }

    public void createExtraButtons(){
        showEventsWithNoAttendeesButton = new JButton();
        showEventsWithNoAttendeesButton.setBounds(sideBarX, sideBarY+400, buttonWidth, buttonLayerHeight);
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
        deleteEventButton.setBounds(sideBarX, sideBarY + 550, buttonWidth, buttonLayerHeight);
        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setAdditionalText(){

    }
}
