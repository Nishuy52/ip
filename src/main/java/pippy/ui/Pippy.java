package pippy.ui;

import pippy.storage.Storage;
import pippy.task.*;

import java.util.Scanner;

public class Pippy {

    private static final String DATA_FILE_PATH = "./data/pippy.txt";

    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;
    private final Scanner scanner;
    private boolean isRunning;

    public Pippy() {
        this.ui = new UI();
        this.storage = new Storage(DATA_FILE_PATH);
        this.taskList = storage.load();
        this.scanner = new Scanner(System.in);
        this.isRunning = false;
    }

    public static void main(String[] args) {
        new Pippy().run();
    }

    public void run() {
        ui.showWelcome();
        isRunning = true;
        while (isRunning) {
            ui.showLine();
            String input = scanner.nextLine();
            processInput(input);
        }
    }

    private void processInput(String input) {
        if (input.trim().isEmpty()) {
            return;
        }
        if (input.equals("bye")) {
            handleExit();
            return;
        }
        try {
            executeCommand(input);
        } catch (PippyException e) {
            ui.showError(e.getMessage());
        }
    }

    private void executeCommand(String input) throws PippyException {
        String command = Parser.getCommand(input);
        switch (command) {
            case "list" -> handleList();
            case "todo" -> handleTodo(input);
            case "deadline" -> handleDeadline(input);
            case "event"    -> handleEvent(input);
            case "mark"     -> handleMark(input);
            case "unmark"   -> handleUnmark(input);
            case "delete"   -> handleDelete(input);
            default -> {}
        }
    }

    private void handleExit() {
        ui.showGoodbye();
        isRunning = false;
    }

    private void handleList() {
        ui.showTaskList(taskList.getTasks());
    }

    private void handleTodo(String input) throws PippyException {
        String[] params = Parser.parseTodoCommand(input);
        Task task = new Todo(params[0]);
        taskList.addTask(task);
        storage.save(taskList);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Todo");
    }

    private void handleDeadline(String input) throws PippyException {
        String[] params = Parser.parseDeadlineCommand(input);
        Task task = new Deadline(params[0], params[1]);
        taskList.addTask(task);
        storage.save(taskList);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Deadline");
    }

    private void handleEvent(String input) throws PippyException {
        String[] params = Parser.parseEventCommand(input);
        Task task = new Event(params[0], params[1], params[2]);
        taskList.addTask(task);
        storage.save(taskList);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Event");
    }

    private void handleMark(String input) throws PippyException {
        int index = Parser.parseTaskIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        taskList.markTaskAsDone(index);
        storage.save(taskList);
        ui.showTaskMarked(taskList.getTask(index), true);
    }

    private void handleUnmark(String input) throws PippyException {
        int index = Parser.parseTaskIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        taskList.markTaskAsUndone(index);
        storage.save(taskList);
        ui.showTaskMarked(taskList.getTask(index), false);
    }

    private void handleDelete(String input) throws PippyException {
        int index = Parser.parseTaskIndex(input);
        if (!taskList.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        Task removed = taskList.deleteTask(index);
        ui.showTaskDeleted(removed, taskList.getTaskCount());
        storage.save(taskList);
    }
}