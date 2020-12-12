package GUI.MainFrame;

import java.awt.Color;


public class ThemeManager {
    private Color backgroundColour;
    private Color buttonColour1;
    private Color buttonColour2;
    private Color textColour;
    private Color textFieldColour;
    /** Light theme (default)*/
    public ThemeManager() {
        this.backgroundColour = new Color(247, 245, 239);
        this.buttonColour1 = new Color(252, 132, 43);
        this.buttonColour2 = new Color(67, 165, 176);
        this.textColour = new Color(56, 50, 56);
        this.textFieldColour = new Color(144, 142, 142);
    }


    public void lightMode() {
        this.backgroundColour = new Color(244, 255, 218);
        this.buttonColour1 = new Color(252, 132, 43);
        this.buttonColour2 = new Color(67, 165, 176);
        this.textColour = new Color(56, 50, 56);
        this.textFieldColour = new Color(144, 142, 142); //
    }
    /**
     * Dark Mode
     * sets colours to dark theme
     * */
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
