package pippy.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).setDone();
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).setUndone();
    }
}