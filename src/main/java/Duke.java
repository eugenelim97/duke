import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        //Task[] taskList = new Task[100];

        while (true) {
            String command = scanner.nextLine();
            try {
                if ("bye".equals(command)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if ("list".equals(command)) {
                    int i = 0;
                    System.out.println("Here are the tasks in your list:");
                    for (Task Tasks : taskList) {
                        System.out.println(++i + "." + Tasks.toString());
                    }
                } else if (command.startsWith("done")) {
                    int num = Integer.parseInt(command.replaceAll("[\\D]", ""));
                    num -= 1;
                    Task t = taskList.get(num);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + t.toString());
                } else if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = command.substring(5);
                    Task t = new Todo(description);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + Task.getTotal() + " tasks in the list.");
                    taskList.add(t);
                } else if (command.startsWith("deadline")) {
                    int index = command.indexOf('/');
                    index--;
                    String description = command.substring(9, index);
                    index += 5;
                    String by = command.substring(index);
                    Task t = new Deadline(description, by);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + Task.getTotal() + " tasks in the list.");
                    taskList.add(t);
                } else if (command.startsWith("event")) {
                    int index = command.indexOf('/');
                    index--;
                    String description = command.substring(6, index);
                    index += 5;
                    String at = command.substring(index);
                    Task t = new Event(description, at);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + Task.getTotal() + " tasks in the list.");
                    taskList.add(t);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
