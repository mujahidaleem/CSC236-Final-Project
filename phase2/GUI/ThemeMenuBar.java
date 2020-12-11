package GUI;

import javax.swing.*;
import java.awt.*;
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
            }
        });

        JMenuItem darkTheme = new JMenuItem("Dark");
        themeMenu.add(darkTheme);
        darkTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themeManager.darkMode();
            }
        });

//TODO: change background, button colours, and text colours to themeManager attributes.
    }
}

