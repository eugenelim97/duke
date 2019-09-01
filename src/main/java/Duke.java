import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

        String filepath = "data/tasks.txt";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            readFromFile(filepath, taskList);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
                            Todo todo = new Todo(todoInput.trim());
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
                            try {
                                DateTime dateTime = new DateTime(d[1].trim());
                                Deadline deadline = new Deadline(d[0].trim(), dateTime.toString());
                                taskList.add(deadline);
                                printAdd(deadline, taskList.size());
                            } catch (Exception e1) {
                                throw new DukeException("Please input deadline in correct format \"DD/MM/YYYY HHmm\"");
                            }
                        }
                        break;
                    case "event":
                        String eventInput = scanner.nextLine();
                        if ("".equals(eventInput)) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String[] e = eventInput.split("/at", 2);
                            try {
                                DateTime dateTime = new DateTime(e[1].trim());
                                Event event = new Event(e[0].trim(), dateTime.toString());
                                taskList.add(event);
                                printAdd(event, taskList.size());
                            } catch(Exception e2) {
                                throw new DukeException("Please input event in correct format \"DD/MM/YYYY HHmm\"");
                            }
                        }
                        break;
                    default:
                        scanner.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                writeToFile(filepath, taskList);
                command = scanner.next();
            } catch (DukeException | IOException e) {
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

    private static void writeToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList) {
            fw.write(t.toTextFile() + System.lineSeparator());
        }
        fw.close();
    }

    private static void readFromFile(String filePath, ArrayList<Task> taskList) throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            try {
                String[] Tasks = s.nextLine().split(" \\| ");
                switch (Tasks[0]) {
                    case "T":
                        Todo t = new Todo(Tasks[2], "+".equals(Tasks[1]));
                        taskList.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(Tasks[2], Tasks[3], "+".equals(Tasks[1]));
                        taskList.add(d);
                        break;
                    case "E":
                        Event e = new Event(Tasks[2], Tasks[3], "+".equals(Tasks[1]));
                        taskList.add(e);
                        break;
                    default:
                        throw new DukeException("Something went wrong in reading file");
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
