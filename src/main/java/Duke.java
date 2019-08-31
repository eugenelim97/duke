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
        String command = scanner.next();
        while (!"bye".equals(command)) {
            try {
                switch (command) {
                    case "list":
                        int i = 0;
                        System.out.println("Here are the tasks in your list:");
                        for (Task task : taskList) {
                            System.out.println(++i + "." + task.toString());
                        }
                        break;
                    case "done":
                        Task t = taskList.get(scanner.nextInt() - 1);
                        printDone(t);
                        break;
                    case "todo":
                        String todoInput = scanner.nextLine();
                        if ("".equals(todoInput)) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            Todo todo = new Todo(todoInput);
                            taskList.add(todo);
                            printAdd(todo, taskList.size());
                        }
                        break;
                    case "deadline":
                        String deadlineInput = scanner.nextLine();
                        if ("".equals(deadlineInput)) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String[] d = deadlineInput.split("/by", 2);
                            Deadline deadline = new Deadline(d[0], d[1]);
                            taskList.add(deadline);
                            printAdd(deadline, taskList.size());
                        }
                        break;
                    case "event":
                        String eventInput = scanner.nextLine();
                        if ("".equals(eventInput)) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String[] e = eventInput.split("/at", 2);
                            Event event = new Event(e[0], e[1]);
                            taskList.add(event);
                            printAdd(event, taskList.size());
                        }
                        break;
                    default:
                        scanner.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                command = scanner.next();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                command = scanner.next();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printAdd(Task task, int num) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public static void printDone(Task t) {
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
    }
}
