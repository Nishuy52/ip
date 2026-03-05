package pippy.command;

import pippy.storage.Storage;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.ui.UI;

import java.util.ArrayList;

/**
 * Command to find tasks whose description contains a given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the command by searching for tasks matching the keyword
     * and displaying the results.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance for displaying output.
     * @param storage The storage instance (unused for this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        ui.showFoundTasks(matches);
    }
}