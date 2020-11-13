package Controllers;

import Entities.Event;

import java.time.format.DateTimeFormatter;

public interface LanguagePack {
    public String language = null;
    public String directory = null;

    public String[] menuHeadings();

    public void printStandardCommands();

    public void printOrganizerCommands();

    public String[] standardResultsSuccess(Event event);

    public String[] standardResultsFailure(Event event);

    public String[] organizerResultsSuccess(Event event);

    public String[] organizerResultsFailure(Event event);

    public String unknownCommand();

    public String eventUnchangeable(Event event);

    public String unknownEvent();

    public String unknownSpeaker();

    public String unknownDate();

}
