package GUI.MainFrame;

import java.awt.Color;

/** if the UIManager.put doesn't work, we could use the attributes of this thememanger to manually change
 * the colours of the buttons/background/text etc.
 */
public class ThemeManager {
    private Color backgroundColour;
    private Color buttonColour1;
    private Color buttonColour2;
    private Color textColour;
    private Color textFieldColour;
    /** Light theme (default)
     *     background: (247, 245, 239)
     *     button1: (195, 226, 230)
     *     button2: (252, 112, 43)
     *     text: (56,50,56)
     */
    public ThemeManager() {
        this.backgroundColour = new Color(247, 245, 239);
        this.buttonColour1 = new Color(252, 132, 43);
        this.buttonColour2 = new Color(67, 165, 176);
        this.textColour = new Color(56, 50, 56);
        this.textFieldColour = new Color(144, 142, 142);
    }
    /**
     * Dark Theme
     *      background:(56,50,56)
     *      button1: (88, 74, 73)
     *      button2: (108, 104, 108)
     *      text: (255, 242, 194)
     */

    public void lightMode() {
        this.backgroundColour = new Color(244, 255, 218);
        this.buttonColour1 = new Color(252, 132, 43);
        this.buttonColour2 = new Color(67, 165, 176);
        this.textColour = new Color(56, 50, 56);
        this.textFieldColour = new Color(144, 142, 142); //
    }

    public void darkMode() {
        this.backgroundColour = new Color(50, 50, 50);
        this.buttonColour1 = new Color(120, 60, 43);
        this.buttonColour2 = new Color(60, 90, 120);
        this.textColour = new Color(255, 242, 194);
        this.textFieldColour = new Color(83, 83, 83); //
    }



    // getters
    public Color getBackgroundColour () {
        return backgroundColour;
    }

    public Color getButtonColour2 () {
        return buttonColour2;
    }

    public Color getButtonColour1 () {
        return buttonColour1;
    }

    public Color getTextColour() {
        return textColour;
    }

    public Color getTextFieldColour(){return textFieldColour;}
}
