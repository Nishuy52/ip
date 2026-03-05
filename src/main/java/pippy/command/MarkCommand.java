package pippy.command;

import pippy.storage.Storage;
import pippy.task.TaskList;
import pippy.ui.PippyException;
import pippy.ui.UI;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand for the task at the given (0-based) index.
     *
     * @param index The 0-based index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as done,
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
        tasks.markTaskAsDone(index);
        storage.save(tasks);
        ui.showTaskMarked(tasks.getTask(index), true);
    }
}
