package Controllers;

import Entities.Event;
import Entities.Speaker;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class EnglishLanguagePack implements LanguagePack, OrganizerEventLanguagePack, Serializable {
    private String language;
    public String directory;

    /**
     * EnglishLanguagePack constructor
     * @param language the language of the strings
     */
    public EnglishLanguagePack(String language){
        this.language = language;
        this.directory = "D:\\Language\\" + language + ".ser";
    }

    /**
     * Contains the headings of the eventMenu
     * @return the headings of the eventMenu
     */
    public String[] eventMenuHeadings(){
        return new String[]{"Events Attending", "Events Available"};
    }

    /**
     * Prints the instructions on how to input the standard commands
     */
    public void printStandardCommands(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println("To sign up for an event, type 1_Event");
        System.out.println("To cancel your position in an event, type 2_Event");
    }

    /**
     * Prints how to input the extra organizer commands
     */
    public void printOrganizerCommands(){
        System.out.println("To create a new event, type Create event_Name_YYYY-MM-DDTHH:mm:ss_roomNumber_id");
        System.out.println("To assign a speaker to an event, type Assign speaker_Event_SpeakerID");
        System.out.println("To remove a speaker from an event, type Remove speaker_Event");
        System.out.println("To delete an event, type Delete_Event");
        System.out.println("To change the date of an event, type Change date_Event_new date");
        System.out.println("To change the room of an event, type Change room_Event_roomNumber");
        System.out.println("To create a new speaker account, type Create speaker_name_password");
    }

    /**
     * Contains the strings corresponding to successful standard commands
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] standardResultsSuccess(Event event){
        return new String[]{"Success", "You are now registered for " + event + ".",
                "You are no longer attending " + event + "."};
    }

    /**
     * Contains the strings corresponding to unsuccessful standard commands
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is unsuccessful
     */
    public String[] standardResultsFailure(Event event){
        return new String[]{"Failure", "Sorry, you are unable to attend this event.",
                "You are already not attending " + event.getEventName()};
    }

    /**
     * Contains the strings corresponding to successful organizer commands
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerResultsSuccess(Event event){
        return new String[]{"Your event has successfully been created.",
                event.getSpeaker() + " will now be speaking at " + event.getEventName(),
                "Speaker has successfully been removed.", event.getEventName() + " will now occur at " +
                event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " has been cancelled.",
                event.getEventName() + " will now occur in room " + event.getRoomNumber() + "."
        };
    }

    /**
     * Contains the strings corresponding to unsuccessful organizer commands
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerResultsFailure(Event event){
        return new String[]{"Sorry, your event cannot be created. Please try again",
                "Sorry, " + event.getSpeaker() + " is not available at that specific time.",
                "This event already does not have a speaker.",
                "Sorry, the event time cannot be changed.",
                event.getEventName() + " cannot be cancelled.",
                "Sorry, the room is not available at that time."
        };
    }

    /**
     * Tells the user that the command inputted cannot be understood
     * @return a string telling the user that their commands is not recognized
     */
    public String unknownCommand(){
        return "Sorry, that command was not recognized. Please try again.";
    }

    /**
     * Tells the user cannot modified the selected event
     * @param event the event that the organizer is attempting to modify
     * @return a string telling that event cannot be modified
     */
    public String eventUnchangeable(Event event){
        return event.getEventName() + " cannot be modified by you as you are not the organizer.";
    }

    /**
     * Tells the user that the given event does not exist
     * @return a string telling that the event does not exist
     */
    public String unknownEvent(){
        return "Event does not exist, please try again.";
    }

    /**
     * Tells the user that the speaker does not exist
     * @return a string telling thhe user that the speaker does not exist
     */
    public String unknownSpeaker(){
        return "This speaker does not exist. Please try again.";
    }

    /**
     * Tells the user that the date inputted could not be understood
     * @return a string telling the user that the date inputted could not be understood
     */
    public String unknownDate(){
        return "The date could not be read. Please try again.";
    }

    /**
     * Contains the string telling the user that a new speaker account has been created
     * @param speaker the new speaker account
     * @return a string telling the user that a new speaker account has been created
     */
    public String speakerAccountSuccess(Speaker speaker){
        return "Speaker account has been created with username " + speaker.getId() + " and temporary password " +
                speaker.getPassword();
    }

    /**
     * Contains the string telling the user that a speaker cannot be created
     * @return a string telling the user that a new speaker account cannot be created
     */
    public String speakerAccountFailure(){
        return "Sorry, this speaker account cannot be created.";
    }
}
