package pippy.ui;

/**
 * Parses raw user input strings into structured components.
 * Each method handles one command type and throws a {@link PippyException}
 * with a typed error code if the input is malformed.
 */
public class Parser {

    /**
     * Extracts the command keyword from a user input string.
     * The command is the first whitespace-delimited token.
     *
     * @param input The full user input string.
     * @return The command keyword in lowercase (e.g., "todo", "list").
     */
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses a todo command and extracts the task description.
     *
     * @param input The full user input string (e.g., "todo buy milk").
     * @return A single-element array containing the task description.
     * @throws PippyException If the description is missing or blank.
     */
    public static String[] parseTodoCommand(String input) throws PippyException {
        String[] components = input.split(" ", 2);
        if (components.length < 2 || components[1].trim().isEmpty()) {
            throw new PippyException("TODO_EMPTY");
        }
        return new String[]{components[1]};
    }

    /**
     * Parses a deadline command and extracts the task name and deadline.
     * Expected format: {@code deadline <name> /by <datetime>}
     *
     * @param input The full user input string.
     * @return A two-element array: [taskName, byDateTime].
     * @throws PippyException If the format is missing a name or the {@code /by} separator.
     */
    public static String[] parseDeadlineCommand(String input) throws PippyException {
        String[] components = input.split(" ", 2);
        if (components.length < 2) {
            throw new PippyException("DEADLINE_EMPTY");
        }

        String[] nameAndDeadline = components[1].split(" /by ", 2);
        if (nameAndDeadline.length < 2) {
            throw new PippyException("DEADLINE_FORMAT");
        }

        return nameAndDeadline;
    }

    /**
     * Parses an event command and extracts the event name, start time, and end time.
     * Expected format: {@code event <name> /from <datetime> /to <datetime>}
     *
     * @param input The full user input string.
     * @return A three-element array: [eventName, fromDateTime, toDateTime].
     * @throws PippyException If the format is missing a name, {@code /from}, or {@code /to} separator.
     */
    public static String[] parseEventCommand(String input) throws PippyException {
        String[] components = input.split(" ", 2);
        if (components.length < 2) {
            throw new PippyException("EVENT_EMPTY");
        }

        String[] nameAndTime = components[1].split(" /from ", 2);
        if (nameAndTime.length < 2) {
            throw new PippyException("EVENT_FORMAT");
        }

        String[] timings = nameAndTime[1].split(" /to ", 2);
        if (timings.length < 2) {
            throw new PippyException("EVENT_FORMAT");
        }

        return new String[]{nameAndTime[0], timings[0], timings[1]};
    }

    /**
     * Parses a command that requires a task index (e.g., mark, unmark, delete).
     * The index in user input is 1-based; the returned value is 0-based.
     *
     * @param input The full user input string (e.g., "mark 3").
     * @return The 0-based integer index of the target task.
     * @throws PippyException If the index is missing, not a number, or negative.
     */
    public static int parseTaskIndex(String input) throws PippyException {
        String[] components = input.split(" ");
        if (components.length < 2) {
            throw new PippyException("INDEX_MISSING");
        }

        String indexString = components[1].trim();
        int index;

        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new PippyException("INDEX_NOT_NUMBER");
        }

        if (index < 0) {
            throw new PippyException("INDEX_NEGATIVE");
        }

        return index;
    }

    /**
     * Parses a find command and extracts the search keyword.
     * Expected format: {@code find <keyword>}
     *
     * @param input The full user input string (e.g., "find book").
     * @return The keyword string to search for.
     * @throws PippyException If no keyword is provided.
     */
    public static String parseFindCommand(String input) throws PippyException {
        String[] components = input.split(" ", 2);
        if (components.length < 2 || components[1].trim().isEmpty()) {
            throw new PippyException("FIND_EMPTY");
        }
        return components[1].trim();
    }
}