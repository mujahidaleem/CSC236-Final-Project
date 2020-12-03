package Presenters.EventMenu;

import Controllers.EventMenu.EventMenuController;
import Entities.Events.Event;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.UserManager;

import java.util.Scanner;

/**
 * Displays the event menu and interprets inputs made by the user on this menu
 */
public class EventMenuPresenter {
    protected UserManager manager;
    protected EventMenuController controller;
    protected EventManager eventManager;
    protected LanguageManager languageManager;

    /**
     * EventMenuPresenter constructor
     *
     * @param manager         stores the current user
     * @param controller      the controller that performs the commands inputted
     * @param languageManager prints the strings in the current language of the system
     */
    public EventMenuPresenter(UserManager manager, EventMenuController controller, EventManager eventManager, LanguageManager languageManager) {
        this.manager = manager;
        this.eventManager = eventManager;
        this.controller = controller;
        this.languageManager = languageManager;
    }

    /**
     * The menu for events is initialized and commands relating to events can be
     * performed by typing in the correct strings into the command line.
     */
    public void run() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            printMenu();
            String[] command = userInput.nextLine().split("_");
            if (command[0].equals("0")) {
                break;
            }
            boolean standard = standardCommands(command);
            if (!standard) {
                extraCommands(command);
            }
        }
    }

    /**
     * Checks if the inputted command is one of the standard commands and tells the controller to perform it if it is
     *
     * @param input The command inputted by the user
     * @return True if the command is a standard command. False if it is not.
     */
    protected boolean standardCommands(String[] input) {
        try {
            Event event = eventManager.findEvent(input[1]);
            switch (input[0]) {
                case "1":
                    signUpResult(controller.signUpForEvent(event), event);
                    return true;
                case "2":
                    removalResult(controller.removeSpotFromEvent(event), event);
                    return true;
            }
        } catch (NullPointerException e) {
            System.out.println(languageManager.languagePack.unknownEvent());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(languageManager.languagePack.unknownCommand());
        }
        return false;
    }

    /**
     * Checks if the inputted command is one of the extra commands specific to this type of user. If it is,
     * tells the correct controller to run it.
     *
     * @param input The command inputted by the user.
     */
    protected void extraCommands(String[] input) {
        System.out.println(languageManager.languagePack.unknownCommand());
    }

    /**
     * Prints the initial menu of the Event Menu, showing the user the list of events they are attending, the list
     * of events they are not attending, and what commands can be used
     */
    public void printMenu() {
        System.out.println(manager.getCurrentUser());
        System.out.println(languageManager.languagePack.eventMenuHeadings()[0]);
        for (Event event : eventManager.getEvents()) {
            if (manager.getCurrentUser().getPersonalSchedule().containsKey(event.getEventName())) {
                System.out.println(event);
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(languageManager.languagePack.eventMenuHeadings()[1]);
        for (Event event : eventManager.getEvents()) {
            if (!manager.getCurrentUser().getPersonalSchedule().containsKey(event.getEventName())) {
                System.out.println(event);
            }
        }
        printCommands();
    }

    /**
     * Prints the result of trying to sign up for an event
     *
     * @param i     whether the signup was successful or not
     * @param event the event that the user is trying to sign up for
     */
    public void signUpResult(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.standardEventResultsSuccess(event)[1]);
        } else {
            System.out.println(languageManager.languagePack.standardEventResultsFailure(event)[1]);
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
            System.out.println(languageManager.languagePack.standardEventResultsSuccess(event)[2]);
        } else {
            System.out.println(languageManager.languagePack.standardEventResultsFailure(event)[2]);
        }
    }

    /**
     * Prints the list of commands that can be executed by the organizer
     */
    protected void printCommands() {
        languageManager.languagePack.printEventStandardCommands();
    }
}
