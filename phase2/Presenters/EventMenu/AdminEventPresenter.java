package Presenters.EventMenu;

import GUI.Events.AdminEventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;

import javax.swing.*;

public class AdminEventPresenter extends EventMenuPresenter{
    private AdminEventMenuPanel adminEventMenuPanel;

    /**
     * Constructor for admin event presenter
     * @param adminManager Admin use cases
     * @param eventManager Event use cases
     * @param languageManager Language selector (eng or fr)
     * @param adminEventMenuPanel Admin menu panel
     * @param mainMenuPanel main menu page/panel
     */
    public AdminEventPresenter(AdminManager adminManager, EventManager eventManager, LanguageManager languageManager, AdminEventMenuPanel adminEventMenuPanel, MainMenuPanel mainMenuPanel){
        super(adminManager, eventManager, languageManager, adminEventMenuPanel, mainMenuPanel);
        this.adminEventMenuPanel = adminEventMenuPanel;
    }

    /**
     * Calls the correct prompt when an event has been deleted
     * @param i success or failure of the event deletion
     */
    public void deleteEventResult(boolean i){
        if (i){
            JOptionPane.showMessageDialog(adminEventMenuPanel.getPanel(), languageManager.languagePack.adminEventMenuPrompts()[3]);
        } else {
            JOptionPane.showMessageDialog(adminEventMenuPanel.getPanel(), languageManager.languagePack.adminEventMenuPrompts()[4]);
        }
    }

    /**
     * Presents the event menu
     */
    public void showEventMenu(){
        adminEventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        mainMenuPanel.changePanel(adminEventMenuPanel.getPanel());
    }

    /**
     * Sets up the menu and themes it accordingly
     * @param theme Theme of the program
     */
    public void setUpMenu(String theme){
        adminEventMenuPanel.printMenu(theme);
        showEventMenu();
    }

    /**
     * Reprints the events on screen
     */
    public void reprintEvents(){
        adminEventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
    }

    /**
     * Changes the theme of the program from light to dark or the other way around
     * @param theme Theme of the program
     */
    public void changeTheme(String theme){
        adminEventMenuPanel.changeTheme(theme);
    }

    /**
     * Changes the language of the program
     * @param language Language of the program that is wanted
     */
    public void changeLanguage(String language){
        adminEventMenuPanel.changeLanguage(language);
    }
}
