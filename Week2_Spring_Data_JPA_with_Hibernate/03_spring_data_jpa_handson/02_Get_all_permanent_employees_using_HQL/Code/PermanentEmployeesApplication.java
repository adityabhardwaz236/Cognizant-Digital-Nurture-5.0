package com.cognizant.week2.jpa3.ex02;

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
public class PermanentEmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermanentEmployeesApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            employeeService.seedEmployees();
            employeeService.getPermanentEmployees().forEach(employee ->
                System.out.println(employee.getName() + " -> " + employee.getEmploymentType()));
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
    private String employmentType;

    Employee() {
    }

    Employee(String name, String employmentType) {
        this.name = name;
        this.employmentType = employmentType;
    }

    public String getName() {
        return name;
    }

    public String getEmploymentType() {
        return employmentType;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.employmentType = 'Permanent'")
    List<Employee> fetchPermanentEmployees();
}

@org.springframework.stereotype.Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    void seedEmployees() {
        employeeRepository.saveAll(List.of(
            new Employee("Asha", "Permanent"),
            new Employee("Ravi", "Contract"),
            new Employee("Neha", "Permanent")
        ));
    }

    List<Employee> getPermanentEmployees() {
        return employeeRepository.fetchPermanentEmployees();
    }
}
