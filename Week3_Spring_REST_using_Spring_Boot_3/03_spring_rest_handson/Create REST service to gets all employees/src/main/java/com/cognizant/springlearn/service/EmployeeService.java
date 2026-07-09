package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = Arrays.asList(
            new Employee(1, "John Doe", "Engineering"),
            new Employee(2, "Jane Smith", "HR"),
            new Employee(3, "Alex Johnson", "Finance")
    );

    public List<Employee> getAllEmployees() {
        return employees;
    }
}
