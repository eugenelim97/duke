/**
 * An extension of the Task class
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTextFile() {
        return "T" + " | " + super.getStatusIcon() + " | " + description;
    }
}
