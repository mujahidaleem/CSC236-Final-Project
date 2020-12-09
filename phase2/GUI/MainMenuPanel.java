package GUI;

import Entities.Events.Event;
import Gateways.ScheduleSaver;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import UseCases.PdfGenerator;
import com.itextpdf.text.DocumentException;

public class MainMenuPanel extends JPanel {
    private int width;
    private int height;
    private int tableX = 120;
    private int tableY = 750;
    private int tableWidth = 600;
    private int tableHeight = 650;
    private int tableCellWidth = tableWidth/8;
    private int tableCellHeight = tableHeight/25;

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

    private LanguageManager languageManager;
    private ScheduleSaver scheduleSaver;
    private EventManager eventManager;
    private PdfGenerator pdfGenerator;

    public MainMenuPanel(JFrame frame, JPanel messageMenu, JPanel eventMenu, LanguageManager languageManager, EventManager eventManager){
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        this.languageManager = languageManager;
        this.setLayout(null);
        this.scheduleSaver = new ScheduleSaver(eventManager);
        this.eventManager = eventManager;
        this.pdfGenerator = new PdfGenerator(eventManager);
        //setDateButton(this);
        setTimeTable();
        drawBlock(eventManager.getEvents().get(0),0,1);
        showSchedule(LocalDateTime.now());
        createEventButton(eventMenu, frame);
        createMessageButton(messageMenu, frame);
        setSaveScheduleButton();
        setText();
    }

    public void createEventButton(JPanel panel, JFrame frame){
        event = new JButton();
        event.setBounds(10,100,100,50);

        event.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panel);
                frame.repaint();
                frame.revalidate();
            }
        });
        add(event);
    }

    public void setDateButton(JPanel origin){
        yearLabel = new JLabel();
        yearLabel.setBounds(50,20,40,20);
        yearTextField = new JTextField();
        yearTextField.setBounds(90, 20, 40, 20);

        monthLabel = new JLabel();
        monthLabel.setBounds(150,20,40,20);
        monthTextField = new JTextField();
        monthTextField.setBounds(190, 20, 40, 20);

        dayLabel = new JLabel();
        dayLabel.setBounds(250,20,40,20);
        dayTextField = new JTextField();
        dayTextField.setBounds(290, 20, 40, 20);

        setDate = new JButton();
        setDate.setBounds(350, 20, 100, 20);
        setDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    LocalDateTime date = LocalDateTime.parse(yearTextField.getText()+"-"+monthTextField.getText()+"-"+dayTextField.getText()+"T00:00:00");
                    showSchedule(date);
                } catch (DateTimeParseException f) {
                    JOptionPane.showMessageDialog(origin, "Please input a correct date.","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        add(yearLabel);
        add(yearTextField);
        add(monthLabel);
        add(monthTextField);
        add(dayLabel);
        add(dayTextField);
        add(setDate);
    }

    public void createMessageButton(JPanel panel, JFrame frame){
        messages = new JButton();
        messages.setBounds(10,200,100,50);

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(panel);
                frame.repaint();
                frame.revalidate();
            }
        });
        add(messages);
    }

    private void drawBlock(Event event, int order, int number){
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        JLabel label = new JLabel();

        Rectangle rectangle = getPosition(event, order, number);

        label.setBounds(rectangle);
        panel.setBounds(rectangle);

        label.setText(event.getEventName() + "\n" + event.getRoomNumber());
        panel.add(label);

        add(panel);
        panel.setBackground(Color.red);

        repaint();
        revalidate();


    }

    private void addEvents(LocalDateTime dateTime) {
        HashMap<LocalDateTime, ArrayList<Integer>> orders = new HashMap<>();
        HashMap<LocalDateTime, Integer> number = pdfGenerator.eventsOnHour(dateTime);

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

    public void showSchedule(LocalDateTime dateTime){
        addEvents(pdfGenerator.getStartOfWeek(dateTime));
    }

    private Rectangle getPosition(Event event, int order, int number){
        Integer[] coordinates = pdfGenerator.getPosition(event, order, number, tableX, tableY, tableCellWidth, tableCellHeight, "swing");
        return new Rectangle(coordinates[0], coordinates[1], coordinates[2] - coordinates[0], coordinates[3]-coordinates[1]);
    }

    public void setSaveScheduleButton () {
        saveScheduleButton = new JButton();
        saveScheduleButton.setBounds(500, 20, 70, 30);
        saveScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime date = LocalDateTime.parse(yearTextField.getText()+"-"+monthTextField.getText()+"-"+dayTextField.getText()+"T00:00:00");
                try{
                    scheduleSaver.generatePDF(pdfGenerator.getStartOfWeek(date));
                } catch (DocumentException | IOException e1){
                    e1.printStackTrace();
                }
            }
        });

        add(saveScheduleButton);
    }

    private void setText(){
        event.setText(languageManager.languagePack.mainMenuCommands()[0]);
        messages.setText(languageManager.languagePack.mainMenuCommands()[1]);
        yearLabel.setText(languageManager.languagePack.mainMenuCommands()[2]);
        monthLabel.setText(languageManager.languagePack.mainMenuCommands()[3]);
        dayLabel.setText(languageManager.languagePack.mainMenuCommands()[4]);
        setDate.setText(languageManager.languagePack.mainMenuCommands()[5]);
        saveScheduleButton.setText(languageManager.languagePack.mainMenuCommands()[6]);
    }

    private void setTimeTable(){
        JPanel timeTable = new JPanel();
        timeTable.setBounds(tableX, tableY-tableHeight, tableWidth, tableHeight);
        add(timeTable);
        timeTable.setBackground(Color.CYAN);
    }
}
