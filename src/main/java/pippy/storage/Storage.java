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

public class Storage {

    private final String filePath;
    private final int max_task;

    public Storage(String filePath, int max_task) {
        this.filePath = filePath;
        this.max_task = max_task;
    }

    public TaskList load() {
        TaskList taskList = new TaskList(max_task);
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
                    String to   = parts[4].trim();
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

    private String encodeTask(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            return "T | " + done + " | " + task.getDescription();
        }
    }
}