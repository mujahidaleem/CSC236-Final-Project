package UseCases;

import Entities.Events.Event;
import UseCases.Events.EventManager;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     // Use ghost j4 for this
     public void ImageGen(String pdf){
     PDFDocument document = new PDFDocument();
     document.load(new File(pdf));
     SimpleRenderer renderer = new SimpleRenderer();

     // set resolution (in DPI)
     renderer.setResolution(300);
     List<Image> images = renderer.render(document);

     }
     */

    /**
     * sorts the list of events in order of when they happen
     *
     * @return a sorted list of events with respect to when they will occur
     */
    public ArrayList<Event> sortEvents() {
        ArrayList<Event> sortedList = new ArrayList<>();
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
     * @return a hashMap showing how many events happen at each time block throughout the week
     */
    public HashMap<LocalDateTime, Integer> eventsOnHour() {
        HashMap<LocalDateTime, Integer> map = new HashMap<>();
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

    public Integer[] getPosition(Event event, int order, int number, int tableX, int tableY, int tableCellWidth, int tableCellHeight, String type) {
        DayOfWeek[] days = new DayOfWeek[]{DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};
        int x = 0;
        int y = 0;
        int i = 0;
        while (!event.getEventTime().getDayOfWeek().equals(days[i])) {
            i++;
        }
        x = tableX + (i * tableCellWidth) - 8 + order * (tableCellWidth / number);
        if (type.equals("swing")) {
            y = tableY - (24 - event.getEventTime().getHour() * tableCellHeight);
            return new Integer[]{x, y, x + (tableCellWidth) / number, y + (event.getDuration() / 60) * tableCellHeight};
        } else {
            y = tableY + (24 - event.getEventTime().getHour()) * tableCellHeight;
            return new Integer[]{x, y, x + (tableCellWidth / number), y - (event.getDuration() / 60) * tableCellHeight};
        }
    }

    public LocalDateTime getStartOfWeek(LocalDateTime date) {
        while (!date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            date = date.minusDays(1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = date.format(formatter) + "T00:00:00";
        return LocalDateTime.parse(now);
    }
}
