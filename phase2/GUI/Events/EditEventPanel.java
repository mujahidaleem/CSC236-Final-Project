package GUI.Events;

import Controllers.EventMenu.NullEventException;
import Controllers.EventMenu.NullSpeakerException;
import Controllers.EventMenu.OrganizerEventController;
import GUI.GUIPanel;
import UseCases.Events.EventManager;
import Entities.Events.Event;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EditEventPanel extends GUIPanel {
    private final int labelX = 20;
    private final int labelWidth = 100;
    private final int labelHeight = 20;
    private final int dateWidth = 50;

    private final int textBoxX = 120;
    private final int textBoxWidth = 100;
    private final int textBoxHeight = 20;

    private final int buttonWidth = 100;
    private final int buttonHeight = 30;

    private final int y = 20;
    private final int heightIncrement = 40;

    private JLabel eventNameLabel;
    private JLabel eventRoomLabel;
    private JLabel eventDurationLabel;
    private JLabel eventMaxCapacityLabel;
    private JLabel speakers;

    private JTextField eventNameTextField;
    private JTextField eventRoomTextField;
    private JTextField eventDurationTextField;
    private JTextField eventMaxCapacityTextField;
    private JTextArea speakersTextField;

    private final String[] months = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private final String[] days = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private final String[] hours = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private final String[] minutes = new String[]{"00", "10", "20", "30", "40", "50"};

    private JLabel year;
    private JTextField yearValue;
    private JLabel month;
    private JComboBox<String> monthValue;
    private JLabel day;
    private JComboBox<String> dayValue;
    private JLabel hour;
    private JComboBox<String> hourValue;
    private JLabel minute;
    private JComboBox<String> minuteValue;

    private String[] types;
    private JComboBox<String> eventTypeLabel;

    private JButton deleteEventButton;
    private JButton saveChangesButton;
    private JButton returnButton;
    private JButton addSpeakerButton;
    private JButton removeSpeakerButton;

    private OrganizerEventController organizerEventController;
    private EventManager eventManager;

    public EditEventPanel(JFrame frame, OrganizerEventController organizerEventController, LanguageManager languageManager){
        super(frame, languageManager);
        this.organizerEventController = organizerEventController;
        this.types = new String[]{languageManager.languagePack.changeEventPrompts()[7], languageManager.languagePack.changeEventPrompts()[8],
                languageManager.languagePack.changeEventPrompts()[9]};
        this.eventManager = organizerEventController.eventManager;
    }

    public void printMenu(boolean i, String theme){
        setLabels();
        setTextFields();
        setEventNameTextField(i);
        setDateComponents();
        setEventType();
        setUpButtons();
        changeTheme(theme);
    }

    private void setLabels(){
        eventNameLabel = new JLabel();
        eventNameLabel.setBounds(labelX, y, labelWidth, labelHeight);
        eventRoomLabel = new JLabel();
        eventRoomLabel.setBounds(labelX, y+heightIncrement, labelWidth, labelHeight);
        eventDurationLabel = new JLabel();
        eventDurationLabel.setBounds(labelX, y+2*heightIncrement, labelWidth, labelHeight);
        year = new JLabel();
        year.setBounds(labelX, y+3*heightIncrement, dateWidth, labelHeight);
        month = new JLabel();
        month.setBounds(labelX + 210, y+3*heightIncrement, dateWidth, labelHeight);
        day = new JLabel();
        day.setBounds(labelX + 310, y+3*heightIncrement, dateWidth, labelHeight);
        hour = new JLabel();
        hour.setBounds(labelX + 410, y+3*heightIncrement, dateWidth, labelHeight);
        minute = new JLabel();
        minute.setBounds(labelX + 510, y+3*heightIncrement, dateWidth, labelHeight);

        eventMaxCapacityLabel = new JLabel();
        eventMaxCapacityLabel.setBounds(labelX, y + 4*heightIncrement, labelWidth, labelHeight);
        speakers = new JLabel();
        speakers.setBounds(labelX, y + 5*heightIncrement, labelWidth, labelHeight);

        panel.add(eventNameLabel);
        panel.add(eventRoomLabel);
        panel.add(eventDurationLabel);
        panel.add(year);
        panel.add(month);
        panel.add(day);
        panel.add(hour);
        panel.add(minute);
        panel.add(eventMaxCapacityLabel);
        panel.add(speakers);
    }

    private void setEventNameTextField(boolean i){
        eventNameTextField = new JTextField();
        eventNameTextField.setBounds(textBoxX, y, textBoxWidth, textBoxHeight);
        eventNameTextField.setEditable(i);
        panel.add(eventNameTextField);
    }

    private void setTextFields(){
        eventRoomTextField = new JTextField();
        eventRoomTextField.setBounds(textBoxX, y+heightIncrement, textBoxWidth, textBoxHeight);
        eventDurationTextField = new JTextField();
        eventDurationTextField.setBounds(textBoxX, y+2*heightIncrement, textBoxWidth, textBoxHeight);
        yearValue = new JTextField();
        yearValue.setBounds(textBoxX, y + 3*heightIncrement, textBoxWidth, textBoxHeight);
        eventMaxCapacityTextField = new JTextField();
        eventMaxCapacityTextField.setBounds(textBoxX, y + 4*heightIncrement, textBoxWidth, textBoxHeight);

        speakersTextField = new JTextArea();
        speakersTextField.setBounds(textBoxX, y + 5*heightIncrement, textBoxWidth + 50, textBoxHeight + 50);
        speakersTextField.setEditable(false);

        panel.add(eventRoomTextField);
        panel.add(eventDurationTextField);
        panel.add(yearValue);
        panel.add(eventMaxCapacityTextField);
        panel.add(speakersTextField);
    }

    private void setDateComponents(){
        monthValue = new JComboBox<>(months);
        monthValue.setBounds(labelX + 250, y+3*heightIncrement, dateWidth, labelHeight);
        dayValue = new JComboBox<>(days);
        dayValue.setBounds(labelX + 350, y + 3*heightIncrement, dateWidth, labelHeight);
        hourValue = new JComboBox<>(hours);
        hourValue.setBounds(labelX + 450, y+ 3*heightIncrement, dateWidth, labelHeight);
        minuteValue = new JComboBox<>(minutes);
        minuteValue.setBounds(labelX + 550, y + 3*heightIncrement, dateWidth, labelHeight);

        panel.add(monthValue);
        panel.add(dayValue);
        panel.add(hourValue);
        panel.add(minuteValue);
    }

    private void setEventType(){
        eventTypeLabel = new JComboBox<>(types);
        eventTypeLabel.setBounds(300, y, textBoxWidth + 100, textBoxHeight);

        panel.add(eventTypeLabel);
    }

    private void setUpButtons(){

        returnButton = new JButton();
        returnButton.setBounds(labelX + 100, y + 7*heightIncrement, buttonWidth, buttonHeight);
        returnButton.addActionListener(e -> organizerEventController.showEventMenu());
        panel.add(returnButton);

        deleteEventButton = new JButton();
        deleteEventButton.setBounds(labelX + 250, y + 7*heightIncrement, buttonWidth + 50, buttonHeight);
        deleteEventButton.addActionListener(e -> {
            try{
                organizerEventController.deleteEvent(eventManager.findEvent(eventNameTextField.getText()));
                clearAdditionalText();
                organizerEventController.showEventMenu();
            } catch (NullEventException f){
                organizerEventController.showNullEventError();
            }
        });
        panel.add(deleteEventButton);

        saveChangesButton = new JButton();
        saveChangesButton.setBounds(labelX + 450, y + 7*heightIncrement, buttonWidth + 50, buttonHeight);
        saveChangesButton.addActionListener(e -> {
            try {
                LocalDateTime date = LocalDateTime.parse(yearValue.getText() + "-" + months[monthValue.getSelectedIndex()] + "-" +
                        days[dayValue.getSelectedIndex()] + "T" + hours[hourValue.getSelectedIndex()] + ":" + minutes[minuteValue.getSelectedIndex()] + ":00");
                if (eventNameTextField.isEditable()) {
                    String[] eventTypes = new String[]{"attendeeOnlyEvent", "multiSpeakerEvent", "oneSpeakerEvent"};
                    organizerEventController.createEvent(eventNameTextField.getText(), date, Integer.parseInt(eventRoomTextField.getText()),
                            Integer.parseInt(eventMaxCapacityTextField.getText()),Integer.parseInt(eventDurationTextField.getText()), eventTypes[eventTypeLabel.getSelectedIndex()]);
                } else {
                    organizerEventController.changeEventInformation(organizerEventController.eventManager.findEvent(eventNameTextField.getText()), date, Integer.parseInt(eventRoomTextField.getText()),
                            Integer.parseInt(eventMaxCapacityTextField.getText()), Integer.parseInt(eventDurationTextField.getText()));
                }
            } catch (DateTimeParseException | NumberFormatException f){
                organizerEventController.showIncorrectDate();
            } finally{
                clearAdditionalText();
                organizerEventController.showEventMenu();
            }
        });
        panel.add(saveChangesButton);

        removeSpeakerButton = new JButton();
        removeSpeakerButton.setBounds(labelX + 280, y + 5*heightIncrement, buttonWidth + 70, buttonHeight);
        removeSpeakerButton.addActionListener(e -> {
            try {
                organizerEventController.removeSpeaker(eventManager.findEvent(eventNameTextField.getText()), organizerEventController.showAddSpeakerPrompt());
            } catch (NullSpeakerException f){
                organizerEventController.showNullSpeaker();
            }
        });
        panel.add(removeSpeakerButton);

        addSpeakerButton = new JButton();
        addSpeakerButton.setBounds(labelX + 480, y + 5*heightIncrement, buttonWidth + 70, buttonHeight);
        addSpeakerButton.addActionListener(e -> {
            try {
                organizerEventController.addSpeaker(eventManager.findEvent(eventNameTextField.getText()), organizerEventController.showAddSpeakerPrompt());
            } catch (NullSpeakerException nullSpeakerException) {
                organizerEventController.showNullSpeaker();
            }
        });
        panel.add(addSpeakerButton);
    }

    public void setMode(boolean i){
        eventNameTextField.setEditable(i);
    }

    public void setText(Event event, LanguagePack languagePack){
        setStrings(languagePack);

        if(event!= null){
            eventNameTextField.setText(event.getEventName());
            eventRoomTextField.setText(Integer.toString(event.getRoomNumber()));
            eventDurationTextField.setText(Integer.toString(event.getDuration()));
            eventMaxCapacityTextField.setText(Integer.toString(event.getMaxCapacity()));
            yearValue.setText(String.valueOf(event.getEventTime().getYear()));
            monthValue.setSelectedIndex(event.getEventTime().getMonthValue());
            hourValue.setSelectedIndex(event.getEventTime().getHour());
            minuteValue.setSelectedIndex(event.getEventTime().getMinute()/60);
            if(event.hasSpeaker()){
                StringBuilder stringBuilder = new StringBuilder();
                for (int id: event.getSpeakers()){
                    stringBuilder.append(organizerEventController.getSpeakerManager().findSpeaker(id)).append("\n");
                }
                speakersTextField.setText(stringBuilder.toString());
            }
        }
    }
    public void setStrings(LanguagePack languagePack){
        eventNameLabel.setText(languagePack.changeEventPrompts()[0]);
        eventRoomLabel.setText(languagePack.changeEventPrompts()[1]);
        eventDurationLabel.setText(languagePack.changeEventPrompts()[3]);
        eventMaxCapacityLabel.setText(languagePack.changeEventPrompts()[4]);
        speakers.setText(languagePack.changeEventPrompts()[5]);

        year.setText(languagePack.changeEventPrompts()[6]);
        month.setText(languagePack.changeEventPrompts()[15]);
        day.setText(languagePack.changeEventPrompts()[16]);
        hour.setText(languagePack.changeEventPrompts()[17]);
        minute.setText(languagePack.changeEventPrompts()[18]);

        types = new String[]{languagePack.changeEventPrompts()[7], languagePack.changeEventPrompts()[8],
                languagePack.changeEventPrompts()[9]};

        deleteEventButton.setText(languagePack.changeEventPrompts()[10]);
        saveChangesButton.setText(languagePack.changeEventPrompts()[11]);
        returnButton.setText(languagePack.changeEventPrompts()[12]);
        addSpeakerButton.setText(languagePack.changeEventPrompts()[13]);
        removeSpeakerButton.setText(languagePack.changeEventPrompts()[14]);
    }

    public void clearAdditionalText(){
        eventNameTextField.setText("");
        eventRoomTextField.setText("");
        eventDurationTextField.setText("");
        eventMaxCapacityTextField.setText("");
        yearValue.setText("");
        speakersTextField.setText("");
    }

    public void changeColours(){
        panel.setBackground(backgroundColour);

        deleteEventButton.setForeground(textColour);
        saveChangesButton.setForeground(textColour);
        removeSpeakerButton.setForeground(textColour);
        returnButton.setForeground(textColour);
        addSpeakerButton.setForeground(textColour);

        deleteEventButton.setBackground(buttonColour1);
        saveChangesButton.setBackground(buttonColour1);
        removeSpeakerButton.setBackground(buttonColour1);
        returnButton.setBackground(buttonColour1);
        addSpeakerButton.setBackground(buttonColour1);

        eventTypeLabel.setForeground(textColour);
        eventTypeLabel.setBackground(buttonColour2);

        year.setForeground(textColour);
        yearValue.setForeground(textColour);
        month.setForeground(textColour);
        monthValue.setForeground(textColour);
        day.setForeground(textColour);
        dayValue.setForeground(textColour);
        hour.setForeground(textColour);
        hourValue.setForeground(textColour);
        minute.setForeground(textColour);
        minuteValue.setForeground(textColour);

        yearValue.setBackground(textFieldColour);
        monthValue.setBackground(textFieldColour);
        dayValue.setBackground(textFieldColour);
        hourValue.setBackground(textFieldColour);
        minuteValue.setBackground(textFieldColour);

        eventNameTextField.setForeground(textColour);
        eventRoomTextField.setForeground(textColour);
        eventDurationTextField.setForeground(textColour);
        eventMaxCapacityTextField.setForeground(textColour);
        speakersTextField.setForeground(textColour);

        eventNameTextField.setBackground(textFieldColour);
        eventRoomTextField.setBackground(textFieldColour);
        eventDurationTextField.setBackground(textFieldColour);
        eventMaxCapacityTextField.setBackground(textFieldColour);
        speakersTextField.setBackground(textFieldColour);

        eventNameLabel.setForeground(textColour);
        eventRoomLabel.setForeground(textColour);
        eventDurationLabel.setForeground(textColour);
        eventMaxCapacityLabel.setForeground(textColour);
        speakers.setForeground(textColour);
    }
    public void changeText(LanguageManager languageManager){
        setStrings(languageManager.languagePack);
    }
}
