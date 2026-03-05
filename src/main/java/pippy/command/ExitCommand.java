package pippy.command;

import pippy.storage.Storage;
import pippy.task.TaskList;
import pippy.ui.UI;

/**
 * Command to exit the Pippy application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying the farewell message.
     *
     * @param tasks   The current list of tasks (unused).
     * @param ui      The UI instance for displaying the farewell.
     * @param storage The storage instance (unused).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Signals that this command should terminate the application.
     *
     * @return true always.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
