package pippy.command;

import pippy.storage.Storage;
import pippy.task.TaskList;
import pippy.ui.PippyException;
import pippy.ui.UI;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand for the task at the given (0-based) index.
     *
     * @param index The 0-based index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as not done,
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
        tasks.markTaskAsUndone(index);
        storage.save(tasks);
        ui.showTaskMarked(tasks.getTask(index), false);
    }
}
