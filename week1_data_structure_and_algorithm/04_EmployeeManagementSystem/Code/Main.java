public class Main {
    public static void main(String[] args) {
        EmployeeManager company = new EmployeeManager();

        company.addEmployee(1, "John Doe", "IT", 75000, "Developer");
        company.addEmployee(2, "Jane Smith", "HR", 65000, "Manager");
        company.addEmployee(3, "Mike Johnson", "Sales", 70000, "Executive");
        company.addEmployee(4, "Sarah Lee", "Finance", 68000, "Analyst");

        System.out.println("All Employees:");
        company.displayAll();

        System.out.println("\nTotal Payroll: $" + company.getTotalPayroll());

        company.updateSalary(1, 80000);
        company.updatePosition(2, "Senior Manager");

        System.out.println("\nAfter updates:");
        company.displayAll();

        System.out.println("\nNew Total Payroll: $" + company.getTotalPayroll());
    }
}
