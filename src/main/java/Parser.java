public class Parser {
    public static void execute(String command, TaskList tasks, Ui ui, Storage storage) throws DukeException, Exception {
        String[] tokens = command.split(" ");
        try {
            switch (tokens[0]) {
                case "list":
                    ui.printList(tasks);
                    break;

                case "done":
                    try {
                        int i = Integer.parseInt(tokens[1]) - 1;
                        Task t = tasks.get(i);
                        t.markAsDone();
                        ui.printDone(t);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Please enter a valid index.");
                    }
                    break;

                case "delete":
                    try {
                        int i = Integer.parseInt(tokens[1]) - 1;
                        Task t = tasks.get(i);
                        tasks.remove(i);
                        ui.printDelete(t, tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Please enter a valid index.");
                    }
                    break;

                case "todo":
                    if (!command.substring(4).isBlank()) {
                        Todo todo = new Todo(command.substring(5).trim());
                        tasks.add(todo);
                        ui.printAdd(todo, tasks);
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;

                case "deadline":
                    try {
                        String[] d = command.substring(9).split("/by");
                        DateTime dateTime = new DateTime(d[1].trim());
                        Deadline deadline = new Deadline(d[0].trim(), dateTime.toString());
                        tasks.add(deadline);
                        ui.printAdd(deadline, tasks);
                    } catch (Exception e) {
                        throw new DukeException("Please input deadline in correct format \"DD/MM/YYYY HHmm\"");
                    }
                    break;

                case "event":
                    try {
                        String[] e = command.substring(6).split("/at");
                        DateTime dateTime = new DateTime(e[1].trim());
                        Event event = new Event(e[0].trim(), dateTime.toString());
                        tasks.add(event);
                        ui.printAdd(event, tasks);
                    } catch (Exception e) {
                        throw new DukeException("Please input deadline in correct format \"DD/MM/YYYY HHmm\"");
                    }
                    break;

                case "find":
                    try {
                        String keyword = command.substring(5);
                        ui.printMatch(keyword, tasks);
                    } catch (Exception e) {
                        throw new DukeException("Keyword cannot be empty.");
                    }
                    break;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            storage.save(tasks);
        } catch(DukeException err) {
            ui.printError(err.getMessage());
        }
    }
}
