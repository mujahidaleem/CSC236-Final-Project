package Presenters;

import Entities.Event;
import UseCases.EventManager;
import UseCases.UserManager;

public class EventMenuPresenter {
    public UserManager manager;

    /**
     * EventMenuPresenter constructor
     * @param manager stores the current user
     */
    public EventMenuPresenter(UserManager manager) {
        this.manager = manager;
        System.out.println("b " + manager.getCurrentUser());
    }

    /**
     * Prints the initial menu of the Event Menu, showing the organizer what commands can be used
     * @param eventManager stores the list of events
     */
    public void printMenu(EventManager eventManager) {
        System.out.println("Events Attending");
        printEventsAttending(eventManager);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Events Available");
        for (Event event : eventManager.events) {
            if (!manager.getCurrentUser().get_personalSchedule().containsKey(event.getName())) {
                System.out.println(event);
            }
        }
        printCommands();
    }

    /**
     * Prints the result of trying to sign up for an event
     * @param i whether the signup was successful or not
     * @param event the event that the user is trying to sign up for
     */
    public void signUpResult(boolean i, Event event) {
        if (i) {
            System.out.println("You are now registered for " + event.getName() + ".");
        } else {
            System.out.println("Sorry, you are unable to attend this event.");
        }
    }

    /**
     * Prints the result of trying to cancel the users spot from an event
     * @param i whether the removal was successful or not
     * @param event the event that the user is trying to cancel their spot from
     */
    public void removalResult(boolean i, Event event) {
        if (i) {
            System.out.println("You are no longer attending " + event + ".");
        } else {
            System.out.println("You are already not attending " + event);
        }
    }

    protected void printCommands(){
        printStandardCommands();
    }

    protected void printStandardCommands(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To return to the main menu, type return");
        System.out.println("To sign up for an event, type Sign up for_Event");
        System.out.println("To cancel your position in an event, type Leave_Event");
    }

    protected void printEventsAttending(EventManager eventManager){
        if (!(manager.getCurrentUser().get_personalSchedule() == null)){
            for (Event event : eventManager.events) {
                if (manager.getCurrentUser().get_personalSchedule().containsKey(event.getName())){
                    System.out.println(event);
                }
            }
        } else{
            System.out.println("You are currently not attending any events.");
        }
    }
}
