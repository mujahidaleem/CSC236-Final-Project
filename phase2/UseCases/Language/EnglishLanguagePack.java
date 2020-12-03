package UseCases.Language;

import Entities.Events.Event;
import Entities.Users.Speaker;
import Entities.Users.User;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Contains all the strings for the event menu written in english
 */
public class EnglishLanguagePack implements LanguagePack, Serializable {
    private String language;
    public String directory;

    /**
     * EnglishLanguagePack constructor
     *
     * @param language the language of the strings
     */
    public EnglishLanguagePack(String language) {
        this.language = language;
        this.directory = language + ".ser";
    }

    @Override
    public String[] eventMenuHeadings() {
        return new String[]{"Events Attending", "Events Available", "Events Speaking At"};
    }

    @Override
    public void printEventStandardCommands() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("To return to the main menu, type 0");
        System.out.println("To sign up for an event, type 1_Event");
        System.out.println("To cancel your position in an event, type 2_Event");
    }

    @Override
    public void printOrganizerCommands() {
        System.out.println("To create a new event, type 3_Name_YYYY-MM-DDTHH:mm:ss_roomNumber_id_duration");
        System.out.println("To assign a speaker to an event, type 4_Event_SpeakerID");
        System.out.println("To remove a speaker from an event, type 5_Event");
        System.out.println("To delete an event, type 6_Event");
        System.out.println("To change the date of an event, type 7_Event_new date");
        System.out.println("To change the room of an event, type 8_Event_roomNumber");
        System.out.println("To create a new speaker account, type 9_name_password");
        System.out.println("To see the list of speakers, type 10");
    }

    @Override
    public String[] standardEventResultsSuccess(Event event) {
        return new String[]{"Success", "You are now registered for " + event + ".",
                "You are no longer attending " + event + "."};
    }

    @Override
    public String[] standardEventResultsFailure(Event event) {
        return new String[]{"Failure", "Sorry, you are unable to attend this event.",
                "You are already not attending " + event.getEventName()};
    }

    @Override
    public String[] organizerEventResultsSuccess(Event event) {
        return new String[]{"Your event has successfully been created.",
                event.getSpeaker() + " will now be speaking at " + event.getEventName(),
                "Speaker has successfully been removed.", event.getEventName() + " will now occur at " +
                event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " has been cancelled.",
                event.getEventName() + " will now occur in room " + event.getRoomNumber() + "."
        };
    }

    @Override
    public String[] organizerEventResultsFailure(Event event) {
        return new String[]{"Sorry, an event with that name already exists",
                "Sorry, " + event.getSpeaker() + " is not available at that specific time.",
                "This event already does not have a speaker.",
                "Sorry, the event time cannot be changed.",
                event.getEventName() + " cannot be cancelled.",
                "Sorry, the room is not available at that time."
        };
    }

    @Override
    public String unknownCommand() {
        return "Sorry, that command was not recognized. Please try again.";
    }

    @Override
    public String eventUnchangeable(Event event) {
        return event.getEventName() + " cannot be modified by you as you are not the organizer.";
    }

    @Override
    public String unknownEvent() {
        return "Event does not exist, please try again.";
    }

    @Override
    public String unknownSpeaker() {
        return "This speaker does not exist. Please try again.";
    }

    @Override
    public String unknownDate() {
        return "The date could not be read. Please try again.";
    }


    public String speakerAccountSuccess(Speaker speaker) {
        return "Speaker account has been created with username " + speaker.getId() + " and temporary password " +
                speaker.getPassword();
    }

    @Override
    public String speakerAccountFailure() {
        return "Sorry, this speaker account cannot be created.";
    }

    @Override
    public String loginMenuGreeting(){
        return "Welcome to the conference! Would you like to log into your existing account or" +
                " create a new account?\n" +
                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
                "Enter 'exit' to exit the program.";
    }

    @Override
    public String loggingInPrompt(){
        return "Please enter your ID NUMBER and PASSWORD, separated by a _ \nor input \"exit\" to go back.";
    }

    @Override
    public String[] userCreationPrompt(){
        return new String[]{"Please enter your first and last name separated by _",
                "Please enter your password",
                "Are you an attendee or an organizer? Please input \"attendee\" or \"organizer\" in lower case."};
    }

    @Override
    public String userCreationResult(String id){
        return "Your id is " + id + ". Please remember it for logging in.";
    }

    @Override
    public String incorrectLoginCredentials(){
        return "Your username or password is incorrect or does not exist. Please try again";
    }

    @Override
    public String mainMenuCommands(){
        return "---------------------------------------------------------------------------------\n" +
                "Please select a sub menu. Type the number and press enter:\n" +
                "1. Event Manager\n2. Messages\n3. Log out\n4. Change Password";
    }

    @Override
    public String changePassword() {
        return "Please enter your new password:";
    }

    @Override
    public void printMessageMenuStandardCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type '0'");
        System.out.println("To send a message to a user, type '1_' followed by their userID");
        System.out.println("To add a user, type '2_' followed by their userID");
        System.out.println("To remove a user, type '3_' followed by their userID");
    }

    @Override
    public String[] messageMenuHeadings(){
        return new String[]{"Friend List", "Commands"};
    }

    @Override
    public String sendMessagePrompt(){
        return "Please enter the message, otherwise enter \"0\" to close chat log.";
    }

    @Override
    public String unknownFriend() {
        return "This user is not your friend.";
    }

    @Override
    public String[] messageMenuResultsSuccess(User anotherUser){
        return new String[]{anotherUser.getName() + " is now your friend.",
                anotherUser.getName() + " is no longer your friend."};
    }

    @Override
    public String[] messageMenuResultsFailure(){
        return new String[] {"Sorry, this user is already your friend.", "This user is already not your friend."};
    }

    @Override
    public void printOrganizerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("To send a message to all the attendees, type 4");
        System.out.println("To send a message to all the speakers, type 5");
    }

    @Override
    public String messageAllAttendeePrompt() {
        return "Please type the message you want to send to all Attendees.";
    }

    @Override
    public String messageAllSpeakerPrompt() {
        return "Please type the message you want to send to all Speakers";
    }
    @Override
    public String messageSuccessful() {
        return "Message has been sent.";
    }

    @Override
    public void printSpeakerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("To message all users attending one of your talks, type 4_eventName");
        System.out.println("To message all users attending any of your talks, type 5");
    }

    @Override
    public String notSpeakerAtEvent() {
        return "You do not speak at this event.";
    }

    @Override
    public String messageAllAttendeeForOneEventPrompt() {
        return "Please type the message you want to sent to all Attendees of this event.";
    }
}
