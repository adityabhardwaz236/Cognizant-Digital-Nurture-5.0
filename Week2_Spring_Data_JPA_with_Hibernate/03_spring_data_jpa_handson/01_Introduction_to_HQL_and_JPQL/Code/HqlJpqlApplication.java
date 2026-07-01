package com.cognizant.week2.jpa3.ex01;

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
public class HqlJpqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqlJpqlApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            employeeService.seedEmployees();
            System.out.println(employeeService.countPermanentEmployees());
            employeeService.listEmployeeNames().forEach(System.out::println);
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
    private double salary;

    Employee() {
    }

    Employee(String name, String employmentType, double salary) {
        this.name = name;
        this.employmentType = employmentType;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getEmploymentType() {
        return employmentType;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select count(e) from Employee e where e.employmentType = 'Permanent'")
    long countPermanentEmployees();

    @Query("select e.name from Employee e order by e.name")
    List<String> fetchEmployeeNames();
}

@org.springframework.stereotype.Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    void seedEmployees() {
        employeeRepository.saveAll(List.of(
            new Employee("Asha", "Permanent", 60000),
            new Employee("Ravi", "Contract", 45000),
            new Employee("Neha", "Permanent", 70000)
        ));
    }

    long countPermanentEmployees() {
        return employeeRepository.countPermanentEmployees();
    }

    List<String> listEmployeeNames() {
        return employeeRepository.fetchEmployeeNames();
    }
}
