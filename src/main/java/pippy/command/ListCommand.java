package pippy.command;

import pippy.storage.Storage;
import pippy.task.TaskList;
import pippy.ui.UI;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance (unused for this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
