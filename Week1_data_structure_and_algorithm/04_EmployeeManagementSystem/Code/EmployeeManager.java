import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {
    private Map<Integer, Employee> employees;

    public EmployeeManager() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(int id, String name, String dept, double salary, String position) {
        employees.put(id, new Employee(id, name, dept, salary, position));
    }

    public boolean removeEmployee(int id) {
        return employees.remove(id) != null;
    }

    public void updateSalary(int id, double newSalary) {
        if (employees.containsKey(id)) {
            employees.get(id).salary = newSalary;
        }
    }

    public void updatePosition(int id, String newPosition) {
        if (employees.containsKey(id)) {
            employees.get(id).position = newPosition;
        }
    }

    public double getTotalPayroll() {
        double total = 0;
        for (Employee emp : employees.values()) {
            total += emp.salary;
        }
        return total;
    }

    public void displayAll() {
        for (Employee emp : employees.values()) {
            System.out.println("ID: " + emp.employeeId + ", Name: " + emp.name + 
                             ", Dept: " + emp.department + ", Salary: $" + emp.salary + 
                             ", Position: " + emp.position);
        }
    }
}
