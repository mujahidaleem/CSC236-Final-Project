package GUI;

import javax.swing.*;

public class CreateUserGUI {
    public static void main(){
        JFrame frame = new JFrame("Create a new user");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainMenuPanel = new JPanel();
        frame.add(mainMenuPanel);
        frame.setVisible(true);
    }
}
