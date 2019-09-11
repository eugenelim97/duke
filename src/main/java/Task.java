/**
 * Represents a task with description and isDone
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description The String description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task
     * @param description The String description of the Task
     * @param isDone Boolean of whether the Task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return a String description of the Task
     * @return The description of the Task
     */
    public String getDescription() { return description; }

    /**
     * Returns a "+" or "-", based on whether the Task is done or not
     * @return A "+" is the Task is done or a "-" if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    /**
     * Marks the isDone of the Task to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the Task object to a String that can be printed to the user console
     * @return A String of the Task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String toTextFile() {
        return "";
    }
}
