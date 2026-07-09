package com.cognizant.springlearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EmployeeDao {
    private final List<Employee> employees;

    public EmployeeDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        this.employees = (List<Employee>) context.getBean("employees");
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}
