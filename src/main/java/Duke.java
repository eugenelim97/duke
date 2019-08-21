import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        while (true) {
            String command = scanner.nextLine();
            if ("bye".equals(command)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(command)) {
                int i = 0;
                System.out.println("Here are the tasks in your list:");
                for (Task todo : taskList) {
                    System.out.println(++i + "." + "[" + todo.getStatusIcon() + "] " + todo.getTask());
                }
            } else if (command.startsWith("done")) {
                int num = Integer.parseInt(command.replaceAll("[\\D]", ""));
                num -= 1;
                Task todo = taskList.get(num);
                todo.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + "[" + todo.getStatusIcon() + "] " + todo.getTask());
            } else {
                Task t = new Task(command);
                System.out.println("added: " + command);
                taskList.add(t);
            }
        }
    }
}
