package src.Controllers;
import src.Entities.Event;
import src.Entities.User;
import src.Presenters.EventMenuPresenter;

import java.util.Scanner;

public class EventMenuController {
    User currentUser;
    UserManager Manager;
    EventMenuPresenter presenter;
    EventManager eventManager;

    public EventMenuController(User currentUser, UserManager userManager,
                               EventManager eventmanager, EventMenuPresenter presenter) {
        this.currentUser = currentUser;
        this.Manager = userManager;
        this.eventManager = eventmanager;
        this.presenter = presenter;
    }

    public EventMenuController() {

    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            presenter.printMenu(eventManager);
            String[] command = userInput.nextLine().split("_");
            if (command[0].equals("return")) {
                break;
            }
            try {
                switch (command[0]) {
                    case "Sign up for":
                        signUpForEvent(findEvent(command[1], eventManager));
                        break;
                    case "Leave":
                        removeSpotFromEvent(findEvent(command[1], eventManager));
                        break;
                    default:
                        System.out.println("Command does not exist, please try again.");
                }
            } catch (NullPointerException e) {
                System.out.println("Event does not exist, please try again.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Your command was invalid, please try again.");
            }
        }
    }

    public void signUpForEvent(Event event) {
        if (Manager.canAttendEvent(currentUser, event) && eventManager.userCanSignUp(currentUser, event)) {
            Manager.attendEvent(currentUser, event);
            eventManager.addUser(event, currentUser);
            presenter.signUpResult(true, event);
        } else {
            presenter.signUpResult(false, event);
        }
    }

    public void removeSpotFromEvent(Event event) {
        if (Manager.canLeaveEvent(currentUser, event) && eventManager.userCanLeave(currentUser, event)) {
            Manager.leaveEvent(currentUser, event);
            eventManager.removeUser(event, currentUser);
            presenter.removalResult(true, event);
        } else {
            presenter.removalResult(false, event);
        }
    }

    protected Event findEvent(String name, EventManager manager) {
        for (Event event : manager.events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }
}
