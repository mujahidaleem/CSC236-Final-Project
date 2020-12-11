package GUI.Events;

import Controllers.EventMenu.AttendeeEventController;
import UseCases.Language.LanguageManager;

import javax.swing.*;

public class AttendeeEventMenuPanel extends EventMenuPanel{

    public AttendeeEventMenuPanel(AttendeeEventController attendeeEventController, JFrame frame, LanguageManager languageManager){
        super(attendeeEventController, frame, languageManager);
    }
}
