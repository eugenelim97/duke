import java.util.Scanner;

/**
 * The main class of the whole program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a Duke constructor with the input filepath and creates a new Ui, Storage, and TaskList object
     * @param filePath The filepath where data is stored and needs to be loaded when the program is run
     */
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

    /**
     * Main method that runs the whole program
     * @throws Exception
     */
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



