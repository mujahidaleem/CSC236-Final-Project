package UseCases.Language;

import Entities.Events.Event;
import Entities.Users.User;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class FrenchLanguagePack implements LanguagePack, Serializable {
    public String directory;

    /**
     * EnglishLanguagePack constructor
     *
     * @param language the language of the strings
     */
    public FrenchLanguagePack(String language) {
        this.directory = language + ".ser";
    }

    @Override
    public String[] eventMenuHeadings() {
        return new String[]{"Événements participant", "Événements disponsibles", "Événements parlant à"};
    }

    @Override
    public void printEventStandardCommands() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Pour revenir au menu principal, tapez 0");
        System.out.println("Pour vous inscrire à un événement, tapez 1_Événements");
        System.out.println("Pour annuler votre position dans un événement, tapez 2_Événement");
    }

    @Override
    public void printOrganizerCommands() {
        System.out.println("Pour créer un nouvel événement, tapez 3_Nom_YYYY-MM-DDTHH:mm:ss_numéro de chambre_id_la durée_capacité maximale");
        System.out.println("Pour affecter un orateur à un événement, tapez 4_événement_OrateurID");
        System.out.println("Pour supprimer un orateur d'un événement, tapez 5_événement");
        System.out.println("Pour supprimer un événement, tapez 6_événement");
        System.out.println("Pour changer la date d'un événement, tapez 7_Événement_nouvelle date");
        System.out.println("Pour changer la salle d'un événement, tapez 8_Événement_nouveau numéro de chambre");
        System.out.println("Pour créer un nouveau compte de conférencier, tapez 9_Nom_mot de passe");
        System.out.println("Pour voir la liste des orateurs, tapez 10");
    }

    @Override
    public String[] standardEventResultsSuccess(Event event) {
        return new String[]{"Échec", "Désolé, vous ne pouvez pas assister à cet événement.", "Vous n'êtes déjà pas présent " + event + "."};
    }

    @Override
    public String[] standardEventResultsFailure(Event event) {
        return new String[]{"Échec", "Désolé, vous ne pouvez pas assister à cet événement.",
                "Vous n'assistez déjà pas à " + event.getEventName()};
    }

    @Override
    public String[] organizerEventResultsSuccess(Event event) {
        return new String[]{"Votre événement a été créé avec succès.",
                event.getSpeaker() + " parlera maintenant à " + event.getEventName(),
                "L'enceinte a bien été supprimée.", "les " + event.getEventName() + " aura maintenant lieu à " +
                event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " a été annulé",
                event.getEventName() + " aura maintenant lieu dans la salle " + event.getRoomNumber() + "."
        };
    }

    @Override
    public String[] organizerEventResultsFailure(Event event) {
        return new String[]{"Désolé, un événement portant ce nom existe déjà",
                "Désolé, " + event.getSpeaker() +" n'est pas disponible à ce moment précis.",
                "Cet événement n'a déjà pas de conférencier.",
                "Désolé, l'heure de l'événement ne peut pas être modifiée.",
                event.getEventName() + " ne peut pas être annulé.",
                "Désolé, la salle n'est pas disponible à ce moment-là."
        };
    }

    @Override
    public String unknownCommand() {
        return "Désolé, cette commande n'a pas été reconnue. Veuillez réessayer.";
    }

    @Override
    public String eventUnchangeable(Event event) {
        return event.getEventName() + " ne peut pas être modifié par vous car vous n'êtes pas l'organisateur.";
    }

    @Override
    public String unknownEvent() {
        return "L'événement n'existe pas, veuillez réessayer.";
    }

    @Override
    public String unknownSpeaker() {
        return "Ce haut-parleur n'existe pas. Veuillez réessayer.";
    }

    @Override
    public String unknownDate() {
        return "La date n'a pas pu être lue. Veuillez réessayer.";
    }

    @Override
    public String organizerAccountCreationSuccess(User user) {
        return "Le compte du conférencier a été créé avec le nom d'utilisateur " + user.getId() + " et le mot de passe temporaire " +
                user.getPassword();
    }

    @Override
    public String speakerAccountFailure() {
        return "Désolé, ce compte de conférencier ne peut pas être créé.";
    }

    @Override
    public String loginMenuGreeting(){
        return "\n" +
                "Bienvenue à la conférence! Souhaitez-vous vous connecter à votre compte existant ou créer un nouveau compte?\n"+
                "euillez saisir '1' pour vous connecter à votre compte existant et '2' pour créer un nouveau compte.\n" +
                "Entrez 'exit' pour quitter le programme.";
    }

    @Override
    public String[] loggingInPrompt(){
        return new String[]{"a", "b", "c", "d"}; //TODO
        //return "Veuillez saisir votre identifiant et votre mot de passe, séparés par un _ \nou tapez 'exit' pour revenir en arrière.";
    }

    @Override
    public String[] userCreationPrompt(){
        return new String[]{"Veuillez saisir votre prénom et votre nom séparés par _",
                "s'il vous plait entrez votre mot de passe",
                "Êtes-vous un participant ou un organisateur? Veuillez saisir 'attendee' ou 'organizer' en minuscules."};
    }

    @Override
    public String userCreationResult(String id){
        return "Votre identifiant est " + id + ". Veuillez vous en souvenir pour vous connecter.";
    }

    @Override
    public String incorrectLoginCredentials(){
        return "Votre nom d'utilisateur ou mot de passe est incorrect ou n'existe pas. Veuillez réessayer.";
    }

    @Override
    public String mainMenuCommands(){
        return "---------------------------------------------------------------------------------\n" +
                "Veuillez sélectionner un sous-menu. Tapez le numéro et appuyez sur Entrée:\n" +
                "1. Événements\n2. Messages\n3. Se déconnecter\n4. Changer le mot de passe";
    }

    @Override
    public String changePassword() {
        return "Veuillez saisir votre nouveau mot de passe:";
    }

    @Override
    public void printMessageMenuStandardCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("Pour revenir au menu principal, tapez '0'");
        System.out.println("To send a message to a user, type '1_' followed by their userID");
        System.out.println("Pour envoyer un message à un utilisateur, tapez '1_' suivi de son ID utilisateur");
        System.out.println("Pour supprimer un utilisateur, saisissez '3_' suivi de son ID utilisateur");
    }

    @Override
    public String[] messageMenuHeadings(){
        return new String[]{"Liste d'amis", "Commandes"};
    }

    @Override
    public String sendMessagePrompt(){
        return "Veuillez entrer le message, sinon entrez \"0\" pour fermer le journal de discussion.";
    }

    @Override
    public String unknownFriend() {
        return "Cet utilisateur n'est pas votre ami.";
    }

    @Override
    public String[] messageMenuResultsSuccess(User anotherUser){
        return new String[]{anotherUser.getName() + " est maintenant ton ami.",
                anotherUser.getName() + " n'est plus ton ami."};
    }

    @Override
    public String[] messageMenuResultsFailure(){
        return new String[] {"Désolé, cet utilisateur est déjà votre ami.", "Cet utilisateur n'est déjà pas votre ami."};
    }

    @Override
    public void printOrganizerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("Pour envoyer un message à tous les participants, tapez 4");
        System.out.println("Pour envoyer un message à tous les orateurs, tapez 5");
    }

    @Override
    public String messageAllAttendeePrompt() {
        return "\n" +
                "Veuillez saisir le message que vous souhaitez envoyer à tous les Attendees.";
    }

    @Override
    public String messageAllSpeakerPrompt() {
        return "Veuillez saisir le message que vous souhaitez envoyer à tous les orateurs.";
    }
    @Override
    public String messageSuccessful() {
        return "Message has been sent.";
    }

    @Override
    public void printSpeakerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("Pour envoyer un message à tous les utilisateurs participant à l'une de vos présentations, saisissez 4_ nom de l'événement");
        System.out.println("Pour envoyer un message à tous les utilisateurs participant à l'une de vos présentations, tapez 5");
    }

    @Override
    public String notSpeakerAtEvent() {
        return "Vous ne parlez pas à cet événement.";
    }

    @Override
    public String messageAllAttendeeForOneEventPrompt() {
        return "Veuillez saisir le message que vous souhaitez envoyer à tous les Attendees à cet événement.";
    }

    @Override
    public String saveScheduleAsPdf() {
        return "veuillez saisir où vous souhaitez enregistrer le fichier.";
    }
}
