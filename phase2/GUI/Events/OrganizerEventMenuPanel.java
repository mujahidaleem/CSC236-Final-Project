package GUI.Events;

import Controllers.EventMenu.NullEventException;
import Controllers.EventMenu.OrganizerEventController;
import UseCases.Language.LanguagePack;


import javax.swing.*;

public class OrganizerEventMenuPanel extends EventMenuPanel{

    private JButton createEventButton;
    private JButton editEventButton;
    private JButton createAccountButton;

    private JLabel eventToBeChanged;
    private JTextArea instructions;
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
        setTextFields();
    }

    /**
     * Creates a text field so the user can specify an event by its name
     */
    public void setTextFields(){
        instructions = new JTextArea();
        instructions.setBounds(sideBarX, sideBarY + 200, labelWidth + 150, labelHeight+20);
        instructions.setEditable(false);

        eventToBeChanged = new JLabel();
        eventToBeChanged.setBounds(sideBarX, sideBarY + 250, labelWidth, labelHeight);

        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(sideBarX + 80, sideBarY + 250, textFieldWidth, 20);
        panel.add(eventNameTextField);
        panel.add(eventToBeChanged);
        panel.add(instructions);
    }

    /**
     * Creates extra buttons needed for the organizer event menu GUI
     */
    public void setExtraButtons(){
        createEventButton = new JButton();
        createEventButton.setBounds(sideBarX, sideBarY + 300, buttonWidth + 40, buttonLayerHeight);
        createEventButton.addActionListener(e -> {
            organizerEventController.showEditMenu(null, true);
            clearAdditionalText();
        });

        editEventButton = new JButton();
        editEventButton.setBounds(sideBarX , sideBarY + 340, buttonWidth + 40, buttonLayerHeight);
        editEventButton.addActionListener(e -> {
            try{
                if(organizerEventController.eventModifiable(organizerEventController.eventManager.findEvent(eventNameTextField.getText()))){
                    organizerEventController.showEditMenu(organizerEventController.eventManager.findEvent(eventNameTextField.getText()), false);
                    clearAdditionalText();
                } else {
                    organizerEventController.showNonModifiableEventPrompt(eventNameTextField.getText());
                }
            } catch (NullEventException f){
                organizerEventController.showNullEventError();
            }
        });

        createAccountButton = new JButton();
        createAccountButton.setBounds(sideBarX, sideBarY + 380, buttonWidth + 40, buttonLayerHeight);
        createAccountButton.addActionListener(e -> {
            organizerEventController.showCreateAccountMenu();
            clearAdditionalText();
        });

        panel.add(createEventButton);
        panel.add(editEventButton);
        panel.add(createAccountButton);
    }

    /**
     * Sets the strings shown on the GUI
     */
    @Override
    public void setAdditionalText(LanguagePack languagePack){
        instructions.setText(languagePack.organizerEventCommands()[4]);
        eventToBeChanged.setText(languagePack.organizerEventCommands()[3]);
        createEventButton.setText(languagePack.organizerEventCommands()[0]);
        editEventButton.setText(languagePack.organizerEventCommands()[1]);
        createAccountButton.setText(languagePack.organizerEventCommands()[2]);
    }

    /**
     * Rests the text in the text fields
     */
    public void clearAdditionalText(){
        eventNameTextField.setText("");

    }

    public void changeColourOfExtraComponents(){
        createEventButton.setForeground(textColour);
        editEventButton.setForeground(textColour);
        createAccountButton.setForeground(textColour);

        eventToBeChanged.setForeground(textColour);
        instructions.setForeground(textColour);
        eventNameTextField.setForeground(textColour);

        createEventButton.setBackground(buttonColour1);
        editEventButton.setBackground(buttonColour1);
        createAccountButton.setBackground(buttonColour1);
        eventNameTextField.setBackground(textFieldColour);
        instructions.setBackground(backgroundColour);
    }
}
