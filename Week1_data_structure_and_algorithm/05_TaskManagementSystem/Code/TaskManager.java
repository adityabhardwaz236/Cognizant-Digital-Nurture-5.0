import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int counter;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.counter = 0;
    }

    public void addTask(String title, String desc, String assigned, long due) {
        tasks.add(new Task(++counter, title, desc, "Pending", assigned, due));
    }

    public void updateStatus(int id, String newStatus) {
        for (Task t : tasks) {
            if (t.taskId == id) {
                t.status = newStatus;
                break;
            }
        }
    }

    public List<Task> getByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.status.equalsIgnoreCase(status)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getByAssignee(String assignee) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.assignedTo.equalsIgnoreCase(assignee)) {
                result.add(t);
            }
        }
        return result;
    }

    public void displayAll(List<Task> taskList) {
        for (Task t : taskList) {
            System.out.println("ID: " + t.taskId + ", Title: " + t.title + 
                             ", Status: " + t.status + ", Assigned to: " + t.assignedTo);
        }
    }
}
