package pippy.task;

/**
 * Represents an event task that occurs over a specific time period.
 * An Event has a description, a start time ({@code from}), and an end time ({@code to}).
 *
 * <p>Example display: {@code [E][ ] project meeting (from: Mon 2pm to: 4pm)}</p>
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description and time range.
     * The task is marked as not done by default.
     *
     * @param description The name or description of the event.
     * @param from        The start date/time string (e.g., {@code "Mon 2pm"}).
     * @param to          The end date/time string (e.g., {@code "4pm"}).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return The start time string as provided by the user.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return The end time string as provided by the user.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns the string representation of this event task.
     * Prepends the type prefix {@code [E]} and appends the time range.
     *
     * @return Formatted string in the form {@code [E][X] description (from: ... to: ...)}
     *         or {@code [E][ ] description (from: ... to: ...)}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}