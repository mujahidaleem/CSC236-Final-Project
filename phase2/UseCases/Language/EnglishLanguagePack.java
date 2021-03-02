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
    public String directory;

    /**
     * EnglishLanguagePack constructor
     *
     * @param language the language of the strings
     */
    public EnglishLanguagePack(String language) {
        this.directory = language + ".ser";
    }

    @Override
    public String[] eventMenuHeadings() {
        return new String[]{"Events", "Events Attending", "Events Available", "Events Speaking At"};
    }

    @Override
    public String[] eventStandardCommands() {
        return new String[]{"Main Menu", "Sign Up", "Leave", "Event Name"};
    }

    @Override
    public String[] organizerEventCommands() {
        return new String[]{"Create New Event", "Change Event", "Create Account", "Event Name",
                "To change the details of an event, please type the event name and click Change Event."};
    }

    public String[] changeEventPrompts(){
        return new String[] {"Name", "Room Number", "Date", "Duration", "Max Capacity", "Speakers", "Year",
                "AttendeeOnlyEvent", "MultiSpeakerEvent", "OneSpeakerEvent",
                "Delete Event", "Save Changes", "Cancel", "Add Speaker", "Remove Speaker",
                "Month", "Day", "Hour", "Minute", "Please type the id of the speaker."};
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
                event.getSpeakers() + " will now be speaking at " + event.getEventName(),
                "Speaker has successfully been removed.",
                event.getEventName() + " will now occur at " + event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")),
                event.getEventName() + " has been cancelled.",
                event.getEventName() + " will now occur in room " + event.getRoomNumber() + "."
        };
    }

    public String changeEventInfoResults(Event event){
        return event.getSpeakers() + " will now be speaking at " + event.getEventName() + "\n" + event.getEventName() +
                " will now occur at " + event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")) +
                "\n" + event.getEventName() + " will now occur in room " + event.getRoomNumber() + ".";
    }

    @Override
    public String[] organizerEventResultsFailure(Event event) {
        return new String[]{"You cannot create events in the past.",
                "Sorry, an event with that name already exists",
                "Sorry, the room is not available at that time.",
                "Sorry, " + event.getSpeakers() + " is not available at that specific time.",
                "This event already does not have a speaker.",
                "Sorry, the event time cannot be changed.",
                event.getEventName() + " cannot be cancelled.",
                "Sorry, this event could not be created."
        };
    }

    public String changeEventTimeFailure(){
        return "Sorry, the event time cannot be changed.";
    }

    public String speakerRemovalSuccess(Speaker speaker){
        return speaker.getName() + " is no longer speaking at this event";
    }

    public String speakerRemovalFailure(Speaker speaker){
        return speaker.getName() + " is already not speaking at this event";
    }

    @Override
    public String speakerAdditionSuccess(Speaker speaker) {
        return speaker.getName() + " will now be speaking in this event";
    }

    @Override
    public String speakerAdditionFailure(Speaker speaker) {
        return speaker.getName() + " is unavailable at this time";
    }

    @Override
    public String changeEventDurationFailure() {
        return "duration change unsuccessful";
    }

    @Override
    public String changeEventCapacityFailure() {
        return "capacity change unsuccessful";
    }

    @Override
    public String changeEventRoomFailure() {
        return "change room unsuccessful";
    }

    @Override
    public String unknownCommand() {
        return "Sorry, that command was not recognized. Please try again.";
    }

    @Override
    public String eventUnchangeable(String event) {
        return event + " cannot be modified by you as you are not the organizer.";
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

    @Override
    public String organizerAccountCreationSuccess(User user, String type) {
        return type + " account has been created with username " + user.getId() + " and temporary password " +
                user.getPassword();
    }

    @Override
    public String accountCreationFailure() {
        return "Sorry, this speaker account cannot be created.";
    }

    @Override
    public String loginMenuGreeting(){
        return "Welcome to the conference!";

//        return "Welcome to the conference! Would you like to log into your existing account or" +
//                " create a new account?\n" +
//                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
//                "Enter 'exit' to exit the program.";
    }

    @Override
    public String[] loggingInPrompt(){
        return new String[] {"Username", "Password", "Create New Account", "Login"};

        //return "Please enter your ID NUMBER and PASSWORD, separated by a _ \nor input \"exit\" to go back.";
    }

    @Override
    public String[] userCreationPrompt(){
        return new String[]{"Name", "Password", "Attendee", "Organizer", "Create Account", "Cancel", "Speaker", "Admin"};
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
    public String[] mainMenuCommands(){
        return new String[]{"Events", "Messages", "Year", "Month", "Day", "Show Schedule", "Save Schedule Pdf",
                "Change Password", "Logout"};

//                "---------------------------------------------------------------------------------\n" +
//            "Please select a sub menu. Type the number and press enter:\n" +
//                "1. Event Manager\n2. Messages\n3. Log out\n4. Change Password\n5. Save schedule";
    }

    @Override
    public String[] changePassword() {
        return new String[]{"Please enter your new password:", "Your password has been changed."};
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
        return new String[]{"Friend List", "Commands", "Add Friend"};
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

    @Override
    public String saveScheduleAsPdf() {
        return "Please input where you want to save the file.";
    }

    public String logoutPrompt(){
        return "Do you want to exit the program?";
    }

    public String[] adminEventMenuPrompts(){
        return new String[]{ "Delete Event", "Show Events", "To see all events with no attendees, press Show Events. " +
                "To delete an event with no attendees, type the event name into the text box and press Delete Event.",
                "Event Deleted.", "This event cannot be deleted as it has attendees."
        };
    }
}
