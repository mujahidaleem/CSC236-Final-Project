package GUI.Events;

import Controllers.EventMenu.OrganizerEventController;

import javax.swing.*;

public class OrganizerEventMenuPanel extends EventMenuPanel{
    private JButton createEventButton;
    private JButton addSpeakerButton;

    public OrganizerEventMenuPanel(OrganizerEventController organizerEventController, JFrame frame){
        super(organizerEventController, frame);
    }


}
