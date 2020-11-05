package src.Controllers;
import src.Entities.Organizer;
import src.Presenters.OrganizerEventPresenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizerEventController extends EventMenuController {
    Organizer currentUser;
    OrganizerManager manager;
    OrganizerEventPresenter presenter;
    EventManager eventManager;
    UserManager userManager;
    SpeakerManager speakerManger;

    public OrganizerEventController(Organizer currentUser, OrganizerManager manager,
                                    EventManager eventmanager, UserManager userManager, SpeakerManager speakerManager,
                                    OrganizerEventPresenter presenter) {
        super();
        this.currentUser = currentUser;
        this.userManager = userManager;
        this.eventManager = eventmanager;
        this.presenter = presenter;
        this.manager = manager;
        this.speakerManger = speakerManager;
    }

    @Override
    public void run() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            presenter.printMenu(eventManager);
            String[] command = userInput.nextLine().split("_");
            if (command[0].equals("return")) {
                break;
            }
            try {
                switch (command[0]) {
                    case "Sign up for":
                        signUpForEvent(findEvent(command[1], eventManager));
                        break;
                    case "Leave":
                        removeSpotFromEvent(findEvent(command[1], eventManager));
                        break;
                    case "Create event":
                        eventCreation(command[1], LocalDateTime.parse(command[2]));
                        break;
                    case "Assign speaker":
                        assignSpeaker(findEvent(command[1], eventManager), command[2]);
                        break;
                    case "Change date":
                        changeEvent(findEvent(command[1], eventManager), LocalDateTime.parse(command[2]));
                        break;
                    case "Delete":
                        deleteEvent(findEvent(command[1], eventManager));
                        break;
                    default:
                        System.out.println("Command does not exist, please try again.");
                }
            } catch (NullPointerException e) {
                System.out.println("Event does not exist, please try again.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Your command was invalid, please try again.");
            } catch (DateTimeParseException e) {
                System.out.println("We cannot recognize that date, please try again.");
            }
        }
    }

    private void eventCreation(String name, LocalDateTime date) {
        if (eventManager.eventCreatable(name, date, currentUser) &&
                manager.eventCreatable(name, date, currentUser)) {
            Event event = new Event(name, date, currentUser.get_name(), null, new ArrayList<>());
            eventManager.events.add(event);
            manager.createEvent(currentUser, event);
            presenter.createEventResults(true);
        } else {
            presenter.createEventResults(false);
        }
    }

    private void assignSpeaker(Event event, String speaker) {
        if (speakerManger.available(speakerManger.findSpeaker(speaker), event.getDate())) {
            eventManager.setSpeaker(speakerManger.findSpeaker(speaker), event);
            speakerManger.setSpeaker(speakerManger.findSpeaker(speaker), event);
            presenter.setSpeakerResults(true, event);
        } else{
            presenter.setSpeakerResults(false, event);
        }
    }

    private void changeEvent(Event event, LocalDateTime date) {
        if (eventManager.eventDateChangeable(event, date) &&
                speakerManger.dateChangeable(event, date)) {
            speakerManger.changeDate(event, date);
            eventManager.changeDate(event, date);
            presenter.changeEventResults(true, event);
        } else {
            presenter.changeEventResults(false, event);
        }
    }

    private void deleteEvent(Event event) {
        userManager.deleteEvent(event);
        eventManager.events.remove(event);
        presenter.deleteEvent(event);
    }
}
