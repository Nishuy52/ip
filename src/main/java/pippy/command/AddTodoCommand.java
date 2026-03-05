package pippy.command;

import pippy.storage.Storage;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.task.Todo;
import pippy.ui.UI;

/**
 * Command to add a Todo task to the task list.
 */
public class AddTodoCommand extends Command {

    private final String description;

    /**
     * Constructs an AddTodoCommand with the given task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a Todo task, adding it to the list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance for persisting tasks.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.getTaskCount(), "Todo");
    }
}
