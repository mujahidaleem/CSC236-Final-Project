package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLoginPanel extends JPanel {
    private int width;
    private int height;

    private JLabel introMessage;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton createUserButton;

    private LanguageManager languageManager;

    public MainLoginPanel(JFrame frame, LanguageManager languageManager){
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        this.languageManager = languageManager;
        this.setLayout(null);
        createButtons();
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

        add(userLabel);
        add(userText);
        add(passwordLabel);
        add(passwordText);
        add(introMessage);
    }

    public void setCreateUserButton(JPanel panel2, JFrame frame){
        createUserButton = new JButton();
        createUserButton.setBounds(150, 300, 200, 25);

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panel2);
                frame.repaint();
                frame.revalidate();
            }
        });
        add(createUserButton);
    }

    public void setLoginButton(JPanel original, LoginMenuController loginMenuController, JFrame frame){
        loginButton = new JButton();
        loginButton.setBounds(450, 300, 200, 25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int username = Integer.parseInt(userText.getText());

                String password = new String(passwordText.getPassword());
                if(loginMenuController.checkLogin(username, password) == null){
                    JOptionPane.showMessageDialog(original, "Username and password did not match.", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    JPanel panel = new JPanel();
                    frame.setContentPane(panel);
                    frame.repaint();
                    frame.revalidate();
                }
            }
        });
        add(loginButton);
    }

    protected void setText(){
        introMessage.setText(languageManager.languagePack.loginMenuGreeting());
        userLabel.setText(languageManager.languagePack.loggingInPrompt()[0]);
        passwordLabel.setText(languageManager.languagePack.loggingInPrompt()[1]);
        createUserButton.setText(languageManager.languagePack.loggingInPrompt()[2]);
        loginButton.setText(languageManager.languagePack.loggingInPrompt()[3]);
    }
}
