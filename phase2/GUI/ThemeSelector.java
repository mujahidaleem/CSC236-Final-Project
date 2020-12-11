package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.Color;
public class ThemeSelector {
    /**
     * Maybe copy the below code and integrate this into the main menu?
     * this would show up as a menu bar at the very top
     *
     * Light theme (default)
     *     background: (247, 245, 239)
     *     panels: (195, 226, 230)
     *     buttons: (252, 112, 43)
     *     text: (56,50,56)

     * Dark Theme
     *      background:(56,50,56)
     *      panels: (88, 74, 73)
     *      buttons: (108, 104, 108)
     *      text: (255, 242, 194)
     */

        public static void main(String args[]) {
            JMenuBar mb = new JMenuBar();
            JMenu themeMenu = new JMenu("Theme");
            mb.add(themeMenu);
            JMenuItem lightTheme = new JMenuItem("Light");
            themeMenu.add(lightTheme);
            lightTheme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UIManager.put("background", new Color(247, 245, 239));
                    UIManager.put("panel.color", new Color(252, 112, 43));
                    UIManager.put("text.color", new Color(56,50,56));
                }
            });

            JMenuItem darkTheme = new JMenuItem("Dark");
            themeMenu.add(darkTheme);
            darkTheme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UIManager.put("background", new Color(56,50,56));
                    UIManager.put("panel.color", new Color(108, 104, 108));
                    UIManager.put("text.color", new Color(255, 242, 194));
                }
            });


        }
        }

