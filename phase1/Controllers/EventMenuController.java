package Controllers;

import Entities.Event;
import Presenters.EventMenuPresenter;
import UseCases.EventManager;
import UseCases.UserManager;

import java.util.Scanner;

public abstract class EventMenuController {
    public UserManager userManager;
    public EventManager eventManager;
    public EventMenuPresenter presenter;
    /**
     * AttendeeEventController constructor
     * @param userManager contains the attendee using the current session
     * @param eventManager contains the list of events
     */
    public EventMenuController(UserManager userManager,
                               EventManager eventManager, EventMenuPresenter presenter) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.presenter = presenter;
    }

    /**
     * The menu for events is initialized and commands relating to events can be
     * performed by typing in the correct strings into the command line.
     */
    public void run() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            presenter.printMenu(eventManager);
            String[] command = userInput.nextLine().split("_");
            if (command[0].equals("return")) {
                break;
            }
            boolean standard = standardCommands(command, presenter);
            if(!standard){
                extraCommands(command);
            }
        }
    }

    protected boolean standardCommands(String[] command, EventMenuPresenter presenter){
        try{
            switch (command[0]){
                case "Sign up for":
                    signUpForEvent(eventManager.findEvent(command[1]), presenter);
                    return true;
                case "Leave":
                    removeSpotFromEvent(eventManager.findEvent(command[1]), presenter);
                    return true;
            }
        } catch (NullPointerException e) {
            System.out.println("Event does not exist, please try again.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your command was invalid, please try again.");
        }
        return false;
    }

    protected void extraCommands(String[] command){
        System.out.println("Sorry, that command was not recognized. Please try again.");
    }

    protected void signUpForEvent(Event event,
                                  EventMenuPresenter presenter) {
        if (userManager.canAttendEvent(event) && eventManager.userCanSignUp(userManager.getCurrentUser(), event)) {
            userManager.attendEvent(event);
            eventManager.addUser(event, userManager.getCurrentUser());
            presenter.signUpResult(true, event);
        } else {
            presenter.signUpResult(false, event);
        }
    }

    protected void removeSpotFromEvent(Event event, EventMenuPresenter presenter) {
        if (userManager.canLeaveEvent(event) && eventManager.userCanLeave(userManager.getCurrentUser(), event)) {
            userManager.leaveEvent(event);
            eventManager.removeUser(event, userManager.getCurrentUser());
            presenter.removalResult(true, event);
        } else {
            presenter.removalResult(false, event);
        }
    }
}
