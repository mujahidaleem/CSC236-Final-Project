package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageMenuGUI {

    private JFrame frame;
    private JPanel loginPanel;


    public static void main(String[] args){
        MessageMenuGUI a = new MessageMenuGUI();
    }

    public MessageMenuGUI() {
        createWindow();
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
            userLabel.setBounds(273, 155, 80, 25);
            panel.add(userLabel);
            /*
             * Create text field for user input
             */
            JTextField userText = new JTextField(20);
            userText.setBounds(363, 155, 165, 25);
            panel.add(userText);

            }
        }


