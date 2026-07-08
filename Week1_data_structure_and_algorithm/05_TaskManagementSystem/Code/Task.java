public class Task {
    public int taskId;
    public String title;
    public String description;
    public String status;
    public String assignedTo;
    public long dueDate;

    public Task(int id, String title, String desc, String status, String assigned, long due) {
        this.taskId = id;
        this.title = title;
        this.description = desc;
        this.status = status;
        this.assignedTo = assigned;
        this.dueDate = due;
    }
}
