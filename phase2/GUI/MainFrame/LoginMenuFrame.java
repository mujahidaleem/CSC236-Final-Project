package GUI.MainFrame;

import Controllers.LoginMenuController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginMenuFrame extends JFrame{
    private JMenuBar menuBar;
    private JMenu languageMenu;
    private JMenu themeMenu;

    private JMenuItem englishLanguage;
    private JMenuItem frenchLanguage;
    private JMenuItem lightTheme;
    private JMenuItem darkTheme;

    private LoginMenuController loginMenuController;

    public LoginMenuFrame(LoginMenuController loginMenuController){
        this.loginMenuController = loginMenuController;
    }

    public void setMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        languageMenu = new JMenu("Language");
        languageBar();
        themeMenu = new JMenu("Theme");
        themeBar();
        setJMenuBar(menuBar);
        menuBar.add(languageMenu);
        menuBar.add(themeMenu);
    }

    private void resetPanel(JFrame frame){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    protected void languageBar(){
        englishLanguage = new JMenuItem();
        englishLanguage.setText("English");
        frenchLanguage = new JMenuItem();
        frenchLanguage.setText("French");

        englishLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMenuController.changeLanguage("english");
            }
        });

        frenchLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMenuController.changeLanguage("french");
            }
        });
        languageMenu.add(englishLanguage);
        languageMenu.add(frenchLanguage);
    }

    private void themeBar(){
        lightTheme = new JMenuItem("Light Theme");
        darkTheme = new JMenuItem("Dark Theme");
        lightTheme.addActionListener(e -> loginMenuController.changeTheme("lightTheme"));
        darkTheme.addActionListener(e -> loginMenuController.changeTheme("darkTheme"));

        themeMenu.add(lightTheme);
        themeMenu.add(darkTheme);
    }
}


