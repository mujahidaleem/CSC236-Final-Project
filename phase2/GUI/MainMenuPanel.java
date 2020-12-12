package GUI;

import Controllers.MainMenuController;
import Entities.Events.Event;
import GUI.MainFrame.ThemeManager;
import Gateways.ScheduleSaver;
import UseCases.Events.EventManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;
import UseCases.PdfGenerator;
import com.itextpdf.text.DocumentException;

public class MainMenuPanel extends GUIPanel {
    private final int tableX = 120;
    private final int tableY = 750;
    private final int tableWidth = 600;
    private final int tableHeight = 650;
    private final int tableCellWidth = tableWidth / 8;
    private final int tableCellHeight = tableHeight / 25;

    private final int buttonX = 10;
    private final int buttonHeight = 30;
    private final int buttonWidth = 100;

    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;
    private JTextField yearTextField;
    private JTextField monthTextField;
    private JTextField dayTextField;

    private JButton event;
    private JButton messages;
    private JButton setDate;
    private JButton saveScheduleButton;
    private JButton logoutButton;
    private JButton changePasswordButton;

    private final ScheduleSaver scheduleSaver;
    private EventManager eventManager;
    private final PdfGenerator pdfGenerator;
    private final MainMenuController mainMenuController;
    private ThemeManager themeManager;

    public MainMenuPanel(JFrame frame, EventManager eventManager, MainMenuController mainMenuController, LanguageManager languageManager) {
        super(frame, languageManager);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.scheduleSaver = new ScheduleSaver(eventManager);
        this.eventManager = eventManager;
        this.pdfGenerator = new PdfGenerator(eventManager);
        this.mainMenuController = mainMenuController;
    }

    /**
     * Create the components of the GUI
     */
    public void setUpMenu(String theme) {
        createDateButton();
        createSaveScheduleButton();
        createButtons();
        changeTheme(theme);
    }

    /**
     * Creates the buttons shown on the GUI
     */
    public void createButtons() {

        event = new JButton();
        event.setBounds(buttonX, 100, buttonWidth, buttonHeight);
        event.addActionListener(e -> mainMenuController.printEventMenu());
        panel.add(event);

        messages = new JButton();
        messages.setBounds(buttonX, 200, buttonWidth, buttonHeight);
        messages.addActionListener(e -> mainMenuController.printMessageMenu());
        panel.add(messages);

        changePasswordButton = new JButton();
        changePasswordButton.setBounds(buttonX, 300, buttonWidth, buttonHeight);
        changePasswordButton.addActionListener(e -> mainMenuController.showChangePasswordPrompt());
        panel.add(changePasswordButton);

        logoutButton = new JButton();
        logoutButton.setBounds(buttonX, 400, buttonWidth, buttonHeight);
        logoutButton.addActionListener(e -> mainMenuController.logout());
        panel.add(logoutButton);
    }

