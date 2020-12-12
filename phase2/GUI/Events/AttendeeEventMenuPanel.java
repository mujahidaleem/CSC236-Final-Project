package GUI.Events;

import Controllers.EventMenu.AttendeeEventController;
import UseCases.Language.LanguageManager;

import javax.swing.*;

public class AttendeeEventMenuPanel extends EventMenuPanel{

    /**
     * Constructor for AttendeeEventMenuPanel
     * @param attendeeEventController the controller that executes commands
     * @param frame the initial frame of the program
     * @param languageManager stores all the strings used to generate text
     */
    public AttendeeEventMenuPanel(AttendeeEventController attendeeEventController, JFrame frame, LanguageManager languageManager){
        super(attendeeEventController, frame, languageManager);
    }
}
