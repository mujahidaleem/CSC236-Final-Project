package Presenters;

import Controllers.NullSpeakerException;
import Entities.Event;
import Entities.Speaker;
import UseCases.*;
import Controllers.OrganizerEventController;
import UseCases.Language.LanguageManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Displays the event menu for an organizer and interprets the
 * commands inputted by the organizer
 */
public class OrganizerEventPresenter extends EventMenuPresenter {
    private OrganizerEventController organizerEventController;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;

    /**
     * EventMenuPresenter constructor
     *
     * @param organizerManager         stores the current user
     * @param speakerManager           stores a list of speakers
     * @param organizerEventController the controller that performs the commands inputted
     * @param languageManager          decides which language is used in the UI
     */
    public OrganizerEventPresenter(OrganizerManager organizerManager, SpeakerManager speakerManager,
                                   OrganizerEventController organizerEventController, EventManager eventManager,
                                   LanguageManager languageManager) {
        super(organizerManager, organizerEventController, eventManager, languageManager);
        this.organizerEventController = organizerEventController;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
    }

    /**
     * Prints the list of commands that can be executed by the organizer
     */
    @Override
    protected void printCommands() {
        languageManager.languagePack.printEventStandardCommands();
        languageManager.languagePack.printOrganizerCommands();
    }

    /**
     * The menu for events is initialized and commands relating to events can be
     * performed by typing in the correct strings into the command line.
     */
    @Override
    protected void extraCommands(String[] command) {
        outer:
        try {
            switch (command[0]) {
                case "3":
                    Event event1 = organizerEventController.createEvent(command[1], LocalDateTime.parse(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
                    if (event1 == null) {
                        createEventResults(false, null);
                    } else {
                        createEventResults(true, event1);
                    }
                    break outer;
                case "9":
                    Speaker speaker = organizerEventController.createSpeaker(command[1], command[2]);
                    if (speaker == null) {
                        createSpeakerAccountResults(false, null);
                    } else {
                        createSpeakerAccountResults(true, speaker);
                    }
                    break outer;
                case "10":
                    printSpeakers();
                    break outer;
            }
            Event event = eventManager.findEvent(command[1]);
            if (!organizerEventController.eventModifiable(event)) {
                System.out.println(languageManager.languagePack.eventUnchangeable(event));
            } else {
                switch (command[0]) {
                    case "4":
                        setSpeakerResults(organizerEventController.assignSpeaker(event, Integer.parseInt(command[2])), event);
                        break;
                    case "7":
                        changeEventDateResults(organizerEventController.changeEventDate(event, LocalDateTime.parse(command[2])), event);
                        break;
                    case "8":
                        changeEventRoomResults(organizerEventController.changeEventRoom(event, Integer.parseInt(command[2])), event);
                    case "6":
                        deleteEvent(organizerEventController.deleteEvent(event), event);
                        break;
                    case "5":
                        removeSpeakerResults(organizerEventController.removeSpeaker(event), event);
                        break;
                    default:
                        System.out.println(languageManager.languagePack.unknownCommand());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(languageManager.languagePack.unknownEvent());
        } catch (NullSpeakerException e) {
            System.out.println(languageManager.languagePack.unknownSpeaker());
        } catch (DateTimeParseException e) {
            System.out.println(languageManager.languagePack.unknownDate());
        } catch (SameEventNameException e) {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(null)[0]);
        }
    }

    /**
     * Prints the result of trying to create a new event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that the organizer is trying to create
     */
    public void createEventResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[0]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[5]);
        }
    }

    /**
     * Prints the result of trying to assign a speaker to an event
     *
     * @param i     determines whether the command was successful or not
     * @param event The event that the organizer is trying change speakers
     */
    public void setSpeakerResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[1]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[1]);
        }
    }

    /**
     * Prints the results of trying to remove a speaker from an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that the organizer is trying to remove the speaker from
     */
    public void removeSpeakerResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[2]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[2]);
        }
    }

    /**
     * Prints the result of trying to change the time of an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event whose date is trying to be changed by the organizer
     */
    public void changeEventDateResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[3]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[3]);
        }
    }

    /**
     * Prints the result of trying to change the room of an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event whose date is trying to be changed by the organizer
     */
    public void changeEventRoomResults(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[5]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[5]);
        }
    }

    /**
     * Prints the result of trying to remove an event
     *
     * @param i     Determines whether the command was successful or not
     * @param event The event that is being deleted by the organizer
     */
    public void deleteEvent(boolean i, Event event) {
        if (i) {
            System.out.println(languageManager.languagePack.organizerEventResultsSuccess(event)[4]);
        } else {
            System.out.println(languageManager.languagePack.organizerEventResultsFailure(event)[4]);
        }
    }

    /**
     * Prints the result of trying to create a speaker account
     *
     * @param i       determines whether the command was successful or not
     * @param speaker the new speaker account that has been created
     */
    public void createSpeakerAccountResults(boolean i, Speaker speaker) {
        if (i) {
            System.out.println(languageManager.languagePack.speakerAccountSuccess(speaker));
        } else {
            System.out.println(languageManager.languagePack.speakerAccountFailure());
        }
    }

    /**
     * Prints the list of speakers
     */
    private void printSpeakers() {
        System.out.println("------------------------------------------------------");
        for (Speaker speaker : speakerManager.getSpeakers()) {
            System.out.println(speaker);
        }
    }
}