    /**
     * Creates the GUI components related to showing the schedule
     */
    public void createDateButton() {
        yearLabel = new JLabel();
        yearLabel.setBounds(50, 20, 40, 20);
        yearTextField = new JTextField();
        yearTextField.setBounds(90, 20, 40, 20);

        monthLabel = new JLabel();
        monthLabel.setBounds(150, 20, 40, 20);
        monthTextField = new JTextField();
        monthTextField.setBounds(190, 20, 40, 20);

        dayLabel = new JLabel();
        dayLabel.setBounds(250, 20, 40, 20);
        dayTextField = new JTextField();
        dayTextField.setBounds(290, 20, 40, 20);

        setDate = new JButton();
        setDate.setBounds(350, 20, 100, 20);
        setDate.addActionListener(e -> {
            try {
                LocalDateTime date = LocalDateTime.parse(yearTextField.getText() + "-" + monthTextField.getText() + "-" + dayTextField.getText() + "T00:00:00");
                showSchedule(date);
            } catch (DateTimeParseException f) {
                JOptionPane.showMessageDialog(panel, "Please input a correct date.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        });

        panel.add(yearLabel);
        panel.add(yearTextField);
        panel.add(monthLabel);
        panel.add(monthTextField);
        panel.add(dayLabel);
        panel.add(dayTextField);
        panel.add(setDate);
    }

    private void drawBlock(Event event, int order, int number) {
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        JLabel label = new JLabel();

        Rectangle rectangle = getPosition(event, order, number);

        label.setBounds(rectangle);
        panel.setBounds(rectangle);

        label.setText(event.getEventName() + "\n" + event.getRoomNumber());
        panel.add(label);

        panel.add(panel);
        panel.setBackground(Color.red);

        panel.repaint();
        panel.revalidate();
    }

    private void addEvents(LocalDateTime dateTime) {
        HashMap<LocalDateTime, ArrayList<Integer>> orders = new HashMap<>();
        HashMap<LocalDateTime, Integer> number = pdfGenerator.eventsOnHour();

        for (Event event : pdfGenerator.sortEvents()) {
            if (event.getEventTime().isAfter(dateTime) && event.getEventTime().isBefore(dateTime.plusDays(7))) {
                int order = 0;
                try {
                    while (orders.get(event.getEventTime()).contains(order)) {
                        order++;
                    }
                } catch (NullPointerException e) {
                    orders.put(event.getEventTime(), new ArrayList<>());
                }
                drawBlock(event, order, scheduleSaver.getLargestNumber(number, event));
                for (int i = 0; i < event.getDuration() / 60; i++) {
                    try {
                        orders.get(event.getEventTime().plusHours(i)).add(order);
                    } catch (NullPointerException e) {
                        orders.put(event.getEventTime().plusHours(i), new ArrayList<>());
                        orders.get(event.getEventTime().plusHours(i)).add(order);
                    }
                }
            }
        }
    }

    public void showSchedule(LocalDateTime dateTime) {
        ArrayList<Event> events = eventManager.getEvents();
        ArrayList<Event> correct_timed = null;
        for (Event temp: events ) {
            if(temp.getEventTime() == dateTime){
                correct_timed.add(temp);
            }
        }
        //addEvents(correct_timed);
        //addEvents(pdfGenerator.getStartOfWeek(dateTime));
    }

    private Rectangle getPosition(Event event, int order, int number) {
        Integer[] coordinates = pdfGenerator.getPosition(event, order, number, tableX, tableY, tableCellWidth, tableCellHeight, "swing");
        return new Rectangle(coordinates[0], coordinates[1], coordinates[2] - coordinates[0], coordinates[3] - coordinates[1]);
    }

    public void createSaveScheduleButton() {
        saveScheduleButton = new JButton();
        saveScheduleButton.setBounds(500, 20, 100, 30);
        saveScheduleButton.addActionListener(e -> {
            try {
                LocalDateTime date = LocalDateTime.parse(yearTextField.getText() + "-" + monthTextField.getText() + "-" + dayTextField.getText() + "T00:00:00");
                try {
                    scheduleSaver.generatePDF(pdfGenerator.getStartOfWeek(date));
                } catch (DocumentException | IOException e1) {
                    e1.printStackTrace();
                }
            } catch (DateTimeParseException f){
                mainMenuController.showIncorrectDate();
            }
        });

        panel.add(saveScheduleButton);
    }

    /**
     * Sets the time table
     */
    private void setTimeTable() {
        JPanel timeTable = new JPanel();
        timeTable.setBounds(tableX, tableY - tableHeight, tableWidth, tableHeight);
        panel.add(timeTable);
        timeTable.setBackground(Color.CYAN);
    }

    /**
     * shows all strings in the GUI in a specific language
     *
     * @param languagePack contains the language the GUI is in
     */
    public void setText(LanguagePack languagePack) {
        event.setText(languagePack.mainMenuCommands()[0]);
        messages.setText(languagePack.mainMenuCommands()[1]);
        yearLabel.setText(languagePack.mainMenuCommands()[2]);
        monthLabel.setText(languagePack.mainMenuCommands()[3]);
        dayLabel.setText(languagePack.mainMenuCommands()[4]);
        setDate.setText(languagePack.mainMenuCommands()[5]);
        saveScheduleButton.setText(languagePack.mainMenuCommands()[6]);
        changePasswordButton.setText(languagePack.mainMenuCommands()[7]);
        logoutButton.setText(languagePack.mainMenuCommands()[8]);
    }

    /**
     * Changes the colours of the menu
     */
    public void changeColours(){
        panel.setBackground(backgroundColour);

        event.setForeground(textColour);
        messages.setForeground(textColour);
        yearLabel.setForeground(textColour);
        monthLabel.setForeground(textColour);
        dayLabel.setForeground(textColour);

        saveScheduleButton.setForeground(textColour);
        changePasswordButton.setForeground(textColour);
        logoutButton.setForeground(textColour);

        setDate.setForeground(textColour);
        setDate.setBackground(buttonColour1);
        changePasswordButton.setBackground(buttonColour1);
        event.setBackground(buttonColour1);
        messages.setBackground(buttonColour1);
        saveScheduleButton.setBackground(buttonColour1);
        logoutButton.setBackground(buttonColour1);

        yearTextField.setForeground(textColour);
        yearTextField.setBackground(textFieldColour);
        monthTextField.setForeground(textColour);
        monthTextField.setBackground(textFieldColour);
        dayTextField.setForeground(textColour);
        dayTextField.setBackground(textFieldColour);
    }
}