package src.Controllers;
import Event;

import java.util.Scanner;

public class EventMenuController {
    User currentUser;
    UserManager userManager;
    EventMenuPresenter presenter;
    EventManager eventManager;

    public EventMenuController(User currentUser, UserManager userManager,
                               EventManager eventmanager, EventMenuPresenter presenter) {
        this.currentUser = currentUser;
        this.userManager = userManager;
        this.eventManager = eventmanager;
        this.presenter = presenter;
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            presenter.printMenu(eventManager);
            String[] command = userInput.nextLine().split(":");
            if (command[0].equals("return")) {
                break;
            }
            try {
                switch (command[0]) {
                    case "Sign up for":
                        signUpForEvent(findEvent(command[1]));
                        break;
                    case "Leave":
                        removeSpotFromEvent(findEvent(command[1]));
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
        if (userManager.canAttendEvent(currentUser, event) && eventManager.userCanSignUp(currentUser, event)) {
            userManager.attendEvent(currentUser, event);
            event.userList.add(currentUser.get_name());
            presenter.signUpResult(true, event);
        } else {
            presenter.signUpResult(false, event);
        }
    }

    public void removeSpotFromEvent(Event event) {
        if (userManager.canLeaveEvent(currentUser, event) && eventManager.userCanLeave(currentUser, event)) {
            userManager.leaveEvent(currentUser, event);
            event.userList.remove(currentUser.get_name());
            presenter.removalResult(true, event);
        } else {
            presenter.removalResult(false, event);
        }
    }

    protected Event findEvent(String name) {
        for (Event event : eventManager.events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }
    /**
     public void displayEventsAttending(){

     }

     public void displayAllEvents(){

     }
     **/
}
