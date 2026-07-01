package com.cognizant.week2.jpa3.ex05;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class OneToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneToManyApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(DepartmentRepository departmentRepository) {
        return args -> {
            Department department = new Department("Operations");
            department.addEmployee(new Employee("Karan"));
            department.addEmployee(new Employee("Meera"));
            departmentRepository.save(department);
            departmentRepository.findAll().forEach(savedDepartment ->
                System.out.println(savedDepartment.getName() + " -> " + savedDepartment.getEmployees().size()));
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private List<Employee> employees = new ArrayList<>();

    Department() {
    }

    Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    void addEmployee(Employee employee) {
        employees.add(employee);
    }
}

@Entity
@Table(name = "employee")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    Employee() {
    }

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface DepartmentRepository extends JpaRepository<Department, Long> {
}
