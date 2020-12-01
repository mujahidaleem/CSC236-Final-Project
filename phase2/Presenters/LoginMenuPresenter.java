package Presenters;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;

import java.util.Scanner;


public class LoginMenuPresenter {

    public LoginMenuController loginMenuController;
    public LanguageManager languageManager;

    /**
     * LoginMenuPresenter constructor
     *
     * @param loginMenuController need to call loginMenuController
     */

    public LoginMenuPresenter(LoginMenuController loginMenuController, LanguageManager languageManager) {
        this.loginMenuController = loginMenuController;
        this.languageManager = languageManager;
    }

    public void changeLanguage(String language){
        languageManager.changeLanguage(language);
    }

    /**
     * First, calls the greeting message with the commands available to the user
     * The user can create an account or login
     * Depending on the command, a switch case will be triggered
     * Logging in command will push the user to the loginCommand method
     * The user can exit by typing "exit"
     * The create account is done by inputting a name (it can be anything) and then password, the id is given at the end
     * The id is used to login, to allow for users who have the same name
     */
    public void run() {
        outer:
        while (true) {
            greetingMessage();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {
                case "exit":
                    break outer;
                case "1":
                    System.out.println(languageManager.languagePack.loggingInPrompt());
                    loginCommand(scanner.next());
                    break;
                case "2":
                    System.out.println(languageManager.languagePack.userCreationPrompt()[0]);
                    String name = scanner.next();
                    System.out.println(languageManager.languagePack.userCreationPrompt()[1]);
                    String password = scanner.next();
                    System.out.println(languageManager.languagePack.userCreationPrompt()[2]);
                    while (true) {
                        String type = scanner.next();
                        if (type.equals("attendee") || type.equals("organizer")) {
                            loginMenuController.signUp(name, password, type);
                            String id = String.valueOf(loginMenuController.return_id());
                            System.out.println(languageManager.languagePack.userCreationResult(id));
                            break;
                        }
                        System.out.println(languageManager.languagePack.unknownCommand());
                    }
                    break;
            }
        }
    }

    /**
     * The login security checkpoint
     * Inputted USERID_PASSWORD is checked. If correct the user moves to their main menu
     * Otherwise, go back to run()
     *
     * @param command inputted USERID_PASSWORD from run method
     */
    public void loginCommand(String command) {
        if (command.equals("exit")) {
            return;
        }
        if (loginMenuController.checkLogin(command) != null) {
            loginMenuController.login(languageManager).run();
        } else {
            System.out.println(languageManager.languagePack.incorrectLoginCredentials());
        }
    }

    /**
     * Generic Greeting message
     */
    public void greetingMessage() {
        System.out.println(languageManager.languagePack.loginMenuGreeting());
    }
}

