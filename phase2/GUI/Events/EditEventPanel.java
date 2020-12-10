package GUI.Events;

import Controllers.EventMenu.NullSpeakerException;
import Controllers.EventMenu.OrganizerEventController;
import GUI.GUIPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import Entities.Events.Event;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class EditEventPanel extends GUIPanel {
    private final int labelX = 20;
    private final int labelWidth = 50;
    private final int labelHeight = 20;

    private final int textBoxX = 70;
    private final int textBoxWidth = 100;
    private final int textBoxHeight = 20;

    private final int buttonWidth = 50;
    private final int buttonHeight = 30;

    private final int y = 20;
    private final int heightIncrement = 40;

    private JLabel eventNameLabel;
    private JLabel eventRoomLabel;
    private JLabel eventDateLabel;
    private JLabel eventDurationLabel;
    private JLabel eventMaxCapacityLabel;
    private JLabel speakers;

    private JTextField eventNameTextField;
    private JTextField eventRoomTextField;
    private JTextField eventDateTextField;
    private JTextField eventDurationTextField;
    private JTextField eventMaxCapacityTextField;
    private JTextField speakersTextField;
    private JCheckBox newEventCheckBox;

    private final Integer[] months = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private final Integer[] days = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private final Integer[] hours = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private final Integer[] minutes = new Integer[]{0, 10, 20, 30, 40, 50};

    private JLabel year;
    private JTextField yearValue;
    private JComboBox<Integer> month;
    private JComboBox<Integer> day;
    private JComboBox<Integer> hour;
    private JComboBox<Integer> minute;

    private String[] types = new String[]{""}; //TODO: add text
    private JComboBox<String> eventTypeLabel;

    private JButton deleteEventButton;
    private JButton saveChangesButton;
    private JButton returnButton;
    private JButton addSpeakerButton;
    private JButton removeSpeakerButton;

    private OrganizerEventController organizerEventController;
    private EventManager eventManager;

    public EditEventPanel(JFrame frame, OrganizerEventController organizerEventController){
        super(frame);
        this.organizerEventController = organizerEventController;
    }

    public void printMenu(){
        setEventNameComponents();
        setEventRoomComponents();
        setEventDurationComponents();
    }

    private void setEventNameComponents(){
        eventNameLabel = new JLabel();
        eventNameLabel.setBounds(labelX, y, labelWidth, labelHeight);
        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(textBoxX, y, textBoxWidth, textBoxHeight);
        eventNameTextField.setEditable(false);
        panel.add(eventNameLabel);
        panel.add(eventNameTextField);
    }

    private void setEventRoomComponents(){
        eventRoomLabel = new JLabel();
        eventRoomLabel.setBounds(labelX, y+heightIncrement, labelWidth, labelHeight);
        eventRoomTextField = new JTextField();
        eventRoomTextField.setBounds(textBoxX, y+heightIncrement, textBoxWidth, textBoxHeight);
        panel.add(eventRoomLabel);
        panel.add(eventRoomTextField);
    }

    private void setEventDurationComponents(){
        eventDurationLabel = new JLabel();
        eventDurationLabel.setBounds(labelX, y+2*heightIncrement, labelWidth, labelHeight);
        eventDurationTextField = new JTextField();
        eventDurationTextField.setBounds(textBoxX, y+2*heightIncrement, textBoxWidth, textBoxHeight);
        panel.add(eventDurationLabel);
        panel.add(eventDurationTextField);
    }

    private void setDateComponents(){

        year = new JLabel();
        year.setBounds(labelX, y+3*heightIncrement, labelWidth, labelHeight);
        yearValue = new JTextField();
        yearValue.setBounds(labelX + 60, y + 3*heightIncrement, textBoxWidth, textBoxHeight);
        month = new JComboBox<>(months);
        month.setBounds(labelX + 150, y+3*heightIncrement, labelWidth, labelHeight);
        day = new JComboBox<>(days);
        day.setBounds(labelX + 200, y + 3*heightIncrement, labelWidth, labelHeight);
        hour = new JComboBox<>(hours);
        hour.setBounds(labelX + 250, y+ 3*heightIncrement, labelWidth, labelHeight);
        minute = new JComboBox<>(minutes);
        minute.setBounds(labelX + 300, y + 3*heightIncrement, labelWidth, labelHeight);

        panel.add(year);
        panel.add(yearValue);
        panel.add(month);
        panel.add(day);
        panel.add(hour);
        panel.add(minute);
    }

    private void setSpeakerComponents(){
        speakers = new JLabel();
        speakers.setBounds(labelX, y + 4*heightIncrement, labelWidth, labelHeight);
        speakersTextField = new JTextField();
        speakersTextField.setBounds(textBoxX, y + 4*heightIncrement, textBoxWidth, textBoxHeight);
        speakersTextField.setEditable(false);
        panel.add(speakers);
        panel.add(speakersTextField);
    }

    private void setEventType(LanguageManager languageManager){
        eventTypeLabel = new JComboBox<>(types);
        eventTypeLabel.setBounds(300, y, textBoxWidth, textBoxHeight);

        panel.add(eventTypeLabel);
    }

    private void setUpButtons(){
        newEventCheckBox = new JCheckBox();
        newEventCheckBox.setBounds(labelX, y + 4*heightIncrement, buttonWidth, buttonHeight);
        panel.add(newEventCheckBox);

        deleteEventButton = new JButton();
        deleteEventButton.setBounds(labelX, y + 5*heightIncrement, buttonWidth, buttonHeight);
        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organizerEventController.deleteEvent(eventManager.findEvent(eventNameTextField.getText()));
            }
        });
        panel.add(deleteEventButton);

        saveChangesButton = new JButton();
        saveChangesButton.setBounds(labelX + 300, y + 5*heightIncrement, buttonWidth, buttonHeight);
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newEventCheckBox.isSelected()){
                    LocalDateTime date = LocalDateTime.parse(yearValue.getText() + "-" + months[month.getSelectedIndex()] + "-" +
                            days[day.getSelectedIndex()] + "T" + hours[hour.getSelectedIndex()] + ":" + minutes[minute.getSelectedIndex()] + ":00");
                    organizerEventController.changeEventInformation(eventManager.findEvent(eventNameTextField.getText()
                            ), date, Integer.parseInt(eventDurationTextField.getText()),
                            Integer.parseInt(eventMaxCapacityTextField.getText()), Integer.parseInt(eventRoomTextField.getText()));
                } else {
                    LocalDateTime date = LocalDateTime.parse(yearValue.getText() + "-" + months[month.getSelectedIndex()] + "-" +
                            days[day.getSelectedIndex()] + "T" + hours[hour.getSelectedIndex()] + ":" + minutes[minute.getSelectedIndex()] + ":00");
                    organizerEventController.createEvent(eventNameTextField.getText(), date,  Integer.parseInt(eventRoomTextField.getText()),
                            Integer.parseInt(eventMaxCapacityTextField.getText()), Integer.parseInt(eventDurationTextField.getText()), types[eventTypeLabel.getSelectedIndex()]);
                }
            }
        });
        panel.add(saveChangesButton);

        returnButton = new JButton();
        returnButton.setBounds(labelX + 350, y + 5*heightIncrement, buttonWidth, buttonHeight);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organizerEventController.showEventMenu();
            }
        });
        panel.add(returnButton);

        removeSpeakerButton = new JButton();
        removeSpeakerButton.setBounds(labelX + 500, y + 5*heightIncrement, buttonWidth, buttonHeight);
        removeSpeakerButton.addActionListener(e -> {
            try {
                organizerEventController.removeSpeaker(eventManager.findEvent(eventNameTextField.getText()), organizerEventController.showAddSpeakerPrompt());
            } catch (NullSpeakerException f){
                organizerEventController.showNullSpeaker();
            }
        });
        panel.add(returnButton);

        addSpeakerButton = new JButton();
        addSpeakerButton.setBounds(labelX + 400, y + 5*heightIncrement, buttonWidth, buttonHeight);
        addSpeakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    organizerEventController.addSpeaker(eventManager.findEvent(eventNameTextField.getText()), organizerEventController.showAddSpeakerPrompt());
                } catch (NullSpeakerException nullSpeakerException) {
                    organizerEventController.showNullSpeaker();
                }
            }
        });
        panel.add(addSpeakerButton);
    }

    public void setText(Event event, LanguagePack languagePack){
        //TODO:
    }

}
