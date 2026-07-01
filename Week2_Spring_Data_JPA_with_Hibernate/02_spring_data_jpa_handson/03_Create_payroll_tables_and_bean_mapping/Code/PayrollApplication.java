package com.cognizant.week2.jpa3.ex03;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class PayrollApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PayrollService payrollService) {
        return args -> {
            payrollService.createEmployeePayroll("Anita", new BigDecimal("75000"), new BigDecimal("5000"));
            System.out.println(payrollService.totalSalaryFor("Anita"));
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

    Employee() {
    }

    Employee(String name) {
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
@Table(name = "payroll")
class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal basicPay;
    private BigDecimal allowance;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    Payroll() {
    }

    Payroll(Employee employee, BigDecimal basicPay, BigDecimal allowance) {
        this.employee = employee;
        this.basicPay = basicPay;
        this.allowance = allowance;
    }

    public BigDecimal getTotalPay() {
        return basicPay.add(allowance);
    }

    public Employee getEmployee() {
        return employee;
    }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);
}

interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Payroll findByEmployee(Employee employee);
}

@org.springframework.stereotype.Service
class PayrollService {

    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;

    PayrollService(EmployeeRepository employeeRepository, PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
    }

    void createEmployeePayroll(String employeeName, BigDecimal basicPay, BigDecimal allowance) {
        Employee employee = employeeRepository.save(new Employee(employeeName));
        payrollRepository.save(new Payroll(employee, basicPay, allowance));
    }

    BigDecimal totalSalaryFor(String employeeName) {
        Employee employee = employeeRepository.findByName(employeeName);
        return payrollRepository.findByEmployee(employee).getTotalPay();
    }
}
