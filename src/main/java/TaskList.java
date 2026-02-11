public class TaskList {
    private final Task[] tasks;
    private int taskCount;

    public TaskList(int maxSize) {
        this.tasks = new Task[maxSize];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    public void markTaskAsDone(int index) {
        tasks[index].setDone();
    }

    public void markTaskAsUndone(int index) {
        tasks[index].setUndone();
    }
}
