package GUI.Events;

import Controllers.EventMenu.SpeakerEventController;
import Entities.Events.Event;

import UseCases.Events.EventManager;
import UseCases.Language.LanguagePack;
import UseCases.Users.SpeakerManager;


import javax.swing.*;

public class SpeakerEventMenuPanel extends EventMenuPanel{
    private JLabel heading3;
    private JTextArea eventsSpeaking;

    private EventManager eventManager;
    private SpeakerManager speakerManager;
    private SpeakerEventController speakerEventController;

    public SpeakerEventMenuPanel(SpeakerEventController speakerEventController, JFrame frame, SpeakerManager speakerManager, EventManager eventManager){
        super(speakerEventController, frame);
        this.speakerEventController = speakerEventController;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
    }

    public void printExtraComponents(){
        heading3 = new JLabel();
        heading3.setBounds(labelLayerX, labelLayerY + 380, labelWidth, labelHeight);
        panel.add(heading3);

        eventsSpeaking = new JTextArea();
        eventsSpeaking.setBounds(labelLayerX + 20, labelLayerY + 400, 400, 150);
        eventsSpeaking.setEditable(false);
        panel.add(eventsSpeaking);
    }

    public void setAdditionalText(LanguagePack languagePack){
        heading3.setText(languagePack.eventMenuHeadings()[3]);
        StringBuilder eventsSpeakingAt = new StringBuilder();
        for (Event event : eventManager.getEvents()) {
            if (event.hasSpeaker() && event.getSpeakers().contains(speakerManager.getCurrentSpeaker().getId())){
                eventsSpeakingAt.append(event.toString()).append("\n");
            }
        }eventsSpeaking.setText(eventsSpeakingAt.toString());
    }
}
