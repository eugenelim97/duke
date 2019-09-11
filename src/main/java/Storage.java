import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage
     * @param filePath The filepath where data is stored and needs to be loaded when the program is run
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new ArrayList of Tasks and loads it with data taken from the file
     * @return An ArrayList of Tasks that is being loaded from the file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
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
                }
            }
            return taskList;
        } catch(Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the Task objects in the ArrayList into a textfile
     * @param taskList The Arraylist of Tasks in the program
     * @throws DukeException
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < taskList.size(); i++) {
                bw.write(taskList.get(i).toTextFile());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error encountered in saving file.");
        }
    }
}
