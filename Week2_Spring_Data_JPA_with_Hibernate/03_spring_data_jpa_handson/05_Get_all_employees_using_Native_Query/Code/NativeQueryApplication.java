package com.cognizant.week2.jpa3.ex05;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class NativeQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NativeQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            employeeService.seedEmployees();
            employeeService.getAllEmployees().forEach(employee ->
                System.out.println(employee.getName() + " -> " + employee.getDepartment()));
        };
    }
}

@Entity
@Table(name = "employee")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

    Employee() {
    }

    Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee", nativeQuery = true)
    List<Employee> fetchAllEmployeesNative();
}

@org.springframework.stereotype.Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    void seedEmployees() {
        employeeRepository.saveAll(List.of(
            new Employee("Asha", "IT"),
            new Employee("Ravi", "HR"),
            new Employee("Neha", "Finance")
        ));
    }

    List<Employee> getAllEmployees() {
        return employeeRepository.fetchAllEmployeesNative();
    }
}
