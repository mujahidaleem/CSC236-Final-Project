package Controllers;

import UseCases.SpeakerManager;
import UseCases.EventManager;
import Presenters.SpeakerEventPresenter;

public class SpeakerEventController extends EventMenuController{
    public SpeakerManager speakerManager;

    /**
     * SpeakerEventController constructor
     * @param manager contains the current speaker
     * @param eventManager contains the list of events
     * @param presenter displays the possible commands and the results of the commands
     */
    public SpeakerEventController(SpeakerManager manager, EventManager eventManager,
                                  SpeakerEventPresenter presenter) {
        super(manager, eventManager, presenter);
        this.speakerManager = manager;
    }
}