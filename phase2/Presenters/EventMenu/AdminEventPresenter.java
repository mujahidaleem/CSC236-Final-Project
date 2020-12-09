package Presenters.EventMenu;

import Controllers.EventMenu.AdminEventController;
import GUI.Events.AdminEventMenuPanel;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;

public class AdminEventPresenter extends EventMenuPresenter{

    public AdminEventPresenter(AdminManager adminManager, EventManager eventManager, LanguageManager languageManager, AdminEventMenuPanel adminEventMenuPanel){
        super(adminManager, eventManager, languageManager, adminEventMenuPanel);
    }
}
