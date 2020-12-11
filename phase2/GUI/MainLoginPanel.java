package GUI;

import Controllers.LoginMenuController;
import GUI.MainFrame.ThemeManager;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLoginPanel extends GUIPanel {

    private JLabel introMessage;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton createUserButton;

    private LoginMenuController loginMenuController;

    public MainLoginPanel(JFrame frame, LoginMenuController loginMenuController, LanguageManager languageManager){
        super(frame, languageManager);
        this.loginMenuController = loginMenuController;
    }

    public void printMenu(){
        createButtons();
        setCreateUserButton();
        setLoginButton();
        changeTheme("lightTheme");
    }

    public void createButtons(){
        introMessage = new JLabel();
        introMessage.setBounds(273,100,500,40);

        userLabel = new JLabel();
        userLabel.setBounds(273,155,80,25);

        userText = new JTextField(20);
        userText.setBounds(363,155,165,25);

        passwordLabel = new JLabel();
        passwordLabel.setBounds(273,185,80,25);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(363,185,165,25);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(introMessage);
    }

    public void setCreateUserButton(){
        createUserButton = new JButton();
        createUserButton.setBounds(150, 300, 200, 25);
        createUserButton.addActionListener(e -> {
            clearText();
            loginMenuController.showCreateNewAccountPrompt();
        });
        panel.add(createUserButton);
    }

    public void setLoginButton(){
        loginButton = new JButton();
        loginButton.setBounds(450, 300, 200, 25);
        loginButton.addActionListener(e -> {
            try{
                int username = Integer.parseInt(userText.getText());
                String password = new String(passwordText.getPassword());
                clearText();
                loginMenuController.checkLogin(username, password);
            } catch (NumberFormatException f) {
                loginMenuController.showWrongCredentials();
            }
        });
        panel.add(loginButton);
    }

    public void setText(LanguagePack languagePack){
        introMessage.setText(languagePack.loginMenuGreeting());
        userLabel.setText(languagePack.loggingInPrompt()[0]);
        passwordLabel.setText(languagePack.loggingInPrompt()[1]);
        createUserButton.setText(languagePack.loggingInPrompt()[2]);
        loginButton.setText(languagePack.loggingInPrompt()[3]);
    }

    private void clearText(){
        userText.setText("");
        passwordText.setText("");
    }
    @Override
    public void changeColours(){
        panel.setBackground(backgroundColour);

        introMessage.setForeground(textColour);
        userLabel.setForeground(textColour);
        userText.setForeground(textColour);
        passwordLabel.setForeground(textColour);
        passwordText.setForeground(textColour);

        userText.setBackground(textFieldColour);
        passwordText.setBackground(textFieldColour);

        createUserButton.setForeground(textColour);
        createUserButton.setBackground(buttonColour1);
        loginButton.setForeground(textColour);
        loginButton.setBackground(buttonColour1);
    }
}
