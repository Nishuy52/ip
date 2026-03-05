package pippy.task;

/**
 * Represents a basic todo task with no associated date or time.
 * A Todo is the simplest task type — it has only a description and a done status.
 *
 * <p>Example display: {@code [T][ ] borrow book}</p>
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     * The task is marked as not done by default.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo task.
     * Prepends the type prefix {@code [T]} to the base task string.
     *
     * @return Formatted string in the form {@code [T][X] description} or {@code [T][ ] description}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}