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

    /**
     * English event menu headings
     * @return The event menu headers in english
     */
    @Override
    public String[] eventMenuHeadings() {
        return new String[]{"Events", "Events Attending", "Events Available", "Events Speaking At"};
    }

    /**
     * English standard commands
     * @return The standard commands in english
     */
    @Override
    public String[] eventStandardCommands() {
        return new String[]{"Main Menu", "Sign Up", "Leave", "Event Name"};
    }

    /**
     * English organizer commands
     * @return The organizer commands in english
     */
    @Override
    public String[] organizerEventCommands() {
        return new String[]{"Create New Event", "Change Event", "Create Account", "Event Name",
                "To change the details of an event, please type the event name and click Change Event."};
    }

    /**
     * English commands for changing events (organizer specific)
     * @return The organizer event changing commands in English
     */
    public String[] changeEventPrompts(){
        return new String[] {"Name", "Room Number", "Date", "Duration", "Max Capacity", "Speakers", "Year",
                "AttendeeOnlyEvent", "MultiSpeakerEvent", "OneSpeakerEvent",
                "Delete Event", "Save Changes", "Cancel", "Add Speaker", "Remove Speaker",
                "Month", "Day", "Hour", "Minute"};
    }

    /**
     * Success text for an event signup in english
     * @param event the event which the commands pertain to
     * @return Success if the event was registered (in english)
     */
    @Override
    public String[] standardEventResultsSuccess(Event event) {
        return new String[]{"Success", "You are now registered for " + event + ".",
                "You are no longer attending " + event + "."};
    }

    /**
     * Failure text for an event signup in english
     * @param event the event which the commands pertain to
     * @return Failure if the event did not register correctly (in english)
     */
    @Override
    public String[] standardEventResultsFailure(Event event) {
        return new String[]{"Failure", "Sorry, you are unable to attend this event.",
                "You are already not attending " + event.getEventName()};
    }

    /**
     * Tells the user that the command inputted cannot be understood
     * @return a string telling the user that their commands is not recognized
     */
    @Override
    public String unknownCommand() {
        return "Unknown command, please try again.";
    }

    /**
     * Success text when an organizer has created an event (in english)
     * @param event the event which the commands pertain to
     * @return Success statement on successful creation of an event (in english)
     */
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

    /**
     * Change event text in english
     * @param event the event which the commands pertain to
     * @return Return string of speakers speaking at event in english
     */
    public String changeEventInfoResults(Event event){
        return event.getSpeakers() + " will now be speaking at " + event.getEventName() + "\n" + event.getEventName() +
                " will now occur at " + event.getEventTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")) +
                "\n" + event.getEventName() + " will now occur in room " + event.getRoomNumber() + ".";
    }

    /**
     * Failure prompt in creation of event (in english)
     * @param event the event which the commands pertain to
     * @return Failure prompt in attempt of an event creation (in english)
     */
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

    /**
     * Failure prompt when attempting to change the time of an event
     * @return Failure prompt in english
     */
    public String changeEventTimeFailure(){
        return "Sorry, the event time cannot be changed.";
    }

    /**
     * Success prompt when speaker was removed successfully
     * @param speaker Given speaker that is removed
     * @return Success prompt of speaker being removed in english
     */
    public String speakerRemovalSuccess(Speaker speaker){
        return speaker.getName() + " is no longer speaking at this event";
    }

    /**
     * Success prompt when speaker wasn't able to be removed
     * @param speaker Given speaker needs to be removed
     * @return Failure prompt of speaker not being removed (in english)
     */
    public String speakerRemovalFailure(Speaker speaker){
        return speaker.getName() + " is already not speaking at this event";
    }

    /**
     * Success prompt when speaker was added successfully
     * @param speaker Given speaker that was added to an event
     * @return Success prompt when the speaker is now speaking at the event (in english)
     */
    @Override
    public String speakerAdditionSuccess(Speaker speaker) {
        return speaker.getName() + " will now be speaking in this event";
    }

    /**
     * Failure prompt when speaker was not available
     * @param speaker Given speaker that was failed to be add to an event
     * @return Failure prompt when the speaker can't be added to speak at this event (in english)
     */
    @Override
    public String speakerAdditionFailure(Speaker speaker) {
        return speaker.getName() + " is unavailable at this time";
    }

    /**
     * Failure prompt when the event duration was not being able to be changed
     * @return Failure prompt when the duration can't be changed in english
     */
    @Override
    public String changeEventDurationFailure() {
        return "The event duration cannot not changed";
    }

    /**
     * Failure prompt when the event capacity was not being able to be changed
     * @return Failure prompt when the event capacity can't be changed in english
     */
    @Override
    public String changeEventCapacityFailure() {
        return "The event capacity cannot be changed";
    }

    /**
     * Failure prompt when the event room was not being able to be changed
     * @return Failure prompt when the event room was not being able to be changed in english
     */
    @Override
    public String changeEventRoomFailure() {
        return "The event room cannot be changed";
    }

    /**
     * Failure prompt when the event cannot be changed
     * @param event the event that the organizer is attempting to modify
     * @return Failure prompt when the event cannot be changed in english
     */
    @Override
    public String eventUnchangeable(String event) {
        return event + " cannot be modified by you as you are not the organizer.";
    }

    /**
     * Failure prompt when the event DNE
     * @return Failure prompt when the event DNE in english
     */
    @Override
    public String unknownEvent() {
        return "Event does not exist, please try again.";
    }

    /**
     * Failure prompt when the user id DNE
     * @return Failure prompt when the user id DNE in english
     */
    @Override
    public String unknownUserID(){
        return "User ID does not exist, please try again.";
    }

    /**
     * Failure prompt when the id is invalid
     * @return Failure prompt when the id is invalid in english
     */
    @Override
    public String invalidIDInput(){
        return "Input user id is invalid, please try again";
    }

    /**
     * Failure prompt when the speaker DNE in english
     * @return Failure prompt when the speaker DNE in english
     */
    @Override
    public String unknownSpeaker() {
        return "This speaker does not exist. Please try again.";
    }

    /**
     * Failure prompt when the date cannot be read
     * @return Failure prompt when the date cannot be read (in english)
     */
    @Override
    public String unknownDate() {
        return "The date could not be read. Please try again.";
    }

    /**
     * Prompt when an organizer account has been created
     * @param user the new speaker account
     * @param type type of user
     * @return Prompt when organizer account has been created successfully in english
     */
    @Override
    public String organizerAccountCreationSuccess(User user, String type) {
        return type + " account has been created with username " + user.getId() + " and temporary password " +
                user.getPassword();
    }

    /**
     * Failure prompt when a speaker account could not be created
     * @return Failure prompt a speaker account could not be created in english
     */
    @Override
    public String accountCreationFailure() {
        return "Sorry, this speaker account cannot be created.";
    }

    /**
     * Greeting for the login menu
     * @return A greeting for the login menu
     */
    @Override
    public String loginMenuGreeting(){
        return "Welcome to the conference!";

//        return "Welcome to the conference! Would you like to log into your existing account or" +
//                " create a new account?\n" +
//                "Please enter '1' to log into your existing account, and '2' to create a new account\n" +
//                "Enter 'exit' to exit the program.";
    }

    /**
     * Logging in prompt in english
     * @return Login commands in english
     */
    @Override
    public String[] loggingInPrompt(){
        return new String[] {"Username", "Password", "Create New Account", "Login"};

        //return "Please enter your ID NUMBER and PASSWORD, separated by a _ \nor input \"exit\" to go back.";
    }

    /**
     * User creation prompts during login phase
     * @return User creation prompts during login phase in english
     */
    @Override
    public String[] userCreationPrompt(){
        return new String[]{"Name", "Password", "Attendee", "Organizer", "Create Account", "Cancel", "Speaker", "Admin"};
    }

    /**
     * User id for logging in prompt
     * @param id the id of the new account
     * @return Prompt instructing the user to remember their user id string in english
     */
    @Override
    public String userCreationResult(String id){
        return "Your id is " + id + ". Please remember it for logging in.";
    }

    /**
     * Incorrect login prompt
     * @return Incorrect login prompt in english
     */
    @Override
    public String incorrectLoginCredentials(){
        return "Your username or password is incorrect or does not exist. Please try again";
    }

    /**
     * Strings for the main menu commands
     * @return main menu commands in english
     */
    @Override
    public String[] mainMenuCommands(){
        return new String[]{"Events", "Messages", "Year", "Month", "Day", "Show Schedule", "Save Schedule Pdf",
                "Change Password", "Logout"};

//                "---------------------------------------------------------------------------------\n" +
//            "Please select a sub menu. Type the number and press enter:\n" +
//                "1. Event Manager\n2. Messages\n3. Log out\n4. Change Password\n5. Save schedule";
    }

    /**
     * Change password prompt
     * @return Change password commands in english
     */
    @Override
    public String[] changePassword() {
        return new String[]{"Please enter your new password:", "Your password has been changed."};
    }

    /**
     * Message menu commands
     */
    @Override
    public void printMessageMenuStandardCommands(){
        System.out.println("------------------------------------------------------------");
        System.out.println("To return to the main menu, type '0'");
        System.out.println("To send a message to a user, type '1_' followed by their userID");
        System.out.println("To add a user, type '2_' followed by their userID");
        System.out.println("To remove a user, type '3_' followed by their userID");
    }

    /**
     *Message menu headers
     * @return Message menu headers in english
     */
    @Override
    public String[] messageMenuHeadings(){
        return new String[]{"Friend List", "Commands", "Add Friend"};
    }

    /**
     * send message prompt
     * @return Send message prompt in english
     */
    @Override
    public String sendMessagePrompt(){
        return "Please enter the message, otherwise enter \"0\" to close chat log.";
    }

    /**
     * Unknown friend prompt
     * @return unknown friend prompt in english
     */
    @Override
    public String unknownFriend() {
        return "This user is not your friend.";
    }

    /**
     * Friend request accepted prompt
     * @param anotherUser user that is the new friend
     * @return friend request success prompt in english
     */
    @Override
    public String[] messageMenuResultsSuccess(User anotherUser){
        return new String[]{anotherUser.getName() + " is now your friend.",
                anotherUser.getName() + " is no longer your friend."};
    }

    /**
     * Message result failure when trying to send a message to a user
     * @return Message result failure in english to specific user
     */
    @Override
    public String[] messageMenuResultsFailure(){
        return new String[] {"Sorry, this user is already your friend.", "This user is already not your friend."};
    }

    /**
     * Message commands in english
     */
    @Override
    public void printOrganizerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("To send a message to all the attendees, type 4");
        System.out.println("To send a message to all the speakers, type 5");
    }

    /**
     * Message prompt to send to all attendees
     * @return message prompt to send to all attendees in english
     */
    @Override
    public String messageAllAttendeePrompt() {
        return "Please type the message you want to send to all Attendees.";
    }

    /**
     * Message prompt to send to all speakers
     * @return message prompt to send to all speakers in english
     */
    @Override
    public String messageAllSpeakerPrompt() {
        return "Please type the message you want to send to all Speakers";
    }

    /**
     * Message prompt when the message sent was successfully sent
     * @return message success prompt in english
     */
    @Override
    public String messageSuccessful() {
        return "Message has been sent.";
    }

    /**
     * Prints the speaker message commands
     */
    @Override
    public void printSpeakerMessageCommands() {
        printMessageMenuStandardCommands();
        System.out.println("To message all users attending one of your talks, type 4_eventName");
        System.out.println("To message all users attending any of your talks, type 5");
    }

    /**
     * Prompt for when a speaker doesn't speak at this event
     * @return Prompt for when a speaker doesn't speak at this event in english
     */
    @Override
    public String notSpeakerAtEvent() {
        return "You do not speak at this event.";
    }

    /**
     * Prompt to send message to all attendees in an event
     * @return Prompt to send message to all attendees in an event in english
     */
    @Override
    public String messageAllAttendeeForOneEventPrompt() {
        return "Please type the message you want to sent to all Attendees of this event.";
    }

    /**
     * Prompt for where the user wants to save the schedule file
     * @return Prompt for where the user wants to save the schedule file n english
     */
    @Override
    public String saveScheduleAsPdf() {
        return "Please input where you want to save the file.";
    }

    /**
     * Logout prompt
     * @return Logout/exit prompt in english
     */
    public String logoutPrompt(){
        return "Do you want to exit the program?";
    }

    /**
     * Admin menu commands
     * @return Admin menu commands/prompts in english
     */
    public String[] adminEventMenuPrompts(){
        return new String[]{ "Delete Event", "Show Events", "To see all events with no attendees, press Show Events. " +
                "To delete an event with no attendees, type the event name into the text box and press Delete Event.",
                "Event Deleted.", "This event cannot be deleted as it has attendees."
        };
    }

    /**
     * Message menu buttons
     * @return Message menu buttons strings in english
     */
    @Override
    public String[] messageMenuButtons(){
        return new String[]{"Add friend", "Delete friend", "Start chat", "Return"};
    }

    /**
     * Message menu labels for friend lists
     * @return Message menu labels for friend lists in english
     */
    @Override
    public String[] messageMenuLabels(){
        return new String[]{"Friend List", "Enter Friend ID:"};
    }
}
