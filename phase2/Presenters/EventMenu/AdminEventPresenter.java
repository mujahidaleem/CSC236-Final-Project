package Presenters.EventMenu;

import GUI.Events.AdminEventMenuPanel;
import GUI.MainMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;

import javax.swing.*;

public class AdminEventPresenter extends EventMenuPresenter{
    private AdminEventMenuPanel adminEventMenuPanel;

    public AdminEventPresenter(AdminManager adminManager, EventManager eventManager, LanguageManager languageManager, AdminEventMenuPanel adminEventMenuPanel, MainMenuPanel mainMenuPanel){
        super(adminManager, eventManager, languageManager, adminEventMenuPanel, mainMenuPanel);
        this.adminEventMenuPanel = adminEventMenuPanel;
    }

    public void deleteEventResult(boolean i){
        if (i){
            JOptionPane.showMessageDialog(adminEventMenuPanel.getPanel(), languageManager.languagePack.adminEventMenuPrompts()[3]);
        } else {
            JOptionPane.showMessageDialog(adminEventMenuPanel.getPanel(), languageManager.languagePack.adminEventMenuPrompts()[4]);
        }
    }

    public void setUpMenu(){
        adminEventMenuPanel.printMenu();
        adminEventMenuPanel.setText(eventManager.getEvents(), manager.getCurrentUser(), languageManager.languagePack);
        mainMenuPanel.changePanel(adminEventMenuPanel.getPanel());
    }

    public void reprintEvents(){
        adminEventMenuPanel.reprintEvents(eventManager.getEvents(), manager.getCurrentUser());
    }
}
