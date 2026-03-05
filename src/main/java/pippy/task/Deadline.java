package pippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * If the deadline string is in {@code yyyy-MM-dd} format, it is stored as a
 * {@link LocalDate} and displayed in {@code MMM d yyyy} format (e.g., Oct 15 2019).
 * Otherwise, the raw string is kept and displayed as-is.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /** Parsed date, or null if the by-string was not in yyyy-MM-dd format. */
    private final LocalDate byDate;

    /** Raw deadline string, used when byDate is null. */
    private final String byRaw;

    /**
     * Constructs a Deadline task.
     * Attempts to parse {@code by} as a {@code yyyy-MM-dd} date.
     * Falls back to storing it as a plain string if parsing fails.
     *
     * @param description The name of the deadline task.
     * @param by          The deadline string, either {@code yyyy-MM-dd} or free-form.
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate parsed = null;
        try {
            parsed = LocalDate.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            // Not a structured date — store as raw string
        }
        this.byDate = parsed;
        this.byRaw = by;
    }

    /**
     * Returns the display string for the deadline.
     * If a {@link LocalDate} was parsed, returns it formatted as {@code MMM d yyyy}.
     * Otherwise returns the raw original string.
     *
     * @return Human-readable deadline string.
     */
    public String getBy() {
        if (byDate != null) {
            return byDate.format(OUTPUT_FORMAT);
        }
        return byRaw;
    }

    /**
     * Returns the raw original deadline string as provided by the user.
     * Used by {@link pippy.storage.Storage} to persist the original value unchanged.
     *
     * @return The raw deadline string.
     */
    public String getByRaw() {
        return byRaw;
    }

    /**
     * Returns the parsed {@link LocalDate}, or null if the deadline was free-form text.
     *
     * @return The parsed date, or null.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the string representation of this deadline task.
     *
     * @return Formatted string including task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}