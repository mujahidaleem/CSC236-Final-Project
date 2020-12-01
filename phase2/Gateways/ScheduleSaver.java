package Gateways;

import Entities.Event;
import UseCases.EventManager;
import UseCases.PdfGenerator;
import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleSaver {
    private EventManager eventManager;
    private PdfGenerator pdfGenerator;
    private float tableX = 36;
    private float tableY = 36;
    private float tableWidth = 500;
    private float tableHeight = 750;
    private float tableCellWidth = 63.5f;
    private float tableCellHeight = 30f;

    /**
     * Constructor for Gateways.ScheduleSaver
     *
     * @param eventManager contains the list of events
     */
    public ScheduleSaver(EventManager eventManager) {
        this.eventManager = eventManager;
        this.pdfGenerator = new PdfGenerator(eventManager);
    }

    /**
     * Draws a block on the time table to represent an event
     *
     * @param event  the event whose block is being drawn
     * @param order  the position of the block compared to the other blocks in the same time block
     * @param number the total number of blocks in that specific time block
     * @param under  fills in the colour of the block
     * @param over   draws the block
     */
    protected void drawBlock(Event event, int order, int number, PdfContentByte under, PdfContentByte over) {
        under.saveState();
        BaseColor colour = WebColors.getRGBColor("CYAN");
        under.setColorFill(colour);
        Rectangle rectangle = getPosition(event, order, number);
        under.rectangle(rectangle.getLeft(), rectangle.getBottom(), rectangle.getWidth(), rectangle.getHeight());
        under.fill();
        over.rectangle(rectangle.getLeft(), rectangle.getBottom(), rectangle.getWidth(), rectangle.getHeight());
        over.stroke();
        under.restoreState();
    }

    /**
     * Writes the name and room number of an event on its block
     *
     * @param event         the event whose information is being written
     * @param order         the position of the block compared to the other blocks in the same time block
     * @param number        the total number of blocks in that specific time block
     * @param directContent writes the String
     * @throws DocumentException tells the program if the document cannot be accessed
     */
    protected void writeEventInfo(Event event, int order, int number, PdfContentByte directContent) throws DocumentException {
        Rectangle rectangle = getPosition(event, order, number);
        ColumnText columnText = new ColumnText(directContent);
        columnText.setSimpleColumn(new Phrase(event.getEventName() + "\n" + event.getRoomNumber()), rectangle.getLeft(), rectangle.getBottom(), rectangle.getLeft() + rectangle.getWidth(), rectangle.getBottom() + rectangle.getHeight(), 10, Element.ALIGN_CENTER);
        columnText.go();
    }

    /**
     * Saves the schedule of this week as a PDF
     *
     * @throws IOException       tells the user that the file could not be saved
     * @throws DocumentException tells the user that the document cannot be accessed
     */
    public void generatePDF() throws DocumentException, IOException {
        Document doc = new Document();

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Schedule.pdf"));
        doc.open();
        doc.add(new Paragraph("Schedule of this week."));

        PdfContentByte cb = writer.getDirectContent();

        drawTimeTable(cb);
        drawTimeSlots(cb);
        writeText(cb);

        PdfContentByte under = writer.getDirectContentUnder();
        PdfContentByte over = writer.getDirectContent();
        addEvents(over, under);

        doc.close();
    }

    /**
     * Adds blocks to the time table representing the events happening this week
     *
     * @param over  draws the rectangles and writes the strings
     * @param under fills in the colour of the rectangles
     * @throws DocumentException tells the user that the document could not be accessed
     */
    private void addEvents(PdfContentByte over, PdfContentByte under) throws DocumentException {
        HashMap<LocalDateTime, ArrayList<Integer>> orders = new HashMap<LocalDateTime, ArrayList<Integer>>();
        HashMap<LocalDateTime, Integer> number = pdfGenerator.eventsOnHour(getStartOfWeek());

        for (Event event : pdfGenerator.sortEvents()) {
            if (event.getEventTime().isAfter(getStartOfWeek()) && event.getEventTime().isBefore(getStartOfWeek().plusDays(7))) {
                int order = 0;
                try {
                    while (orders.get(event.getEventTime()).contains(order)) {
                        order++;
                    }
                } catch (NullPointerException e) {
                    orders.put(event.getEventTime(), new ArrayList<Integer>());
                }
                drawBlock(event, order, getLargestNumber(number, event), under, over);
                writeEventInfo(event, order, getLargestNumber(number, event), over);
                for (int i = 0; i < event.getDuration() / 60; i++) {
                    try {
                        orders.get(event.getEventTime().plusHours(i)).add(order);
                    } catch (NullPointerException e) {
                        orders.put(event.getEventTime().plusHours(i), new ArrayList<Integer>());
                        orders.get(event.getEventTime().plusHours(i)).add(order);
                    }
                }
            }
        }
    }

    /**
     * Draws the outer edge of the time table
     *
     * @param directContent draws the lines
     */
    protected void drawTimeTable(PdfContentByte directContent) {
        directContent.saveState();
        directContent.setLineWidth(1.2f);
        float llx, lly, urx, ury;
        llx = tableX;
        lly = tableY;
        urx = tableX + tableWidth;
        ury = tableY + tableHeight;
        directContent.moveTo(llx, lly);
        directContent.lineTo(urx, lly);
        directContent.lineTo(urx, ury);
        directContent.lineTo(llx, ury);
        directContent.closePath();
        directContent.stroke();
        directContent.restoreState();
    }

    /**
     * Draws the inner lines of the time table
     *
     * @param directContent draws the dotted lines
     */
    protected void drawTimeSlots(PdfContentByte directContent) {
        directContent.saveState();
        float x;
        x = tableX + tableCellWidth - 8;
        directContent.moveTo(x, tableY);
        directContent.lineTo(x, tableY + tableHeight);
        for (int i = 2; i < 8; i++) {
            x = tableX + (i * tableCellWidth) - 8;
            directContent.moveTo(x, tableY);
            directContent.lineTo(x, tableY + tableHeight);
        }
        float y;
        for (int i = 1; i < 25; i++) {
            y = tableY + (i * tableCellHeight);
            directContent.moveTo(tableX, y);
            directContent.lineTo(tableX + tableWidth, y);
        }
        directContent.setLineWidth(0.3f);
        directContent.setColorStroke(BaseColor.GRAY);
        directContent.setLineDash(3, 1);
        directContent.stroke();
        directContent.restoreState();
    }

    /**
     * Writes the text onto the time table.
     *
     * @param canvas writes the text
     */
    private void writeText(PdfContentByte canvas) {
        Font helvetica = new Font(Font.FontFamily.HELVETICA, 12);
        BaseFont bf = helvetica.getCalculatedBaseFont(false);
        canvas.beginText();
        canvas.setFontAndSize(bf, 12);
        String[] dates = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        float x;
        for (int i = 1; i < 8; i++) {
            x = tableX + (i * tableCellWidth) - 8;
            canvas.showTextAligned(Element.ALIGN_LEFT, dates[i - 1], x + 2, tableY + tableHeight - tableCellHeight + 2, 0);
        }
        float y;
        for (int i = 0; i < 24; i++) {
            y = tableY + ((23 - i) * tableCellHeight);
            canvas.showTextAligned(Element.ALIGN_LEFT, Integer.toString(i) + ":00", tableX + 2, y + 14, 0);
        }
        canvas.endText();
    }

    /**
     * Gets the date of the start of the week (the sunday of the current week)
     *
     * @return the date of the start of the week
     */
    public LocalDateTime getStartOfWeek() {
        LocalDateTime date = LocalDateTime.now();
        while (!date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            date = date.minusDays(1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = date.format(formatter) + "T00:00:00";
        return LocalDateTime.parse(now);
    }

    /**
     * Returns the position and size of the rectangle corresponding to an event on the time table
     *
     * @param event  the event whose block is being drawn
     * @param order  the position of the block with respect to the other blocks in the time block
     * @param number the total number of blocks in a time block
     * @return the block representing the event on the time table
     */
    private Rectangle getPosition(Event event, int order, int number) {
        DayOfWeek[] days = new DayOfWeek[]{DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};
        float x = 0;
        float y = 0;
        int i = 0;
        while (!event.getEventTime().getDayOfWeek().equals(days[i])) {
            i++;
        }
        x = tableX + (i * tableCellWidth) - 8 + order * (tableCellWidth / number);
        y = tableY + ((24 - event.getEventTime().getHour()) * tableCellHeight);
        return new Rectangle(x, y - tableCellHeight, x + (tableCellWidth) / number, y - tableCellHeight - (event.getDuration() / 60f) * tableCellHeight);
    }

    /**
     * Finds the largest number of blocks present in one of the time blocks that the current event is part of
     *
     * @param map   contains a map of how many events are in each time block
     * @param event the event in question
     * @return the largest number of blocks present in one of the time blocks that the current event is part of
     */
    private int getLargestNumber(HashMap<LocalDateTime, Integer> map, Event event) {
        int i = 0;
        for (int j = 0; j < event.getDuration() / 60; j++) {
            if (i < map.get(event.getEventTime().plusHours(j))) {
                i = map.get(event.getEventTime().plusHours(j));
            }
        }
        return i;
    }
}
