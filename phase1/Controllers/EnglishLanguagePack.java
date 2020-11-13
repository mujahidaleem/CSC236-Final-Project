package Controllers;

import Entities.Event;

import java.time.format.DateTimeFormatter;

public class EnglishLanguagePack implements LanguagePack{
    private String language;
    private String directory;

    public EnglishLanguagePack(String language){
        this.language = language;
        this.directory = "D:\\Language\\" + language + ".ser";
    }

    public String[] menuHeadings(){
        return new String[]{"Events Attending", "Events Available"};
    }

    public void printStandardCommands(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println("To sign up for an event, type 1_Event");
        System.out.println("To cancel your position in an event, type 2_Event");
    }

    public void printOrganizerCommands(){
        System.out.println("To create a new event, type Create event_Name_YYYY-MM-DDTHH:mm:ss");
        System.out.println("To assign a speaker to an event, type Assign speaker_Event_Speaker");
        System.out.println("To remove a speaker from an event, type Remove speaker_Event");
        System.out.println("To delete an event, type Delete_Event");
        System.out.println("To change the date of an event, type Change date_Event_new date");
    }

    public String[] standardResultsSuccess(Event event){
        return new String[]{"Success", "You are now registered for " + event + ".",
                "You are no longer attending " + event + "."};
    }

    public String[] standardResultsFailure(Event event){
        return new String[]{"Failure", "Sorry, you are unable to attend this event.",
                "You are already not attending " + event.getEventName()};
    }

    public String[] organizerResultsSuccess(Event event){
        return new String[]{"Your event has successfully been created.",
                event.getSpeaker() + " will now be speaking at " + event.getEventName(),
                "Speaker has successfully been removed.", event.getEventName() + " will now occur at " +
                event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " has been cancelled."
        };
    }

    public String[] organizerResultsFailure(Event event){
        return new String[]{"Sorry, your event cannot be created. Please try again",
                "Sorry, " + event.getSpeaker() + " is not available at that specific time.",
                "This event already does not have a speaker.",
                "Sorry, the event time cannot be changed.",
                event.getEventName() + " cannot be cancelled."
        };
    }

    public String unknownCommand(){
        return "Sorry, that command was not recognized. Please try again.";
    }

    public String eventUnchangeable(Event event){
        return event.getEventName() + " cannot be modified by you.";
    }

    public String unknownEvent(){
        return "Event does not exist, please try again.";
    }

    public String unknownSpeaker(){
        return "This speaker does not exist. Please try again.";
    }

    public String unknownDate(){
        return "The date could not be read. Please try again.";
    }

}
