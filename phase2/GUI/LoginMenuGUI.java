package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginMenuGUI {
    private JFrame frame;
    private JPanel loginPanel;

    public static void main(String[] args){
        LoginMenuGUI a = new LoginMenuGUI();
    }

    public LoginMenuGUI() {
        createWindow();
        //Add components to the panel
    }

    private void createWindow(){
        // Create JFrame instance
        JFrame frame = new JFrame("Login");
        // Setting the width and height of frame
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel loginPanel = new JPanel();
        // Add panel
        frame.add(loginPanel);
        frame.setVisible(true);
        placeComponents(loginPanel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        // Create JLabel
        JLabel userLabel = new JLabel("User ID:");
        /*
         * setBounds(x, y, width, height)
         */
        userLabel.setBounds(273,155,80,25);
        panel.add(userLabel);
        /*
         * Create text field for user input
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(363,155,165,25);
        panel.add(userText);

        // Text field for password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(273,185,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(363,185,165,25);
        panel.add(passwordText);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(450, 300, 200, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuGUI.main();
                frame.dispose();
            }
        });

        // Create new user button
        JButton createUserButton = new JButton("Create new user");
        createUserButton.setBounds(150, 300, 200, 25);
        panel.add(createUserButton);

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserGUI.main();
            }
        });
    }

}


