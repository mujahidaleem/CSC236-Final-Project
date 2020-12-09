package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenuFrame {
    private JFrame frame;
    private LoginMenuController loginMenuController;
    final private String[] languages = new String[] {"english", "french"};

    private MainLoginPanel mainLoginPanel;
    private UserCreationPanel userCreationPanel;
    private JComboBox languageSelection;


    public LoginMenuFrame(){
    }

    public void showMenu(){
        frame = new JFrame();
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainLoginPanel.getPanel());
        frame.setVisible(true);

//        userCreationPanel = new UserCreationPanel(frame);
//        mainLoginPanel = new MainLoginPanel(frame);
//        mainLoginPanel.setCreateUserButton(userCreationPanel.getPanel(), frame);
//        mainLoginPanel.setLoginButton(mainLoginPanel, loginMenuController, frame);
//
//        userCreationPanel.setCreateAccountButton(userCreationPanel.getPanel(), mainLoginPanel, loginMenuController);
//
//        setText();
//        languageBar();
//
//        frame.setLayout(null);
//        frame.setContentPane(mainLoginPanel);
//        frame.setVisible(true);
    }

    private void resetPanel(JFrame frame){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

//    protected void languageBar(){
//        languageSelection = new JComboBox(languages);
//        languageSelection.setBounds(650,0,100,20);
//        languageSelection.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String language = languages[languageSelection.getSelectedIndex()];
//                languageManager.changeLanguage(language);
//                setText();
//            }
//        });
//
//        mainLoginPanel.getPanel().add(languageSelection);
//        userCreationPanel.panel.add(languageSelection);
//    }
//
//    protected void setText(){
//        mainLoginPanel.setText();
//        userCreationPanel.setText();
//    }

}


