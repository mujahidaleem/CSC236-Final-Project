package UseCases.Language;

import Entities.Events.Event;
import Entities.Users.Speaker;
import Entities.Users.User;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class FrenchLanguagePack implements LanguagePack, Serializable {
    public String directory;

    /**
     * FrenchLanguagePack constructor
     *
     * @param language the language of the strings
     */
    public FrenchLanguagePack(String language) {
        this.directory = language + ".ser";
    }

    @Override
    public String[] eventMenuHeadings() {
        return new String[]{
                "Événements", "Événements auxquels participent", "Événements disponibles", "Événements qui parlent à"};
    }

    @Override
    public String[] eventStandardCommands() {
        return new String[]{"Menu principal", "Inscription", "Quitter", "Nom de l'événement"};
    }

    @Override
    public String[] organizerEventCommands() {
        return new String[]{"Créer un nouvel événement", "Modifier l'événement", "Créer un compte", "Nom de l'événement",
                "Pour modifier les détails d'un événement, veuillez saisir le nom de l'événement et cliquez sur Modifier l'événement."};
    }

    @Override
    public String[] standardEventResultsSuccess(Event event) {
        return new String[]{"Succès", "Vous êtes maintenant inscrit pour " + event + ".",
                "Vous n'assistez plus" + event + "."};
    }

    @Override
    public String[] standardEventResultsFailure(Event event) {
        return new String[]{"Échec", "Désolé, vous ne pouvez pas assister à cet événement.",
                "Vous n'êtes déjà pas présent" + event.getEventName()};
    }

    @Override
    public String[] organizerEventResultsSuccess(Event event) {
        return new String[]{"Votre événement a été créé avec succès.",
                event.getSpeakers() + " parlera maintenant à " + event.getEventName(),
                "L'enceinte a bien été supprimée.", "les " + event.getEventName() + " aura maintenant lieu à " +
                event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " a été annulé",
                event.getEventName() + " aura maintenant lieu dans la salle " + event.getRoomNumber() + "."
        };
    }

    @Override
    public String[] organizerEventResultsFailure(Event event) {
        return new String[]{"Vous ne pouvez pas créer d'événements dans le passé.",
                "Désolé, un événement portant ce nom existe déjà",
                "Désolé, la salle n'est pas disponible à ce moment-là.",
                "Désolé, " + event.getSpeakers() +" n'est pas disponible à ce moment précis.",
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
    public String eventUnchangeable(String event) {
        return event + "ne peut pas être modifié par vous car vous n'êtes pas l'organisateur.";
    }

    @Override
    public String unknownEvent() {
        return "L'événement n'existe pas, veuillez réessayer.";
    }

    /**
     * contains the strings used in the editEventPanel GUI
     *
     * @return strings to generate text
     */
    @Override
    public String[] changeEventPrompts() {
        return new String[]{"Nom", "Numéro de la salle", "Date", "Durée", "Capacité maximale", "Haut-parleurs", "Année",
                "AttendeeOnlyEvent", "MultiSpeakerEvent", "OneSpeakerEvent",
                "Supprimer l'événement", "Enregistrer les modifications", "Annuler", "Ajouter un haut-parleur", "Supprimer le haut-parleur",
                "Mois", "Jour", "Heure", "Minute"};
    }

    /**
     * Contains the string that will be shown upon starting the program
     *
     * @return Greetings to the user and available commands
     */
    @Override
    public String unknownUserID() {
        return null;
    }

    @Override
    public String invalidIDInput() {
        return null;
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
    public String organizerAccountCreationSuccess(User user, String string) {
        return "Le compte du conférencier a été créé avec le nom d'utilisateur " + user.getId() + " et le mot de passe temporaire " +
                user.getPassword();
    }

    @Override
    public String accountCreationFailure() {
        return "Désolé, ce compte de conférencier ne peut pas être créé.";
    }

    @Override
    public String loginMenuGreeting(){
        return "Bienvenue à la conférence!";
    }

    @Override
    public String[] loggingInPrompt(){
        return new String[]{"Nom d'utilisateur", "Mot de passe", "Créer un nouveau compte", "Connexion"}; //TODO
        //return "Veuillez saisir votre identifiant et votre mot de passe, séparés par un _ \nou tapez 'exit' pour revenir en arrière.";
    }

    @Override
    public String[] userCreationPrompt(){
        return new String[]{"Nom", "Mot de passe", "Attendee", "Organisateur", "Créer un compte", "Annuler", "Président", "Admin"};
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
    public String[] mainMenuCommands(){
        return new String[]{"Evénements", "Messages", "Année", "Mois", "Jour", "Afficher le programme", "Enregistrer le programme Pdf",
                "Changer le mot de passe", "Déconnexion"};
//        return "---------------------------------------------------------------------------------\n" +
//                "Veuillez sélectionner un sous-menu. Tapez le numéro et appuyez sur Entrée:\n" +
//                "1. Événements\n2. Messages\n3. Se déconnecter\n4. Changer le mot de passe";
    }

    @Override
    public String[] changePassword() {
        return new String[]{"Veuillez saisir votre nouveau mot de passe:"};
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
        return new String[]{"Liste d'amis", "Commandes", "Ajouter un ami"};
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
        return "Veuillez saisir le message que vous souhaitez envoyer à tous les participants.";
    }

    @Override
    public String messageAllSpeakerPrompt() {
        return "Veuillez saisir le message que vous souhaitez envoyer à tous les orateurs";
    }
    @Override
    public String messageSuccessful() {
        return "Le message a été envoyé.";
    }

    @Override
    public void printSpeakerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("Pour envoyer un message à tous les utilisateurs participant à l'une de vos présentations, saisissez 4_eventName");
        System.out.println("Pour envoyer un message à tous les utilisateurs participant à l'une de vos présentations, tapez 5");
    }

    @Override
    public String notSpeakerAtEvent() {
        return "Vous ne parlez pas à cet événement.";
    }

    @Override
    public String messageAllAttendeeForOneEventPrompt() {
        return "Veuillez saisir le message que vous souhaitez envoyer à tous les participants à cet événement.";
    }

    @Override
    public String saveScheduleAsPdf() {
        return "Veuillez saisir où vous souhaitez enregistrer le fichier.";
    }

    @Override
    public String[] messageMenuButtons() {
        return new String[0];
    }

    @Override
    public String[] messageMenuLabels() {
        return new String[0];
    }

    @Override
    public String logoutPrompt() {
        return "Voulez-vous quitter le programme?";
    }

    @Override
    public String[] adminEventMenuPrompts() {
        return new String[]{"Supprimer l'événement", "Afficher les événements", "Pour voir tous les événements sans participant, appuyez sur Afficher les événements." +
                "Pour supprimer un événement sans participant, saisissez le nom de l'événement dans la zone de texte et appuyez sur Supprimer l'événement.",
                "Événement supprimé.", "Cet événement ne peut pas être supprimé car il a des participants."};
    }

    @Override
    public String changeEventInfoResults(Event event) {
        return event.getSpeakers() + "parlera maintenant à" + event.getEventName() + "\n" + event.getEventName() +
                "se produira maintenant à" + event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")) +
                "\n" + event.getEventName() + "se produira désormais dans la salle" + event.getRoomNumber() + ".";
    }

    public String changeEventTimeFailure(){
        return "Désolé, l'heure de l'événement ne peut pas être modifiée.";
    }

    public String speakerRemovalSuccess(Speaker speaker){
        return speaker.getName() + " ne parle plus à cet événement";
    }

    public String speakerRemovalFailure(Speaker speaker){
        return speaker.getName() + " ne parle déjà pas à cet événement";
    }

    @Override
    public String speakerAdditionSuccess(Speaker speaker) {
        return speaker.getName() + " parlera maintenant de cet événement";
    }

    @Override
    public String speakerAdditionFailure(Speaker speaker) {
        return speaker.getName() + " n'est pas disponible pour le moment";
    }

    @Override
    public String changeEventDurationFailure() {
        return "échec du changement de durée";
    }

    @Override
    public String changeEventCapacityFailure() {
        return "échec du changement de capacité";
    }

    @Override
    public String changeEventRoomFailure() {
        return "échec du vestiaire";
    }
}
