package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ThemeSelector {
    /**
     *
     * Maybe copy the below code and integrate this into the main menu?
     * this would show up as a menu bar at the very top
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
            JMenu m1 = new JMenu("Theme");
            mb.add(m1);
            JMenuItem m11 = new JMenuItem("Light");
            JMenuItem m22 = new JMenuItem("Dark");
            m1.add(m11);
            m1.add(m22);


        }
}
