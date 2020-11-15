package Presenters;

import Controllers.LanguagePack;
import Controllers.NullSpeakerException;
import Controllers.OrganizerEventLanguagePack;
import Entities.Event;
import Entities.Speaker;
import UseCases.EventManager;
import UseCases.OrganizerManager;
import Controllers.OrganizerEventController;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class OrganizerEventPresenter extends EventMenuPresenter {
    private OrganizerEventController organizerEventController;
    private OrganizerManager organizerManager;
    private OrganizerEventLanguagePack organizerEventLanguagePack;

    /**
     * EventMenuPresenter constructor
     *
     * @param organizerManager         stores the current user
     * @param organizerEventController the controller that performs the commands inputted
     * @param language                 decides which language is used in the UI
     */
    public OrganizerEventPresenter(OrganizerManager organizerManager, OrganizerEventController organizerEventController, EventManager eventManager, String language) {
        super(organizerManager, organizerEventController, eventManager, language);
        this.organizerEventController = organizerEventController;
        this.organizerManager = organizerManager;
    }

    /**
     * reads the ser file containing all the commands in a specific language so the event menu is in that language
     */
    @Override
    public void setupLanguagePackage() {
        try {
            FileInputStream fi = new FileInputStream(new File("D:\\Language\\" + language + ".ser"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            this.languagePack = (LanguagePack) oi.readObject();
            this.organizerEventLanguagePack = (OrganizerEventLanguagePack) languagePack;

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error initializing stream.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the list of commands that can be executed by the organizer
     */
    @Override
    protected void printCommands() {
        languagePack.printStandardCommands();
        organizerEventLanguagePack.printOrganizerCommands();
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
                case "Create event":
                    Event event1 = organizerEventController.createEvent(command[1], LocalDateTime.parse(command[2]), Integer.parseInt(command[3]));
                    if (event1 == null) {
                        createEventResults(false, null);
                    } else {
                        createEventResults(true, event1);
                    }
                    break outer;
                case "Create speaker":
                    Speaker speaker = organizerEventController.createSpeaker(command[1], command[2]);
                    if (speaker == null) {
                        createSpeakerAccountResults(false, null);
                    } else {
                        createSpeakerAccountResults(true, speaker);
                    }
                    break outer;
            }
            Event event = eventManager.findEvent(command[1]);
            if (!organizerEventController.eventModifiable(event)) {
                System.out.println(organizerEventLanguagePack.eventUnchangeable(event));
            } else {
                switch (command[0]) {
                    case "Assign speaker":
                        setSpeakerResults(organizerEventController.assignSpeaker(event, Integer.parseInt(command[2])), event);
                        break;
                    case "Change date":
                        changeEventDateResults(organizerEventController.changeEventDate(event, LocalDateTime.parse(command[2])), event);
                        break;
                    case "Change room":
                        changeEventRoomResults(organizerEventController.changeEventRoom(event, Integer.parseInt(command[2])), event);
                    case "Delete":
                        deleteEvent(organizerEventController.deleteEvent(event), event);
                        break;
                    case "Remove speaker":
                        removeSpeakerResults(organizerEventController.removeSpeaker(event), event);
                    default:
                        System.out.println(organizerEventLanguagePack.unknownCommand());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(organizerEventLanguagePack.unknownEvent());
        } catch (NullSpeakerException e) {
            System.out.println(organizerEventLanguagePack.unknownSpeaker());
        } catch (DateTimeParseException e) {
            System.out.println(organizerEventLanguagePack.unknownDate());
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[0]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[0]);
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[1]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[1]);
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[2]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[2]);
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[3]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[3]);
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[5]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[5]);
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
            System.out.println(organizerEventLanguagePack.organizerResultsSuccess(event)[4]);
        } else {
            System.out.println(organizerEventLanguagePack.organizerResultsFailure(event)[4]);
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
            System.out.println(organizerEventLanguagePack.speakerAccountSuccess(speaker));
        } else {
            System.out.println(organizerEventLanguagePack.speakerAccountFailure());
        }
    }
}
