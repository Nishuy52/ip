import java.util.Scanner;
import java.lang.Integer;
public class Pippy {
    static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = " Hello! I'm Pippy\n" +
                " What can I do for you?\n";
        String bye =  " Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        Task[] task_list = new Task[100];
        int task_count = 0;
        Scanner input = new Scanner(System.in);
        String input_line = "";
        System.out.println(line + hello);
        while (true) {
            System.out.println(line);
            input_line = input.nextLine();
            if (input_line.equals("")) {
                continue;
            }

            if  (input_line.equals("bye")) {
                System.out.println(bye);
                System.out.println(line);
                return;
            }

            if (input_line.equals("list")) {
                for (int i = 0; i < task_count; i++) {
                    System.out.println((i+1) + "." + task_list[i].toString());
                }
                continue;
            }

            if (input_line.startsWith("todo")) {
                String[] components = input_line.split(" ", 2);
                if (components.length < 2) {
                    System.out.println("Give your task a name, you lazy buffoon" + System.lineSeparator() +
                            "Format: todo [task]");
                    continue;
                }
                task_list[task_count] = new Todo(components[1]);
                System.out.println("Added Todo:" + System.lineSeparator() +
                        "   " + task_list[task_count].toString());
                task_count++;
                System.out.println("You now have " + task_count + " tasks in your list, unproductive waste of space");
            }

            if (input_line.startsWith("deadline")) {
                String[] components = input_line.split(" ", 2);
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
                task_list[task_count] = new Deadline(nameAndDeadline[0], nameAndDeadline[1]);
                System.out.println("Added Deadline:" + System.lineSeparator() +
                        "   " + task_list[task_count].toString());
                task_count++;
                System.out.println("You now have " + task_count + " tasks in your list, unproductive waste of space");
            }

            if (input_line.startsWith("event")) {
                String[] components = input_line.split(" ", 2);
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
                task_list[task_count] = new Event(nameAndTime[0], timings[0], timings[1]);
                System.out.println("Added Event:" + System.lineSeparator() +
                        "   " + task_list[task_count].toString());
                task_count++;
                System.out.println("You now have " + task_count + " tasks in your list, unproductive waste of space");
            }

            if (input_line.startsWith("mark") || input_line.startsWith("unmark")) {
                String[] components = input_line.split(" ");
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
                if  (index >= task_count) {
                    System.out.println("Invalid index, you do not have that many tasks you lazy monkey");
                    continue;
                }
                if (input_line.startsWith("unmark")) {
                    task_list[index].setUndone();
                    System.out.println("Marked as Undone: " + task_list[index].toString());
                    continue;
                }
                if (input_line.startsWith("mark")) {
                    task_list[index].setDone();
                    System.out.println("Marked as Done: " + task_list[index].toString());
                    continue;
                }
            }
            //task_list[task_count] = new Task(input_line);
            //task_count++;

            //System.out.println(line);
            //System.out.println("added:" + input_line);
            //System.out.println();
        }
    }

}
