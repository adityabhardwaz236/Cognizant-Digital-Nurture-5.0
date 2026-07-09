package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    private final List<Department> departments = Arrays.asList(
            new Department(1, "Engineering"),
            new Department(2, "HR"),
            new Department(3, "Finance")
    );

    public List<Department> getAllDepartments() {
        LOGGER.info("Department REST service called");
        return departments;
    }
}
