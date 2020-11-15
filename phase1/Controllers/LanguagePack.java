package Controllers;

import Entities.Event;

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
    public void printStandardCommands();

    /**
     * Contains the strings corresponding to successful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] standardResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful standard commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is unsuccessful
     */
    public String[] standardResultsFailure(Event event);

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


}