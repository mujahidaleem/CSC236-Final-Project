package UseCases;

import Entities.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PdfGenerator {
    private EventManager eventManager;

    /**
     * Constructor for PdfGenerator
     *
     * @param eventManager contains the list of events
     */
    public PdfGenerator(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * sorts the list of events in order of when they happen
     *
     * @return a sorted list of events with respect to when they will occur
     */
    public ArrayList<Event> sortEvents() {
        ArrayList<Event> sortedList = new ArrayList<Event>();
        sortedList.add(eventManager.getEvents().get(0));
        for (int i = 1; i < eventManager.getEvents().size(); i++) {
            sortedList.add(sortEventHelper(eventManager.getEvents().get(i), sortedList, 0), eventManager.getEvents().get(i));
        }
        return sortedList;
    }

    /**
     * A helper method for sortEvents
     *
     * @param event        the event being compared to the other events
     * @param sortedEvents the other events
     * @param n            the current index in the list of events that the current event is being compared to
     * @return where the current event should be placed within the list
     */
    private int sortEventHelper(Event event, ArrayList<Event> sortedEvents, int n) {
        try {
            if (event.getEventTime().isBefore(sortedEvents.get(n).getEventTime())) {
                return n;
            } else {
                return sortEventHelper(event, sortedEvents, n + 1);
            }
        } catch (IndexOutOfBoundsException e) {
            return n;
        }
    }

    /**
     * Finds out how many events are happening at each time block
     *
     * @param startOfWeek the date of the start of the week
     * @return a hashMap showing how many events happen at each time block throughout the week
     */
    public HashMap<LocalDateTime, Integer> eventsOnHour(LocalDateTime startOfWeek) {
        HashMap<LocalDateTime, Integer> map = new HashMap<LocalDateTime, Integer>();
        for (Event event : eventManager.getEvents()) {
            for (int i = 0; i < event.getDuration() / 60; i++) {
                if (map.containsKey(event.getEventTime().plusHours(i))) {
                    map.replace(event.getEventTime().plusHours(i), map.get(event.getEventTime().plusHours(i)) + 1);
                } else {
                    map.put(event.getEventTime().plusHours(i), 1);
                }
            }
        }
        return map;
    }
}
