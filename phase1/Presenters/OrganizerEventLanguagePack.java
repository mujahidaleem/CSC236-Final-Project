package Presenters;

import Entities.Event;
import Entities.Speaker;
import Presenters.LanguagePack;

public interface OrganizerEventLanguagePack extends LanguagePack {

    /**
     * Prints how to input the extra organizer commands
     */
    public void printOrganizerCommands();

    /**
     * Contains the strings corresponding to successful organizer commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerResultsSuccess(Event event);

    /**
     * Contains the strings corresponding to unsuccessful organizer commands
     *
     * @param event the event which the commands pertain to
     * @return a string telling the user their command is successful
     */
    public String[] organizerResultsFailure(Event event);

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

}
