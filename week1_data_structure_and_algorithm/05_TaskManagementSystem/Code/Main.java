public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        taskManager.addTask("Design Database", "Create ER diagram", "Alice", 1625097600L);
        taskManager.addTask("Implement API", "Build REST endpoints", "Bob", 1625184000L);
        taskManager.addTask("Write Tests", "Unit and integration tests", "Charlie", 1625270400L);
        taskManager.addTask("Documentation", "API documentation", "Alice", 1625356800L);

        System.out.println("All Tasks:");
        taskManager.displayAll(taskManager.getByStatus("Pending"));

        taskManager.updateStatus(1, "In Progress");
        taskManager.updateStatus(2, "In Progress");

        System.out.println("\nTasks In Progress:");
        taskManager.displayAll(taskManager.getByStatus("In Progress"));

        System.out.println("\nTasks assigned to Alice:");
        taskManager.displayAll(taskManager.getByAssignee("Alice"));
    }
}
