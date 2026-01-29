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
                    System.out.println((i+1) + ".[" + task_list[i].getStatusIcon() + "] " + task_list[i].getDescription());
                }
                continue;
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
                    System.out.println("Marked as Undone: [" + task_list[index].getStatusIcon() + "] " + task_list[index].getDescription());
                    continue;
                }
                if (input_line.startsWith("mark")) {
                    task_list[index].setDone();
                    System.out.println("Marked as Done: [" + task_list[index].getStatusIcon() + "] " + task_list[index].getDescription());
                    continue;
                }
            }
            task_list[task_count] = new Task(input_line);
            task_count++;

            System.out.println(line);
            System.out.println("added:" + input_line);
            System.out.println();
        }
    }

}
