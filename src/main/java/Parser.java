public class Parser {

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static String[] parseTodoCommand(String input) throws PippyException {
        String[] components = input.split(" ", 2);
        if (components.length < 2 || components[1].trim().isEmpty()) {
            throw new PippyException("TODO_EMPTY");
        }
        return new String[]{components[1]};
    }

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
}
