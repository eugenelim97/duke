import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of Task objects
     * @return The number of Task objects in the ArrayList of Tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the Task object in the ArrayList with index of i
     * @param i index of the Task object in the ArrayList
     * @return The task object with the index of i in the ArrayList
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Removes the Task object in the ArrayList with index of i
     * @param i index of the Task object in the ArrayList
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Adds the Task object to the back of the ArrayList
     * @param t The Task object
     */
    public void add(Task t) {
        taskList.add(t);
    }
}
