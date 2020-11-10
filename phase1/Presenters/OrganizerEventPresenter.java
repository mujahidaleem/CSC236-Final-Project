package Presenters;

import Entities.Event;
import UseCases.OrganizerManager;

import java.time.format.DateTimeFormatter;

public class OrganizerEventPresenter extends EventMenuPresenter {
    /**
     * OrganizerEventPresenter constructor
     * @param organizerManager stores the current user
     */
    public OrganizerEventPresenter(OrganizerManager organizerManager) {
        super(organizerManager);
        System.out.println("a " + organizerManager.getCurrentUser());
        System.out.println("aa " + this.manager.getCurrentUser());
    }

    /**
     * Prints the list of commands that can be executed by the organizer
     */
    @Override
    protected void printCommands(){
        printStandardCommands();
        printExtraCommands();
    }

    /**
     * Prints the result of trying to create a new event
     * @param i determines whether the command was successful or not
     */
    public void createEventResults(boolean i){
        if (i) {
            System.out.println("Your event has successfully been created.");
        } else {
            System.out.println("Sorry, your event cannot be created. Please try again");
        }
    }

    /**
     * Prints the result of trying to assign a speaker to an event
     * @param i determines whether the command was successful or not
     */
    public void setSpeakerResults(boolean i, Event event){
        if (i) {
            System.out.println(event.getSpeaker() + " will now be speaking at " + event.getName());
        } else {
            System.out.println("Sorry, " + event.getSpeaker() + " is not available at that specific time.");
        }
    }

    /**
     * Prints the results of trying to remove a speaker from an event
     * @param i determines whether the command was successful or not
     */
    public void removeSpeakerResults(boolean i){
        if(i){
            System.out.println("Speaker has successfully been removed.");
        } else {
            System.out.println("This event already does not have a speaker.");
        }
    }

    /**
     * Prints the result of trying to change the time of an event
     * @param i determines whether the command was successful or not
     */
    public void changeEventResults(boolean i, Event event){
        if (i) {
            System.out.println(event.getName() + " will now occur at " +
                    event.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")));
        } else {
            System.out.println("Sorry, the event time cannot be changed. ");
        }
    }

    /**
     * Prints that an event has been deleted
     * @param event stores the event that has been deleted
     */
    public void deleteEvent(Event event){
        System.out.println(event.getName() + " has been deleted.");
    }

    private void printExtraCommands(){
        System.out.println("To create a new event, type Create event_Name_YYYY-MM-DDTHH:mm:ss");
        System.out.println("To assign a speaker to an event, type Assign speaker_Event_Speaker");
        System.out.println("To remove a speaker from an event, type Remove speaker_Event");
        System.out.println("To delete an event, type Delete_Event");
        System.out.println("To change the date of an event, type Change date_Event_new date");
    }

}
