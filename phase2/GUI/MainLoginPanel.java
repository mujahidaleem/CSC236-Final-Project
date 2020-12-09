package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;
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

    public MainLoginPanel(JFrame frame, LoginMenuController loginMenuController){
        super(frame);
        this.loginMenuController = loginMenuController;
    }

    public void printMenu(){
        createButtons();
        setCreateUserButton();
        setLoginButton();
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

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMenuController.showCreateNewAccountPrompt();
            }
        });
        panel.add(createUserButton);
    }

    public void setLoginButton(){
        loginButton = new JButton();
        loginButton.setBounds(450, 300, 200, 25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int username = Integer.parseInt(userText.getText());
                String password = new String(passwordText.getPassword());
                loginMenuController.checkLogin(username, password);
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
}
