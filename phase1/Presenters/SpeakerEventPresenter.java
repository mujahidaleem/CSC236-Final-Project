package Presenters;

import UseCases.SpeakerManager;

public class SpeakerEventPresenter extends EventMenuPresenter {

    /**
     * SpeakerEventPresenter constructor
     * @param manager stores the current user
     */
    public SpeakerEventPresenter(SpeakerManager manager) {
        super(manager);
    }
}
