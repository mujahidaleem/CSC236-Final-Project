package GUI.Events;

import Controllers.EventMenu.SpeakerEventController;
import Entities.Events.Event;

import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;
import UseCases.Users.SpeakerManager;


import javax.swing.*;

public class SpeakerEventMenuPanel extends EventMenuPanel{
    private JLabel heading3;
    private JTextArea eventsSpeaking;

    private EventManager eventManager;
    private SpeakerManager speakerManager;
    private SpeakerEventController speakerEventController;

    /**
     * Constructor for SpeakerEventMenuPanel
     * @param speakerEventController the controller that executes the code
     * @param frame the original frame of the code
     * @param speakerManager contains the speakers
     * @param eventManager contains the events
     * @param languageManager contains the strings used to generate the text of the GUI
     */
    public SpeakerEventMenuPanel(SpeakerEventController speakerEventController, JFrame frame,
                                 SpeakerManager speakerManager, EventManager eventManager, LanguageManager languageManager){
        super(speakerEventController, frame, languageManager);
        this.speakerEventController = speakerEventController;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
    }

    /**
     * Creates the components unique to this type of user
     */
    public void printExtraComponents(){
        heading3 = new JLabel();
        heading3.setBounds(labelLayerX, labelLayerY + 380, labelWidth, labelHeight);
        panel.add(heading3);

        eventsSpeaking = new JTextArea();
        eventsSpeaking.setBounds(labelLayerX + 20, labelLayerY + 400, 400, 150);
        eventsSpeaking.setEditable(false);
        panel.add(eventsSpeaking);
    }

    /**
     * Displays the text of the GUI in a specific language
     * @param languagePack contains the strings needed to create the text
     */
    public void setAdditionalText(LanguagePack languagePack){
        heading3.setText(languagePack.eventMenuHeadings()[3]);
        StringBuilder eventsSpeakingAt = new StringBuilder();
        for (Event event : eventManager.getEvents()) {
            if (event.hasSpeaker() && event.getSpeakers().contains(speakerManager.getCurrentSpeaker().getId())){
                eventsSpeakingAt.append(event.toString()).append("\n");
            }
        }eventsSpeaking.setText(eventsSpeakingAt.toString());
    }

    /**
     * Changes the colour of the components to match the theme
     */
    public void changeColourOfExtraComponents(){
        heading3.setForeground(textColour);
        eventsSpeaking.setForeground(textColour);
        eventsSpeaking.setBackground(textFieldColour);
    }

}
