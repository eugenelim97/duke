import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws Exception {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!"bye".equals(command)) {
            Parser.execute(command, tasks, ui, storage);
            command = scanner.nextLine();
        }
        ui.printExit();
    }

    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();
    }
}



