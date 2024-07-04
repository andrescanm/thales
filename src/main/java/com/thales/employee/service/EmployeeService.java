package com.thales.employee.service;

import com.thales.employee.model.Employee;
import com.thales.employee.model.EmployeeResponse;
import com.thales.employee.model.SingleEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAllEmployees() {
        EmployeeResponse response = restTemplate.getForObject(BASE_URL + "/employees", EmployeeResponse.class);
        return response != null ? response.getData() : null;
    }

    public Employee getEmployeeById(int id) {
        SingleEmployeeResponse response = restTemplate.getForObject(BASE_URL + "/employee/" + id, SingleEmployeeResponse.class);
        return response != null ? response.getData() : null;
    }
}
