package Presenters.EventMenu;

import Controllers.EventMenu.AdminEventController;
import UseCases.Events.EventManager;
import UseCases.Language.LanguageManager;
import UseCases.Users.AdminManager;

public class AdminEventPresenter extends EventMenuPresenter{
    public AdminEventPresenter(AdminManager adminManager, AdminEventController adminEventController, EventManager eventManager, LanguageManager languageManager){
        super(adminManager, adminEventController, eventManager, languageManager);
    }
}
