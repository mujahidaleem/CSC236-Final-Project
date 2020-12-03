package UseCases.Language;

import Entities.Events.Event;
import Entities.Users.User;

/**
 * Contains the strings displayed on the event manager in a specific manager
 */
public interface LanguagePack {

    /**
     * Contains the headings of the eventMenu
     *
     * @return the headings of the eventMenu
     */
    String[] eventMenuHeadings();

    /**
     * Prints the instructions on how to input the standard commands
     */
    void printEventStandardCommands();

    /**
     * Contains the strings corresponding to successful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    String[] standardEventResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is unsuccessful
     */
    String[] standardEventResultsFailure(Event event);

    /**
     * Tells the user that the command inputted cannot be understood
     *
     * @return a string telling the user that their commands is not recognized
     */
    String unknownCommand();

    /**
     * Tells the user that the given event does not exist
     *
     * @return a string telling that the event does not exist
     */
    String unknownEvent();

    /**
     * Contains the string that will be shown upon starting the program
     *
     * @return Greetings to the user and available commands
     */
    String loginMenuGreeting();

    /**
     * Contains the strings that tells the user how to login
     *
     * @return commands to login in
     */
    String loggingInPrompt();

    /**
     * Contains the strings that tell the user how to create an account
     *
     * @return commands on how to create an account.
     */
    String[] userCreationPrompt();

    /**
     * Returns the id of the new account
     * @param id the id of the new account
     * @return string containing the id of the new account
     */
    String userCreationResult(String id);

    /**
     * Tells the user that the inputted ID and password is incorrect
     *
     * @return an error message saying that the ID and password are incorrect
     */
    String incorrectLoginCredentials();

    /**
     * Contains the strings telling the user the available commands at the main menu
     *
     * @return a list of commands available at the main menu
     */
    String mainMenuCommands();

    /**
     * Contains the strings associated with changing the password
     *
     * @return the strings associated with changing the password
     */
    String changePassword();

    /**
     * Contains the list of standard commands for the message menu
     */
    void printMessageMenuStandardCommands();

    /**
     * Contains the strings for the headings on the message menu
     * @return a list of headings on the message menu
     */
    String[] messageMenuHeadings();

    /**
     * Tells the user how to send a message
     * @return commands on how to send a message
     */
    String sendMessagePrompt();

    /**
     * Tells the user that this other user is not a friend
     * @return tells the user that the other user is not a friend
     */
    String unknownFriend();

    /**
     * Contains the strings corresponding to successful standard commands on the message menu
     * @return a string telling the user their command was successful
     */
    String[] messageMenuResultsSuccess(User user);

    /**
     * Contains the strings corresponding to unsuccessful standard commands on the message menu
     * @return a string telling the user their command was unsuccessful
     */
    String[] messageMenuResultsFailure();

    /**
     * Prints how to input the extra organizer event commands
     */
    void printOrganizerCommands();

    /**
     * Contains the strings corresponding to successful organizer event commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    String[] organizerEventResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful organizer event commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    String[] organizerEventResultsFailure(Event event);

    /**
     * Contains the string telling the user that a new speaker account has been created
     *
     * @param user the new speaker account
     * @return a string telling the user that a new speaker account has been created
     */
    String organizerAccountCreationSuccess(User user);

    /**
     * Contains the string telling the user that a speaker cannot be created
     *
     * @return a string telling the user that a new speaker account cannot be created
     */
    String speakerAccountFailure();

    /**
     * Tells the user cannot modified the selected event
     *
     * @param event the event that the organizer is attempting to modify
     * @return a string telling that event cannot be modified
     */
    String eventUnchangeable(Event event);

    /**
     * Tells the user that the speaker does not exist
     *
     * @return a string telling the user that the speaker does not exist
     */
    String unknownSpeaker();

    /**
     * Tells the user that the date inputted could not be understood
     *
     * @return a string telling the user that the date inputted could not be understood
     */
    String unknownDate();

    /**
     * Prints how to input the extra organizer message commands
     */
    void printOrganizerMessageCommands();

    /**
     * Contains the prompts related to messaging all attendees.
     *
     * @return a string telling the user their command is successful
     */
    String messageAllAttendeePrompt();

    /**
     * Contains the prompts related to messaging all speakers.
     *
     * @return a string telling the user their command is successful
     */
    String messageAllSpeakerPrompt();

    /**
     * Contains the prompts related to messaging all attendees.
     *
     * @return a string telling the user their command is successful
     */
    String messageSuccessful();

    /**
     * Prints how to input the extra speaker message commands
     */
    void printSpeakerMessageCommands();

    /**
     * Tells the speaker that they do not speak at this event
     * @return a string telling the speaker they do not speak at this event
     */
    String notSpeakerAtEvent();

    /**
     * Contains the prompts related to messaging all attendees of an event.
     *
     * @return a string telling the user their command is successful
     */
    String messageAllAttendeeForOneEventPrompt();
}