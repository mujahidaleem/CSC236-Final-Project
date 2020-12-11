package GUI.MainFrame;

import GUI.MainFrame.ThemeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.Color;

public class ThemeMenuBar extends JMenuBar {


    public ThemeMenuBar(ThemeManager themeManager){
        JMenuBar mb = new JMenuBar();
        JMenu themeMenu = new JMenu("Theme");
        mb.add(themeMenu);
        JMenuItem lightTheme = new JMenuItem("Light");
        themeMenu.add(lightTheme);

        lightTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               themeManager.lightMode();
//              // please read the comments for themeManager
//                UIManager.put("Button.background", new Color (252, 132, 43));
//                UIManager.put("Panel.background",new Color (244, 255, 218));
//                UIManager.put("Button.select", new Color(67, 165, 176));
//                UIManager.put("button.foreground", new Color(42, 36, 37));
//                UIManager.put("Textfield.foreground",new Color(42, 36, 37));
            }
        });

        JMenuItem darkTheme = new JMenuItem("Dark");
        themeMenu.add(darkTheme);
        darkTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themeManager.darkMode();
//                UIManager.put("Button.background", new Color(109, 62, 54));
//                UIManager.put("Panel.background",new Color (50, 50, 50));
//                UIManager.put("Button.select", new Color(60, 90, 120));
//                UIManager.put("button.foreground", new Color(255, 242, 194));
//                UIManager.put("Textfield.foreground",new Color(255, 242, 194));
            }
        });

    }
}

