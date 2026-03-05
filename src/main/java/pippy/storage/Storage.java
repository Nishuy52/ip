package pippy.storage;

import pippy.task.Deadline;
import pippy.task.Event;
import pippy.task.Task;
import pippy.task.TaskList;
import pippy.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles reading and writing tasks to a persistent file.
 * The file format uses pipe-delimited fields, one task per line.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance for the given file path.
     *
     * @param filePath Path to the data file (will be created if it does not exist).
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file into a {@link TaskList}.
     * If the file does not exist, returns an empty task list.
     * Corrupted or unrecognised lines are skipped with a warning.
     *
     * @return A TaskList populated with the saved tasks.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return taskList;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("[Storage] Warning: could not read data file: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves all tasks in the given {@link TaskList} to the data file.
     * Creates parent directories if they do not exist.
     * Prints a warning if the file cannot be written.
     *
     * @param taskList The task list to persist.
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                fw.write(encodeTask(taskList.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("[Storage] Warning: could not save data file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file into a {@link Task} object.
     * Returns null and prints a warning for lines that are corrupted or unknown.
     *
     * @param line A pipe-delimited line from the data file.
     * @return The corresponding Task, or null if parsing fails.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task;
            switch (type) {
                case "T" -> task = new Todo(description);
                case "D" -> {
                    String by = parts[3].trim();
                    task = new Deadline(description, by);
                }
                case "E" -> {
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    task = new Event(description, from, to);
                }
                default -> {
                    System.out.println("[Storage] Warning: unknown task type '" + type + "', skipping line.");
                    return null;
                }
            }

            if (isDone) {
                task.setDone();
            }
            return task;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[Storage] Warning: corrupted line skipped: " + line);
            return null;
        }
    }

    /**
     * Encodes a {@link Task} into a pipe-delimited string for storage.
     * For Deadline tasks, persists the raw original date string (not the formatted display string)
     * so that structured dates ({@code yyyy-MM-dd}) remain parseable on reload.
     *
     * @param task The task to encode.
     * @return A pipe-delimited string representing the task.
     */
    private String encodeTask(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Deadline d) {
            // Use getByRaw() to preserve the original yyyy-MM-dd string for reload
            return "D | " + done + " | " + d.getDescription() + " | " + d.getByRaw();
        } else if (task instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            return "T | " + done + " | " + task.getDescription();
        }
    }
}