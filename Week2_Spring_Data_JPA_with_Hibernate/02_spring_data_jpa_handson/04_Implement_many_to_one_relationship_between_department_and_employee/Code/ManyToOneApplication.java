package com.cognizant.week2.jpa3.ex04;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class ManyToOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToOneApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        return args -> {
            Department it = departmentRepository.save(new Department("IT"));
            Department hr = departmentRepository.save(new Department("HR"));
            employeeRepository.saveAll(List.of(
                new Employee("Asha", it),
                new Employee("Ravi", it),
                new Employee("Neha", hr)
            ));
            employeeRepository.findAll().forEach(employee ->
                System.out.println(employee.getName() + " -> " + employee.getDepartment().getName()));
        };
    }
}

@Entity
@Table(name = "department")
class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    Department() {
    }

    Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

@Entity
@Table(name = "employee")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    Employee() {
    }

    Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }
}

interface DepartmentRepository extends JpaRepository<Department, Long> {
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
