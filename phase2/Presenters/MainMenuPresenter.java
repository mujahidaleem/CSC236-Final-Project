package Presenters;

import GUI.MainMenuPanel;
import UseCases.Language.LanguageManager;

import javax.swing.*;

public class MainMenuPresenter {
    private LanguageManager languageManager;
    private MainMenuPanel mainMenuPanel;

    /**
     * MainMenuPresenter constructor
     *
     * @param languageManager need to display strings
     * @param mainMenuPanel   the GUI representing the main menu
     */
    public MainMenuPresenter(LanguageManager languageManager, MainMenuPanel mainMenuPanel) {
        this.languageManager = languageManager;
        this.mainMenuPanel = mainMenuPanel;
    }

    /**
     * sets up the main menu panel so it can be presented to the user.
     */
    public void setUpMenu(String theme) {
        mainMenuPanel.setUpMenu(theme);
        mainMenuPanel.setText(languageManager.languagePack);
        mainMenuPanel.changePanel(mainMenuPanel.getPanel());
    }

    /**
     * changes the language of GUI
     *
     * @param language the new language of the GUI
     */
    public void changeLanguage(String language) {
        languageManager.changeLanguage(language);
        mainMenuPanel.setText(languageManager.languagePack);
    }

    /**
     * Asks the user for the new password
     *
     * @return the new password
     */
    public String changePassword() {
        return JOptionPane.showInputDialog(mainMenuPanel.getPanel(), languageManager.languagePack.changePassword()[0]);
    }

    /**
     * Tells the user that the password has been changed
     */
    public void showChangePasswordResult() {
        JOptionPane.showMessageDialog(mainMenuPanel.getPanel(), languageManager.languagePack.changePassword()[1]);
    }

    public void showIncorrectDate(){
        JOptionPane.showMessageDialog(mainMenuPanel.getPanel(), languageManager.languagePack.unknownDate());
    }

    public void changeTheme(String theme){
        mainMenuPanel.changeTheme(theme);
    }
}
