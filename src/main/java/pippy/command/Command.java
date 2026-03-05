package pippy.command;

import pippy.storage.Storage;
import pippy.task.TaskList;
import pippy.ui.UI;

/**
 * Represents an executable command in the Pippy application.
 * Each subclass encapsulates the logic for one user command.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance for persisting tasks.
     * @throws pippy.ui.PippyException If the command encounters an error during execution.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws pippy.ui.PippyException;

    /**
     * Returns true if this command should cause the application to exit.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
