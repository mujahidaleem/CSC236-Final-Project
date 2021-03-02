package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;

public class UserCreationPanel extends GUIPanel{
    private String[] types;
    private int buttonY = 300;
    private int buttonX = 200;
    private int buttonWidth = 200;
    private int buttonHeight = 25;

    private JLabel name;
    private JTextField newName;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JLabel introMessage;
    private JButton createNewUser;
    private JButton cancelButton;
    private JComboBox<String> type;

    private final LoginMenuController loginMenuController;

    public UserCreationPanel(JFrame frame, LanguageManager languageManager, LoginMenuController loginMenuController){
        super(frame, languageManager);
        this.types = new String[]{languageManager.languagePack.userCreationPrompt()[2], languageManager.languagePack.userCreationPrompt()[3]};
        this.loginMenuController = loginMenuController;
    }

    public void printMenu(){
        createLabels();
        setCreateAccountButton();
        setCancelButton();
        changeTheme("lightTheme");
    }

    public void createLabels(){
        introMessage = new JLabel();
        introMessage.setBounds(273,100,500,40);

        name = new JLabel();
        name.setBounds(273,155,80,25);

        newName = new JTextField(20);
        newName.setBounds(363,155,165,25);

        passwordLabel = new JLabel();
        passwordLabel.setBounds(273,185,80,25);

        passwordText = new JTextField(20);
        passwordText.setBounds(363,185,165,25);

        type = new JComboBox<>(types);
        type.setBounds(273, 230, 150, 25);

        panel.add(introMessage);
        panel.add(name);
        panel.add(newName);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(type);
    }

    public void setCreateAccountButton(){
        createNewUser = new JButton();
        createNewUser.setBounds(450, buttonY, 200, 25);

        JTextField tf3 = new JTextField();
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);

        createNewUser.addActionListener(e -> {
            String name = newName.getText();
            String password = passwordText.getText();
            String[] types = {"attendee", "organizer"};
            loginMenuController.signUp(name, password, types[type.getSelectedIndex()]);
        });
        panel.add(createNewUser);
    }

    private void setCancelButton(){
        cancelButton = new JButton();
        cancelButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        cancelButton.addActionListener(e -> loginMenuController.returnToLoginMenu());
        panel.add(cancelButton);
    }

    public void setText(LanguagePack languagePack){
        introMessage.setText(languagePack.userCreationPrompt()[4]);
        name.setText(languagePack.userCreationPrompt()[0]);
        passwordLabel.setText(languagePack.userCreationPrompt()[1]);
        createNewUser.setText(languagePack.userCreationPrompt()[4]);
        cancelButton.setText(languagePack.userCreationPrompt()[5]);
        types = new String[]{languagePack.userCreationPrompt()[2], languagePack.userCreationPrompt()[3]};
    }

    @Override
    public void changeColours(){
        panel.setBackground(backgroundColour);
        introMessage.setForeground(textColour);

        name.setForeground(textColour);
        newName.setForeground(textColour);
        passwordLabel.setForeground(textColour);
        passwordText.setForeground(textColour);
        newName.setBackground(textFieldColour);
        passwordText.setBackground(textFieldColour);

        createNewUser.setForeground(textColour);
        createNewUser.setBackground(buttonColour1);
        cancelButton.setForeground(textColour);
        cancelButton.setBackground(buttonColour1);
        type.setForeground(textColour);
        type.setBackground(buttonColour2);
    }
}
