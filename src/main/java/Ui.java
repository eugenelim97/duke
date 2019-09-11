/**
 * Deals with interaction with the user
 */
public class Ui {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String line = "____________________________________________________________";


    public Ui() {
    }

    public void printWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t" + line);
    }

    public void printAdd(Task t, TaskList taskList) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDelete(Task t, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDone(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
    }

    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }

    public void printMatch(String keyword, TaskList taskList) {
        int num = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                System.out.println(++num + "." + taskList.get(i).toString());
            }
        }
    }

    public void printLoadingError() {
        System.out.println("Error encountered in loading file.");
    }

    public void printError(String err) {
        System.out.println(err);
    }
}
