package pippy.command;

import pippy.storage.Storage;
import pippy.task.Deadline;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.ui.UI;

/**
 * Command to add a Deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand with the given description and deadline.
     *
     * @param description The name of the deadline task.
     * @param by          The deadline date/time string.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a Deadline task, adding it to the list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance for persisting tasks.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.getTaskCount(), "Deadline");
    }
}
