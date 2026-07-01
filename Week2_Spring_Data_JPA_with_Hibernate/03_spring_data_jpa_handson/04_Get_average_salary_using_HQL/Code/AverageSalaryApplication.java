package com.cognizant.week2.jpa3.ex04;

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
public class AverageSalaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AverageSalaryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            employeeService.seedEmployees();
            System.out.println(employeeService.averageSalary());
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
    private double salary;

    Employee() {
    }

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select avg(e.salary) from Employee e")
    Double averageSalary();
}

@org.springframework.stereotype.Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    void seedEmployees() {
        employeeRepository.saveAll(List.of(
            new Employee("Asha", 60000),
            new Employee("Ravi", 45000),
            new Employee("Neha", 75000)
        ));
    }

    Double averageSalary() {
        return employeeRepository.averageSalary();
    }
}
