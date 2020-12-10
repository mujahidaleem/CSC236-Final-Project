package GUI;

import javax.swing.*;

public class GUIPanel {
    protected JFrame frame;
    protected JPanel panel;

    protected int panelWidth;
    protected int panelHeight;

    public GUIPanel (JFrame frame){
        this.frame = frame;
        this.panelWidth = frame.getWidth();
        this.panelHeight = frame.getHeight();
        this.panel = new JPanel();
        this.panel.setLayout(null);
        panel.setSize(panelWidth, panelHeight);

    }

    public void changePanel(JPanel newPanel){
        frame.setContentPane(newPanel);
        frame.repaint();
        frame.revalidate();
    }

    public JPanel getPanel(){
        return panel;
    }


}
