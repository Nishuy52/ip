import java.util.Scanner;
import java.lang.Integer;
public class Pippy {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = " Hello! I'm Pippy\n" +
                " What can I do for you?\n";
        String bye =  " Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        final int MAX_TASKS = 100;
        Task[] taskList = new Task[MAX_TASKS];
        int taskCount = 0;
        Scanner input = new Scanner(System.in);
        String inputLine = "";
        System.out.println(line + hello);
        while (true) {
            System.out.println(line);
            inputLine = input.nextLine();
            switch (inputLine) {
                case "" -> {
                    continue;
                }
                case "bye" -> {
                    System.out.println(bye);
                    System.out.println(line);
                    return;
                }
                case "list" -> {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + taskList[i].toString());
                    }
                    continue;
                }
            }

            if (inputLine.startsWith("todo")) {
                String[] components = inputLine.split(" ", 2);
                if (components.length < 2) {
                    System.out.println("Give your task a name, you lazy buffoon" + System.lineSeparator() +
                            "Format: todo [task]");
                    continue;
                }
                taskList[taskCount] = new Todo(components[1]);
                System.out.println("Added Todo:" + System.lineSeparator() +
                        "   " + taskList[taskCount].toString());
                taskCount++;
                System.out.println("You now have " + taskCount + " tasks in your list, unproductive waste of space");
            }

            if (inputLine.startsWith("deadline")) {
                String[] components = inputLine.split(" ", 2);
                if (components.length < 2) {
                    System.out.println("Give your task a name, you lazy buffoon" + System.lineSeparator() +
                            "Format: deadline [taskname] /by [datetime]");
                    continue;
                }
                String[] nameAndDeadline = components[1].split(" /by ", 2);
                if (nameAndDeadline.length < 2) {
                    System.out.println("There's a format for a reason smartass" + System.lineSeparator() +
                            "Format: deadline [taskname] /by [datetime]");
                    continue;
                }
                taskList[taskCount] = new Deadline(nameAndDeadline[0], nameAndDeadline[1]);
                System.out.println("Added Deadline:" + System.lineSeparator() +
                        "   " + taskList[taskCount].toString());
                taskCount++;
                System.out.println("You now have " + taskCount + " tasks in your list, unproductive waste of space");
            }

            if (inputLine.startsWith("event")) {
                String[] components = inputLine.split(" ", 2);
                if (components.length < 2) {
                    System.out.println("Give your event a name, you lazy buffoon" + System.lineSeparator() +
                            "Format: event [event] /from [datetime] /to [datetime]");
                    continue;
                }
                String[] nameAndTime = components[1].split(" /from ", 2);
                if (nameAndTime.length < 2) {
                    System.out.println("There's a format for a reason smartass" + System.lineSeparator() +
                            "Format: event [event] /from [datetime] /to [datetime]");
                    continue;
                }
                String[] timings = nameAndTime[1].split(" /to ", 2);
                if (timings.length < 2) {
                    System.out.println("There's a format for a reason smartass" + System.lineSeparator() +
                            "Format: event [event] /from [datetime] /to [datetime]");
                    continue;
                }
                taskList[taskCount] = new Event(nameAndTime[0], timings[0], timings[1]);
                System.out.println("Added Event:" + System.lineSeparator() +
                        "   " + taskList[taskCount].toString());
                taskCount++;
                System.out.println("You now have " + taskCount + " tasks in your list, unproductive waste of space");
            }

            if (inputLine.startsWith("mark") || inputLine.startsWith("unmark")) {
                String[] components = inputLine.split(" ");
                if (components.length < 2) {
                    System.out.println("Invalid index, I need an index to refer to dumbass");
                    continue;
                }
                components[1]  = components[1].trim();
                int index;
                try {
                    index = Integer.parseInt(components[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index, Index must be a number you baboon");
                    continue;
                }
                if  (index < 0) {
                    System.out.println("Invalid index, would you like to demonstrate what a negative index looks like? I didn't think so");
                    continue;
                }
                if  (index >= taskCount) {
                    System.out.println("Invalid index, you do not have that many tasks you lazy monkey");
                    continue;
                }
                if (inputLine.startsWith("unmark")) {
                    taskList[index].setUndone();
                    System.out.println("Marked as Undone: " + taskList[index].toString());
                    continue;
                }
                if (inputLine.startsWith("mark")) {
                    taskList[index].setDone();
                    System.out.println("Marked as Done: " + taskList[index].toString());
                    continue;
                }
            }
        }
    }

}
