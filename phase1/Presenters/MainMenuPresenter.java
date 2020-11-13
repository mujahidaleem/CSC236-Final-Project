package Presenters;

import java.util.Scanner;

public class MainMenuPresenter {
    /**
     * Print the main menu and takes user input to decide what to do
     */
    public static void printMainMenu() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Please select a sub menu. Type the number and press enter:");
        System.out.println("1. Event Manager");
        System.out.println("2. Messages");
        System.out.println("3. Log out");
        System.out.println("4. Change Password");
        System.out.println("5. Quit");
    }

    public static String takeInput(){
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

}
