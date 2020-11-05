package src.Presenters;
import src.Entities.Event;
import src.Entities.User;

public class EventMenuPresenter {
    private User currentUser;

    public EventMenuPresenter(User currentUser) {
        this.currentUser = currentUser;
    }

    public void printMenu(EventManager eventManager) {
        System.out.println("Events Attending");
        for (Event event : eventManager.events) {
            if (currentUser.get_personalSchedule().containsKey(event.getName())){
                System.out.println(event);
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Events Available");
        for (Event event : eventManager.events) {
            if (!currentUser.get_personalSchedule().containsKey(event.getName())) {
                System.out.println(event);
            }
        }
        printCommands();
    }

    public void signUpResult(boolean i, Event event) {
        if (i) {
            System.out.println("You are now registered for " + event.getName() + ".");
        } else {
            System.out.println("Sorry, you are unable to attend this event.");
        }
    }

    public void removalResult(boolean i, Event event) {
        if (i) {
            System.out.println("You are no longer attending " + event + ".");
        } else {
            System.out.println("You are already not attending " + event);
        }
    }

    public void printCommands(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To sign up for an event, type Sign up for_Event");
        System.out.println("To cancel your position in an event, type Leave_Event");
        System.out.println("To return to the main menu, type return");
    }
}
