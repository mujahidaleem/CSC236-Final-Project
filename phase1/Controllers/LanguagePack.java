package Controllers;

import Entities.Event;
import Entities.Speaker;


public interface LanguagePack {

    public String[] eventMenuHeadings();

    public void printStandardCommands();

    public void printOrganizerCommands();

    public String[] standardResultsSuccess(Event event);

    public String[] standardResultsFailure(Event event);

    public String unknownCommand();

    public String unknownEvent();

    public String unknownDate();
}
