package UseCases.Language;

import Entities.Event;
import Entities.Speaker;
import Entities.User;

/**
 * Contains the strings displayed on the event manager in a specific manager
 */
public interface LanguagePack {

    /**
     * Contains the headings of the eventMenu
     *
     * @return the headings of the eventMenu
     */
    public String[] eventMenuHeadings();

    /**
     * Prints the instructions on how to input the standard commands
     */
    public void printEventStandardCommands();

    /**
     * Contains the strings corresponding to successful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] standardEventResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is unsuccessful
     */
    public String[] standardEventResultsFailure(Event event);

    /**
     * Tells the user that the command inputted cannot be understood
     *
     * @return a string telling the user that their commands is not recognized
     */
    public String unknownCommand();

    /**
     * Tells the user that the given event does not exist
     *
     * @return a string telling that the event does not exist
     */
    public String unknownEvent();

    /**
     * Contains the string that will be shown upon starting the program
     *
     * @return Greetings to the user and available commands
     */
    public String loginMenuGreeting();

    /**
     * Contains the strings that tells the user how to login
     *
     * @return commands to login in
     */
    public String loggingInPrompt();

    /**
     * Contains the strings that tell the user how to create an account
     *
     * @return commands on how to create an account.
     */
    public String[] userCreationPrompt();

    /**
     * Returns the id of the new account
     * @param id the id of the new account
     * @return string containing the id of the new account
     */
    public String userCreationResult(String id);

    /**
     * Tells the user that the inputted ID and password is incorrect
     *
     * @return an error message saying that the ID and password are incorrect
     */
    public String incorrectLoginCredentials();

    /**
     * Contains the strings telling the user the available commands at the main menu
     *
     * @return a list of commands available at the main menu
     */
    public String mainMenuCommands();

    /**
     * Contains the strings associated with changing the password
     *
     * @return the strings associated with changing the password
     */
    public String changePassword();

    /**
     * Contains the list of standard commands for the message menu
     * @return a list of standard commands of the message menu
     */
    public void printMessageMenuStandardCommands();

    /**
     * Contains the strings for the headings on the message menu
     * @return a list of headings on the message menu
     */
    public String[] messageMenuHeadings();

    /**
     * Tells the user how to send a message
     * @return commands on how to send a message
     */
    public String sendMessagePrompt();

    /**
     * Tells the user that this other user is not a friend
     * @return tells the user that the other user is not a friend
     */
    public String unknownFriend();

    /**
     * Contains the strings corresponding to successful standard commands on the message menu
     * @return a string telling the user their command was successful
     */
    public String[] messageMenuResultsSuccess(User user);

    /**
     * Contains the strings corresponding to unsuccessful standard commands on the message menu
     * @return a string telling the user their command was unsuccessful
     */
    public String[] messageMenuResultsFailure();

    /**
     * Prints how to input the extra organizer event commands
     */
    public void printOrganizerCommands();

    /**
     * Contains the strings corresponding to successful organizer event commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerEventResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful organizer event commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerEventResultsFailure(Event event);

    /**
     * Contains the string telling the user that a new speaker account has been created
     *
     * @param speaker the new speaker account
     * @return a string telling the user that a new speaker account has been created
     */
    public String speakerAccountSuccess(Speaker speaker);

    /**
     * Contains the string telling the user that a speaker cannot be created
     *
     * @return a string telling the user that a new speaker account cannot be created
     */
    public String speakerAccountFailure();

    /**
     * Tells the user cannot modified the selected event
     *
     * @param event the event that the organizer is attempting to modify
     * @return a string telling that event cannot be modified
     */
    public String eventUnchangeable(Event event);

    /**
     * Tells the user that the speaker does not exist
     *
     * @return a string telling the user that the speaker does not exist
     */
    public String unknownSpeaker();

    /**
     * Tells the user that the date inputted could not be understood
     *
     * @return a string telling the user that the date inputted could not be understood
     */
    public String unknownDate();

    /**
     * Prints how to input the extra organizer message commands
     */
    public void printOrganizerMessageCommands();

    /**
     * Contains the prompts related to messaging all attendees.
     *
     * @return a string telling the user their command is successful
     */
    public String messageAllAttendeePrompt();

    /**
     * Contains the prompts related to messaging all speakers.
     *
     * @return a string telling the user their command is successful
     */
    public String messageAllSpeakerPrompt();

    /**
     * Contains the prompts related to messaging all attendees.
     *
     * @return a string telling the user their command is successful
     */
    public String messageSuccessful();

    /**
     * Prints how to input the extra speaker message commands
     */
    public void printSpeakerMessageCommands();

    /**
     * Tells the speaker that they do not speak at this event
     * @return a string telling the speaker they do not speak at this event
     */
    public String notSpeakerAtEvent();

    /**
     * Contains the prompts related to messaging all attendees of an event.
     *
     * @return a string telling the user their command is successful
     */
    public String messageAllAttendeeForOneEventPrompt();
}