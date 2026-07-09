package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Employee;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employee;
    }
}
