package pippy.command;

import pippy.storage.Storage;
import pippy.task.Event;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.ui.UI;

/**
 * Command to add an Event task to the task list.
 */
public class AddEventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand with the given description and time range.
     *
     * @param description The name of the event.
     * @param from        The start date/time string.
     * @param to          The end date/time string.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating an Event task, adding it to the list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance for persisting tasks.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.getTaskCount(), "Event");
    }
}
