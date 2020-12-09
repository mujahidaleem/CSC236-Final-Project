package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox type;

    private LanguageManager languageManager;
    private LoginMenuController loginMenuController;

    public UserCreationPanel(JFrame frame, LanguageManager languageManager, LoginMenuController loginMenuController){
        super(frame);
        this.types = new String[]{languageManager.languagePack.userCreationPrompt()[3], languageManager.languagePack.userCreationPrompt()[4]};
        this.loginMenuController = loginMenuController;
    }

    public void printMenu(){
        createLabels();
        setCreateAccountButton();
        setCancelButton();
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

        type = new JComboBox(types);

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

        createNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newName.getText();
                String password = passwordText.getText();
                String[] accountType = {"attendee, organizer"};
                loginMenuController.signUp(name, password, accountType[type.getSelectedIndex()]);
            }
        });
        panel.add(createNewUser);
    }

    private void setCancelButton(){
        cancelButton = new JButton();
        cancelButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMenuController.returnToLoginMenu();
            }
        });
        panel.add(cancelButton);
    }

    public void setText(LanguagePack languagePack){
        introMessage.setText(languagePack.userCreationPrompt()[0]);
        name.setText(languagePack.userCreationPrompt()[1]);
        passwordLabel.setText(languagePack.userCreationPrompt()[2]);
        createNewUser.setText(languagePack.userCreationPrompt()[5]);
        cancelButton.setText("RETURN"); //TODO: edit this
        types = new String[]{languagePack.userCreationPrompt()[3], languagePack.userCreationPrompt()[4]};
    }
}
