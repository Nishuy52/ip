package pippy.task;

import java.util.ArrayList;

/**
 * Manages the list of tasks in the Pippy application.
 * Provides methods to add, retrieve, delete, and mark tasks,
 * as well as index validation.
 *
 * <p>All task indices used in public methods are 0-based.</p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList pre-populated with an existing list of tasks.
     * Typically used when loading tasks from storage.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the given 0-based index.
     * All subsequent tasks shift down by one position.
     *
     * @param index The 0-based index of the task to remove.
     * @return The task that was removed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the total number of tasks currently in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of all tasks.
     * This is the live list — modifications to the returned list will affect this TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns whether the given 0-based index refers to an existing task.
     *
     * @param index The index to validate.
     * @return {@code true} if the index is within bounds, {@code false} otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Marks the task at the given 0-based index as done.
     *
     * @param index The 0-based index of the task to mark as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).setDone();
    }

    /**
     * Marks the task at the given 0-based index as not done.
     *
     * @param index The 0-based index of the task to mark as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).setUndone();
    }
}