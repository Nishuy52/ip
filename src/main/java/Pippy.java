import java.util.Scanner;

public class Pippy {
    private static final int MAX_TASKS = 100;
    
    private final TaskList taskList;
    private final UI ui;
    private final Scanner scanner;
    private boolean isRunning;

    public Pippy() {
        this.taskList = new TaskList(MAX_TASKS);
        this.ui = new UI();
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
            case "event" -> handleEvent(input);
            case "mark" -> handleMark(input);
            case "unmark" -> handleUnmark(input);
            default -> {
                // Unknown commands are silently ignored in the original
            }
        }
    }

    private void handleExit() {
        ui.showGoodbye();
        isRunning = false;
    }

    private void handleList() {
        ui.showTaskList(taskList.getTasks(), taskList.getTaskCount());
    }

    private void handleTodo(String input) throws PippyException {
        String[] params = Parser.parseTodoCommand(input);
        Task task = new Todo(params[0]);
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Todo");
    }

    private void handleDeadline(String input) throws PippyException {
        String[] params = Parser.parseDeadlineCommand(input);
        Task task = new Deadline(params[0], params[1]);
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Deadline");
    }

    private void handleEvent(String input) throws PippyException {
        String[] params = Parser.parseEventCommand(input);
        Task task = new Event(params[0], params[1], params[2]);
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount(), "Event");
    }

    private void handleMark(String input) throws PippyException {
        int index = Parser.parseTaskIndex(input);
        
        if (!taskList.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        
        taskList.markTaskAsDone(index);
        ui.showTaskMarked(taskList.getTask(index), true);
    }

    private void handleUnmark(String input) throws PippyException {
        int index = Parser.parseTaskIndex(input);
        
        if (!taskList.isValidIndex(index)) {
            throw new PippyException("INDEX_TOO_LARGE");
        }
        
        taskList.markTaskAsUndone(index);
        ui.showTaskMarked(taskList.getTask(index), false);
    }
}
