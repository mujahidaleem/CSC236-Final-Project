package Controllers;

import Entities.Event;
import Entities.Speaker;

public interface OrganizerEventLanguagePack extends  LanguagePack{

    public String[] organizerResultsSuccess(Event event);

    public String[] organizerResultsFailure(Event event);

    public String speakerAccountSuccess(Speaker speaker);

    public String speakerAccountFailure();

    public String eventUnchangeable(Event event);

    public String unknownSpeaker();

}
