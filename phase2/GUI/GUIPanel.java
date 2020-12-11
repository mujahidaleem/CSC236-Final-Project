package GUI;

import GUI.MainFrame.ThemeManager;

import javax.swing.*;
import java.awt.*;

public class GUIPanel {
    protected JFrame frame;
    protected JPanel panel;

    protected int panelWidth;
    protected int panelHeight;

    protected Color backgroundColour;
    protected Color buttonColour1;
    protected Color buttonColour2;
    protected Color textColour;
    protected Color textFieldColour;

    protected ThemeManager themeManager;

    public GUIPanel (JFrame frame){
        this.frame = frame;
        this.panelWidth = frame.getWidth();
        this.panelHeight = frame.getHeight();
        this.panel = new JPanel();
        this.panel.setLayout(null);
        panel.setSize(panelWidth, panelHeight);
        panel.setOpaque(true);
        this.themeManager = new ThemeManager();
    }

    public void changePanel(JPanel newPanel){
        frame.setContentPane(newPanel);
        frame.repaint();
        frame.revalidate();
    }

    public JPanel getPanel(){
        return panel;
    }

    public void changeTheme(String theme){
        if(theme.equals("lightTheme")){
            themeManager.lightMode();
        } else {
            themeManager.darkMode();
        }
        this.backgroundColour = themeManager.getBackgroundColour();
        this.buttonColour1 = themeManager.getButtonColour1();
        this.buttonColour2 = themeManager.getButtonColour2();
        this.textColour = themeManager.getTextColour();
        this.textFieldColour = themeManager.getTextFieldColour();
        changeColours();
    }

    public void changeColours(){
    }
}
