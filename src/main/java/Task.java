public class Task {
    private static int totalTasks = 0;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static int getTotal() {
        return totalTasks;
    }
    public String toString() {
        return "{" + this.getStatusIcon() + "} " + description;
    }
}
