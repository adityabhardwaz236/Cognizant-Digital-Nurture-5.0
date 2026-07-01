package com.cognizant.week2.jpa3.ex06;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class CriteriaQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CriteriaQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            employeeService.seedEmployees();
            employeeService.findEmployeesWithSalaryGreaterThan(50000).forEach(employee ->
                System.out.println(employee.getName() + " -> " + employee.getSalary()));
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

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

@Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    List<Employee> findEmployeesWithSalaryGreaterThan(double minSalary) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("salary"), minSalary));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
