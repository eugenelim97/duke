public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "{" + this.getStatusIcon() + "} " + description;
    }

    public String toTextFile() {
        return "";
    }
}
