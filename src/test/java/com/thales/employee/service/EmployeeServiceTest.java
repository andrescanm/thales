package com.thales.employee.service;

import com.thales.employee.model.Employee;
import com.thales.employee.model.EmployeeResponse;
import com.thales.employee.model.SingleEmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(restTemplate);
    }

    @Test
    void getAllEmployees() {
        EmployeeResponse response = new EmployeeResponse();
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmployee_name("John Doe");
        response.setData(Collections.singletonList(employee));

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponse.class))).thenReturn(response);

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getEmployee_name());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(EmployeeResponse.class));
    }

    @Test
    void getEmployeeById() {
        SingleEmployeeResponse response = new SingleEmployeeResponse();
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmployee_name("John Doe");
        response.setData(employee);

        when(restTemplate.getForObject(anyString(), eq(SingleEmployeeResponse.class))).thenReturn(response);

        Employee result = employeeService.getEmployeeById(1);
        assertEquals("John Doe", result.getEmployee_name());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(SingleEmployeeResponse.class));
    }
}
