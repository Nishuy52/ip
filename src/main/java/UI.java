public class UI {
    private static final String LOGO = " ____  _                   \n"
            + "|  _ \\(_)_ __  _ __  _   _ \n"
            + "| |_) | | '_ \\| '_ \\| | | |\n"
            + "|  __/| | |_) | |_) | |_| |\n"
            + "|_|   |_| .__/| .__/ \\__, |\n"
            + "        |_|   |_|    |___/ \n";

    private static final String GREETING = " Hello! I'm Pippy\n What can I do for you?\n";
    private static final String FAREWELL = " Bye. Hope to see you again soon!\n";
    private static final String LINE = "____________________________________________________________\n";
    
    // Error messages
    private static final String ERROR_TODO_EMPTY = "Give your task a name, you lazy buffoon\nFormat: todo [task]";
    private static final String ERROR_DEADLINE_EMPTY = "Give your task a name, you lazy buffoon\nFormat: deadline [taskname] /by [datetime]";
    private static final String ERROR_DEADLINE_FORMAT = "There's a format for a reason smartass\nFormat: deadline [taskname] /by [datetime]";
    private static final String ERROR_EVENT_EMPTY = "Give your event a name, you lazy buffoon\nFormat: event [event] /from [datetime] /to [datetime]";
    private static final String ERROR_EVENT_FORMAT = "There's a format for a reason smartass\nFormat: event [event] /from [datetime] /to [datetime]";
    private static final String ERROR_INDEX_MISSING = "Invalid index, I need an index to refer to dumbass";
    private static final String ERROR_INDEX_NOT_NUMBER = "Invalid index, Index must be a number you baboon";
    private static final String ERROR_INDEX_NEGATIVE = "Invalid index, would you like to demonstrate what a negative index looks like? I didn't think so";
    private static final String ERROR_INDEX_TOO_LARGE = "Invalid index, you do not have that many tasks you lazy monkey";

    // Tasklist Messages
    private static final String TASK_COUNT_MESSAGE = "You now have %d tasks in your list, unproductive waste of space";

    public void showWelcome() {
        System.out.println(LINE + LOGO + GREETING);
    }

    public void showGoodbye() {
        System.out.println(FAREWELL);
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showTaskAdded(Task task, int totalTasks, String taskType) {
        System.out.println("Added " + taskType + ":");
        System.out.println("   " + task.toString());
        System.out.printf((TASK_COUNT_MESSAGE) + "%n", totalTasks);
    }

    public void showTaskMarked(Task task, boolean isDone) {
        String status = isDone ? "Done" : "Undone";
        System.out.println("Marked as " + status + ": " + task.toString());
    }

    public void showError(String errorType) {
        String message = switch (errorType) {
            case "TODO_EMPTY" -> ERROR_TODO_EMPTY;
            case "DEADLINE_EMPTY" -> ERROR_DEADLINE_EMPTY;
            case "DEADLINE_FORMAT" -> ERROR_DEADLINE_FORMAT;
            case "EVENT_EMPTY" -> ERROR_EVENT_EMPTY;
            case "EVENT_FORMAT" -> ERROR_EVENT_FORMAT;
            case "INDEX_MISSING" -> ERROR_INDEX_MISSING;
            case "INDEX_NOT_NUMBER" -> ERROR_INDEX_NOT_NUMBER;
            case "INDEX_NEGATIVE" -> ERROR_INDEX_NEGATIVE;
            case "INDEX_TOO_LARGE" -> ERROR_INDEX_TOO_LARGE;
            default -> errorType;
        };
        System.out.println(message);
    }

    public void showTaskList(Task[] tasks, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }
}
