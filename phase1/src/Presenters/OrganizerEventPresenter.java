package src.Presenters;

import src.Entities.Organizer;

import java.time.format.DateTimeFormatter;

public class OrganizerEventPresenter extends EventMenuPresenter{

    public OrganizerEventPresenter(Organizer currentUser) {
        super(currentUser);
    }

    public void createEventResults(boolean i){
        if (i) {
            System.out.println("Your event has successfully been created.");
        } else {
            System.out.println("Sorry, your event cannot be created. Please try again");
        }
    }

    public void setSpeakerResults(boolean i, Event event){
        if (i) {
            System.out.println(event.getSpeaker() + " will now be speaking at " + event.getName());
        } else {
            System.out.println("Sorry, " + event.getSpeaker() + " is not available at that specific time.");
        }
    }

    public void changeEventResults(boolean i, Event event){
        if (i) {
            System.out.println(event.getName() + " will now occur at " +
                    event.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")));
        } else {
            System.out.println("Sorry, the event time cannot be changed. ");
        }
    }

    public void deleteEvent(Event event){
        System.out.println(event.getName() + " has been deleted.");
    }

    @Override
    public void printCommands(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To sign up for an event, type Sign up for_Event");
        System.out.println("To cancel your position in an event, type Leave_Event");
        System.out.println("To create a new event, type Create event_Name_YYYY-MM-DDT1HH:mm:ss");
        System.out.println("To assign a speaker to an event, type Assign speaker_Event_Speaker");
        System.out.println("To delete an event, type Delete_Event");
        System.out.println("To change the date of an event, type Change date_Event_new date");
        System.out.println("To return to the main menu, type return");
    }

}

