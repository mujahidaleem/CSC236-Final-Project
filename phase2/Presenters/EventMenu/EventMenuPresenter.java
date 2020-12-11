package Presenters.EventMenu;

import Entities.Events.Event;
import GUI.Events.EventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import javax.swing.*;

/**
 * Displays the event menu and interprets inputs made by the user on this menu
 */
public class EventMenuPresenter {
    protected UserManager manager;
    protected EventManager eventManager;
    protected LanguageManager languageManager;

    private EventMenuPanel eventMenuPanel;
    protected MainMenuPanel mainMenuPanel;


    /**
     * EventMenuPresenter constructor
     *
     * @param eventManager    contains all the events
     * @param mainMenuPanel   contains the GUI of the main menu
     * @param eventMenuPanel  contains the GUI of hte event menu
     * @param manager         stores the current user
     * @param languageManager prints the strings in the current language of the system
     */
    public EventMenuPresenter(UserManager manager, EventManager eventManager, LanguageManager languageManager, EventMenuPanel eventMenuPanel, MainMenuPanel mainMenuPanel) {
        this.manager = manager;
        this.eventManager = eventManager;
        this.languageManager = languageManager;
        this.eventMenuPanel = eventMenuPanel;
        this.mainMenuPanel = mainMenuPanel;
    }

    /**
     * Sets up the components of the GUI
     */
    public void setUpMenu() {
        eventMenuPanel.printMenu();
        eventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        eventMenuPanel.changePanel(eventMenuPanel.getPanel());
    }

    public void showNullEventError(){
        JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.unknownEvent(),"Warning", JOptionPane.WARNING_MESSAGE); //TODO:
    }

//    /**
//     * The menu for events is initialized and commands relating to events can be
//     * performed by typing in the correct strings into the command line.
//     */
//    public void run() {
//        Scanner userInput = new Scanner(System.in);
//        while (true) {
//            printMenu();
//            String[] command = userInput.nextLine().split("_");
//            if (command[0].equals("0")) {
//                break;
//            }
//            boolean standard = standardCommands(command);
//            if (!standard) {
//                extraCommands(command);
//            }
//        }
//    }

//    /**
//     * Checks if the inputted command is one of the standard commands and tells the controller to perform it if it is
//     *
//     * @param input The command inputted by the user
//     * @return True if the command is a standard command. False if it is not.
//     */
//    protected boolean standardCommands(String[] input) {
//        try {
//            Event event = eventManager.findEvent(input[1]);
//            switch (input[0]) {
//                case "1":
//                    signUpResult(controller.signUpForEvent(event), event);
//                    return true;
//                case "2":
//                    removalResult(controller.removeSpotFromEvent(event), event);
//                    return true;
//            }
//        } catch (NullPointerException e) {
//            System.out.println(languageManager.languagePack.unknownEvent());
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(languageManager.languagePack.unknownCommand());
//        }
//        return false;
//    }
//
//    /**
//     * Checks if the inputted command is one of the extra commands specific to this type of user. If it is,
//     * tells the correct controller to run it.
//     *
//     * @param input The command inputted by the user.
//     */
//    protected void extraCommands(String[] input) {
//        System.out.println(languageManager.languagePack.unknownCommand());
//    }

//    /**
//     * Prints the initial menu of the Event Menu, showing the user the list of events they are attending, the list
//     * of events they are not attending, and what commands can be used
//     */
//    public void printMenu() {
//        eventMenuPanel.printMenu();
//        //printCommands();
//    }

    /**
     * Prints the result of trying to sign up for an event
     *
     * @param i     whether the signup was successful or not
     * @param event the event that the user is trying to sign up for
     */
    public void signUpResult(boolean i, Event event) {
        if (i) {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsSuccess(event)[1]);
            eventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
        } else {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsFailure(event)[1]);
        }
    }

    /**
     * Prints the result of trying to cancel the users spot from an event
     *
     * @param i     whether the removal was successful or not
     * @param event the event that the user is trying to cancel their spot from
     */
    public void removalResult(boolean i, Event event) {
        if (i) {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsSuccess(event)[2]);
            eventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
        } else {
            JOptionPane.showMessageDialog(eventMenuPanel.getPanel(), languageManager.languagePack.standardEventResultsFailure(event)[2]);
        }
    }

    /**
     * Tells the GUI to return to the main menu panel
     */
    public void returnToMainMenu() {
        eventMenuPanel.changePanel(mainMenuPanel.getPanel());
    }

//    /**
//     * Prints the list of commands that can be executed by the organizer
//     */
//    protected void printCommands() {
//        languageManager.languagePack.printEventStandardCommands();
//    }
}
