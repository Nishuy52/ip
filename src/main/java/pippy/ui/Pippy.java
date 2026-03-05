package pippy.ui;

import pippy.command.*;
import pippy.storage.Storage;
import pippy.task.TaskList;

import java.util.Scanner;

/**
 * Main class for the Pippy task management application.
 * Coordinates the UI, storage, task list, and command execution loop.
 */
public class Pippy {

    private static final String DATA_FILE_PATH = "./data/pippy.txt";

    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;
    private final Scanner scanner;

    /**
     * Constructs a new Pippy instance, loading any previously saved tasks from disk.
     */
    public Pippy() {
        this.ui = new UI();
        this.storage = new Storage(DATA_FILE_PATH);
        this.taskList = storage.load();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Entry point for the Pippy application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Pippy().run();
    }

    /**
     * Starts the main application loop.
     * Displays the welcome message and processes user input until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            ui.showLine();
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                continue;
            }
            Command command = parseCommand(input);
            if (command == null) {
                continue;
            }
            try {
                command.execute(taskList, ui, storage);
            } catch (PippyException e) {
                ui.showError(e.getMessage());
            }
            if (command.isExit()) {
                isRunning = false;
            }
        }
    }

    /**
     * Parses a raw input string into the appropriate {@link Command} object.
     * Returns null and shows an error for unknown commands.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object, or null if the command is unrecognized
     *         or a parse error occurred.
     */
    private Command parseCommand(String input) {
        String commandWord = Parser.getCommand(input);
        try {
            return switch (commandWord) {
                case "bye" -> new ExitCommand();
                case "list" -> new ListCommand();
                case "todo" -> {
                    String[] params = Parser.parseTodoCommand(input);
                    yield new AddTodoCommand(params[0]);
                }
                case "deadline" -> {
                    String[] params = Parser.parseDeadlineCommand(input);
                    yield new AddDeadlineCommand(params[0], params[1]);
                }
                case "event" -> {
                    String[] params = Parser.parseEventCommand(input);
                    yield new AddEventCommand(params[0], params[1], params[2]);
                }
                case "mark" -> new MarkCommand(Parser.parseTaskIndex(input));
                case "unmark" -> new UnmarkCommand(Parser.parseTaskIndex(input));
                case "delete" -> new DeleteCommand(Parser.parseTaskIndex(input));
                default -> null;
            };
        } catch (PippyException e) {
            ui.showError(e.getMessage());
            return null;
        }
    }
}