package Presenters;

import Controllers.NullSpeakerException;
import Entities.Event;
import UseCases.OrganizerManager;
import Controllers.OrganizerEventController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class OrganizerEventPresenter extends EventMenuPresenter {
    private OrganizerEventController organizerEventController;

    /**
     * EventMenuPresenter constructor
     * @param organizerManager stores the current user
     * @param organizerEventController the controller that performs the commands inputted
     * @param language decides which language is used in the UI
     */
    public OrganizerEventPresenter(OrganizerManager organizerManager, OrganizerEventController organizerEventController, String language) {
        super(organizerManager, organizerEventController, language);
        this.organizerEventController = organizerEventController;
    }

    /**
     * Prints the list of commands that can be executed by the organizer
     */
    @Override
    protected void printCommands(){
        languagePack.printStandardCommands();
        languagePack.printOrganizerCommands();
    }

    /**
     * The menu for events is initialized and commands relating to events can be
     * performed by typing in the correct strings into the command line.
     */
    @Override
    protected void extraCommands(String[] command) {
        try{
            if (command[0].equals("Create event")){
                Event event1 = organizerEventController.createEvent(command[1], LocalDateTime.parse(command[2]),
                        Integer.parseInt(command[3]), Integer.parseInt(command[4]));
                if (event1 == null){
                    createEventResults(false, null);
                } else {
                    createEventResults(true, event1);
                }
            } else {
                Event event = eventManager.findEvent(command[1]);
                if(organizerEventController.eventModifiable(event)){
                    System.out.println(languagePack.eventUnchangeable(event));
                } else {
                    switch (command[0]) {
                        case "Assign speaker":
                            setSpeakerResults(organizerEventController.assignSpeaker(event, Integer.parseInt(command[2])), event);
                            break;
                        case "Change date":
                            changeEventResults(organizerEventController.changeEvent(event, LocalDateTime.parse(command[2])), event);
                            break;
                        case "Delete":
                            deleteEvent(organizerEventController.deleteEvent(event),event);
                            break;
                        case "Remove speaker":
                            removeSpeakerResults(organizerEventController.removeSpeaker(event), event);
                        default:
                            System.out.println(languagePack.unknownCommand());
                    }
                }
            }
        } catch (NullPointerException e){
            System.out.println(languagePack.unknownEvent());
        } catch (NullSpeakerException e){
            System.out.println(languagePack.unknownSpeaker());
        } catch (DateTimeParseException e) {
            System.out.println(languagePack.unknownDate());
        }
    }

    /**
     * Prints the result of trying to create a new event
     * @param i Determines whether the command was successful or not
     * @param event The event that the organizer is trying to create
     */
    public void createEventResults(boolean i, Event event){
        if (i) {
            System.out.println(languagePack.organizerResultsSuccess(event)[0]);
        } else {
            System.out.println(languagePack.organizerResultsFailure(event)[0]);
        }
    }

    /**
     * Prints the result of trying to assign a speaker to an event
     * @param i determines whether the command was successful or not
     * @param event The event that the organizer is trying change speakers
     */
    public void setSpeakerResults(boolean i, Event event){
        if (i) {
            System.out.println(languagePack.organizerResultsSuccess(event)[1]);
        } else {
            System.out.println(languagePack.organizerResultsFailure(event)[1]);
        }
    }

    /**
     * Prints the results of trying to remove a speaker from an event
     * @param i Determines whether the command was successful or not
     * @param event The event that the organizer is trying to remove the speaker from
     */
    public void removeSpeakerResults(boolean i, Event event){
        if(i){
            System.out.println(languagePack.organizerResultsSuccess(event)[2]);
        } else {
            System.out.println(languagePack.organizerResultsFailure(event)[2]);
        }
    }

    /**
     * Prints the result of trying to change the time of an event
     * @param i Determines whether the command was successful or not
     * @param event The event whose date is trying to be changed by the organizer
     */
    public void changeEventResults(boolean i, Event event){
        if (i) {
            System.out.println(languagePack.organizerResultsSuccess(event)[3]);
        } else {
            System.out.println(languagePack.organizerResultsFailure(event)[3]);
        }
    }

    /**
     * Prints that an event has been deleted
     * @param i Determines whether hte command was successful or not
     * @param event The event that is being deleted by the organizer
     */
    public void deleteEvent(boolean i, Event event){
        if (i){
            System.out.println(languagePack.organizerResultsSuccess(event)[4]);
        } else {
            System.out.println(languagePack.organizerResultsFailure(event)[4]);
        }
    }
}
