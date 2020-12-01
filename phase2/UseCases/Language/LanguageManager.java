package UseCases.Language;

import java.io.*;

public class LanguageManager {
    public String language;
    public LanguagePack languagePack;

    /**
     * Constructor for LanguageManager
     * @param language the language of the system
     */
    public LanguageManager (String language){
        this.language = language;
        this.languagePack = setupLanguagePackage(language);
    }

    /**
     * Getter for the current language of the system
     * @return the current language of the system
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Changes the language of the system
     * @param language the new language of the system
     */
    public void changeLanguage(String language) {
        this.language = language;
        this.languagePack = setupLanguagePackage(language);
    }

    /**
     * reads the ser file containing all the commands in a specific language so the event menu is in that language
     */
    public LanguagePack setupLanguagePackage(String language) {
        try {
            FileInputStream fi = new FileInputStream(new File(language + ".ser"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            LanguagePack languagePack = (LanguagePack) oi.readObject();

            oi.close();
            fi.close();
            return languagePack;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return setupLanguagePackage("english");
    }
}
