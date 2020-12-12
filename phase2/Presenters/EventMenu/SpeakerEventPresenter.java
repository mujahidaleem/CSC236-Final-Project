package Presenters.EventMenu;


import GUI.Events.SpeakerEventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.SpeakerManager;

/**
 * Displays the event menu for a speaker and interprets the coomands
 * inputted by the speaker on this menu
 */
public class SpeakerEventPresenter extends EventMenuPresenter {
    private SpeakerManager speakerManager;
    private SpeakerEventMenuPanel speakerEventMenuPanel;

    /**
     * EventMenuPresenter constructor
     *
     * @param manager                stores the current user
     * @param languageManager               decides which language is used in the UI
     */
    public SpeakerEventPresenter(SpeakerManager manager, EventManager eventManager, LanguageManager languageManager,
                                 SpeakerEventMenuPanel speakerEventMenuPanel, MainMenuPanel mainMenuPanel) {
        super(manager, eventManager, languageManager, speakerEventMenuPanel, mainMenuPanel);
        this.speakerManager = manager;
        this.speakerEventMenuPanel = speakerEventMenuPanel;
    }

    /**
     * Show the event menu
     */
    public void showEventMenu(){
        speakerEventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        mainMenuPanel.changePanel(speakerEventMenuPanel.getPanel());
    }

    /**
     * Sets up the menu in the correct theme
     * @param theme Theme of the program
     */
    public void setUpMenu(String theme){
        speakerEventMenuPanel.printMenu(theme);
        showEventMenu();
    }
}
