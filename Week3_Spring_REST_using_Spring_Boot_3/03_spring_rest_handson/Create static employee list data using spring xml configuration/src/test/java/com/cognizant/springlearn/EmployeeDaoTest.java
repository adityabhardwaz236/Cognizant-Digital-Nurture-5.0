package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDaoTest {

    @Test
    void shouldLoadEmployeesFromXmlConfiguration() {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getAllEmployees();

        assertEquals(3, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }
}
