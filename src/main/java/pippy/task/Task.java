package pippy.task;

/**
 * Represents a generic task with a description and a completion status.
 * This is the base class for all task types in the Pippy application.
 * Subclasses should override {@link #toString()} to include type-specific formatting.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is marked as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     * An {@code "X"} indicates the task is done; a space indicates it is not.
     *
     * @return {@code "X"} if done, {@code " "} if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of this task, including its status icon and description.
     * Subclasses prepend a type prefix (e.g., {@code [T]}, {@code [D]}, {@code [E]}).
     *
     * @return Formatted string in the form {@code [X] description} or {@code [ ] description}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}