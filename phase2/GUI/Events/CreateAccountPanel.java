package GUI.Events;

import Controllers.EventMenu.OrganizerEventController;
import GUI.GUIPanel;
import UseCases.Language.LanguagePack;

import javax.swing.*;

public class CreateAccountPanel extends GUIPanel {
    private final int labelX = 20;
    private final int labelY = 30;
    private final int labelYHeightIncrement = 40;
    private final int labelWidth = 100;
    private final int labelHeight = 20;

    private final int textWidth = 100;
    private final int textHeight = 20;

    private final int buttonX = 20;
    private final int buttonY = 100;
    private final int buttonWidth = 100;
    private final int buttonHeight = 30;

    private JButton createAccountButton;
    private JButton cancelButton;

    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JComboBox<String> accountType;

    private String[] types;

    private JTextField nameTextField;
    private JTextField passwordTextField;

    private OrganizerEventController organizerEventController;

    /**
     * Constructor for CreateAccountPanel
     *
     * @param frame                    the original frame of the program
     * @param organizerEventController the controller that executes all the commands
     * @param languagePack             contains the strings that will be shown on the GUI
     */
    public CreateAccountPanel(JFrame frame, OrganizerEventController organizerEventController, LanguagePack languagePack) {
        super(frame);
        this.organizerEventController = organizerEventController;
        this.types = new String[]{languagePack.userCreationPrompt()[2], languagePack.userCreationPrompt()[3],
                languagePack.userCreationPrompt()[6], languagePack.userCreationPrompt()[7]};
    }

    /**
     * Creates the components of the GUI
     */
    public void setUpMenu() {
        createLabels();
        createType();
        createTextFields();
        createButtons();
    }

    /**
     * Creates the Labels of the GUI
     */
    public void createLabels() {
        nameLabel = new JLabel();
        nameLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        passwordLabel = new JLabel();
        passwordLabel.setBounds(labelX, labelY + labelYHeightIncrement, labelWidth, labelHeight);

        panel.add(nameLabel);
        panel.add(passwordLabel);
    }

    /**
     * Creates the text fields of the GUI
     */
    public void createTextFields() {
        nameTextField = new JTextField();
        nameTextField.setBounds(labelX + 100, labelY, labelWidth, labelHeight);
        passwordTextField = new JTextField();
        passwordTextField.setBounds(labelX + 100, labelY + labelYHeightIncrement, labelWidth, labelHeight);

        panel.add(nameTextField);
        panel.add(passwordTextField);
    }

    /**
     * Creates the combo box to select what type of user is being created
     */
    private void createType() {
        accountType = new JComboBox<>(types);
        accountType.setBounds(labelX + 350, labelY, buttonWidth + 50, buttonHeight);
        panel.add(accountType);
    }

    /**
     * Creates the buttons in the GUI
     */
    private void createButtons() {
        cancelButton = new JButton();
        cancelButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        cancelButton.addActionListener(e -> {
            organizerEventController.showEventMenu();
            clearTextFields();
        });
        panel.add(cancelButton);

        createAccountButton = new JButton();
        createAccountButton.setBounds(buttonX + 140, buttonY, buttonWidth + 30, buttonHeight);
        createAccountButton.addActionListener(e -> {
            String[] accountTypes = new String[]{"attendee", "organizer", "speaker", "admin"};
            organizerEventController.createAccount(nameTextField.getText(), passwordTextField.getText(), accountTypes[accountType.getSelectedIndex()]);
            clearTextFields();
            organizerEventController.showEventMenu();
        });
        panel.add(createAccountButton);
    }

    /**
     * Resets the text fields
     */
    private void clearTextFields() {
        nameTextField.setText("");
        passwordTextField.setText("");
    }

    /**
     * Creates the strings represented on the GUI
     *
     * @param languagePack contains the strings in a specific language
     */
    public void setText(LanguagePack languagePack) {
        nameLabel.setText(languagePack.userCreationPrompt()[0]);
        passwordLabel.setText(languagePack.userCreationPrompt()[1]);
        cancelButton.setText(languagePack.userCreationPrompt()[5]);
        createAccountButton.setText(languagePack.userCreationPrompt()[4]);
        types = new String[]{languagePack.userCreationPrompt()[2], languagePack.userCreationPrompt()[3],
                languagePack.userCreationPrompt()[6], languagePack.userCreationPrompt()[7]};
    }
}
