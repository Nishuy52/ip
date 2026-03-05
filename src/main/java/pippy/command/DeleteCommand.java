package pippy.command;

import pippy.storage.Storage;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.ui.PippyException;
import pippy.ui.UI;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand for the task at the given (0-based) index.
     *
     * @param index The 0-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the specified task from the list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance for persisting tasks.
     * @throws PippyException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws PippyException {
        if (!tasks.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        Task removed = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showTaskDeleted(removed, tasks.getTaskCount());
    }
}
