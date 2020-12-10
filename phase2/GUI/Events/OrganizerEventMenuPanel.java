package GUI.Events;

import Controllers.EventMenu.OrganizerEventController;
import Entities.Events.Event;
import Entities.Users.User;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.util.ArrayList;

public class OrganizerEventMenuPanel extends EventMenuPanel{

    private JButton createEventButton;
    private JButton editEventButton;
    private JButton createAccountButton;

    private JLabel eventToBeChanged;
    private JTextField eventNameTextField;

    private final OrganizerEventController organizerEventController;

    /**
     * Constructor for OrganizerEventController
     * @param organizerEventController the controller that will execute commands
     * @param frame the initial frame of the program
     */
    public OrganizerEventMenuPanel(OrganizerEventController organizerEventController, JFrame frame){
        super(organizerEventController, frame);
        this.organizerEventController = organizerEventController;
    }

    /**
     * Creates extra components needed for the GUI
     */
    @Override
    public void printExtraComponents(){
        setExtraButtons();
        setEventNameTextField();
    }

    /**
     * Creates a text field so the user can specify an event by its name
     */
    public void setEventNameTextField(){
        eventToBeChanged = new JLabel();
        eventToBeChanged.setBounds(sideBarX, sideBarY + 450, labelWidth, labelHeight);

        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(sideBarX + 60, sideBarY + 450, textFieldWidth, 20);
        panel.add(eventNameTextField);
        panel.add(eventToBeChanged);
    }

    /**
     * Creates extra buttons needed for the organizer event menu GUI
     */
    public void setExtraButtons(){
        createEventButton = new JButton();
        createEventButton.setBounds(sideBarX, sideBarY + 400, buttonWidth, buttonLayerHeight);
        createEventButton.addActionListener(e -> {
            organizerEventController.showEditMenu(null);
            clearAdditionalText();
        });

        editEventButton = new JButton();
        editEventButton.setBounds(sideBarX, sideBarY + 500, buttonWidth, buttonLayerHeight);
        editEventButton.addActionListener(e -> {
            if(organizerEventController.eventModifiable(organizerEventController.eventManager.findEvent(eventNameTextField.getText()))){
                organizerEventController.showEditMenu(organizerEventController.eventManager.findEvent(eventNameTextField.getText()));
                clearAdditionalText();
            } else {
                organizerEventController.showNonModifiableEventPrompt(eventNameTextField.getText());
            }

        });

        createAccountButton = new JButton();
        createAccountButton.setBounds(sideBarX, sideBarY + 550, buttonWidth, buttonLayerHeight);
        createAccountButton.addActionListener(e -> {
            organizerEventController.showCreateAccountMenu();
            clearAdditionalText();
        });

        panel.add(createEventButton);
        panel.add(editEventButton);
    }

    /**
     * Sets the strings shown on the GUI
     */
    @Override
    public void setAdditionalText(){

    }

    /**
     * Rests the text in the text fields
     */
    public void clearAdditionalText(){
        eventNameTextField.setText("");
    }
}
