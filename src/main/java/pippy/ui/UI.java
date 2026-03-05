package pippy.ui;

import pippy.task.Task;
import java.util.ArrayList;

/**
 * Handles all user-facing output for the Pippy application.
 * Contains display methods for each interaction type.
 */
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

    private static final String ERROR_TODO_EMPTY = "Give your task a name, you lazy buffoon\nFormat: todo [task]";
    private static final String ERROR_DEADLINE_EMPTY = "Give your task a name, you lazy buffoon\nFormat: deadline [taskname] /by [datetime]";
    private static final String ERROR_DEADLINE_FORMAT = "There's a format for a reason smartass\nFormat: deadline [taskname] /by [datetime]";
    private static final String ERROR_EVENT_EMPTY = "Give your event a name, you lazy buffoon\nFormat: event [event] /from [datetime] /to [datetime]";
    private static final String ERROR_EVENT_FORMAT = "There's a format for a reason smartass\nFormat: event [event] /from [datetime] /to [datetime]";
    private static final String ERROR_INDEX_MISSING = "Invalid index, I need an index to refer to dumbass";
    private static final String ERROR_INDEX_NOT_NUMBER = "Invalid index, Index must be a number you baboon";
    private static final String ERROR_INDEX_NEGATIVE = "Invalid index, would you like to demonstrate what a negative index looks like? I didn't think so";
    private static final String ERROR_INDEX_TOO_LARGE = "Invalid index, you do not have that many tasks you lazy monkey";
    private static final String ERROR_FIND_EMPTY = "Give me something to search for, genius\nFormat: find [keyword]";

    private static final String TASK_COUNT_MESSAGE = "You now have %d tasks in your list, unproductive waste of space";

    /** Displays the welcome banner and greeting message. */
    public void showWelcome() {
        System.out.println(LINE + LOGO + GREETING);
    }

    /** Displays the farewell message and closing separator line. */
    public void showGoodbye() {
        System.out.println(FAREWELL);
        System.out.println(LINE);
    }

    /** Displays a horizontal separator line. */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays confirmation that a task was added, along with the updated task count.
     *
     * @param task       The task that was added.
     * @param totalTasks The new total number of tasks in the list.
     * @param taskType   A human-readable label for the task type (e.g., "Todo").
     */
    public void showTaskAdded(Task task, int totalTasks, String taskType) {
        System.out.println("Added " + taskType + ":");
        System.out.println("   " + task.toString());
        System.out.printf((TASK_COUNT_MESSAGE) + "%n", totalTasks);
    }

    /**
     * Displays confirmation that a task was marked as done or not done.
     *
     * @param task   The task that was updated.
     * @param isDone true if the task was marked done; false if marked undone.
     */
    public void showTaskMarked(Task task, boolean isDone) {
        String status = isDone ? "Done" : "Undone";
        System.out.println("Marked as " + status + ": " + task.toString());
    }

    /**
     * Displays an error message corresponding to the given error type code.
     *
     * @param errorType The error code string (e.g., "TODO_EMPTY", "INDEX_MISSING").
     */
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
            case "FIND_EMPTY" -> ERROR_FIND_EMPTY;
            default -> errorType;
        };
        System.out.println(message);
    }

    /**
     * Displays confirmation that a task was deleted, along with the updated task count.
     *
     * @param task       The task that was removed.
     * @param totalTasks The new total number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task, slacker:");
        System.out.println("   " + task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays all tasks in the given list, numbered from 1.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Displays the results of a find operation.
     * If no tasks match the keyword, a message indicating this is shown.
     *
     * @param tasks The list of matching tasks to display.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}