package Controllers;

import Entities.Event;
import Entities.Speaker;
import UseCases.OrganizerManager;
import UseCases.EventManager;
import UseCases.UserManager;
import UseCases.SpeakerManager;
import Presenters.OrganizerEventPresenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class OrganizerEventController extends EventMenuController{
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private OrganizerEventPresenter presenter;

    /**
     * OrganizerEventController constructor
     * @param manager contains the organizer using the current session
     * @param eventManager contains the list of events
     * @param userManager contains the list of users
     * @param speakerManager contains the list of speakers
     * @param presenter displays the commands and the results of the commands to the UI
     */
    public OrganizerEventController(OrganizerManager manager,
                                    EventManager eventManager, UserManager userManager, SpeakerManager speakerManager,
                                    OrganizerEventPresenter presenter) {
        super(userManager, eventManager, presenter);
        this.organizerManager = manager;
        this.speakerManager = speakerManager;
        this.presenter = presenter;
        System.out.println(presenter.getClass());
    }

    @Override
    protected void extraCommands(String[] command) {
        try{
            switch (command[0]) {
                case "Create event":
                    eventCreation(command[1], LocalDateTime.parse(command[2]));
                    break;
                case "Assign speaker":
                    assignSpeaker(eventManager.findEvent(command[1]), Integer.parseInt(command[2]));
                    break;
                case "Change date":
                    changeEvent(eventManager.findEvent(command[1]), LocalDateTime.parse(command[2]));
                    break;
                case "Delete":
                    deleteEvent(eventManager.findEvent(command[1]));
                    break;
                case "Remove speaker":
                    removeSpeaker(eventManager.findEvent(command[1]));
                default:
                    System.out.println("Command does not exist, please try again.");
            }
        } catch (NullPointerException e){
            System.out.println("This event does not exist. Please try again.");
        } catch (NullSpeakerException e){
            System.out.println("This speaker does not exist. Please try again.");
        } catch (DateTimeParseException e) {
            System.out.println("The date could not be read. Please try again.");
        }
    }

    private void eventCreation(String name, LocalDateTime date) {
        if (eventManager.eventCreatable(name, date, organizerManager.currentOrganizer) &&
                organizerManager.eventCreatable(name, date, organizerManager.currentOrganizer)) {
            Event event = new Event(name, date, organizerManager.currentOrganizer.get_id(), 0, new ArrayList<>());
            eventManager.events.add(event);
            organizerManager.createEvent(organizerManager.currentOrganizer, event);
            presenter.createEventResults(true);
        } else {
            presenter.createEventResults(false);
        }
    }

    private void assignSpeaker(Event event, int speaker) {
        Speaker speaker1 = speakerManager.findSpeaker(speaker);
        if (speaker1 == null){
            throw new NullSpeakerException("Speaker does not exist");
        } else {
            if (speakerManager.available(speaker1, event.getDate())) {
                eventManager.setSpeaker(speaker1, event);
                speakerManager.setSpeaker(speaker1, event);
                presenter.setSpeakerResults(true, event);
            } else {
                presenter.setSpeakerResults(false, event);
            }
        }
    }

    private void removeSpeaker(Event event){
        if (eventManager.hasSpeaker(event)){
            speakerManager.removeEvent(event);
            eventManager.removeSpeaker(event);
            presenter.removeSpeakerResults(true);
        } else {
            presenter.removeSpeakerResults(false);
        }
    }

    private void changeEvent(Event event, LocalDateTime date) {
        if (eventManager.eventDateChangeable(event, date) &&
                speakerManager.dateChangeable(event, date)) {
            speakerManager.changeDate(event, date);
            eventManager.changeDate(event, date);
            presenter.changeEventResults(true, event);
        } else {
            presenter.changeEventResults(false, event);
        }
    }

    private void deleteEvent(Event event) {
        organizerManager.deleteEvent(event);
        eventManager.events.remove(event);
        presenter.deleteEvent(event);
    }
}
